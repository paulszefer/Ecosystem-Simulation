package io.github.paulszefer.gui.animation;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.GUI;
import io.github.paulszefer.gui.animation.handler.AnimationKeyHandler;
import io.github.paulszefer.gui.animation.handler.AnimationMouseHandler;
import io.github.paulszefer.gui.animation.handler.AnimationScrollHandler;
import io.github.paulszefer.sim.Ecosystem;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Defines the SubScene that will display and handle the animation for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationPane extends SubScene {

    /** The proportion of the GUI's height taken by this SubScene. */
    public static final double PROPORTION = 0.75;

    /** Random number generator. */
    private static final Random GENERATOR = new Random();

    /** The minimum length of a creature's animation timeline. */
    private static final double CREATURE_ANIMATION_DURATION = 6000;

    /** The possible amount of variance in the durations of key frames in a creature's animation. */
    private static final double CREATURE_ANIMATION_DURATION_VARIANCE =
            CREATURE_ANIMATION_DURATION / 3;

    /** The simulation controller. */
    private SimulationController controller;

    /** The root group of this subscene. */
    private final Group root;

    /** The group containing all objects to be rendered. */
    private final Xform world = new Xform();

    /** The group containing all pool objects. */
    private final Xform poolGroup = new Xform();

    /** A construct that organizes 3D transformations. */
    private final Camera3D camera3D;

    /**
     * Creates the GUI pane that will display the animation for the simulation.
     *
     * @param controller
     *         the simulation controller
     */
    public AnimationPane(SimulationController controller) {

        super(new Group(), GUI.WIDTH, GUI.HEIGHT * PROPORTION, true, SceneAntialiasing.BALANCED);
        this.controller = controller;

        // Required due to the necessary super() call before the group can be made
        root = (Group) getRoot();
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        // TODO - extract into own class
        // TODO - this class should receive the update event as well to update the pools
        buildPools();

        camera3D = new Camera3D(root);

        setCamera(camera3D.getCamera());
        setFill(Color.LIGHTSKYBLUE);
        setFocusTraversable(true);
        setFocused(true);

        AnimationMouseHandler mouseHandler = new AnimationMouseHandler(this, camera3D);
        AnimationScrollHandler scrollHandler = new AnimationScrollHandler(camera3D);
        AnimationKeyHandler keyHandler = new AnimationKeyHandler(camera3D);

        setOnMousePressed(mouseHandler);
        setOnMouseDragged(mouseHandler);
        setOnScroll(scrollHandler);
        setOnKeyPressed(keyHandler);
    }

    // TODO - extract
    private void buildPools() {

        final PhongMaterial water = new PhongMaterial();
        water.setDiffuseColor(new Color(0, 0, 0.5, 0.4));
        water.setSpecularColor(Color.TRANSPARENT);

        // TODO - make creature colour based on health coefficient (red <-> green)
        // TODO - Color(1, 0.5, 0.5, 1) to Color(0.5, 1, 0.5, 1) possibly
        final PhongMaterial creatureMaterial = new PhongMaterial();
        creatureMaterial.setDiffuseColor(Color.WHITE);
        creatureMaterial.setSpecularColor(Color.WHITE);

        List<Group> pools = new ArrayList<>();
        int numberOfPools = 3;
        for (int i = 0; i < numberOfPools; i++) {

            Group poolContainer = new Group();

            // create pool
            Box pool = new Box(100, 100, 100);
            pool.setMaterial(water);
            pool.setTranslateX((i - 1) * pool.getWidth() * 1.5);

            // create creatures
            int numberOfCreatures = 200; // TODO - make pool.getPopulation();
            for (int j = 0; j < numberOfCreatures; j++) {
                Sphere creature = new Sphere(pool.getWidth() / 50);
                creature.setMaterial(creatureMaterial);
                creature.setTranslateX(
                        pool.getTranslateX() + (Math.random() * pool.getWidth() * 0.96
                                                - pool.getWidth() * 0.48));
                creature.setTranslateY(
                        pool.getTranslateY() + (Math.random() * pool.getHeight() * 0.96
                                                - pool.getHeight() * 0.48));
                creature.setTranslateZ(
                        pool.getTranslateZ() + (Math.random() * pool.getDepth() * 0.96
                                                - pool.getDepth() * 0.48));

                // TODO - extract animation
                Timeline timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);

                List<KeyFrame> keyFrames = new ArrayList<>();
                keyFrames.addAll(Arrays.asList(new KeyFrame(Duration.ZERO, new KeyValue(
                                                       creature.translateXProperty(), creature.getTranslateX()), new KeyValue(
                                                       creature.translateYProperty(), creature.getTranslateY()), new KeyValue(
                                                       creature.translateZProperty(), creature.getTranslateZ())), new KeyFrame(
                                                       new Duration(Math.random() * CREATURE_ANIMATION_DURATION / 3
                                                                    + CREATURE_ANIMATION_DURATION / 3),
                                                       new KeyValue(creature.translateXProperty(),
                                                                    pool.getTranslateX() + (Math.random() * pool.getWidth() * 0.96
                                                                                            - pool.getWidth() * 0.48)),
                                                       new KeyValue(creature.translateYProperty(),
                                                                    pool.getTranslateY() + (Math.random() * pool.getHeight() * 0.96
                                                                                            - pool.getHeight() * 0.48)),
                                                       new KeyValue(creature.translateZProperty(),
                                                                    pool.getTranslateZ() + (Math.random() * pool.getDepth() * 0.96
                                                                                            - pool.getDepth() * 0.48))),
                                               new KeyFrame(new Duration(
                                                       Math.random() * CREATURE_ANIMATION_DURATION
                                                       / 3 + CREATURE_ANIMATION_DURATION * 2 / 3),
                                                            new KeyValue(
                                                                    creature.translateXProperty(),
                                                                    pool.getTranslateX() + (
                                                                            Math.random() * pool
                                                                                    .getWidth()
                                                                            * 0.96 - pool.getWidth()
                                                                                     * 0.48)),
                                                            new KeyValue(
                                                                    creature.translateYProperty(),
                                                                    pool.getTranslateY() + (
                                                                            Math.random() * pool
                                                                                    .getHeight()
                                                                            * 0.96
                                                                            - pool.getHeight()
                                                                              * 0.48)),
                                                            new KeyValue(
                                                                    creature.translateZProperty(),
                                                                    pool.getTranslateZ() + (
                                                                            Math.random() * pool
                                                                                    .getDepth()
                                                                            * 0.96 - pool.getDepth()
                                                                                     * 0.48))),
                                               new KeyFrame(new Duration(
                                                       Math.random() * CREATURE_ANIMATION_DURATION
                                                       / 3 + CREATURE_ANIMATION_DURATION),
                                                            new KeyValue(
                                                                    creature.translateXProperty(),
                                                                    creature.getTranslateX()),
                                                            new KeyValue(
                                                                    creature.translateYProperty(),
                                                                    creature.getTranslateY()),
                                                            new KeyValue(
                                                                    creature.translateZProperty(),
                                                                    creature.getTranslateZ()))));
                timeline.getKeyFrames().addAll(keyFrames);
                timeline.play();
                poolContainer.getChildren().add(creature);
            }

            poolContainer.getChildren().add(pool);
            pools.add(poolContainer);
        }

        poolGroup.getChildren().addAll(pools);
        poolGroup.setVisible(true);
        world.getChildren().addAll(poolGroup);
    }

    /**
     * Reverses the order of the objects in the pool group. This is used to resolve a quirk
     * involving rotation and transparency in JavaFX.
     * <p>
     * Depending on the orientation of the objects in the scene, objects were not transparent. This
     * is due to the way that objects are rendered by JavaFX. For transparency to work properly,
     * objects must be added to the scene from innermost object to outermost object.
     */
    public void reversePoolGroupOrder() {

        int i = poolGroup.getChildren().size();
        while (i > 0) {
            poolGroup.getChildren().add(poolGroup.getChildren().remove(i - 1));
            i--;
        }
    }

    /**
     * Updates the foreground pane to the current simulation state.
     *
     * @param ecosystem
     *         the current simulation state
     */
    public void updateState(Ecosystem ecosystem) {

        // TODO - update pool 3D object
    }
}
