package io.github.paulszefer.gui;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.animation.AnimationPane;
import io.github.paulszefer.gui.option.OptionPane;
import io.github.paulszefer.sim.Ecosystem;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
    private AnchorPane root;

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

        root = new AnchorPane();
        root.setMinHeight(GUI.HEIGHT);
        root.setMinWidth(GUI.WIDTH);
        animationPane = new AnimationPane(controller);
        optionPane = new OptionPane(controller);
        root.getChildren().add(animationPane);
        root.getChildren().add(optionPane);

        AnchorPane.setTopAnchor(animationPane, 0.0);
        // AnchorPane.setBottomAnchor(animationPane, GUI.HEIGHT * OptionPane.PROPORTION);
        AnchorPane.setLeftAnchor(animationPane, 0.0);
        AnchorPane.setRightAnchor(animationPane, 0.0);

        // AnchorPane.setTopAnchor(optionPane, GUI.HEIGHT * AnimationPane.PROPORTION);
        AnchorPane.setBottomAnchor(optionPane, 0.0);
        AnchorPane.setLeftAnchor(optionPane, 0.0);
        AnchorPane.setRightAnchor(optionPane, 0.0);

        Scene scene = new Scene(root);

        // Adjusts the animation width when the scene window changes size
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            animationPane.setWidth((double) newValue);
        });

        // Adjusts the animation height when the scene window changes size
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            animationPane.setHeight((double) newValue);
        });

        // Passes on key presses to the animation subscene to handle
        scene.setOnKeyPressed(event -> {
            animationPane.getOnKeyPressed().handle(event);
        });

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
