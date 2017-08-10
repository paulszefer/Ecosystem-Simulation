package io.github.paulszefer.gui.option;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.DualLayerPane;
import io.github.paulszefer.gui.GUI;
import io.github.paulszefer.gui.option.handler.BackButtonHandler;
import io.github.paulszefer.gui.option.handler.GraphButtonHandler;
import io.github.paulszefer.gui.option.handler.LoadButtonHandler;
import io.github.paulszefer.gui.option.handler.PlayButtonHandler;
import io.github.paulszefer.gui.option.handler.ReportButtonHandler;
import io.github.paulszefer.gui.option.handler.SaveButtonHandler;
import io.github.paulszefer.gui.option.handler.StepButtonHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Defines the GUI pane that will display the options for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class OptionPane extends DualLayerPane {

    /** The option pane's controls. */
    private static OptionControls controls = new OptionControls();

    /** The proportion of the GUI's height taken by this pane. */
    private static final double PROPORTION = 0.25;

    /**
     * Creates the GUI pane that will display the options for the simulation.
     *
     * @param controller
     *         the simulation controller
     */
    public OptionPane(SimulationController controller) {

        super(controller, GUI.WIDTH, GUI.HEIGHT * PROPORTION, Color.WHITE);
    }

    /** Adds the foreground pane. */
    protected void addForeground() {

        GridPane foreground = new GridPane();
        foreground.setAlignment(Pos.CENTER);

        final int horizontalGap = 80;
        final int verticalGap = 80;
        foreground.setHgap(horizontalGap);
        foreground.setVgap(verticalGap);

        controls.getLoad().setOnAction(new LoadButtonHandler(getController(), controls));
        controls.getSave().setOnAction(new SaveButtonHandler(getController()));
        controls.getBack().setOnAction(new BackButtonHandler(getController(), controls));
        controls.getPlay().setOnAction(new PlayButtonHandler(getController(), controls));
        controls.getStep().setOnAction(new StepButtonHandler(getController(), controls));
        controls.getGraph().setOnAction(new GraphButtonHandler(getController()));
        controls.getReport().setOnAction(new ReportButtonHandler(getController()));

        final int thirdColumn = 3;
        final int fourthColumn = 4;
        foreground.add(controls.getLoad(), 0, 0);
        foreground.add(controls.getSave(), 0, 1);
        foreground.add(controls.getBack(), 1, 0);
        foreground.add(controls.getPlay(), 2, 0);
        foreground.add(controls.getStep(), thirdColumn, 0);
        foreground.add(controls.getSpeedSlider(), 1, 1, thirdColumn, 1);
        foreground.add(controls.getGraph(), fourthColumn, 0);
        foreground.add(controls.getReport(), fourthColumn, 1);

        getChildren().add(foreground);
    }

    /**
     * Returns the controls.
     *
     * @return the controls
     */
    public OptionControls getControls() {

        return controls;
    }
}
