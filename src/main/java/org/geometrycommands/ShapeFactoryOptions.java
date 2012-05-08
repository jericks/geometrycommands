package org.geometrycommands;

import org.kohsuke.args4j.Option;

/**
 * The Options for the ShapeFactoryCommand
 * @author Jared Erickson
 */
public class ShapeFactoryOptions extends GeometryOptions {

    /**
     * The width
     */
    @Option(name = "-w", aliases = "--width", usage = "The width", required = false)
    private int width;

    /**
     * The height
     */
    @Option(name = "-h", aliases = "--height", usage = "The height", required = false)
    private int height;

    /**
     * The number of points
     */
    @Option(name = "-p", aliases = "--numberOfPoints", usage = "The number of points", required = false)
    private int numberOfPoints = 100;

    /**
     * The rotation
     */
    @Option(name = "-r", aliases = "--rotation", usage = "The rotation", required = false)
    private double rotation = 0.0;

    /**
     * The flag to use center (true) or the base (false).
     */
    @Option(name = "-c", aliases = "--center", usage = "The flag to use center (true) or the base (false)", required = false)
    private boolean center;

    /**
     * Get the flag to use center (true) or the base (false).
     * @return The flag to use center (true) or the base (false).
     */
    public boolean isCenter() {
        return center;
    }

    /**
     * Set the flag to use center (true) or the base (false).
     * @param center The flag to use center (true) or the base (false).
     */
    public void setCenter(boolean center) {
        this.center = center;
    }

    /**
     * Get the height
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the height
     * @param height The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the number of points
     * @return The number of points
     */
    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    /**
     * Set the number of points
     * @param numberOfPoints The number of points
     */
    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    /**
     * Get the rotation
     * @return The rotation
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Set the rotation
     * @param rotation The rotation
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Get the width
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the width
     * @param width The width
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
