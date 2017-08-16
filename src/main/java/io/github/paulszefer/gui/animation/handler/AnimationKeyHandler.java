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
