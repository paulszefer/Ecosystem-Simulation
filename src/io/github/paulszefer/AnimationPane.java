package io.github.paulszefer;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Defines the GUI pane that will display and handle the animation for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationPane extends StackPane {

    /** The proportion of the GUI's height taken by this Pane. */
    private static final double PROPORTION = 0.75;

    /** Creates the GUI pane that will display the animation for the simulation. */
    public AnimationPane() {

        StackPane animation = new StackPane();
        addBackground(animation);
        addForeground(animation);
        getChildren().add(animation);
    }

    /**
     * Adds the background to the given pane.
     *
     * @param pane
     *         the pane to which the background is added
     */
    private void addBackground(StackPane pane) {

        // TODO - find solution to allow this to resize
        Canvas animationBackground = new Canvas(SimulationApplication.WIDTH,
                                                SimulationApplication.HEIGHT * PROPORTION);
        animationBackground.getGraphicsContext2D().setFill(Color.DEEPSKYBLUE);
        animationBackground.getGraphicsContext2D().fillRect(0,
                                                            0,
                                                            SimulationApplication.WIDTH,
                                                            SimulationApplication.HEIGHT
                                                                    * PROPORTION);
        pane.getChildren().add(animationBackground);
    }

    /**
     * Adds the foreground to the given pane.
     *
     * @param pane
     *         the pane to which the foreground is added
     */
    private void addForeground(StackPane pane) {

    }

}
