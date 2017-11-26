package org.geometrycommands;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.SuperCircleCommand.SuperCircleOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to create a squircle
 * @author Jared Erickson
 */
public class SuperCircleCommand extends ShapeFactoryCommand<SuperCircleOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "supercircle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a super circle.";
    }

    /**
     * Get the SuperCircleOptions
     * @return The SuperCircleOptions
     */
    @Override
    public SuperCircleOptions getOptions() {
        return new SuperCircleOptions();
    }

    /**
     * Create a squircle Geometry 
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input Geometry
     * @param options The SuperCircleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, SuperCircleOptions options, Reader reader, Writer writer) throws Exception {
        // There is a bug in JTS when creating Super cirlces with base
        if (geometry instanceof Point && !options.isCenter()) {
            Point point = (Point) geometry;
            Coordinate coord = new Coordinate(point.getX() + options.getWidth() / 2, point.getY() + options.getHeight() / 2);
            shapeFactory.setCentre(coord);
        }
        Geometry outputGeometry = shapeFactory.createSupercircle(options.getPower());
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The SuperCircleOptions
     */
    public static class SuperCircleOptions extends ShapeFactoryOptions {

        /**
         * The positive power
         */
        @Option(name = "-o", aliases = "--power", usage = "The positive power", required = false)
        private double power = 4.0;

        /**
         * Get the positive power
         * @return The positive power
         */
        public double getPower() {
            return power;
        }

        /**
         * Set the positive power
         * @param power The positive power
         */
        public void setPower(double power) {
            this.power = power;
        }
    }
}
