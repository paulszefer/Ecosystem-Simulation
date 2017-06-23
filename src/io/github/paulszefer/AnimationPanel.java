package io.github.paulszefer;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * The panel that will hold the animation of the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationPanel extends JPanel {

    /** Creates a panel that will hold the animation of the simulation. */
    public AnimationPanel() {

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
    }

    /**
     * Draws the animation.
     * <p>
     * Currently just a blue box.
     * <p>
     * TODO - create, implement simulation
     *
     * @param g
     *         the graphics object to draw on
     */
    protected void paintComponent(Graphics g) {

        g.setColor(Color.BLUE);
        Dimension screenSize = getToolkit().getScreenSize();
        g.fillRect(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
    }

    /** {@inheritDoc} */
    @Override
    public Dimension getPreferredSize() {

        final double proportion = 0.75;
        return new Dimension(SimulationFrame.WIDTH, (int) (SimulationFrame.HEIGHT * proportion));
    }

}
