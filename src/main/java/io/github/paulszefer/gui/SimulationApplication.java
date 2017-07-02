package io.github.paulszefer.gui;

import io.github.paulszefer.Simulation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A JavaFX application to display the simulation GUI.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SimulationApplication extends Application {

    /** The width of the application. */
    public static final int WIDTH = 800;

    /** The height of the application. */
    public static final int HEIGHT = 800;

    /** Stores the simulation stage. */
    private static Stage stage;

    /** Stores the simulation. */
    private static Simulation simulation = new Simulation();

    /** Stores the simulation GUI. */
    private static GUI gui = new GUI();

    /**
     * Drives the program.
     *
     * @param args
     *         command-line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Creates, configures and then displays the GUI.
     *
     * @param primaryStage
     *         the primary stage of the JavaFX GUI
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Scene scene = new Scene(gui.getRoot());
        // TODO - configure GUI.css to add custom styling
        // scene.getStylesheets().add(SimulationApplication.class.getResource("GUI.css")
        //                                                       .toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation");
        primaryStage.show();
    }

    /**
     * Returns the stage.
     *
     * @return the stage
     */
    public static Stage getStage() {

        return stage;
    }

    /**
     * Returns the simulation.
     *
     * @return the simulation
     */
    public static Simulation getSimulation() {

        return simulation;
    }

    /**
     * Returns the gui.
     *
     * @return the gui
     */
    public static GUI getGui() {

        return gui;
    }
}
