package io.github.paulszefer.gui.animation;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

/**
 * A construct containing groups that handle each of X-Y translation, X-Y rotation, Z translation,
 * and Z rotation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Camera3D extends Xform {

    /** The default near clip of the camera. */
    public static final double NEAR_CLIP = 0.1;

    /** The default far clip of the camera. */
    public static final double FAR_CLIP = 10000.0;

    /** The default x rotation of the camera. */
    public static final double INITIAL_X_ANGLE = 0;

    /** The default y rotation of the camera. */
    public static final double INITIAL_Y_ANGLE = 0;

    /** The default x translation of the camera. */
    public static final double INITIAL_X = 0;

    /** The default y translation of the camera. */
    public static final double INITIAL_Y = -20;

    /** The default z translation of the camera. */
    public static final double INITIAL_Z = -450;

    /** The camera for this subscene. */
    private final PerspectiveCamera camera = new PerspectiveCamera(true);

    /** The group that performs X-Y rotation. */
    private final Xform cameraXYRotation = new Xform();

    /** The group that performs X-Y translation. */
    private final Xform cameraXYTranslation = new Xform();

    /** The group that performs Z rotation. */
    private final Xform cameraZRotation = new Xform();

    /**
     * Creates the construct for organizing 3D transformations.
     *
     * @param root
     *         the root group of the 3D scene
     */
    public Camera3D(Group root) {

        final double halfAngle = 180;

        root.getChildren().add(cameraXYRotation);
        cameraXYRotation.getChildren().add(cameraXYTranslation);
        cameraXYTranslation.getChildren().add(cameraZRotation);
        cameraZRotation.getChildren().add(camera);
        cameraZRotation.setRotateZ(halfAngle);

        camera.setNearClip(NEAR_CLIP);
        camera.setFarClip(FAR_CLIP);
        camera.setTranslateZ(INITIAL_Z);
        cameraXYRotation.getRx().setAngle(INITIAL_X_ANGLE);
        cameraXYRotation.getRy().setAngle(INITIAL_Y_ANGLE);
        cameraXYTranslation.setTx(INITIAL_X);
        cameraXYTranslation.setTy(INITIAL_Y);
    }

    /**
     * Returns the camera. This is used for Z translation.
     *
     * @return the camera
     */
    public PerspectiveCamera getCamera() {
        return camera;
    }

    /**
     * Returns the group that performs X-Y rotation.
     *
     * @return the group that performs X-Y rotation
     */
    public Xform getCameraXYRotation() {
        return cameraXYRotation;
    }

    /**
     * Returns the group that performs X-Y translation.
     *
     * @return the group that performs X-Y translation
     */
    public Xform getCameraXYTranslation() {
        return cameraXYTranslation;
    }

    /**
     * Returns the group that performs Z rotation.
     *
     * @return the group that performs Z rotation
     */
    public Xform getCameraZRotation() {
        return cameraZRotation;
    }
}
