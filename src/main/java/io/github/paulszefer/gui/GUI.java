package io.github.paulszefer.gui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Defines the GUI for the Simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class GUI {

    /** The root pane of the GUI. */
    private Pane root;

    /** The animation pane of the GUI. */
    private AnimationPane animationPane;

    /** The option pane of the GUI. */
    private OptionPane optionPane;

    /** Creates the GUI. */
    public GUI() {

        root = new VBox();
        animationPane = new AnimationPane();
        optionPane = new OptionPane();
        root.getChildren().addAll(animationPane, optionPane);
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot() {

        return root;
    }

    /**
     * Returns the animation pane.
     *
     * @return the animation pane
     */
    public AnimationPane getAnimationPane() {

        return animationPane;
    }

    /**
     * Returns the option pane.
     *
     * @return the option pane
     */
    public OptionPane getOptionPane() {

        return optionPane;
    }
}
