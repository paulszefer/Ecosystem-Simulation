package io.github.paulszefer.gui.animation;

import io.github.paulszefer.SimulationController;
import io.github.paulszefer.gui.GUI;
import io.github.paulszefer.gui.animation.handler.AnimationKeyHandler;
import io.github.paulszefer.gui.animation.handler.AnimationMouseHandler;
import io.github.paulszefer.gui.animation.handler.AnimationScrollHandler;
import io.github.paulszefer.sim.Ecosystem;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;

/**
 * Defines the SubScene that will display and handle the animation for the simulation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationSubScene extends SubScene {

    /** The proportion of the GUI's height taken by this SubScene. */
    public static final double PROPORTION = 0.75;

    /** The simulation controller. */
    private SimulationController controller;

    /** The root group of this subscene. */
    private final Group root;

    /** The group containing all 3D ecosystem objects. */
    private Ecosystem3D ecosystem3D = new Ecosystem3D();

    /** A construct that organizes 3D transformations. */
    private final Camera3D camera3D;

    /**
     * Whether the objects in the scene have been reversed (stored in reverse order). This is used
     * to resolve a quirk involving rotation and transparency in JavaFX.
     * <p>
     * Depending on the orientation of the objects in the scene, objects were not transparent. This
     * is due to the way that objects are rendered by JavaFX. For transparency to work properly,
     * objects must be added to the scene from innermost object to outermost object.
     */
    private boolean ecosystem3DNodesReversed;

    /**
     * Creates the SubScene that will display and handle the animation for the simulation.
     *
     * @param controller
     *         the simulation controller
     */
    public AnimationSubScene(SimulationController controller) {

        super(new Group(), GUI.WIDTH, GUI.HEIGHT * PROPORTION, true, SceneAntialiasing.BALANCED);
        this.controller = controller;

        // Required due to the necessary super() call before the group can be made
        root = (Group) getRoot();
        root.getChildren().add(ecosystem3D);
        root.setDepthTest(DepthTest.ENABLE);

        camera3D = new Camera3D(root);

        setCamera(camera3D.getCamera());
        setFill(Color.LIGHTSKYBLUE);

        AnimationMouseHandler mouseHandler = new AnimationMouseHandler(this, camera3D);
        AnimationScrollHandler scrollHandler = new AnimationScrollHandler(camera3D);
        AnimationKeyHandler keyHandler = new AnimationKeyHandler(camera3D);

        setOnMousePressed(mouseHandler);
        setOnMouseDragged(mouseHandler);
        setOnScroll(scrollHandler);
        setOnKeyPressed(keyHandler);
    }

    /**
     * Returns a boolean representing whether the order of the nodes in ecosystem3D has been
     * reversed.
     *
     * @return true if they are reversed, false otherwise
     */
    public boolean getEcosystem3DNodesReversed() {
        return ecosystem3DNodesReversed;
    }

    /**
     * Sets whether the order of the nodes in ecosystem3D has been reversed.
     *
     * @param ecosystem3DNodesReversed
     *         true if they are reversed, false otherwise
     */
    public void setEcosystem3DNodesReversed(boolean ecosystem3DNodesReversed) {
        this.ecosystem3DNodesReversed = ecosystem3DNodesReversed;
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
        int i = ecosystem3D.getChildren().size();
        while (i > 0) {
            ecosystem3D.getChildren().add(ecosystem3D.getChildren().remove(i - 1));
            i--;
        }
    }

    /**
     * Updates the ecosystem group with the current simulation state.
     *
     * @param ecosystem
     *         the current simulation state
     */
    public void updateState(Ecosystem ecosystem) {
        ecosystem3D.update(ecosystem);
        if (ecosystem3DNodesReversed) {
            reversePoolGroupOrder();
            // ecosystem3DNodesReversed = false;
        }
    }
}
