package io.github.paulszefer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle;

import java.awt.Dimension;

/**
 * The panel that holds and manages the option components for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class OptionPanel extends JPanel {

    /** Creates a panel that holds and manages the option components for the simulation. */
    public OptionPanel() {

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        // Data load controls
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");

        // Animation controls
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton stepButton = new JButton("Step");

        final int speedBound = 3;
        JSlider speedSlider = new JSpeedSlider(-speedBound, speedBound, 0);

        // Data analysis controls
        JButton graphButton = new JButton("Graph");
        JButton reportButton = new JButton("Report");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addComponent(loadButton)
                                      .addComponent(saveButton)
                      )
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addGroup(layout.createSequentialGroup()
                                                      .addComponent(playButton)
                                                      .addPreferredGap(
                                                              LayoutStyle.ComponentPlacement.RELATED,
                                                              GroupLayout.DEFAULT_SIZE,
                                                              Short.MAX_VALUE
                                                      )
                                                      .addComponent(pauseButton)
                                                      .addPreferredGap(
                                                              LayoutStyle.ComponentPlacement.RELATED,
                                                              GroupLayout.DEFAULT_SIZE,
                                                              Short.MAX_VALUE
                                                      )
                                                      .addComponent(stepButton)
                                      )
                                      .addComponent(speedSlider)
                      )
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                      .addComponent(graphButton)
                                      .addComponent(reportButton)
                      )
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                      .addComponent(loadButton)
                                      .addComponent(playButton)
                                      .addComponent(pauseButton)
                                      .addComponent(stepButton)
                                      .addComponent(graphButton)
                      )
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                      .addComponent(saveButton)
                                      .addComponent(speedSlider)
                                      .addComponent(reportButton)
                      )
                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE
                      )
        );

    }

    /** {@inheritDoc} */
    @Override
    public Dimension getPreferredSize() {

        final double proportion = 0.25;
        return new Dimension(SimulationFrame.WIDTH, (int) (SimulationFrame.HEIGHT * proportion));
    }
}
