package io.github.paulszefer;

import io.github.paulszefer.gui.GUI;
import io.github.paulszefer.sim.Ecosystem;
import io.github.paulszefer.sim.Simulation;
import javafx.stage.Stage;

import java.util.List;
import java.util.Scanner;

/**
 * This acts as the intermediary between the application logic and GUI logic.
 * <p>
 * This plays the part of the controller in the Model-View-Controller structure.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SimulationController {

    /** The driver of the simulation. */
    private Simulation model;

    /** The presenter of the GUI. */
    private GUI view;

    /**
     * Creates the object to control the simulation.
     *
     * @param primaryStage
     *         the primary stage of the JavaFX application
     */
    public SimulationController(Stage primaryStage) {

        model = new Simulation(this);
        view = new GUI(this, primaryStage);
    }

    /**
     * Updates the simulation with the given loaded scanner.
     *
     * @param data
     *         a scanner containing data used to update the simulation
     */
    public void updateSimulation(Scanner data) {

        model.loadSimulation(data);
    }

    /**
     * Updates the simulation by shifting it one week backwards or forwards based on the given
     * integer.
     * <p>
     * A negative change shifts the simulation backward one week.
     * <p>
     * A positive change shifts the simulation forward one week.
     *
     * @param change
     *         the shift backwards or forwards
     */
    public void updateSimulation(int change) {

        switch (change) {
            case -1:
                model.previousWeek();
                break;
            case 1:
                model.nextWeek();
                break;
            default:
                throw new IllegalStateException("Illegal simulation change.");
        }
    }

    /**
     * Retrieves the current week from the simulation.
     *
     * @return the current week of the simulation
     */
    public int retrieveWeek() {

        return model.getWeek();
    }

    /**
     * Retrieves the simulation history.
     *
     * @return the simulation history
     */
    public List<Ecosystem> retrieveHistory() {

        return model.getHistory();
    }

    /**
     * Updates the GUI with the new title.
     *
     * @param title
     *         the new title to set
     */
    public void updateGUI(String title) {

        view.updateTitle(title);
    }

    /**
     * Updates the GUI with the new simulation state.
     *
     * @param ecosystem
     *         the new simulation state to update to
     * @param initialUpdate
     *         whether this is the first update to the GUI
     */
    public void updateGUI(Ecosystem ecosystem, boolean initialUpdate) {

        view.updateAnimation(ecosystem, initialUpdate);
    }

    /**
     * Updates the GUI with a new copy of the simulation history, intended to be used to update the
     * graph, when needed.
     *
     * @param history
     *         the simulation history
     */
    public void updateGUIGraph(List<Ecosystem> history) {

        view.updateGraph(history);
    }
}
