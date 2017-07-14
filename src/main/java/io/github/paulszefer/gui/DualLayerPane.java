package io.github.paulszefer.gui;

import io.github.paulszefer.SimulationController;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 * A pane that consists of at least two layers with one layer making up the background of the pane.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public abstract class DualLayerPane extends StackPane {

    /** The simulation controller. */
    private SimulationController controller;

    /** The width of the pane. */
    private double paneWidth;

    /** The height of the pane. */
    private double paneHeight;

    /** The background fill of the pane. */
    private Paint backgroundFill;

    /**
     * Creates a pane that consists of at least two layers.
     *
     * @param controller
     *         the simulation controller
     * @param paneWidth
     *         the width of the pane
     * @param paneHeight
     *         the height of the pane
     * @param backgroundFill
     *         the background fill of the pane
     */
    public DualLayerPane(SimulationController controller, double paneWidth, double paneHeight,
                         Paint backgroundFill) {

        this.controller = controller;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.backgroundFill = backgroundFill;

        addBackground();
        addForeground();
    }

    /**
     * Returns the simulation controller.
     *
     * @return the simulation controller
     */
    public SimulationController getController() {

        return controller;
    }

    /**
     * Returns the pane width.
     *
     * @return the pane width
     */
    public double getPaneWidth() {

        return paneWidth;
    }

    /**
     * Returns the pane height.
     *
     * @return the pane height
     */
    public double getPaneHeight() {

        return paneHeight;
    }

    /** Adds the background pane. */
    protected void addBackground() {

        getChildren().add(new BackgroundCanvas(paneWidth, paneHeight, backgroundFill));
    }

    /** Adds the foreground pane. */
    protected abstract void addForeground();
}

