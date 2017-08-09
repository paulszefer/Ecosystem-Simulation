package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.sim.Ecosystem;
import io.github.paulszefer.sim.Pool;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

/**
 * Generates graphs based on the simulation and opens a new window for display.
 * <p>
 * Graphs can be shown for population over time, volume required over time, average age over time,
 * and health coefficient over time.
 *
 * TODO - the graph should feature an option to update in real time with the simulation
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

        List<Ecosystem> historyCopy = controller.retrieveHistory();
        if (historyCopy.size() < 1) {
            throw new IllegalStateException("There is no history to graph.");
        }

        AnchorPane root = new AnchorPane();
        HBox container = new HBox();

        LineChart<Number, Number> populationChart = createPopulationChart(historyCopy);
        LineChart<Number, Number> volumeChart = createVolumeChart(historyCopy);
        LineChart<Number, Number> ageChart = createAgeChart(historyCopy);
        LineChart<Number, Number> healthChart = createHealthChart(historyCopy);

        final Insets defaultInsets = new Insets(10);
        final double spacingSize = 10;

        VBox settings = new VBox();
        settings.setAlignment(Pos.TOP_CENTER);
        settings.setPadding(defaultInsets);
        settings.setSpacing(spacingSize);
        // adds a vertical divider between graph and settings menu
        // settings.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
        //                                                Color.TRANSPARENT,
        //                                                Color.TRANSPARENT,
        //                                                Color.GREY,
        //                                                BorderStrokeStyle.NONE,
        //                                                BorderStrokeStyle.NONE,
        //                                                BorderStrokeStyle.NONE,
        //                                                BorderStrokeStyle.SOLID,
        //                                                CornerRadii.EMPTY,
        //                                                BorderWidths.DEFAULT,
        //                                                defaultInsets)));

        final double fontSize = 24;

        Label settingsLabel = new Label("Settings");
        settingsLabel.setMaxWidth(Double.MAX_VALUE);
        settingsLabel.setAlignment(Pos.CENTER);
        settingsLabel.setFont(new Font(fontSize));

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setMaxWidth(Double.MAX_VALUE);
        choiceBox.getItems().addAll("Population",
                                    "Volume Required",
                                    "Average Age",
                                    "Average Health");
        choiceBox.getSelectionModel().select(0);

        choiceBox.setOnAction((actionEvent) -> {
            String selectedOption = choiceBox.getSelectionModel().getSelectedItem();
            LineChart<Number, Number> chart;
            switch (selectedOption) {
                case "Population":
                    chart = populationChart;
                    break;
                case "Volume Required":
                    chart = volumeChart;
                    break;
                case "Average Age":
                    chart = ageChart;
                    break;
                case "Average Health":
                    chart = healthChart;
                    break;
                default:
                    chart = populationChart;
                    break;
            }
            if (container.getChildren().size() == 0) {
                container.getChildren().add(chart);
            } else {
                container.getChildren().set(0, chart);
            }
        });

        choiceBox.getOnAction().handle(new ActionEvent());

        settings.getChildren().add(settingsLabel);
        settings.getChildren().add(choiceBox);

        container.setHgrow(container.getChildren().get(0), Priority.ALWAYS);
        container.getChildren().add(settings);

        root.setTopAnchor(container, 0.0);
        root.setBottomAnchor(container, 0.0);
        root.setLeftAnchor(container, 0.0);
        root.setRightAnchor(container, 0.0);

        root.setTopAnchor(settings, 0.0);
        root.setBottomAnchor(settings, 0.0);
        root.setRightAnchor(settings, 0.0);
        root.getChildren().add(container);

        Stage graphStage = new Stage();
        Scene graphScene = new Scene(root);
        graphStage.setScene(graphScene);
        graphStage.show();
    }

    /**
     * Generates a default chart.
     * <p>
     * This chart is then populated with data within another helper method.
     *
     * @param title
     *         the chart title
     * @param xAxisLabel
     *         the x-axis
     * @param yAxisLabel
     *         the y-axis label
     * @param historyCopy
     *         a copy of the simulation history
     *
     * @return a default chart set up with its title and axis labels
     */
    private static LineChart<Number, Number> createChart(String title, String xAxisLabel,
                                                         String yAxisLabel,
                                                         List<Ecosystem> historyCopy) {

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(xAxisLabel);
        xAxis.setMinorTickVisible(false);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisLabel);

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(title);

        // used to aggregate data for each specific pool
        for (Pool pool : historyCopy.get(0).getPools()) {
            XYChart.Series<Number, Number> data = new XYChart.Series<>();
            data.setName(pool.getName());
            chart.getData().add(data);
        }

        return chart;

    }

    /**
     * Creates a line chart with data corresponding to the population size over time.
     *
     * @param historyCopy
     *         a copy of the simulation history
     *
     * @return a line chart representing the population size over time
     */
    private static LineChart<Number, Number> createPopulationChart(List<Ecosystem> historyCopy) {

        LineChart<Number, Number> populationChart = createChart("Population vs. Time",
                                                                "Week",
                                                                "Population",
                                                                historyCopy);

        for (int weekIndex = 0; weekIndex < historyCopy.size(); weekIndex++) {
            List<Pool> pools = historyCopy.get(weekIndex).getPools();
            XYChart.Data<Number, Number> dataPoint;
            for (int poolIndex = 0; poolIndex < pools.size(); poolIndex++) {
                dataPoint = new XYChart.Data<>(weekIndex, pools.get(poolIndex).getPopulation());
                populationChart.getData().get(poolIndex).getData().add(dataPoint);
            }
        }

        return populationChart;
    }

    /**
     * Creates a line chart with data corresponding to the volume of water required (in Litres) by
     * the population over time.
     *
     * @param historyCopy
     *         a copy of the simulation history
     *
     * @return a line chart representing the volume required by the population over time
     */
    private static LineChart<Number, Number> createVolumeChart(List<Ecosystem> historyCopy) {

        LineChart<Number, Number> volumeChart = createChart("Volume Required vs. Time",
                                                            "Week",
                                                            "Volume (L)",
                                                            historyCopy);

        for (int weekIndex = 0; weekIndex < historyCopy.size(); weekIndex++) {
            List<Pool> pools = historyCopy.get(weekIndex).getPools();
            XYChart.Data<Number, Number> dataPoint;
            for (int poolIndex = 0; poolIndex < pools.size(); poolIndex++) {
                dataPoint = new XYChart.Data<>(weekIndex,
                                               pools.get(poolIndex)
                                                    .getCreatureVolumeRequirementInLitres());

                volumeChart.getData().get(poolIndex).getData().add(dataPoint);
            }
        }

        return volumeChart;
    }

    /**
     * Creates a line chart with data corresponding to the average age of the ecosystem's population
     * over time.
     *
     * @param historyCopy
     *         a copy of the simulation history
     *
     * @return a line chart representing the average age of the ecosystem's population over time
     */
    private static LineChart<Number, Number> createAgeChart(List<Ecosystem> historyCopy) {

        LineChart<Number, Number> ageChart = createChart("Average Age vs. Time",
                                                         "Week",
                                                         "Average Age",
                                                         historyCopy);

        for (int weekIndex = 0; weekIndex < historyCopy.size(); weekIndex++) {
            List<Pool> pools = historyCopy.get(weekIndex).getPools();
            XYChart.Data<Number, Number> dataPoint;
            for (int poolIndex = 0; poolIndex < pools.size(); poolIndex++) {
                dataPoint = new XYChart.Data<>(weekIndex,
                                               pools.get(poolIndex).getAverageAgeInWeeks());
                ageChart.getData().get(poolIndex).getData().add(dataPoint);
            }
        }

        return ageChart;
    }

    /**
     * Creates a line chart with data corresponding to the average health of the ecosystem's
     * population over time.
     *
     * @param historyCopy
     *         a copy of the simulation history
     *
     * @return a line chart representing the average health of the ecosystem's population over time
     */
    private static LineChart<Number, Number> createHealthChart(List<Ecosystem> historyCopy) {

        LineChart<Number, Number> healthChart = createChart("Average Health vs. Time",
                                                            "Week",
                                                            "Health Coefficient",
                                                            historyCopy);

        for (int weekIndex = 0; weekIndex < historyCopy.size(); weekIndex++) {
            List<Pool> pools = historyCopy.get(weekIndex).getPools();
            XYChart.Data<Number, Number> dataPoint;
            for (int poolIndex = 0; poolIndex < pools.size(); poolIndex++) {
                dataPoint = new XYChart.Data<>(weekIndex,
                                               pools.get(poolIndex).getAverageHealthCoefficient());
                healthChart.getData().get(poolIndex).getData().add(dataPoint);
            }
        }

        return healthChart;
    }
}
