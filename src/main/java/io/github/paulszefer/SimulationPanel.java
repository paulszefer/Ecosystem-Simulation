package io.github.paulszefer;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

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

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        JPanel animation = new AnimationPanel();
        JPanel options = new OptionPanel();

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(animation,
                                      GroupLayout.DEFAULT_SIZE,
                                      GroupLayout.DEFAULT_SIZE,
                                      Short.MAX_VALUE)
                        .addComponent(options,
                                      GroupLayout.DEFAULT_SIZE,
                                      GroupLayout.DEFAULT_SIZE,
                                      Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(animation,
                                      GroupLayout.DEFAULT_SIZE,
                                      GroupLayout.DEFAULT_SIZE,
                                      Short.MAX_VALUE)
                        .addComponent(options,
                                      GroupLayout.DEFAULT_SIZE,
                                      GroupLayout.DEFAULT_SIZE,
                                      Short.MAX_VALUE)
        );
    }
}