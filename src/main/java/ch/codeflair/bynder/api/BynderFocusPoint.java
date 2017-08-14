package ch.codeflair.bynder.api;
/**
 * Copyright 2017 codeflair Gmbh (http://www.codeflair.ch)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.util.Map;

/**
 * Java bean for focus point data
 * Created by benzahler on 04.07.17.
 */
public class BynderFocusPoint {

    public static final String BYNDER_KEY_X = "x";
    public static final String BYNDER_KEY_Y = "y";
    private double x;
    private double y;
    private int imageWidth;
    private int imageHeight;

    /**
     * create a new focus point object
     *
     * @param x           x-coordinate of the focus point
     * @param y           y-coordinate of the focus point
     * @param imageWidth  total width of the image
     * @param imageHeight total height of the image
     */
    public BynderFocusPoint(double x, double y, int imageWidth, int imageHeight) {
        this.x = x;
        this.y = y;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    /**
     * @return the x-coordinate of the focus point
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y-coordinate of the focus point
     */
    public double getY() {
        return y;
    }

    /**
     * @return the total image with of the original image
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * @return the total image height of the original image
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * factory method to create a focus point object.
     *
     * @param focusPointData bynder focus point data, must contain keys 'x' and 'y'
     * @param width          total image width of the original image
     * @param height         total image height of the original image
     * @return the focus point object or null if @param focusPointData is invalid
     */
    public static BynderFocusPoint createFocusPoint(Map<String, Double> focusPointData, int width, int height) {
        if (focusPointData == null || !focusPointData.containsKey(BYNDER_KEY_X) || !focusPointData.containsKey
                (BYNDER_KEY_Y)) {
            return null;
        }
        return new BynderFocusPoint(focusPointData.get(BYNDER_KEY_X), focusPointData.get(BYNDER_KEY_Y), width, height);

    }
}
