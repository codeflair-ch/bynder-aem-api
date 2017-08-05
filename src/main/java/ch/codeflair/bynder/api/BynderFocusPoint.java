package ch.codeflair.bynder.api;

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
