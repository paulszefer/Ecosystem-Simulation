package io.github.paulszefer.gui;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.animation.AnimationPane;
import io.github.paulszefer.gui.option.OptionPane;
import io.github.paulszefer.sim.Ecosystem;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Defines the GUI for the Simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class GUI {

    /** The default width of the application. */
    public static final int WIDTH = 800;

    /** The default height of the application. */
    public static final int HEIGHT = 800;

    /** The simulation controller. */
    private SimulationController controller;

    /** The primary stage of the JavaFX application. */
    private Stage primaryStage;

    /** The root pane of the GUI. */
    private Pane root;

    /** The animation pane of the GUI. */
    private AnimationPane animationPane;

    /** The option pane of the GUI. */
    private OptionPane optionPane;

    /**
     * Creates the GUI.
     *
     * @param controller
     *         the simulation controller
     * @param primaryStage
     *         the primary stage of the JavaFX application
     */
    public GUI(SimulationController controller, Stage primaryStage) {

        this.controller = controller;
        this.primaryStage = primaryStage;

        root = new VBox();
        animationPane = new AnimationPane(controller);
        optionPane = new OptionPane(controller);
        root.getChildren().addAll(animationPane, optionPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation");
        primaryStage.show();

        // TODO - configure GUI.css to add custom styling
        // scene.getStylesheets().add(SimulationFX.class.getResource("GUI.css").toExternalForm());
    }

    /**
     * Updates the GUI with the given title.
     *
     * @param title
     *         the new title of the GUI window
     */
    public void updateTitle(String title) {

        primaryStage.setTitle(title);
    }

    /**
     * Updates the GUI with the current state of the simulation.
     *
     * @param ecosystem
     *         the current state of the simulation
     */
    public void updateAnimation(Ecosystem ecosystem) {

        animationPane.updateState(ecosystem);
    }
}
