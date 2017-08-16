package io.github.paulszefer.gui.option;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.GUI;
import io.github.paulszefer.gui.option.handler.BackButtonHandler;
import io.github.paulszefer.gui.option.handler.GraphButtonHandler;
import io.github.paulszefer.gui.option.handler.LoadButtonHandler;
import io.github.paulszefer.gui.option.handler.PlayButtonHandler;
import io.github.paulszefer.gui.option.handler.ReportButtonHandler;
import io.github.paulszefer.gui.option.handler.SaveButtonHandler;
import io.github.paulszefer.gui.option.handler.StepButtonHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Defines the GUI pane that will display the options for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class OptionPane extends GridPane {

    /** The proportion of the GUI's height taken by this pane. */
    public static final double PROPORTION = 0.25;

    /** The simulation controller. */
    private SimulationController controller;

    /** The option pane's controls. */
    private OptionControls controls = new OptionControls();

    /**
     * Creates the GUI pane that will display the options for the simulation.
     *
     * @param controller
     *         the simulation controller
     */
    public OptionPane(SimulationController controller) {

        this.controller = controller;
        setMinWidth(GUI.WIDTH);
        setMinHeight(GUI.HEIGHT * PROPORTION);
        setBackground(
                new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        addControls();
    }

    /** Adds the controls to the pane. */
    protected void addControls() {

        setAlignment(Pos.CENTER);

        final int horizontalGap = 60;
        final int verticalGap = 50;
        setHgap(horizontalGap);
        setVgap(verticalGap);

        controls.getLoad().setOnAction(new LoadButtonHandler(controller, controls));
        controls.getSave().setOnAction(new SaveButtonHandler(controller));
        controls.getBack().setOnAction(new BackButtonHandler(controller, controls));
        controls.getPlay().setOnAction(new PlayButtonHandler(controller, controls));
        controls.getStep().setOnAction(new StepButtonHandler(controller, controls));
        controls.getGraph().setOnAction(new GraphButtonHandler(controller));
        controls.getReport().setOnAction(new ReportButtonHandler(controller));

        final int thirdColumn = 3;
        final int fourthColumn = 4;
        add(controls.getLoad(), 0, 0);
        add(controls.getSave(), 0, 1);
        add(controls.getBack(), 1, 0);
        add(controls.getPlay(), 2, 0);
        add(controls.getStep(), thirdColumn, 0);
        add(controls.getSpeedSlider(), 1, 1, thirdColumn, 1);
        add(controls.getGraph(), fourthColumn, 0);
        add(controls.getReport(), fourthColumn, 1);
    }
}
