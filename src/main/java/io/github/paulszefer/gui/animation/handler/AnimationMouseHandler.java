/*
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package io.github.paulszefer.gui.animation.handler;

import io.github.paulszefer.gui.animation.AnimationSubScene;
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
    private final AnimationSubScene subScene;

    /** A construct that organizes 3D transformations. */
    private final Camera3D camera3D;

    /** Stores the current x-coordinate of a pressed or dragged mouse. */
    private double mousePosX;

    /** Stores the current y-coordinate of a pressed or dragged mouse. */
    private double mousePosY;

    /**
     * Creates the handler for all mouse events occurring in the animation subscene.
     *
     * @param subScene
     *         the subscene in which the animation is occurring
     * @param camera3D
     *         A construct that organizes 3D transformations.
     */
    public AnimationMouseHandler(AnimationSubScene subScene, Camera3D camera3D) {
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
                updateTransparency();
            } else if (event.isSecondaryButtonDown()) {
                PerspectiveCamera camera = camera3D.getCamera();
                camera.setTranslateX(
                        camera.getTranslateX() + -mouseDeltaX * MOUSE_SPEED * PAN_FACTOR);
                camera.setTranslateY(
                        camera.getTranslateY() + -mouseDeltaY * MOUSE_SPEED * PAN_FACTOR);

                /*
                   This makes the camera rotate around the viewed point instead of a locked
                   position. It also makes it easier to view outer pools, but requires precise
                   aim onto outer pools to not experience strange control behaviour.
                   This may also may cause problems with the camera reset key.
                */

                // Xform cameraXYTranslation = camera3D.getCameraXYTranslation();
                // cameraXYTranslation.setTranslateX(cameraXYTranslation.getTranslateX()
                // + mouseDeltaX * MOUSE_SPEED * PAN_FACTOR);
                // cameraXYTranslation.setTranslateY(cameraXYTranslation.getTranslateY()
                // + mouseDeltaY * MOUSE_SPEED * PAN_FACTOR);
            }
        }
    }

    /**
     * Updates the transparency of the 3D world. This is done by determining which 180 degree
     * portion the camera is currently looking from. If necessary, the order of the objects in the
     * Ecosystem3D group is reversed.
     * <p>
     * Refer to {@linkplain AnimationSubScene#reversePoolGroupOrder() reversePoolGroupOrder()'s}
     * documentation for an explanation on why this reversion is necessary.
     */
    public void updateTransparency() {
        Xform cameraXYRotation = camera3D.getCameraXYRotation();
        final int fullAngle = 360;
        final int halfAngle = 180;
        final boolean reversed = subScene.getEcosystem3DNodesReversed();
        double reducedAngle = cameraXYRotation.getRy().getAngle() % fullAngle;
        if (reversed && (reducedAngle < -halfAngle
                         || reducedAngle > 0 && reducedAngle < halfAngle)) {
            subScene.reversePoolGroupOrder();
            subScene.setEcosystem3DNodesReversed(false);
        } else if (!reversed && (reducedAngle > -halfAngle && reducedAngle < 0
                                 || reducedAngle > halfAngle)) {
            subScene.reversePoolGroupOrder();
            subScene.setEcosystem3DNodesReversed(true);
        }
    }
}
