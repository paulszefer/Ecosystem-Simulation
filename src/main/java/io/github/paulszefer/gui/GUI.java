package io.github.paulszefer.gui;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.animation.AnimationPane;
import io.github.paulszefer.gui.option.OptionPane;
import io.github.paulszefer.gui.option.handler.GraphButtonHandler;
import io.github.paulszefer.sim.Ecosystem;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.List;

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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            System.out.println("The System look and feel could not be accessed");
        } catch (InstantiationException e) {
            System.out.println("The System look and feel could not be instantiated");
        } catch (ClassNotFoundException e) {
            System.out.println("The System look and feel could not be found");
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("The System look and feel is not supported");
        }

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

    /**
     * Forwards an attempt to update the graph.
     *
     * @param historyCopy
     *         a copy of the current simulation history
     */
    public void updateGraph(List<Ecosystem> historyCopy) {

        ((GraphButtonHandler) optionPane.getControls().getGraph().getOnAction())
                .handleSimulationUpdate(historyCopy);
    }
}
