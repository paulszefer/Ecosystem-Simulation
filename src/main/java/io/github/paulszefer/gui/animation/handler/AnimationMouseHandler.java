package io.github.paulszefer.gui.animation.handler;

import io.github.paulszefer.gui.animation.AnimationPane;
import io.github.paulszefer.gui.animation.Camera3D;
import io.github.paulszefer.gui.animation.Xform;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.MouseEvent;

/**
 * A handler for all mouse events occurring in the animation subscene.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationMouseHandler implements EventHandler<MouseEvent> {

    /** The factor by which transformations are scaled if the Control key is pressed. */
    private static final double CONTROL_MULTIPLIER = 0.1;

    /** The factor by which transformations are scaled if the Shift key is pressed. */
    private static final double SHIFT_MULTIPLIER = 10.0;

    /** The factor by which all transformations are scaled. */
    private static final double MOUSE_SPEED = 0.1;

    /** The factor by which all translations are scaled. */
    private static final double PAN_FACTOR = 8;

    /** The factor by which all rotations are scaled. */
    private static final double ROTATION_SPEED = 2.0;

    /** The animation subscene utilizing this handler. */
    private final AnimationPane subScene;

    /** A construct that organizes 3D transformations. */
    private final Camera3D camera3D;

    /** Stores the current x-coordinate of a pressed or dragged mouse. */
    private double mousePosX;

    /** Stores the current y-coordinate of a pressed or dragged mouse. */
    private double mousePosY;

    /**
     * Whether the objects in the scene have been reversed (stored in reverse order). This is used
     * to resolve a quirk involving rotation and transparency in JavaFX.
     * <p>
     * Depending on the orientation of the objects in the scene, objects were not transparent. This
     * is due to the way that objects are rendered by JavaFX. For transparency to work properly,
     * objects must be added to the scene from innermost object to outermost object.
     */
    private boolean poolGroupOrderReversed;

    /**
     * Creates the handler for all mouse events occurring in the animation subscene.
     *
     * @param subScene
     *         the subscene in which the animation is occurring
     * @param camera3D
     *         A construct that organizes 3D transformations.
     */
    public AnimationMouseHandler(AnimationPane subScene, Camera3D camera3D) {
        this.subScene = subScene;
        this.camera3D = camera3D;
    }

    /**
     * Invoked when this handler receives a mouse event.
     *
     * @param event
     *         the mouse event which occurred
     */
    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            // double mouseOldX = mousePosX;
            // double mouseOldY = mousePosY;
            // mousePosX = event.getSceneX();
            // mousePosY = event.getSceneY();
            // double mouseDeltaX = (mousePosX - mouseOldX);
            // double mouseDeltaY = (mousePosY - mouseOldY);

            double mouseDeltaX = (event.getSceneX() - mousePosX);
            double mouseDeltaY = (event.getSceneY() - mousePosY);
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();

            double modifier = 1;

            if (event.isControlDown()) {
                modifier = CONTROL_MULTIPLIER;
            }
            if (event.isShiftDown()) {
                modifier = SHIFT_MULTIPLIER;
            }
            if (event.isPrimaryButtonDown()) {
                Xform cameraXYRotation = camera3D.getCameraXYRotation();
                cameraXYRotation.setRx(cameraXYRotation.getRx().getAngle()
                                       + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);
                cameraXYRotation.setRy(cameraXYRotation.getRy().getAngle()
                                       - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED);
                final int fullAngle = 360;
                final int halfAngle = 180;
                double reducedAngle = cameraXYRotation.getRy().getAngle() % fullAngle;
                if (!poolGroupOrderReversed && (reducedAngle < -halfAngle
                                                || reducedAngle > 0 && reducedAngle < halfAngle)) {
                    subScene.reversePoolGroupOrder();
                    poolGroupOrderReversed = true;
                } else if (poolGroupOrderReversed && (reducedAngle > -halfAngle && reducedAngle < 0
                                                      || reducedAngle > halfAngle)) {
                    subScene.reversePoolGroupOrder();
                    poolGroupOrderReversed = false;
                }
            } else if (event.isSecondaryButtonDown()) {
                PerspectiveCamera camera = camera3D.getCamera();
                camera.setTranslateX(
                        camera.getTranslateX() + -mouseDeltaX * MOUSE_SPEED * PAN_FACTOR);
                camera.setTranslateY(
                        camera.getTranslateY() + -mouseDeltaY * MOUSE_SPEED * PAN_FACTOR);

                // makes the camera rotate around the viewed point instead of a locked position
                // makes it easier to view outer pools, but requires precise aim onto outer pools
                // to not experience strange control behaviour
                // also may cause problems with camera reset key
                // Xform cameraXYTranslation = camera3D.getCameraXYTranslation();
                // cameraXYTranslation.setTranslateX(cameraXYTranslation.getTranslateX()
                // + mouseDeltaX * MOUSE_SPEED * PAN_FACTOR);
                // cameraXYTranslation.setTranslateY(cameraXYTranslation.getTranslateY()
                // + mouseDeltaY * MOUSE_SPEED * PAN_FACTOR);
            }
        }
    }
}
