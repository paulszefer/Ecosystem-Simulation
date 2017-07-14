package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the graph button.
 * <p>
 * TODO - not yet implemented.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class GraphButtonHandler implements EventHandler<ActionEvent> {

    /** The simulation controller. */
    private SimulationController controller;


    /**
     * Creates a handler for the save button.
     *
     * @param controller
     *         the simulation controller
     */
    public GraphButtonHandler(SimulationController controller) {
this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {

        System.out.println("Graph not yet implemented.");
    }
}
