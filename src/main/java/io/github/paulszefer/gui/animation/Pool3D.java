package io.github.paulszefer.gui.animation;

import io.github.paulszefer.sim.Pool;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A 3D box that represents a Pool in the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Pool3D extends Box {

    /** The maximum number of creatures to animate. */
    public static final int MAX_ANIMATED_CREATURES = 200;

    /** The size of the pool3D's smallest dimension. */
    public static final double POOL_SIZE = 100;

    /** A random number generator. */
    private static final Random GENERATOR = new Random();

    /** The width of the pool3D. */
    private static final double WIDTH = POOL_SIZE;

    /** The height of the pool3D. */
    private static final double HEIGHT = POOL_SIZE;

    /** The depth of the pool3D. */
    private static final double DEPTH = POOL_SIZE;

    /** The gap between pool3Ds. */
    private static final double GAP = 50;

    /** The colour of water. */
    private static final Color WATER_COLOUR = new Color(0, 0, 0.5, 0.4);

    /** The material of water. */
    private static final PhongMaterial WATER = new PhongMaterial(WATER_COLOUR);

    /** The minimum length of a creature's animation timeline. */
    private static final double CREATURE_ANIMATION_DURATION = 6000;

    /** The maximum amount of variation in the durations of key frames in a creature's animation. */
    private static final double CREATURE_ANIMATION_DURATION_VARIATION =
            CREATURE_ANIMATION_DURATION / 3;

    /** The set of timelines for this pool. */
    private final List<Timeline> timelines = new ArrayList<>();

    /** The set of creature3Ds for this pool. */
    private final List<Creature3D> creature3Ds = new ArrayList<>();

    /**
     * Creates a Pool3D.
     * <p>
     * This also sets up the bindings between the creature3Ds and their respective timelines.
     * <p>
     * Finally, the timelines are started.
     *
     * @param translateXFactor
     *         the factor by which the width + gap sum must be multiplied to shift this Pool3D
     * @param creature3Ds
     *         the list of Creature3Ds for this Pool3D
     */
    public Pool3D(int translateXFactor, List<Creature3D> creature3Ds) {
        super(WIDTH, HEIGHT, DEPTH);
        setMaterial(WATER);
        setTranslateX(translateXFactor * (WIDTH + GAP));
        this.creature3Ds.addAll(creature3Ds);
        setupAnimation();
        for (Timeline timeline : timelines) {
            timeline.play();
        }
    }

    public void update(Pool pool) {
        int numberOfCreatures = pool.getPopulation() < MAX_ANIMATED_CREATURES
                                ? pool.getPopulation()
                                : MAX_ANIMATED_CREATURES;
        for (int i = 0; i < numberOfCreatures; i++) {
            creature3Ds.get(i).update(
                    pool.getCreatures().get(GENERATOR.nextInt(pool.getCreatures().size())));
            timelines.get(i).playFromStart();
        }
        int index = numberOfCreatures;
        while (index < MAX_ANIMATED_CREATURES) {
            creature3Ds.get(index).setVisible(false);
            index++;
        }
        // for (Creature3D creature3D : creature3Ds) {
        //     creature3D.update(ecosystem);
        // }
    }

    private void setupAnimation() {
        for (int i = 0; i < creature3Ds.size(); i++) {
            Creature3D creature3D = creature3Ds.get(i);
            creature3D.setTranslateX(getTranslateX() + randomPoint(getWidth()));
            creature3D.setTranslateY(getTranslateY() + randomPoint(getHeight()));
            creature3D.setTranslateZ(getTranslateZ() + randomPoint(getDepth()));
            timelines.add(generateTimeline(creature3D));

            // setTranslateX(pool3D.getTranslateX() + randomPoint(pool3D.getWidth()));
            // setTranslateY(pool3D.getTranslateY() + randomPoint(pool3D.getHeight()));
            // setTranslateZ(pool3D.getTranslateZ() + randomPoint(pool3D.getHeight()));

            // Timeline timeline = pool3D.getOneTimeline(index);
            // for (KeyFrame keyFrame : timeline.getKeyFrames()) {
            //     Object[] keyValues = keyFrame.getValues().toArray();
            //     KeyValue[] keyFrameKeyValues = Arrays.copyOf(keyValues, keyValues.length,
            //                                                  KeyValue[].class);
            //     keyFrameKeyValues[0].getTarget().setValue(translateXProperty());
            //     keyFrame.getValues().clear();
            //     keyFrame.getValues().addAll(Arrays.asList(keyFrameKeyValues));
            // }
            //
            // KeyValue keyValue = new KeyValue((new Box()).translateXProperty(), 100);
            // keyValue.getTarget().setValue();

        }
    }

    private Timeline generateTimeline(Creature3D creature3D) {
        Timeline creatureTimeline = new Timeline();
        creatureTimeline.setCycleCount(Animation.INDEFINITE);

        final double oneThird = 1.0 / 3;
        final double twoThirds = 2.0 / 3;
        // final double throwawaySize = 20;
        // final Box throwaway = new Box(throwawaySize, throwawaySize, throwawaySize);
        // throwaway.setVisible(false);
        KeyFrame startFrame = generateKeyFrame(0, creature3D);
        KeyFrame midFrame1 = generateKeyFrame(oneThird, creature3D);
        KeyFrame midFrame2 = generateKeyFrame(twoThirds, creature3D);
        KeyFrame endFrame = generateKeyFrame(1, creature3D);

        // Object[] keyValues = startFrame.getValues().toArray();
        // KeyValue[] startFrameKeyValues = Arrays.copyOf(keyValues, keyValues.length, KeyValue[].class);
        // startFrameKeyValues[0].getTarget().setValue();
        // startFrame.getValues().clear();
        // startFrame.getValues().addAll(Arrays.asList(startFrameKeyValues));

        creatureTimeline.getKeyFrames().addAll(startFrame, midFrame1, midFrame2, endFrame);
        return creatureTimeline;
        // timelines.add(creatureTimeline);
    }

    // /**
    //  * Creates a 3D cube to represent a pool in the simulation.
    //  *
    //  * @param poolSize
    //  *         the size of the box
    //  * @param translateX
    //  *         the shift in the x-coordinate of this box
    //  */
    // public Pool3D(double poolSize, double translateX) {
    //     super(poolSize, poolSize, poolSize);
    //     setMaterial(WATER);
    //     setTranslateX(translateX);
    //     generateTimelines();
    //     // play animations
    // }
    //
    // /**
    //  * Creates a 3D box to represent a pool in the simulation.
    //  *
    //  * @param width
    //  *         the width of the box
    //  * @param height
    //  *         the height of the box
    //  * @param depth
    //  *         the depth of the box
    //  * @param translateX
    //  *         the shift in the x-coordinate of this box
    //  */
    // public Pool3D(double width, double height, double depth, double translateX) {
    //     super(width, height, depth);
    //     setMaterial(WATER);
    //     setTranslateX(translateX);
    //     generateTimelines();
    //     // play animations
    // }

    // private void generateTimelines() {
    //
    //     for (int i = 0; i < MAX_ANIMATED_CREATURES; i++) {
    //         Timeline creatureTimeline = new Timeline();
    //         creatureTimeline.setCycleCount(Animation.INDEFINITE);
    //
    //         final double oneThird = 1.0 / 3;
    //         final double twoThirds = 2.0 / 3;
    //         final double throwawaySize = 20;
    //         final Box throwaway = new Box(throwawaySize, throwawaySize, throwawaySize);
    //         // throwaway.setVisible(false);
    //         KeyFrame startFrame = generateKeyFrame(0, throwaway);
    //         KeyFrame midFrame1 = generateKeyFrame(oneThird, throwaway);
    //         KeyFrame midFrame2 = generateKeyFrame(twoThirds, throwaway);
    //         KeyFrame endFrame = generateKeyFrame(1, throwaway);
    //
    //         // Object[] keyValues = startFrame.getValues().toArray();
    //         // KeyValue[] startFrameKeyValues = Arrays.copyOf(keyValues, keyValues.length, KeyValue[].class);
    //         // startFrameKeyValues[0].getTarget().setValue();
    //         // startFrame.getValues().clear();
    //         // startFrame.getValues().addAll(Arrays.asList(startFrameKeyValues));
    //
    //         creatureTimeline.getKeyFrames().addAll(startFrame, midFrame1, midFrame2, endFrame);
    //         timelines.add(creatureTimeline);
    //     }
    // }

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
        Duration duration;
        if (animationProgress == 0) {
            duration = Duration.ZERO;
        } else {
            duration = randomDuration(animationProgress);
        }

        if (animationProgress == 0 || animationProgress == 1) {
            return new KeyFrame(duration,
                                new KeyValue(target.translateXProperty(), target.getTranslateX()),
                                new KeyValue(target.translateYProperty(), target.getTranslateY()),
                                new KeyValue(target.translateZProperty(), target.getTranslateZ()));
        } else {
            return new KeyFrame(duration, new KeyValue(target.translateXProperty(),
                                                       getTranslateX() + randomPoint(getWidth())),
                                new KeyValue(target.translateYProperty(),
                                             getTranslateY() + randomPoint(getHeight())),
                                new KeyValue(target.translateZProperty(),
                                             getTranslateZ() + randomPoint(getDepth())));
        }
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

    // public Timeline getOneTimeline(int index) {
    //     try {
    //         return timelines.get(index);
    //     } catch (IndexOutOfBoundsException e) {
    //         System.err.println("The requested index does not exist");
    //         return null;
    //     }
    // }
}
