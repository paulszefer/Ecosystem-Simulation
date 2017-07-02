package io.github.paulszefer.gui;

import javafx.scene.control.Slider;
import javafx.util.StringConverter;

/**
 * Defines a slider with labels representing play speeds.
 * <p>
 * Play speeds increment by a factor of two.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SpeedSlider extends Slider {

    /**
     * Creates a horizontal slider with the given minimum, maximum and start values.
     * <p>
     * Sets up labels incrementing by a factor of two.
     *
     * @param min
     *         the minimum state
     * @param max
     *         the maximum state
     * @param start
     *         the starting state
     */
    public SpeedSlider(int min, int max, int start) {

        super(min, max, start);
        configureLabels();
    }

    /**
     * Configures the labels of the slider to display speeds based on the range of the slider.
     * <p>
     * Speeds increment by a factor of two.
     */
    private void configureLabels() {

        @SuppressWarnings("unchecked") StringConverter<Double> converter = new StringConverter() {

            /**
             * Converts the object provided into its string form.
             * <p>
             * Format of the returned string is defined by the specific converter.
             *
             * @param object
             *         the unformatted bound of the slider
             *
             * @return a string representation of the object passed in.
             */
            @Override
            public String toString(Object object) {

                double bound = (double) object;
                if (bound < 0) {
                    return "1/" + (int) Math.pow(2, -bound) + "x";
                } else if (bound == 0) {
                    return "1x";
                } else {
                    return (int) Math.pow(2, bound) + "x";
                }
            }

            /**
             * Converts the string provided into an object defined by the specific converter.
             * <p>
             * Format of the string and type of the resulting object is defined by the specific
             * converter.
             *
             * @param string
             *         the formatted bound of the slider
             *
             * @return an object representation of the string passed in.
             */
            @Override
            public Object fromString(String string) {

                String value = string.substring(0, string.length() - 1);
                if (value.startsWith("1/")) {
                    return Double.valueOf(value);
                } else if (value.startsWith("1")) {
                    return Double.valueOf("0");
                } else {
                    return Double.valueOf(value);
                }
            }
        };

        setLabelFormatter(converter);
        setMinorTickCount(0);
        setMajorTickUnit(1);
        setShowTickMarks(true);
        setShowTickLabels(true);
        setSnapToTicks(true);
    }

    /**
     * Returns the currently selected speed multiplier.
     *
     * @return the currently selected speed multiplier
     */
    public double returnMultiplier() {

        Double current = getValue();
        if (current < 0) {
            return 1 / Math.pow(2, -current);
        } else if (current.compareTo(0.0) == 0) {
            return 1;
        } else {
            return Math.pow(2, current);
        }
    }
}
