package io.github.paulszefer.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 * A pane that consists of at least two layers with one layer making up the background of the pane.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public abstract class DualLayerPane extends StackPane {

    /** The width of the pane. */
    private double paneWidth;

    /** The height of the pane. */
    private double paneHeight;

    /** The background fill of the pane. */
    private Paint backgroundFill;

    /**
     * Creates a pane that consists of at least two layers.
     *
     * @param paneWidth
     *         the width of the pane
     * @param paneHeight
     *         the height of the pane
     * @param backgroundFill
     *         the background fill of the pane
     */
    public DualLayerPane(double paneWidth, double paneHeight, Paint backgroundFill) {

        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.backgroundFill = backgroundFill;

        addBackground();
        addForeground();
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

