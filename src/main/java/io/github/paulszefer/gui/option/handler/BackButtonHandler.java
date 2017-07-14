package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.option.OptionControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the back button.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class BackButtonHandler implements EventHandler<ActionEvent> {

    /** The simulation controller. */
    private SimulationController controller;

    /** The option pane controls. */
    private OptionControls controls;

    /**
     * Creates a handler for the save button.
     *
     * @param controller
     *         the simulation controller
     * @param controls
     *         the option pane controls
     */
    public BackButtonHandler(SimulationController controller, OptionControls controls) {

        this.controller = controller;
        this.controls = controls;
    }

    @Override
    public void handle(ActionEvent event) {

        controller.updateSimulation(-1);
        if (controller.retrieveWeek() == 0) {
            controls.update(OptionControls.SIMULATION_BACKED_TO_BEGINNING);
        }
    }
}
