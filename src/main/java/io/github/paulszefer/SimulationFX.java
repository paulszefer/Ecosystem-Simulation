package io.github.paulszefer;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A JavaFX application to display a simulation application.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SimulationFX extends Application {

    /** Controls the simulation. */
    private static SimulationController controller;

    /**
     * Creates the controller to set up, configure, display, and then control the simulation.
     *
     * @param primaryStage
     *         the primary stage of the JavaFX GUI
     */
    @Override
    public void start(Stage primaryStage) {

        controller = new SimulationController(primaryStage);
    }

    /**
     * Drives the program.
     * <p>
     * Application entry-point if the environment does not support JavaFX fully.
     *
     * @param args
     *         command-line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
}
