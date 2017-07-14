package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.option.OptionControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the step button.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class StepButtonHandler implements EventHandler<ActionEvent> {

    /** The simulation controller. */
    private SimulationController controller;

    /** The option pane controls. */
    private OptionControls controls;

    /**
     * Creates a handler for the step button.
     *
     * @param controller
     *         the simulation controller
     * @param controls
     *         the option pane controls
     */
    public StepButtonHandler(SimulationController controller, OptionControls controls) {

        this.controller = controller;
        this.controls = controls;
    }

    @Override
    public void handle(ActionEvent event) {

        controller.updateSimulation(1);
        controls.update(OptionControls.SIMULATION_ADVANCED);
    }
}
