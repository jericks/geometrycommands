package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.util.AffineTransformation;
import org.geometrycommands.TranslateCommand.TranslateOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that performs the translate affine transformation on the input Geometry.
 * @author Jared Erickson
 */
public class TranslateCommand extends GeometryCommand<TranslateOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "translate";
    }

    /**
     * Get a new TranslateOptions
     * @return The new TranslateOptions
     */
    @Override
    public TranslateOptions getOptions() {
        return new TranslateOptions();
    }

    /**
     * Perform the translate affine transformation on the input Geometry.
     * @param geometry The input Geometry
     * @param options The TranslateOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, TranslateOptions options) throws Exception {
        Geometry translatedGeometry = AffineTransformation.translationInstance(options.getX(), options.getY()).transform(geometry);
        System.out.println(writeGeometry(translatedGeometry, options));
    }

    /**
     * The TranslateOptions
     */
    public static class TranslateOptions extends GeometryOptions {

        /**
         * The value to translate by in the x direction
         */
        @Option(name = "-x", usage = "The value to translate by in the x direction", required = true)
        private double x;

        /**
         * The value to translate by in the y direction
         */
        @Option(name = "-y", usage = "The value to translate by in the y direction", required = true)
        private double y;

        /**
         * Get the value to translate by in the x direction
         * @return The value to translate by in the x direction
         */
        public double getX() {
            return x;
        }

        /**
         * Set the value to translate by in the x direction
         * @param x The value to translate by in the x direction
         */
        public void setX(double x) {
            this.x = x;
        }

        /**
         * Get the value to translate by in the y direction
         * @return The value to translate by in the y direction
         */
        public double getY() {
            return y;
        }

        /**
         * Set the value to translate by in the y direction
         * @param y The value to translate by in the y direction
         */
        public void setY(double y) {
            this.y = y;
        }
    }
}
