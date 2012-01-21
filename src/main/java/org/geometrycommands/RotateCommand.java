package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.util.AffineTransformation;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.RotateCommand.RotateOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that performs the rotate affine transformation on the input Geometry.
 * @author Jared Erickson
 */
public class RotateCommand extends GeometryCommand<RotateOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "rotate";
    }

    /**
     * Get a new RotateOptions
     * @return The new RotateOptions
     */
    @Override
    public RotateOptions getOptions() {
        return new RotateOptions();
    }

    /**
     * Perform the rotate affine transformation on the input Geometry.
     * @param geometry The input Geometry
     * @param options The RotateOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, RotateOptions options, Reader reader, Writer writer) throws Exception {
        AffineTransformation transformation = null;
        // theta, x, y
        if (!Double.isNaN(options.getTheta()) && !Double.isNaN(options.getX()) && !Double.isNaN(options.getY())) {
            transformation = AffineTransformation.rotationInstance(options.getTheta(), options.getX(), options.getY());
        } // theta
        else if (!Double.isNaN(options.getTheta())) {
            transformation = AffineTransformation.rotationInstance(options.getTheta());
        } // sinTheta, cosTheta, x, y
        else if (!Double.isNaN(options.getSinTheta()) && !Double.isNaN(options.getCosTheta()) && !Double.isNaN(options.getX()) && !Double.isNaN(options.getY())) {
            transformation = AffineTransformation.rotationInstance(options.getSinTheta(), options.getCosTheta(), options.getX(), options.getY());
        } // sinTheta, cosTheta
        else if (!Double.isNaN(options.getSinTheta()) && !Double.isNaN(options.getCosTheta())) {
            transformation = AffineTransformation.rotationInstance(options.getSinTheta(), options.getCosTheta());
        } else {
            throw new IllegalArgumentException("Illegal combination of arguments (theta | theta,x,y | sinTheta, cosTheta, x, y | sinTheta, cosTheta");
        }
        Geometry rotatedGeometry = transformation.transform(geometry);
        writer.write(writeGeometry(rotatedGeometry, options));
    }

    /**
     * The RotateOptions
     */
    public static class RotateOptions extends GeometryOptions {

        /**
         * The rotation angle, in radians
         */
        @Option(name = "-theta", usage = "The rotation angle, in radians", required = false)
        private double theta = Double.NaN;

        /**
         * The x-ordinate of the rotation point
         */
        @Option(name = "-x", usage = "The x-ordinate of the rotation point", required = false)
        private double x = Double.NaN;

        /**
         * The y-ordinate of the rotation point
         */
        @Option(name = "-y", usage = "The y-ordinate of the rotation point", required = false)
        private double y = Double.NaN;

        /**
         * The sine of the rotation angle
         */
        @Option(name = "-sin", usage = "The sine of the rotation angle", required = false)
        private double sinTheta = Double.NaN;

        /**
         * The cosine of the rotation angle
         */
        @Option(name = "-cos", usage = "The cosine of the rotation angle", required = false)
        private double cosTheta = Double.NaN;

        /**
         * Get the cosine of the rotation angle
         * @return The cosine of the rotation angle
         */
        public double getCosTheta() {
            return cosTheta;
        }

        /**
         * Set the cosine of the rotation angle
         * @param cosTheta The cosine of the rotation angle
         */
        public void setCosTheta(double cosTheta) {
            this.cosTheta = cosTheta;
        }

        /**
         * Get the sine of the rotation angle
         * @return The sine of the rotation angle
         */
        public double getSinTheta() {
            return sinTheta;
        }

        /**
         * Set the sine of the rotation angle
         * @param sinTheta The sine of the rotation angle
         */
        public void setSinTheta(double sinTheta) {
            this.sinTheta = sinTheta;
        }

        /**
         * Get the rotation angle, in radians
         * @return The rotation angle, in radians
         */
        public double getTheta() {
            return theta;
        }

        /**
         * Set the rotation angle, in radians
         * @param theta The rotation angle, in radians
         */
        public void setTheta(double theta) {
            this.theta = theta;
        }

        /**
         * Get the x-ordinate of the rotation point
         * @return The x-ordinate of the rotation point
         */
        public double getX() {
            return x;
        }

        /**
         * Set the x-ordinate of the rotation point
         * @param x The x-ordinate of the rotation point
         */
        public void setX(double x) {
            this.x = x;
        }

        /**
         * Get the y-ordinate of the rotation point
         * @return The y-ordinate of the rotation point
         */
        public double getY() {
            return y;
        }

        /**
         * Set the y-ordinate of the rotation point
         * @param y The y-ordinate of the rotation point
         */
        public void setY(double y) {
            this.y = y;
        }
    }
}
