package io.github.paulszefer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A frame that holds the GUI for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class SimulationFrame extends JFrame implements ILoadable {

    /** Width of the frame. */
    public static final int WIDTH = 400;

    /** Height of the frame. */
    public static final int HEIGHT = 400;

    /** A frame to hold and display the simulation. */
    public SimulationFrame() {

        Scanner data = loadData();

        String simulationTitle;
        if (data != null) {
            simulationTitle = data.nextLine();
        } else {
            simulationTitle = "";
        }

        JFrame frame = new JFrame(simulationTitle);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new SimulationPanel(data));
        frame.setSize(WIDTH, HEIGHT);
        frame.pack();
        frame.setVisible(true);
    }

    /** {@inheritDoc} */
    @Override
    public Scanner loadData() {

        // TODO - parse file differently based on file type
        // Scanner should work for text file, but maybe not for xml and json

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File type",
                                                                     "xml",
                                                                     "json",
                                                                     "txt");
        fileChooser.addChoosableFileFilter(filter);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                return new Scanner(file);
            } catch (FileNotFoundException e) {
                return null;
            }
        }
        return null;

    }
}
