package io.github.paulszefer;

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

    /**
     * Drives the program.
     *
     * @param args
     *         command-line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Simulation title"); // TODO - read from file

        GUI gui = new GUI();

        Scene scene = new Scene(gui.getRoot(), WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        scene.getStylesheets().add(SimulationApplication.class.getResource("GUI.css")
                                                              .toExternalForm());
        primaryStage.show();
    }
}
