package io.github.paulszefer.gui;

import io.github.paulszefer.Ecosystem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

import java.util.Random;

/**
 * Defines the GUI pane that will display and handle the animation for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationPane extends StackPane {

    /** The proportion of the GUI's height taken by this Pane. */
    private static final double PROPORTION = 0.75;

    /** The width of the animation pane. */
    private static final double WIDTH = SimulationApplication.WIDTH;

    /** The height of the animation pane. */
    private static final double HEIGHT = SimulationApplication.HEIGHT * PROPORTION;

    /** The background fill of the animation pane. */
    private static final Paint BACKGROUND_FILL = Color.DEEPSKYBLUE;

    /** Random number generator. */
    private static final Random GENERATOR = new Random();

    /** Creates the GUI pane that will display the animation for the simulation. */
    public AnimationPane() {

        addBackground();
        addForeground();
    }

    /** Adds the background pane. */
    private void addBackground() {
        getChildren().add(new BackgroundCanvas(WIDTH, HEIGHT, BACKGROUND_FILL));
    }

    /** Adds the foreground pane. */
    private void addForeground() {

        Canvas foreground = new Canvas(WIDTH, HEIGHT);
        GraphicsContext graphicsContext = foreground.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        final Font textFont = Font.font("Sans-serif", 20);
        graphicsContext.setFont(textFont);
        final int loadFileTextShiftX = 10;
        final int loadFileTextShiftY = 30;
        graphicsContext.fillText("Please load a file", loadFileTextShiftX, loadFileTextShiftY);
        getChildren().add(foreground);
    }

    /**
     * Sets the foreground pane for the animation.
     *
     * @param ecosystem
     *         the current state of the simulation
     */
    private void setForeground(Ecosystem ecosystem) {

        final double centerX = 0.5;
        final double centerY = 0.5;
        final Stop guppyStop1 = new Stop(0.8, Color.WHITE);
        final Stop guppyStop2 = new Stop(1, Color.TRANSPARENT);

        RadialGradient guppyFill = new RadialGradient(0, 0, centerX, centerY, 1, true,
                                                      CycleMethod.NO_CYCLE, guppyStop1, guppyStop2);

        // create new frame
        Canvas foreground = new Canvas(WIDTH, HEIGHT);

        final int poolShiftX = 20;
        final double guppySize = 2;
        final GraphicsContext graphicsContext = foreground.getGraphicsContext2D();
        final Color poolBackground = Color.BLUE;

        final double poolSize = HEIGHT / 4;
        double poolStartX;
        double poolStartY = (HEIGHT - poolSize) / 2;
        final double guppyAreaFactor = 0.9;
        final double guppyBorderFactor = (1 - guppyAreaFactor) / 2;

        for (int i = 0; i < ecosystem.getPools().size(); i++) {

            // draw background
            graphicsContext.setFill(poolBackground);
            poolStartX = poolShiftX * (i + 1) + i * poolSize;
            graphicsContext.fillRect(poolStartX, poolStartY, poolSize, poolSize);

            // draw guppies
            graphicsContext.setFill(guppyFill);
            int guppies = ecosystem.getPools().get(i).getPopulation();
            final int maxToDraw = 1000;
            if (guppies > maxToDraw) {
                guppies = maxToDraw;
            }
            for (int j = 0; j < guppies; j++) {
                graphicsContext.fillOval(GENERATOR.nextDouble() * poolSize * guppyAreaFactor
                                                 + poolSize * guppyBorderFactor + poolStartX,
                                         GENERATOR.nextDouble() * poolSize * guppyAreaFactor
                                                 + poolSize * guppyBorderFactor + poolStartY,
                                         guppySize, guppySize);
            }
        }
        getChildren().set(1, foreground);
    }

    /**
     * Updates the foreground pane to the current simulation state.
     *
     * @param ecosystem
     *         the current simulation state
     */
    public void updateState(Ecosystem ecosystem) {

        setForeground(ecosystem);
    }
}
