package org.geometrycommands;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.PointAtAngleCommand.PointAtAngleOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command for calculating a new Point at a given angle and distance from the input
 * Point
 * @author Jared Erickson
 */
public class PointAtAngleCommand extends GeometryCommand<PointAtAngleOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "pointatangle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate a point at a given angle distance from the input point.";
    }

    /**
     * Get the PointAtAngleOptions
     * @return The PointAtAngleOptions
     */
    @Override
    public PointAtAngleOptions getOptions() {
        return new PointAtAngleOptions();
    }

    /**
     * Calculating a new Point at a given angle and distance from the input Point
     * @param geometry The input Geometry
     * @param options The PointAtAngleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, PointAtAngleOptions options, Reader reader, Writer writer) throws Exception {
        Coordinate start = geometry.getCoordinate();
        double angleAsRadians = Math.toRadians(options.getAngle());
        double x = start.x + (options.getDistance() * Math.cos(angleAsRadians));
        double y = start.y + (options.getDistance() * Math.sin(angleAsRadians));
        Coordinate newCoordinate = new Coordinate(x, y);
        Point point = geometry.getFactory().createPoint(newCoordinate);
        writer.write(writeGeometry(point, options));
    }

    /**
     * The PointAtAngleOptions
     */
    public static class PointAtAngleOptions extends GeometryOptions {

        /**
         * The distance
         */
        @Option(name = "-d", aliases = "--distance", usage = "The distance", required = true)
        private double distance;

        /**
         * The angle (in degrees).
         */
        @Option(name = "-a", aliases = "--angle", usage = "The angle (in degrees)", required = true)
        private int angle;

        /**
         * Get the angle (in degrees)
         * @return The angle (in degrees)
         */
        public int getAngle() {
            return angle;
        }

        /**
         * Set the angle (in degrees)
         * @param angle The angle (in degrees)
         */
        public void setAngle(int angle) {
            this.angle = angle;
        }

        /**
         * Get the distance
         * @return The distance
         */
        public double getDistance() {
            return distance;
        }

        /**
         * Set the distance
         * @param distance The distance
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
