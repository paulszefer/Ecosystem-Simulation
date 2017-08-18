package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.option.OptionControls;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles the play button.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class PlayButtonHandler implements EventHandler<ActionEvent> {

    /** The default delay of the animation (in ms). */
    public static final int DELAY = 1000;

    /** The maximum number of weeks to automatically simulate. */
    private static final int MAX_WEEKS_TO_SIMULATE = 10;

    /** The simulation controller. */
    private SimulationController controller;

    /** The option pane controls. */
    private OptionControls controls;

    /** Whether the animation is currently active. */
    private boolean animationActive;

    /** The number of weeks automatically simulated so far. */
    private int numberOfWeeksAutoSimulated;

    /**
     * Creates a handler for the play button.
     *
     * @param controller
     *         the simulation controller
     * @param controls
     *         the option pane controls
     */
    public PlayButtonHandler(SimulationController controller, OptionControls controls) {

        this.controller = controller;
        this.controls = controls;
    }

    @Override
    public void handle(ActionEvent event) {

        if (controls.getPlay().getText().equals("Play")) {
            Timer timer = new Timer();
            animationActive = true;
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {

                    Platform.runLater(() -> {
                        controller.updateSimulation(1);
                        controls.update(OptionControls.SIMULATION_ANIMATION_PLAYING);
                    });
                    numberOfWeeksAutoSimulated++;
                    animationActive = numberOfWeeksAutoSimulated < MAX_WEEKS_TO_SIMULATE;

                    if (!animationActive) {
                        timer.cancel();
                        numberOfWeeksAutoSimulated = 0;
                        Platform.runLater(() -> {
                            controls.update(OptionControls.SIMULATION_ANIMATION_STOPPED);
                        });
                    }
                }
            }, 0, (int) (DELAY / (controls.getSpeedSlider()).returnMultiplier()));
        } else {
            controls.update(OptionControls.SIMULATION_ANIMATION_STOPPED);
            animationActive = false;
        }
    }
}
