package org.geometrycommands;

import org.geometrycommands.MaximumInscribedCircleCommand.MaximumInscribedCircleOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.algorithm.construct.MaximumInscribedCircle;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the largest empty circle.
 * @author Jared Erickson
 */
public class MaximumInscribedCircleCommand extends GeometryCommand<MaximumInscribedCircleOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "maximuminscribedcircle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the maximum inscribed circle.";
    }

    /**
     * Get a new MaximumInscribedCircleOptions
     * @return A new MaximumInscribedCircleOptions
     */
    @Override
    public MaximumInscribedCircleOptions getOptions() {
        return new MaximumInscribedCircleOptions();
    }

    /**
     * Calculate the boundary of the input geometry
     * @param geometry The input geometry
     * @param options The MaximumInscribedCircleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, MaximumInscribedCircleOptions options, Reader reader, Writer writer) throws Exception {
        MaximumInscribedCircle algorithm = new MaximumInscribedCircle(geometry, options.getTolerance());
        LineString radiusLineString = algorithm.getRadiusLine();
        Point centerPoint = radiusLineString.getStartPoint();
        Geometry outputGeometry = centerPoint.buffer(radiusLineString.getLength());
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The MaximumInscribedCircleOptions
     */
    public static class MaximumInscribedCircleOptions extends GeometryOptions {

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
