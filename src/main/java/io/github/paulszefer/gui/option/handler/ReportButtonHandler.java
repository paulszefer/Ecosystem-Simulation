package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the report button.
 * <p>
 * TODO - not yet implemented.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class ReportButtonHandler implements EventHandler<ActionEvent> {

    /** The simulation controller. */
    private SimulationController controller;

    /**
     * Creates a handler for the report button.
     *
     * @param controller
     *         the simulation controller
     */
    public ReportButtonHandler(SimulationController controller) {

        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {

        System.out.println("Report not yet implemented.");
    }
}