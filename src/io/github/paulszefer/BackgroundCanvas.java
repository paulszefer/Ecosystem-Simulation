package io.github.paulszefer;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

/**
 * Defines a canvas that displays a background.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class BackgroundCanvas extends Canvas {

    /**
     * Creates a background canvas.
     *
     * @param width
     *         the width of the canvas
     * @param height
     *         the height of the canvas
     * @param fill
     *         the paint fill of the canvas
     */
    public BackgroundCanvas(double width, double height, Paint fill) {

        // TODO - find solution to allow this to resize
        super(width, height);
        getGraphicsContext2D().setFill(fill);
        getGraphicsContext2D().fillRect(0, 0, width, height);
    }
}
