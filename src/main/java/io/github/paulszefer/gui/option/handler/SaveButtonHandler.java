package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the save button.
 * <p>
 * TODO - Not yet implemented.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SaveButtonHandler implements EventHandler<ActionEvent> {

    /** The simulation controller. */
    private SimulationController controller;

    /**
     * Creates a handler for the save button.
     *
     * @param controller
     *         the simulation controller
     */
    public SaveButtonHandler(SimulationController controller) {

        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {

        System.out.println("Save not yet implemented.");
        // get simulation state from controller
        // parse state
        // save relevant information in designed format
    }
}
