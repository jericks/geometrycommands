package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.ArcPolygonCommand.ArcPolygonOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to create an arc polygon
 * @author Jared Erickson
 */
public class ArcPolygonCommand extends ShapeFactoryCommand<ArcPolygonOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "arcpoly";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Creates an arc polygon from a start angle and an angle extent.";
    }


    /**
     * Get the ArcPolygonOptions
     * @return The ArcPolygonOptions
     */
    @Override
    public ArcPolygonOptions getOptions() {
        return new ArcPolygonOptions();
    }

    /**
     * Create an arc polygon
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input Geometry
     * @param options The ArcPolygonOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, ArcPolygonOptions options, Reader reader, Writer writer) throws Exception {
        double startAngle = options.getStartAngle();
        double angleExtent = options.getAngleExtent();
        if (options.isDegrees()) {
             startAngle = Math.toRadians(startAngle);
             angleExtent = Math.toRadians(angleExtent);
        }
        Geometry outputGeometry = shapeFactory.createArcPolygon(startAngle, angleExtent);
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The ArcPolygonOptions
     */
    public static class ArcPolygonOptions extends ShapeFactoryOptions {

        /**
         * The start angle (in radians)
         */
        @Option(name = "-a", aliases = "--startAngle", usage = "The start angle (in radians)", required = true)
        private double startAngle;

        /**
         * The size of angle (in radians)
         */
        @Option(name = "-e", aliases= "--angleExtent", usage = "The size of angle (in radians)", required = true)
        private double angleExtent;

        /**
         * The flag for whether given angle measures are in degrees (true) or radians (false)
         */
        @Option(name = "-d", aliases = "--degrees", usage = "The flag for whether given angle measures are in degrees (true) or radians (false)", required = false)
        private boolean degrees;

        /**
         * Get the flag for whether given angle measures are in degrees (true) or radians (false)
         * @return The flag for whether given angle measures are in degrees (true) or radians (false)
         */
        public boolean isDegrees() {
            return degrees;
        }

        /**
         * Set the flag for whether given angle measures are in degrees (true) or radians (false)
         * @param degrees The flag for whether given angle measures are in degrees (true) or radians (false)
         */
        public void setDegrees(boolean degrees) {
            this.degrees = degrees;
        }

        /**
         * Get the size of angle (in radians)
         * @return The size of angle (in radians)
         */
        public double getAngleExtent() {
            return angleExtent;
        }

        /**
         * Set the size of angle (in radians)
         * @param angleExtent The size of angle (in radians)
         */
        public void setAngleExtent(double angleExtent) {
            this.angleExtent = angleExtent;
        }

        /**
         * Get the start angle (in radians)
         * @return The start angle (in radians)
         */
        public double getStartAngle() {
            return startAngle;
        }

        /**
         * Set the start angle (in radians)
         * @param startAngle The start angle (in radians)
         */
        public void setStartAngle(double startAngle) {
            this.startAngle = startAngle;
        }
    }
}
