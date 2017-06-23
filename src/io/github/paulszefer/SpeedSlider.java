package io.github.paulszefer;

import javax.swing.JLabel;
import javax.swing.JSlider;

import java.util.Hashtable;

/**
 * Defines a slider with labels representing play speeds.
 * <p>
 * Play speeds increment by a factor of two.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SpeedSlider extends JSlider {

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
        configureLabels(min, max);
    }

    /**
     * Configures the labels of the slider to display speeds based on the range of the slider.
     * <p>
     * Speeds increment by a factor of two.
     *
     * @param min
     *         the minimum slider state
     * @param max
     *         the maximum slider state
     */
    private void configureLabels(int min, int max) {

        //Create the label table
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = min; i <= max; i++) {
            if (i < 0) {
                labelTable.put(i, new JLabel("1/" + (int) Math.pow(2, -i) + "x"));
            } else if (i == 0) {
                labelTable.put(i, new JLabel("1x"));
            } else {
                labelTable.put(i, new JLabel((int) Math.pow(2, i) + "x"));
            }
        }
        setLabelTable(labelTable);

        setMajorTickSpacing(1);
        setPaintTicks(true);
        setPaintLabels(true);
    }
}
