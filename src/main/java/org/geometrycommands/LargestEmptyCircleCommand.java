package org.geometrycommands;

import org.geometrycommands.LargestEmptyCircleCommand.LargestEmptyCircleOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.algorithm.construct.LargestEmptyCircle;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the largest empty circle.
 * @author Jared Erickson
 */
public class LargestEmptyCircleCommand extends GeometryCommand<LargestEmptyCircleOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "largestemptycircle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the largest empty circle.";
    }

    /**
     * Get a new LargestEmptyCircleOptions
     * @return A new LargestEmptyCircleOptions
     */
    @Override
    public LargestEmptyCircleOptions getOptions() {
        return new LargestEmptyCircleOptions();
    }

    /**
     * Calculate the boundary of the input geometry
     * @param geometry The input geometry
     * @param options The LargestEmptyCircleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, LargestEmptyCircleOptions options, Reader reader, Writer writer) throws Exception {
        LargestEmptyCircle algorithm = new LargestEmptyCircle(geometry, options.getTolerance());
        LineString radiusLineString = algorithm.getRadiusLine();
        Point centerPoint = radiusLineString.getStartPoint();
        Geometry outputGeometry = centerPoint.buffer(radiusLineString.getLength());
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The LargestEmptyCircleOptions
     */
    public static class LargestEmptyCircleOptions extends GeometryOptions {

        /**
         * The tolerance
         */
        @Option(name = "-t", aliases = "--tolerance", usage = "The tolerance", required = false)
        private double tolerance = 1.0;

        public double getTolerance() {
            return tolerance;
        }

        public void setTolerance(double tolerance) {
            this.tolerance = tolerance;
        }
    }
}
