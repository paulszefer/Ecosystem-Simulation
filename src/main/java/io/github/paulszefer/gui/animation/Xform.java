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
package io.github.paulszefer.gui.animation;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * A specialized Group that simplifies transformation of 3D JavaFX objects.
 * <p>
 * The beginning template of this file was retrieved from the JavaFX online documentation. A copy of
 * the beginning template can be found here:
 * <p>
 * http://docs.oracle.com/javase/8/javafx/graphics-tutorial/sampleapp3d-code.htm#CJAGGIFG
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Xform extends Group {

    // TODO - currently unused, implement or remove
    // public enum RotateOrder {
    //     XYZ,
    //     XZY,
    //     YXZ,
    //     YZX,
    //     ZXY,
    //     ZYX
    // }

    /** The translation of the transformation. */
    private Translate translate = new Translate();

    /** The pivot point of the transformation. */
    private Translate pivot = new Translate();

    /** The intersection point of the transformation. */
    private Translate intersection = new Translate();

    /** The rotation around the x-axis of the transformation. */
    private Rotate rx = new Rotate();

    {
        rx.setAxis(Rotate.X_AXIS);
    }

    /** The rotation around the y-axis of the transformation. */
    private Rotate ry = new Rotate();

    {
        ry.setAxis(Rotate.Y_AXIS);
    }

    /** The rotation around the z-axis of the transformation. */
    private Rotate rz = new Rotate();

    {
        rz.setAxis(Rotate.Z_AXIS);
    }

    /** The scale of the transformation. */
    private Scale scale = new Scale();

    /** Creates the Xform with its transformation properties set. */
    public Xform() {

        super();
        getTransforms().addAll(translate, rz, ry, rx, scale);
    }

    // TODO - currently unused, implement or remove
    // public Xform(RotateOrder rotateOrder) {
    //
    //     super();
    //     // choose the order of rotations based on the rotateOrder
    //     switch (rotateOrder) {
    //         case XYZ:
    //             getTransforms().addAll(translate, pivot, rz, ry, rx, scale, intersection);
    //             break;
    //         case YXZ:
    //             getTransforms().addAll(translate, pivot, rz, rx, ry, scale, intersection);
    //             break;
    //         case YZX:
    //             // For Camera
    //             getTransforms().addAll(translate, pivot, rx, rz, ry, scale, intersection);
    //             break;
    //         case ZXY:
    //             getTransforms().addAll(translate, pivot, ry, rx, rz, scale, intersection);
    //             break;
    //         case ZYX:
    //             getTransforms().addAll(translate, pivot, rx, ry, rz, scale, intersection);
    //             break;
    //     }
    // }

    /**
     * Returns the translation.
     *
     * @return the translation
     */
    public Translate getTranslate() {

        return translate;
    }

    /**
     * Sets the x-, y-, and z-coordinates of the translation.
     *
     * @param x
     *         the new x-coordinate
     * @param y
     *         the new y-coordinate
     * @param z
     *         the new z-coordinate
     */
    public void setTranslate(double x, double y, double z) {

        translate.setX(x);
        translate.setY(y);
        translate.setZ(z);
    }

    /**
     * Sets the x- and y-coordinates of the translation.
     *
     * @param x
     *         the new x-coordinate
     * @param y
     *         the new y-coordinate
     */
    public void setTranslate(double x, double y) {

        translate.setX(x);
        translate.setY(y);
    }

    // Cannot override these methods as they are final:
    // public void setTranslateX(double x) { translate.setX(x); }
    // public void setTranslateY(double y) { translate.setY(y); }
    // public void setTranslateZ(double z) { translate.setZ(z); }

    /**
     * Sets the x-coordinate of the translation.
     *
     * @param x
     *         the new x-coordinate
     */
    // Use these methods instead:
    public void setTx(double x) {

        translate.setX(x);
    }

    /**
     * Sets the y-coordinate of the translation.
     *
     * @param y
     *         the new y-coordinate
     */
    public void setTy(double y) {

        translate.setY(y);
    }

    /**
     * Sets the z-coordinate of the translation.
     *
     * @param z
     *         the new z-coordinate
     */
    public void setTz(double z) {

        translate.setZ(z);
    }

    /**
     * Sets the rotation angle around the x-, y- and z-axes.
     *
     * @param x
     *         the new rotation angle around the x-axis
     * @param y
     *         the new rotation angle around the y-axis
     * @param z
     *         the new rotation angle around the z-axis
     */
    public void setRotate(double x, double y, double z) {

        rx.setAngle(x);
        ry.setAngle(y);
        rz.setAngle(z);
    }

    /**
     * Sets the rotation angle around the x-axis.
     *
     * @param x
     *         the new rotation angle
     */
    public void setRotateX(double x) {

        rx.setAngle(x);
    }

    /**
     * Sets the rotation angle around the y-axis.
     *
     * @param y
     *         the new rotation angle
     */
    public void setRotateY(double y) {

        ry.setAngle(y);
    }

    /**
     * Sets the rotation angle around the z-axis.
     *
     * @param z
     *         the new rotation angle
     */
    public void setRotateZ(double z) {

        rz.setAngle(z);
    }

    /**
     * Returns the rotation angle around the x-axis.
     *
     * @return the rotation angle around the x-axis
     */
    public Rotate getRx() {

        return rx;
    }

    /**
     * Sets the rotation angle around the x-axis.
     *
     * @param x
     *         the new rotation angle
     */
    public void setRx(double x) {

        rx.setAngle(x);
    }

    /**
     * Returns the rotation angle around the y-axis.
     *
     * @return the rotation angle around the y-axis
     */
    public Rotate getRy() {

        return ry;
    }

    /**
     * Sets the rotation angle around the y-axis.
     *
     * @param y
     *         the new rotation angle
     */
    public void setRy(double y) {

        ry.setAngle(y);
    }

    /**
     * Returns the rotation angle around the z-axis.
     *
     * @return the rotation angle around the z-axis
     */
    public Rotate getRz() {

        return rz;
    }

    /**
     * Sets the rotation angle around the z-axis.
     *
     * @param z
     *         the new rotation angle
     */
    public void setRz(double z) {

        rz.setAngle(z);
    }

    /**
     * Returns the scale.
     *
     * @return the scale
     */
    public Scale getScale() {

        return scale;
    }

    /**
     * Sets the scale.
     *
     * @param scaleFactor
     *         the new scale
     */
    public void setScale(double scaleFactor) {

        scale.setX(scaleFactor);
        scale.setY(scaleFactor);
        scale.setZ(scaleFactor);
    }

    // Cannot override these methods as they are final:
    // public void setScaleX(double x) { scale.setX(x); }
    // public void setScaleY(double y) { scale.setY(y); }
    // public void setScaleZ(double z) { scale.setZ(z); }
    // Use these methods instead:

    /**
     * Sets the x coordinate of the scale.
     *
     * @param x
     *         the x-coordinate of the new pivot point
     */
    public void setSx(double x) {

        scale.setX(x);
    }

    /**
     * Sets the y coordinate of the scale.
     *
     * @param y
     *         the y-coordinate of the new pivot point
     */
    public void setSy(double y) {

        scale.setY(y);
    }

    /**
     * Sets the z coordinate of the scale.
     *
     * @param z
     *         the z-coordinate of the new pivot point
     */
    public void setSz(double z) {

        scale.setZ(z);
    }

    /**
     * Returns the pivot.
     *
     * @return the pivot
     */
    public Translate getPivot() {

        return pivot;
    }

    /**
     * Sets this group's pivot point.
     *
     * @param x
     *         the x-coordinate of the new pivot point
     * @param y
     *         the y-coordinate of the new pivot point
     * @param z
     *         the z-coordinate of the new pivot point
     */
    public void setPivot(double x, double y, double z) {

        pivot.setX(x);
        pivot.setY(y);
        pivot.setZ(z);
        intersection.setX(-x);
        intersection.setY(-y);
        intersection.setZ(-z);
    }

    /** Resets this group's translate, rotation, scale, pivot and intersection point properties. */
    public void reset() {

        translate.setX(0.0);
        translate.setY(0.0);
        translate.setZ(0.0);
        rx.setAngle(0.0);
        ry.setAngle(0.0);
        rz.setAngle(0.0);
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
        pivot.setX(0.0);
        pivot.setY(0.0);
        pivot.setZ(0.0);
        intersection.setX(0.0);
        intersection.setY(0.0);
        intersection.setZ(0.0);
    }

    /** Resets this group's translate, scale, pivot and intersection point properties. */
    public void resetTSP() {

        translate.setX(0.0);
        translate.setY(0.0);
        translate.setZ(0.0);
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
        pivot.setX(0.0);
        pivot.setY(0.0);
        pivot.setZ(0.0);
        intersection.setX(0.0);
        intersection.setY(0.0);
        intersection.setZ(0.0);
    }

    /** Prints a string of this group's properties to the console. */
    public void debug() {

        System.out.println(
                "translate = (" + translate.getX() + ", " + translate.getY() + ", " + translate
                        .getZ() + ")  " + "r = (" + rx.getAngle() + ", " + ry.getAngle() + ", " + rz
                        .getAngle() + ")  " + "scale = (" + scale.getX() + ", " + scale.getY()
                + ", " + scale.getZ() + ")  " + "pivot = (" + pivot.getX() + ", " + pivot.getY()
                + ", " + pivot.getZ() + ")  " + "intersection = (" + intersection.getX() + ", "
                + intersection.getY() + ", " + intersection.getZ() + ")");
    }
}