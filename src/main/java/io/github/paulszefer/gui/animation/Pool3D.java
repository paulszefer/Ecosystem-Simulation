package io.github.paulszefer.gui.animation;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * A 3D box that represents a Pool in the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Pool3D extends Box {

    /** The colour of water. */
    private static final Color WATER_COLOUR = new Color(0, 0, 0.5, 0.4);

    /** The material of water. */
    private static final PhongMaterial WATER = new PhongMaterial(WATER_COLOUR);

    /**
     * Creates a 3D box to represent a pool in the simulation.
     *
     * @param poolSize
     *         the size of the box
     * @param translateX
     *         the shift in the x-coordinate of this box
     */
    public Pool3D(double poolSize, double translateX) {
        super(poolSize, poolSize, poolSize);
        setMaterial(WATER);
        setTranslateX(translateX);
    }

    /**
     * Creates a 3D box to represent a pool in the simulation.
     *
     * @param width
     *         the width of the box
     * @param height
     *         the height of the box
     * @param depth
     *         the depth of the box
     * @param translateX
     *         the shift in the x-coordinate of this box
     */
    public Pool3D(double width, double height, double depth, double translateX) {
        super(width, height, depth);
        setMaterial(WATER);
        setTranslateX(translateX);
    }
}
