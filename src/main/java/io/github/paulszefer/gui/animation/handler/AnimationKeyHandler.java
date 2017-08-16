package io.github.paulszefer.gui.animation.handler;

import io.github.paulszefer.gui.animation.Camera3D;
import io.github.paulszefer.gui.animation.Xform;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * A handler for all key events occurring in the animation subscene.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationKeyHandler implements EventHandler<KeyEvent> {

    /** A construct that organizes 3D transformations. */
    private Camera3D camera3D;

    /**
     * Creates the handler for all key events occurring in the animation subscene.
     *
     * @param camera3D
     *         a construct for organizing 3D transformations
     */
    public AnimationKeyHandler(Camera3D camera3D) {
        this.camera3D = camera3D;
    }

    /**
     * Invoked when this handler receives a key event.
     *
     * @param event
     *         the key event which occurred
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            // Reset camera view
            if (event.getCode() == KeyCode.R) {
                PerspectiveCamera camera = camera3D.getCamera();
                Xform cameraXYRotation = camera3D.getCameraXYRotation();
                Xform cameraXYTranslation = camera3D.getCameraXYTranslation();
                camera.setTranslateX(0);
                camera.setTranslateY(0);
                camera.setTranslateZ(Camera3D.INITIAL_Z);
                cameraXYRotation.getRx().setAngle(Camera3D.INITIAL_X_ANGLE);
                cameraXYRotation.getRy().setAngle(Camera3D.INITIAL_Y_ANGLE);
                cameraXYTranslation.getTranslate().setX(Camera3D.INITIAL_X);
                cameraXYTranslation.getTranslate().setY(Camera3D.INITIAL_Y);
            }
        }

    }
}
