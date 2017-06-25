package io.github.paulszefer;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Defines the GUI pane that will display and handle the options for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class OptionPane extends StackPane {

    /** The proportion of the GUI's height taken by this Pane. */
    private static final double PROPORTION = 0.25;

    /** Creates the GUI pane that will display the options for the simulation. */
    public OptionPane() {

        StackPane options = new StackPane();
        addBackground(options);
        addForeground(options);
        getChildren().add(options);
    }

    /**
     * Adds the background to the given pane.
     *
     * @param pane
     *         the pane to which the background is added
     */
    private void addBackground(StackPane pane) {

        // TODO - find solution to allow this to resize
        Canvas optionsBackground = new Canvas(SimulationApplication.WIDTH,
                                              SimulationApplication.HEIGHT * PROPORTION);
        optionsBackground.getGraphicsContext2D().setFill(Color.WHITE);
        optionsBackground.getGraphicsContext2D().fillRect(0,
                                                          0,
                                                          SimulationApplication.WIDTH,
                                                          SimulationApplication.HEIGHT
                                                                  * PROPORTION);
        pane.getChildren().add(optionsBackground);
    }

    /**
     * Adds the foreground to the given pane.
     *
     * @param pane
     *         the pane to which the foreground is added
     */
    private void addForeground(StackPane pane) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        final int horizontalGap = 80;
        final int verticalGap = 80;
        grid.setHgap(horizontalGap);
        grid.setVgap(verticalGap);

        // Data load controls
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");

        // Animation controls
        Button backButton = new Button("Back");
        Button playPauseButton = new Button("Play");
        Button stepButton = new Button("Step");

        final int speedBound = 3;
        Slider speedSlider = new SpeedSlider(-speedBound, speedBound, 0);

        // Data analysis controls
        Button graphButton = new Button("Graph");
        Button reportButton = new Button("Report");

        final int thirdColumn = 3;
        final int fourthColumn = 4;

        grid.add(loadButton, 0, 0);
        grid.add(saveButton, 0, 1);

        grid.add(backButton, 1, 0);
        grid.add(playPauseButton, 2, 0);
        grid.add(stepButton, thirdColumn, 0);

        grid.add(speedSlider, 1, 1, thirdColumn, 1);

        grid.add(graphButton, fourthColumn, 0);
        grid.add(reportButton, fourthColumn, 1);

        pane.getChildren().add(grid);
    }

}
