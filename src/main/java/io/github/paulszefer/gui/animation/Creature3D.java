package io.github.paulszefer.gui.animation;

import io.github.paulszefer.sim.Creature;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.Random;

/**
 * A 3D sphere that represents a creature in the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Creature3D extends Sphere {

    /** A random number generator. */
    private static final Random GENERATOR = new Random();

    /** The coefficient above which a creature is considered healthy. */
    private static final double HEALTHY_COEFFICIENT = 0.9;

    /** The coefficient above which a creature is considered in okay health. */
    private static final double OKAY_COEFFICIENT = 0.7;

    /** The coefficient above which a creature is considered in average health. */
    private static final double AVERAGE_COEFFICIENT = 0.3;

    /** The coefficient above which a creature is considered in poor health. */
    private static final double POOR_COEFFICIENT = 0.1;

    /** A colour representing a healthy creature. */
    private static final Color HEALTHY_COLOUR = new Color(0, 1, 0, 1);

    /** A colour representing an okay-health creature. */
    private static final Color OKAY_COLOUR = new Color(0.6, 1, 0, 1);

    /** A colour representing an average-health creature. */
    private static final Color AVERAGE_COLOUR = new Color(1, 1, 0, 1);

    /** A colour representing a poor-health creature. */
    private static final Color POOR_COLOUR = new Color(1, 0.6, 0, 1);

    /** A colour representing an unhealthy creature. */
    private static final Color UNHEALTHY_COLOUR = new Color(1, 0, 0, 1);

    /** A material representing a healthy creature. */
    private static final PhongMaterial CREATURE_HEALTHY = new PhongMaterial(HEALTHY_COLOUR);

    /** A material representing an okay-health creature. */
    private static final PhongMaterial CREATURE_OKAY = new PhongMaterial(OKAY_COLOUR);

    /** A material representing an average-health creature. */
    private static final PhongMaterial CREATURE_AVERAGE = new PhongMaterial(AVERAGE_COLOUR);

    /** A material representing a poor-health creature. */
    private static final PhongMaterial CREATURE_POOR = new PhongMaterial(POOR_COLOUR);

    /** A material representing an unhealthy creature. */
    private static final PhongMaterial CREATURE_UNHEALTHY = new PhongMaterial(UNHEALTHY_COLOUR);

    /**
     * Creates a 3D sphere that represents a creature in the simulation.
     *
     * @param creature
     *         a creature in the simulation
     */
    public Creature3D(Creature creature) {
        final double creatureRadius = Pool3D.POOL_SIZE / 50;
        setRadius(creatureRadius);
        if (creature == null) {
            setVisible(false);
        } else {
            setMaterial(determineMaterial(creature));
        }
    }

    /**
     * Updates this Creature3D's material based on the given creature's health coefficient. This
     * also sets this Creature3D's visible property to true.
     *
     * @param creature
     *         the new creature
     */
    public void update(Creature creature) {
        setMaterial(determineMaterial(creature));
        setVisible(true);
    }

    /**
     * Returns the PhongMaterial corresponding to the given creature's health coefficient.
     *
     * @param creature
     *         the creature whose health will be checked
     *
     * @return the PhongMaterial corresponding to the given creature's health coefficient
     */
    private PhongMaterial determineMaterial(Creature creature) {
        if (creature.getHealth().getCoefficient() > HEALTHY_COEFFICIENT) {
            return CREATURE_HEALTHY;
        } else if (creature.getHealth().getCoefficient() > OKAY_COEFFICIENT) {
            return CREATURE_OKAY;
        } else if (creature.getHealth().getCoefficient() > AVERAGE_COEFFICIENT) {
            return CREATURE_AVERAGE;
        } else if (creature.getHealth().getCoefficient() > POOR_COEFFICIENT) {
            return CREATURE_POOR;
        } else {
            return CREATURE_UNHEALTHY;
        }
    }
}
