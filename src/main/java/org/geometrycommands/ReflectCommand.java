package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.util.AffineTransformation;
import org.geometrycommands.ReflectCommand.ReflectOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that performs the reflect affine transformation on the input Geometry.
 * @author Jared Erickson
 */
public class ReflectCommand extends GeometryCommand<ReflectOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "reflect";
    }

    /**
     * Get a new ReflectOptions
     * @return The new ReflectOptions
     */
    @Override
    public ReflectOptions getOptions() {
        return new ReflectOptions();
    }

    /**
     * Perform the reflect affine transformation on the input Geometry.
     * @param geometry The input Geometry
     * @param options The ReflectOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ReflectOptions options) throws Exception {
        AffineTransformation transformation;
        if (!Double.isNaN(options.getX1()) && !Double.isNaN(options.getY1())) {
            transformation = AffineTransformation.reflectionInstance(options.getX0(), options.getY0(), options.getX1(), options.getY1());
        } else {
            transformation = AffineTransformation.reflectionInstance(options.getX0(), options.getY0());
        }
        Geometry reflectedGeometry = transformation.transform(geometry);
        System.out.println(writeGeoemtry(reflectedGeometry, options));
    }

    /**
     * The ReflectOptions
     */
    public static class ReflectOptions extends GeometryOptions {

        /**
         * The x-ordinate of a point on the reflection line
         */
        @Option(name = "-x0", usage = "The x-ordinate of a point on the reflection line", required = true)
        private double x0;

        /**
         * The y-ordinate of a point on the reflection line
         */
        @Option(name = "-y0", usage = "The y-ordinate of a point on the reflection line", required = true)
        private double y0;

        /**
         * The x-ordinate of a another point on the reflection line
         */
        @Option(name = "-x1", usage = "The x-ordinate of a another point on the reflection line", required = false)
        private double x1 = Double.NaN;

        /**
         * The y-ordinate of a another point on the reflection line
         */
        @Option(name = "-y1", usage = "The y-ordinate of a another point on the reflection line", required = false)
        private double y1 = Double.NaN;

        /**
         * Get the x-ordinate of a point on the reflection line
         * @return The x-ordinate of a point on the reflection line
         */
        public double getX0() {
            return x0;
        }

        /**
         * Set the x-ordinate of a point on the reflection line
         * @param x0 The x-ordinate of a point on the reflection line
         */
        public void setX0(double x0) {
            this.x0 = x0;
        }

        /**
         * Get the x-ordinate of a another point on the reflection line
         * @return The x-ordinate of a another point on the reflection line
         */
        public double getX1() {
            return x1;
        }

        /**
         * Set the x-ordinate of a another point on the reflection line
         * @param x1 The x-ordinate of a another point on the reflection line
         */
        public void setX1(double x1) {
            this.x1 = x1;
        }

        /**
         * Get the y-ordinate of a point on the reflection line
         * @return The y-ordinate of a point on the reflection line
         */
        public double getY0() {
            return y0;
        }

        /**
         * Set the y-ordinate of a point on the reflection line
         * @param y0 The y-ordinate of a point on the reflection line
         */
        public void setY0(double y0) {
            this.y0 = y0;
        }

        /**
         * Get the y-ordinate of a another point on the reflection line
         * @return The y-ordinate of a another point on the reflection line
         */
        public double getY1() {
            return y1;
        }

        /**
         * Set the y-ordinate of a another point on the reflection line
         * @param y1 The y-ordinate of a another point on the reflection line
         */
        public void setY1(double y1) {
            this.y1 = y1;
        }
    }
}
