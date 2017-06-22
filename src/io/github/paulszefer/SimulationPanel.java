package io.github.paulszefer;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Scanner;

/**
 * A panel to hold the components that will display the simulation GUI.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SimulationPanel extends JPanel {

    /**
     * A panel to hold the components of the simulation GUI.
     *
     * @param data
     *         a scanner containing the data to load into the simulation
     */
    public SimulationPanel(Scanner data) {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints;

        JPanel animationPanel = new AnimationPanel();
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.8;
        panel.add(animationPanel, constraints);
    }
}