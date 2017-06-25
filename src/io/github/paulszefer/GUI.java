package io.github.paulszefer;

import javafx.scene.Node;
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

    /** Creates the GUI. */
    public GUI() {

        setRoot(new VBox());
        addChild(new AnimationPane());
        addChild(new OptionPane());
    }

    /**
     * Returns the root.
     *
     * @return the root
     */
    public Pane getRoot() {

        return root;
    }

    /**
     * Sets the root.
     *
     * @param root
     *         the root
     */
    public void setRoot(Pane root) {

        this.root = root;
    }

    /**
     * Adds the given node to the root pane.
     *
     * @param node
     *         the node to be added
     */
    public void addChild(Node node) {

        root.getChildren().add(node);
    }
}
