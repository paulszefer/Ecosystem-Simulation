package io.github.paulszefer;

import java.util.ArrayList;

/**
 * Drives a simulation of an ecosystem of pools containing guppies.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Simulation {

    /**
     * The ecosystem to be simulated.
     */
    private Ecosystem ecosystem;

    /**
     * Sets up a simulation with three pools.
     */
    public Simulation() {

        new SimulationFrame();

        ecosystem = new Ecosystem();

        ecosystem.addPool(setupSkookumchuk());
        ecosystem.addPool(setupRutherford());
        ecosystem.addPool(setupGamelin());

    }

    /**
     * Sets up the simulation.
     *
     * @param args
     *         command-line arguments
     */
    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        final int weeksToSimulate = 40;
        simulation.simulate(weeksToSimulate);

    }

    /**
     * Generates and populates the Skookumchuk pool.
     *
     * @return the generated Skookumchuk pool
     */
    public Pool setupSkookumchuk() {

        final Pool skookumchuk = new Pool("Skookumchuk", 1000.0, 42.0, 7.9, 0.9);

        final int numberOfGuppies = 100;
        final String genus = "Poecilia";
        final String species = "reticulata";
        final int minAge = 10;
        final int maxAge = 25;
        final double femaleChance = 0.5;
        final double minHealthCoefficient = 0.5;
        final double maxHealthCoefficient = 0.8;

        skookumchuk.populatePool(numberOfGuppies, genus, species, minAge, maxAge, femaleChance,
                                 minHealthCoefficient, maxHealthCoefficient);

        return skookumchuk;

    }

    /**
     * Generates and populates the Rutherford pool.
     *
     * @return the generated Rutherford pool
     */
    public Pool setupRutherford() {

        final Pool rutherford = new Pool("Rutherford", 5000.0, 39.0, 7.7, 0.85);

        final int numberOfGuppies = 100;
        final String genus = "Poecilia";
        final String species = "reticulata";
        final int minAge = 10;
        final int maxAge = 15;
        final double femaleChance = 0.5;
        final double minHealthCoefficient = 0.8;
        final double maxHealthCoefficient = 1.0;

        rutherford.populatePool(numberOfGuppies, genus, species, minAge, maxAge, femaleChance,
                                minHealthCoefficient, maxHealthCoefficient);

        return rutherford;

    }

    /**
     * Generates and populates the Gamelin pool.
     *
     * @return the generated Gamelin pool
     */
    public Pool setupGamelin() {

        final Pool gamelin = new Pool("Gamelin", 4300.0, 37.0, 7.5, 1.0);

        final int numberOfGuppies = 30;
        final String genus = "Poecilia";
        final String species = "reticulata";
        final int minAge = 15;
        final int maxAge = 49;
        final double femaleChance = 0.5;
        final double minHealthCoefficient = 0.0;
        final double maxHealthCoefficient = 1.0;

        gamelin.populatePool(numberOfGuppies, genus, species, minAge, maxAge, femaleChance,
                             minHealthCoefficient, maxHealthCoefficient);

        return gamelin;

    }

    /**
     * Simulates the given number of weeks passing in the ecosystem.
     *
     * @param numberOfWeeks
     *         the number of weeks to simulate
     */
    public void simulate(int numberOfWeeks) {

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

        int diedOfOldAge = 0;
        int starvedToDeath = 0;
        int newFry = 0;
        int crowdedOut = 0;
        int numberRemoved = 0;
        ArrayList<Pool> pools = ecosystem.getPools();

        for (Pool pool : pools) {

            diedOfOldAge += pool.incrementAges();
            numberRemoved += pool.removeDeadGuppies();
            starvedToDeath += pool.applyNutrientCoefficient();
            numberRemoved += pool.removeDeadGuppies();
            newFry += pool.spawn();
            numberRemoved += pool.removeDeadGuppies();

        }

        crowdedOut = ecosystem.adjustForCrowding();
        numberRemoved += crowdedOut;

        if (diedOfOldAge + starvedToDeath + crowdedOut == numberRemoved) {

            System.out.println("----------------------");
            System.out.println("Deaths to old age: " + diedOfOldAge);
            System.out.println("Deaths to starvation: " + starvedToDeath);
            System.out.println("Crowded out: " + crowdedOut);
            System.out.println("Number of births: " + newFry);
            System.out.println("Pool 1 population: " + pools.get(0).getPopulation());
            System.out.println("Pool 2 population: " + pools.get(1).getPopulation());
            System.out.println("Pool 3 population: " + pools.get(2).getPopulation());
            System.out.println("Ecosystem population: " + (pools.get(0).getPopulation()
                    + pools.get(1).getPopulation() + pools.get(2).getPopulation()));
            System.out.println();

        }

    }

}
