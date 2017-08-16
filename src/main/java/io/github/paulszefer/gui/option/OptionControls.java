package io.github.paulszefer.gui.option;

import javafx.scene.control.Button;

/**
 * Defines the controls needed for the simulation option pane.
 * <p>
 * This provides access to the controls where needed, and also handles changing whether the controls
 * are disabled based on the application state.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class OptionControls {

    /** The state upon application load. */
    public static final int APPLICATION_LOADED = 0;

    /** The state upon loading a simulation from an external file. */
    public static final int SIMULATION_LOADED = 1;

    /** The state upon having advanced the simulation at least one step. */
    public static final int SIMULATION_ADVANCED = 2;

    /** The state upon having stepped the simulation backwards to the initial state. */
    public static final int SIMULATION_BACKED_TO_BEGINNING = 3;

    /** The state upon starting the simulation animation. */
    public static final int SIMULATION_ANIMATION_PLAYING = 4;

    /** The state upon stopping the simulation animation. */
    public static final int SIMULATION_ANIMATION_STOPPED = 5;

    /** The number of speeds below and above 0. */
    private static final int SPEEDS = 3;

    /** Loads a new simulation from an external file. */
    private Button load = new Button("Load");

    /** Saves the simulation state to an external file. */
    private Button save = new Button("Save");

    /** Decrements the simulation by one step. */
    private Button back = new Button("Back");

    /** Plays/pauses the simulation. */
    private Button play = new Button("Play");

    /** Increments the simulation by one step. */
    private Button step = new Button("Step");

    /** Provides several speeds for the animation to play at. */
    private SpeedSlider speedSlider = new SpeedSlider(-SPEEDS, SPEEDS, 0);

    /** Displays an interactive graph to display the simulation information. */
    private Button graph = new Button("Graph");

    /** Exports a report with selected simulation data. */
    private Button report = new Button("Report");

    /** Creates the set of controls needed for the option pane. */
    public OptionControls() {

        final int minWidth = 80;
        load.setMinWidth(minWidth);
        save.setMinWidth(minWidth);
        back.setMinWidth(minWidth);
        play.setMinWidth(minWidth);
        step.setMinWidth(minWidth);
        speedSlider.setMinWidth(minWidth);
        graph.setMinWidth(minWidth);
        report.setMinWidth(minWidth);
        update(0);
    }

    /**
     * Returns the load button.
     *
     * @return the load button
     */
    public Button getLoad() {

        return load;
    }

    /**
     * Returns the save button.
     *
     * @return the save button
     */
    public Button getSave() {

        return save;
    }

    /**
     * Returns the back button.
     *
     * @return the back button
     */
    public Button getBack() {

        return back;
    }

    /**
     * Returns the play button.
     *
     * @return the play button
     */
    public Button getPlay() {

        return play;
    }

    /**
     * Returns the step button.
     *
     * @return the step button
     */
    public Button getStep() {

        return step;
    }

    /**
     * Returns the speed slider.
     *
     * @return the speed slider
     */
    public SpeedSlider getSpeedSlider() {

        return speedSlider;
    }

    /**
     * Returns the graph button.
     *
     * @return the graph button
     */
    public Button getGraph() {

        return graph;
    }

    /**
     * Returns the report button.
     *
     * @return the report button
     */
    public Button getReport() {

        return report;
    }

    /**
     * Updates the controls based on the given state of the application.
     *
     * @param state
     *         tbe state of the application
     */
    public void update(int state) {

        switch (state) {
            case APPLICATION_LOADED:
                load.setDisable(false);
                save.setDisable(true);
                back.setDisable(true);
                play.setDisable(true);
                step.setDisable(true);
                speedSlider.setDisable(true);
                graph.setDisable(true);
                report.setDisable(true);
                break;
            case SIMULATION_LOADED:
                load.setDisable(false);
                save.setDisable(true);
                back.setDisable(true);
                play.setDisable(false);
                step.setDisable(false);
                speedSlider.setDisable(false);
                graph.setDisable(false);
                report.setDisable(false);
                break;
            case SIMULATION_ADVANCED:
                load.setDisable(false);
                save.setDisable(false);
                back.setDisable(false);
                play.setDisable(false);
                step.setDisable(false);
                speedSlider.setDisable(false);
                graph.setDisable(false);
                report.setDisable(false);
                break;
            case SIMULATION_BACKED_TO_BEGINNING:
                load.setDisable(false);
                save.setDisable(false);
                back.setDisable(true);
                play.setDisable(false);
                step.setDisable(false);
                speedSlider.setDisable(false);
                graph.setDisable(false);
                report.setDisable(false);
                break;
            case SIMULATION_ANIMATION_PLAYING:
                load.setDisable(true);
                save.setDisable(true);
                back.setDisable(true);
                play.setText("Pause");
                step.setDisable(true);
                speedSlider.setDisable(true);
                graph.setDisable(true);
                report.setDisable(true);
                break;
            case SIMULATION_ANIMATION_STOPPED:
                load.setDisable(false);
                save.setDisable(false);
                back.setDisable(false);
                play.setText("Play");
                step.setDisable(false);
                speedSlider.setDisable(false);
                graph.setDisable(false);
                report.setDisable(false);
                break;
            default:
                break;
        }
    }
}
