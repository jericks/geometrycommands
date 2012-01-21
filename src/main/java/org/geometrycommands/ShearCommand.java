package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.util.AffineTransformation;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.ShearCommand.ShearOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that performs the shear affine transformation on the input Geometry.
 * @author Jared Erickson
 */
public class ShearCommand extends GeometryCommand<ShearOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "shear";
    }

    /**
     * Get a new ShearOptions
     * @return The new ShearOptions
     */
    @Override
    public ShearOptions getOptions() {
        return new ShearOptions();
    }

    /**
     * Perform the shear affine transformation on the input Geometry.
     * @param geometry The input Geometry
     * @param options The ShearOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ShearOptions options, Reader reader, Writer writer) throws Exception {
        Geometry shearGeometry = AffineTransformation.shearInstance(options.getX(), options.getY()).transform(geometry);
        writer.write(writeGeometry(shearGeometry, options));
    }

    /**
     * The ShearOptions
     */
    public static class ShearOptions extends GeometryOptions {

        /**
         * The value to shear by in the x direction
         */
        @Option(name = "-x", usage = "The value to translate by in the x direction", required = true)
        private double x;

        /**
         * The value to shear by in the y direction
         */
        @Option(name = "-y", usage = "The value to translate by in the y direction", required = true)
        private double y;

        /**
         * Get the value to shear by in the x direction
         * @return The value to shear by in the x direction
         */
        public double getX() {
            return x;
        }

        /**
         * Set the value to shear by in the x direction
         * @param x The value to shear by in the x direction
         */
        public void setX(double x) {
            this.x = x;
        }

        /**
         * Get the value to shear by in the y direction
         * @return The value to shear by in the y direction
         */
        public double getY() {
            return y;
        }

        /**
         * Set the value to shear by in the y direction
         * @param y The value to shear by in the y direction
         */
        public void setY(double y) {
            this.y = y;
        }
    }
}
