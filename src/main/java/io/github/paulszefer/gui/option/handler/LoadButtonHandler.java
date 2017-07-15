package io.github.paulszefer.gui.option.handler;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.option.OptionControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handles the load button.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class LoadButtonHandler implements EventHandler<ActionEvent> {

    /** The simulation controller. */
    private SimulationController controller;

    /** The option pane controls. */
    private OptionControls controls;

    /**
     * Creates the handler for the load button.
     *
     * @param controller
     *         the simulation controller
     * @param controls
     *         the option pane controls
     */
    public LoadButtonHandler(SimulationController controller, OptionControls controls) {

        this.controller = controller;
        this.controls = controls;
    }

    @Override
    public void handle(ActionEvent event) {

        loadFile();
    }

    /** Loads the simulation data from a file. */
    public void loadFile() {

        // TODO - parse file differently based on file type
        // Scanner should work for text file, but maybe not for xml and json
        // Option: DataFile extends File, has a method called nextField()
        // that returns the next field based on the file type/structure

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Simulation load file (*.txt)",
                                                                     "txt");
        //"xml", "json", "txt");
        fileChooser.setFileFilter(filter);

        Scanner fileData;

        final int[] fileChooserResult = new int[1];
        try {
            EventQueue.invokeAndWait(() -> {
                fileChooserResult[0] = fileChooser.showOpenDialog(null);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (fileChooserResult[0] == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fileData = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return;
            }
        } else {
            System.out.println("No file selected");
            return;
        }

        String simulationTitle;

        try {
            simulationTitle = fileData.nextLine();
        } catch (NullPointerException e) {
            System.out.println("Invalid data file");
            return;
        } catch (NoSuchElementException e) {
            System.out.println("Invalid file type");
            return;
        }

        controller.updateGUI(simulationTitle);
        controller.updateSimulation(fileData);
        controls.update(OptionControls.SIMULATION_LOADED);
    }
}
