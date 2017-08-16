package io.github.paulszefer.gui.animation.handler;

import io.github.paulszefer.gui.animation.Camera3D;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

/**
 * A handler for all scroll events occurring in the animation subscene.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class AnimationScrollHandler implements EventHandler<ScrollEvent> {

    /** The factor by which all transformations are scaled. */
    private static final double MOUSE_SPEED = 2;

    /** A construct that organizes 3D transformations. */
    private final Camera3D camera3D;

    /**
     * Creates the handler for all scroll events occurring in the animation subscene.
     *
     * @param camera3D
     *         A construct that organizes 3D transformations.
     */
    public AnimationScrollHandler(Camera3D camera3D) {
        this.camera3D = camera3D;
    }

    /**
     * Invoked when this handler receives a scroll event.
     *
     * @param event
     *         the scroll event which occurred
     */
    @Override
    public void handle(ScrollEvent event) {
        if (event.getEventType() == ScrollEvent.SCROLL) {
            double z = camera3D.getCamera().getTranslateZ();
            // TODO - reverse on mac
            double newZ = z - event.getDeltaY() * MOUSE_SPEED;
            camera3D.getCamera().setTranslateZ(newZ);
        }
    }
}
