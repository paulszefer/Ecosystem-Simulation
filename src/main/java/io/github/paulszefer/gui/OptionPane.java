package io.github.paulszefer.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Defines the GUI pane that will display and handle the options for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class OptionPane extends StackPane {

    /** The default delay of the animation (in ms). */
    public static final int DELAY = 1000;

    /** The proportion of the GUI's height taken by this Pane. */
    private static final double PROPORTION = 0.25;

    /** The width of the option pane. */
    private static final double WIDTH = SimulationApplication.WIDTH;

    /** The height of the option pane. */
    private static final double HEIGHT = SimulationApplication.HEIGHT * PROPORTION;

    /** The background fill of the animation pane. */
    private static final Paint BACKGROUND_FILL = Color.WHITE;

    /** Creates the GUI pane that will display the options for the simulation. */
    public OptionPane() {

        addBackground();
        addForeground();
    }

    /** Adds the background pane. */
    private void addBackground() {

        getChildren().add(new BackgroundCanvas(WIDTH, HEIGHT, BACKGROUND_FILL));
    }

    /** Adds the foreground pane. */
    private void addForeground() {

        GridPane foreground = new GridPane();
        foreground.setAlignment(Pos.CENTER);

        final int horizontalGap = 80;
        final int verticalGap = 80;
        foreground.setHgap(horizontalGap);
        foreground.setVgap(verticalGap);

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

        // Control configurations
        loadButton.setOnAction((actionEvent) -> {

            boolean fileLoaded = SimulationApplication.getSimulation().loadFile();
            if (fileLoaded) {
                backButton.setDisable(true);
                playPauseButton.setDisable(false);
                stepButton.setDisable(false);

                speedSlider.setDisable(false);

                graphButton.setDisable(false);
                reportButton.setDisable(false);
            }
        });
        saveButton.setDisable(true);

        backButton.setOnAction((actionEvent) -> {
            SimulationApplication.getSimulation().previousWeek();
            if (SimulationApplication.getSimulation().getWeek() == 0) {
                backButton.setDisable(true);
            }
        });
        backButton.setDisable(true);
        final boolean[] animationActive = { false };
        playPauseButton.setOnAction((actionEvent) -> {
            if (playPauseButton.getText().equals("Play")) {
                playPauseButton.setText("Pause");
                speedSlider.setDisable(true);
                Timer timer = new Timer();
                animationActive[0] = true;
                timer.scheduleAtFixedRate(new TimerTask() {

                    @Override
                    public void run() {

                        if (!animationActive[0]) {
                            timer.cancel();
                            timer.purge();
                            return;
                        }
                        Platform.runLater(() -> {
                            SimulationApplication.getSimulation().nextWeek();
                            backButton.setDisable(false);
                        });
                    }
                }, 0, (int) (DELAY / ((SpeedSlider) speedSlider).returnMultiplier()));
            } else {
                playPauseButton.setText("Play");
                speedSlider.setDisable(false);
                animationActive[0] = false;
            }
        });
        playPauseButton.setDisable(true);
        stepButton.setOnAction((actionEvent) -> {
            SimulationApplication.getSimulation().nextWeek();
            backButton.setDisable(false);
        });
        stepButton.setDisable(true);

        speedSlider.setDisable(true);

        graphButton.setDisable(true);
        reportButton.setDisable(true);

        final int thirdColumn = 3;
        final int fourthColumn = 4;

        foreground.add(loadButton, 0, 0);
        foreground.add(saveButton, 0, 1);

        foreground.add(backButton, 1, 0);
        foreground.add(playPauseButton, 2, 0);
        foreground.add(stepButton, thirdColumn, 0);

        foreground.add(speedSlider, 1, 1, thirdColumn, 1);

        foreground.add(graphButton, fourthColumn, 0);
        foreground.add(reportButton, fourthColumn, 1);

        getChildren().add(foreground);
    }
}
