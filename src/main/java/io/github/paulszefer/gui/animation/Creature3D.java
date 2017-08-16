package io.github.paulszefer.gui.animation;

import io.github.paulszefer.sim.Creature;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

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

    /** The minimum length of a creature's animation timeline. */
    private static final double CREATURE_ANIMATION_DURATION = 6000;

    /** The maximum amount of variation in the durations of key frames in a creature's animation. */
    private static final double CREATURE_ANIMATION_DURATION_VARIATION =
            CREATURE_ANIMATION_DURATION / 3;

    /**
     * Creates a 3D sphere that represents a creature in the simulation.
     *
     * @param creature
     *         a creature in the simulation
     * @param radius
     *         the radius of the sphere
     * @param pool3D
     *         the 3D representation of the pool in which the creature resides
     */
    public Creature3D(Creature creature, double radius, Pool3D pool3D) {
        super(radius);
        if (creature.getHealth().getCoefficient() > HEALTHY_COEFFICIENT) {
            setMaterial(CREATURE_HEALTHY);
        } else if (creature.getHealth().getCoefficient() > OKAY_COEFFICIENT) {
            setMaterial(CREATURE_OKAY);
        } else if (creature.getHealth().getCoefficient() > AVERAGE_COEFFICIENT) {
            setMaterial(CREATURE_AVERAGE);
        } else if (creature.getHealth().getCoefficient() > POOR_COEFFICIENT) {
            setMaterial(CREATURE_POOR);
        } else {
            setMaterial(CREATURE_UNHEALTHY);
        }
        setTranslateX(pool3D.getTranslateX() + randomPoint(pool3D.getWidth()));
        setTranslateY(pool3D.getTranslateY() + randomPoint(pool3D.getHeight()));
        setTranslateZ(pool3D.getTranslateZ() + randomPoint(pool3D.getHeight()));

        Timeline creatureAnimation = new Timeline();
        creatureAnimation.setCycleCount(Animation.INDEFINITE);

        final double oneThird = 1.0 / 3;
        final double twoThirds = 2.0 / 3;
        KeyFrame startFrame = generateKeyFrame(0, this);
        KeyFrame midFrame1 = generateKeyFrame(oneThird, this, pool3D);
        KeyFrame midFrame2 = generateKeyFrame(twoThirds, this, pool3D);
        KeyFrame endFrame = generateKeyFrame(1, this);

        creatureAnimation.getKeyFrames().addAll(startFrame, midFrame1, midFrame2, endFrame);
        creatureAnimation.play();
    }

    /**
     * Generates a KeyFrame with the original coordinates of the sphere.
     *
     * @param animationProgress
     *         the proportion of the simulation that has completed
     * @param target
     *         the node to animate
     *
     * @return a KeyFrame with the original coordinates of the sphere
     */
    private KeyFrame generateKeyFrame(double animationProgress, Node target) {
        Duration duration = animationProgress != 0 ? randomDuration(animationProgress)
                                                   : Duration.ZERO;
        return new KeyFrame(duration,
                            new KeyValue(target.translateXProperty(), target.getTranslateX()),
                            new KeyValue(target.translateYProperty(), target.getTranslateY()),
                            new KeyValue(target.translateZProperty(), target.getTranslateZ()));
    }

    /**
     * Generates a KeyFrame with randomly generated coordinates.
     *
     * @param animationProgress
     *         the proportion of the simulation that has completed
     * @param target
     *         the node to animate
     * @param container
     *         the container in which the target is contained
     *
     * @return a KeyFrame with randomly generated coordinates
     */
    private KeyFrame generateKeyFrame(double animationProgress, Node target, Box container) {
        Duration duration = animationProgress != 0 ? randomDuration(animationProgress)
                                                   : Duration.ZERO;
        return new KeyFrame(duration, new KeyValue(target.translateXProperty(),
                                                   container.getTranslateX() + randomPoint(
                                                           container.getWidth())),
                            new KeyValue(target.translateYProperty(),
                                         container.getTranslateY() + randomPoint(
                                                 container.getHeight())),
                            new KeyValue(target.translateZProperty(),
                                         container.getTranslateZ() + randomPoint(
                                                 container.getDepth())));
    }

    /**
     * Generates a randomly generated duration.
     * <p>
     * This is intended to be used to generate semi-random KeyFrame durations to create variation in
     * the length between each KeyFrame.
     *
     * @param animationProgress
     *         the proportion of the simulation that has completed
     *
     * @return a randomly generated duration
     */
    private Duration randomDuration(double animationProgress) {
        return new Duration(GENERATOR.nextDouble() * CREATURE_ANIMATION_DURATION_VARIATION
                            + CREATURE_ANIMATION_DURATION * animationProgress);
    }

    /**
     * Generates a randomly generated coordinate within an allowed range determined by the given
     * dimensionSize.
     * <p>
     * Coordinates can be generated from -0.48 * dimensionSize to 0.48 * dimensionSize
     *
     * @param dimensionSize
     *         allowed range
     *
     * @return a randomly generated coordinate
     */
    private double randomPoint(double dimensionSize) {
        final double allowedFillProportion = 0.96;
        final double allowedSize = dimensionSize * allowedFillProportion;
        final double shiftHalfNegative = dimensionSize * allowedFillProportion / 2;
        return GENERATOR.nextDouble() * allowedSize - shiftHalfNegative;
    }
}
