package io.github.paulszefer.sim;

import io.github.paulszefer.gui.SimulationApplication;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Drives a simulation of an ecosystem of pools containing guppies.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Simulation {

    /** The current simulation state identifier. */
    private int week;

    /** The storage of ecosystem states. */
    private ArrayList<Ecosystem> history;

    /** Sets up the simulation. */
    public Simulation() {

        // initialize current identifier
        week = -1;

        // initialize history storage
        history = new ArrayList<>();
    }

    /**
     * Returns the week.
     *
     * @return the week
     */
    public int getWeek() {

        return week;
    }

    /**
     * Loads the simulation data from a file.
     *
     * @return true if the file was loaded successfully; false otherwise
     */
    public boolean loadFile() {

        // TODO - parse file differently based on file type
        // Scanner should work for text file, but maybe not for xml and json
        // Option: DataFile extends File, has a method called nextField()
        // that returns the next field based on the file type/structure

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File type", "xml", "json",
                                                                     "txt");
        Scanner data;
        fileChooser.addChoosableFileFilter(filter);
        if (fileChooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                data = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return false;
            }
        } else {
            System.out.println("No file selected");
            return false;
        }

        try {
            SimulationApplication.getStage().setTitle(data.nextLine());
        } catch (NullPointerException e) {
            System.out.println("Invalid data file");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Invalid file type");
            return false;
        }
        Ecosystem ecosystem = new Ecosystem();
        Pool pool;
        while (data.hasNext()) {
            pool = new Pool(data.nextLine(), Double.valueOf(data.nextLine()),
                            Double.valueOf(data.nextLine()), Double.valueOf(data.nextLine()),
                            Double.valueOf(data.nextLine()));
            pool.populate(Integer.valueOf(data.nextLine()), data.nextLine(), data.nextLine(),
                          Integer.valueOf(data.nextLine()), Integer.valueOf(data.nextLine()),
                          Double.valueOf(data.nextLine()), Double.valueOf(data.nextLine()),
                          Double.valueOf(data.nextLine()));
            ecosystem.addPool(pool);
        }

        // update the simulation history
        week = 0;
        history = new ArrayList<>();
        history.add(ecosystem.copy());

        // update the animation
        SimulationApplication.getGui().getAnimationPane().updateState(ecosystem);

        return true;
    }

    /** Returns the simulation to its state one week prior. */
    public void previousWeek() {

        if (week > 0) {
            week--;
            SimulationApplication.getGui().getAnimationPane().updateState(history.get(week));
        } else {
            System.out.println("There are no previous weeks.");
            // TODO - grey out back button if there are no previous weeks
        }
    }

    /**
     * Returns the simulation to its state one week subsequent or simulates the week if it does not
     * exist.
     */
    public void nextWeek() {

        if (week == -1) {
            System.out.println("Please load a simulation first.");
        } else if (week + 1 < history.size()) {
            week++;
            SimulationApplication.getGui().getAnimationPane().updateState(history.get(week));
        } else {
            simulateOneWeek();
        }
    }

    /**
     * Simulates the given number of weeks passing in the ecosystem.
     *
     * @param numberOfWeeks
     *         the number of weeks to simulate
     */
    public void simulate(int numberOfWeeks) {

        if (week == -1) {
            System.out.println("Please load a simulation first.");
        }

        for (int i = 0; i < numberOfWeeks; i++) {
            System.out.println("Simulating Week " + (i + 1));
            simulateOneWeek();
        }
    }

    /**
     * Simulates a week passing in the ecosystem and prints out the results of the week.
     * <p>
     * The statistics that will be printed are:
     * </p>
     * <ul>
     * <li>Deaths to old age</li>
     * <li>Deaths to starvation</li>
     * <li>Crowded out</li>
     * <li>Number of births</li>
     * <li>Population of pool 1</li>
     * <li>Population of pool 2</li>
     * <li>Population of pool 3</li>
     * <li>Population of the ecosystem</li>
     * </ul>
     */
    public void simulateOneWeek() {

        if (week == -1) {
            System.out.println("Please load a simulation first.");
            return;
        }

        Ecosystem ecosystem = history.get(week).copy();

        int diedOfOldAge = 0;
        int starvedToDeath = 0;
        int newFry = 0;
        int crowdedOut = 0;
        int numberRemoved = 0;
        List<Pool> pools = ecosystem.getPools();

        for (Pool pool : pools) {
            diedOfOldAge += pool.incrementAges();
            numberRemoved += pool.removeDeadCreatures();
            starvedToDeath += pool.applyNutrientCoefficient();
            numberRemoved += pool.removeDeadCreatures();
            newFry += pool.spawn();
            numberRemoved += pool.removeDeadCreatures();
        }

        crowdedOut += ecosystem.adjustForCrowding();
        numberRemoved += crowdedOut;
        for (Pool pool : pools) {
            pool.removeDeadCreatures();
        }

        if (diedOfOldAge + starvedToDeath + crowdedOut == numberRemoved) {
            System.out.println("Simulating Week " + (week + 1));
            System.out.println("----------------------");
            System.out.println("Deaths to old age: " + diedOfOldAge);
            System.out.println("Deaths to starvation: " + starvedToDeath);
            System.out.println("Crowded out: " + crowdedOut);
            System.out.println("Number of births: " + newFry);
            for (int i = 0; i < pools.size(); i++) {
                System.out.println(
                        "Pool " + (i + 1) + " population: " + pools.get(i).getPopulation());
            }
            System.out.println("Ecosystem population: " + ecosystem.getCreaturePopulation());
            System.out.println();
        }

        week++;
        history.add(ecosystem);
        SimulationApplication.getGui().getAnimationPane().updateState(history.get(week));
    }
}
