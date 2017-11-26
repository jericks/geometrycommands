package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.ScaleCommand.ScaleOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that performs the scale affine transformation on the input Geometry.
 * @author Jared Erickson
 */
public class ScaleCommand extends GeometryCommand<ScaleOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "scale";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a new geometry by scaling the input geometry";
    }

    /**
     * Get a new ScaleOptions
     * @return The new ScaleOptions
     */
    @Override
    public ScaleOptions getOptions() {
        return new ScaleOptions();
    }

    /**
     * Perform the scale affine transformation on the input Geometry.
     * @param geometry The input Geometry
     * @param options The ScaleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ScaleOptions options, Reader reader, Writer writer) throws Exception {
        AffineTransformation transformation;
        if (!Double.isNaN(options.getX()) && !Double.isNaN(options.getY())) {
            transformation = AffineTransformation.scaleInstance(options.getXScale(), options.getYScale(), options.getX(), options.getY());
        } else {
            transformation = AffineTransformation.scaleInstance(options.getXScale(), options.getYScale());
        }
        Geometry scaledGeometry = transformation.transform(geometry);
        writer.write(writeGeometry(scaledGeometry, options));
    }

    /**
     * The ScaleOptions
     */
    public static class ScaleOptions extends GeometryOptions {

        /**
         * The value to scale by in the x direction
         */
        @Option(name = "-s", aliases = "--xscale", usage = "The value to scale by in the x direction", required = true)
        private double xScale;

        /**
         * The value to scale by in the y direction
         */
        @Option(name = "-t", aliases = "--yscale", usage = "The value to scale by in the y direction", required = true)
        private double yScale;

        /**
         * The x-ordinate of the point to scale around
         */
        @Option(name = "-x", aliases = "--xcoordinate", usage = "The x-ordinate of the point to scale around", required = false)
        private double x = Double.NaN;

        /**
         * The y-ordinate of the point to scale around
         */
        @Option(name = "-y", aliases = "--ycoordinate", usage = "The y-ordinate of the point to scale around", required = false)
        private double y = Double.NaN;

        /**
         * Get the value to scale by in the x direction
         * @return The value to scale by in the x direction
         */
        public double getXScale() {
            return xScale;
        }

        /**
         * Set the value to scale by in the x direction
         * @param xScale The value to scale by in the x direction
         */
        public void setXScale(double xScale) {
            this.xScale = xScale;
        }

        /**
         * Get the value to translate by in the y direction
         * @return The value to scale by in the y direction
         */
        public double getYScale() {
            return yScale;
        }

        /**
         * Set the value to scale by in the y direction
         * @param yScale The value to scale by in the y direction
         */
        public void setYScale(double yScale) {
            this.yScale = yScale;
        }

        /**
         * Get the x-ordinate of the point to scale around
         * @return The x-ordinate of the point to scale around
         */
        public double getX() {
            return x;
        }

        /**
         * Set the x-ordinate of the point to scale around
         * @param x The x-ordinate of the point to scale around
         */
        public void setX(double x) {
            this.x = x;
        }

        /**
         * Get the y-ordinate of the point to scale around
         * @return The y-ordinate of the point to scale around
         */
        public double getY() {
            return y;
        }

        /**
         * Set the y-ordinate of the point to scale around
         * @param y The y-ordinate of the point to scale around
         */
        public void setY(double y) {
            this.y = y;
        }
    }
}
