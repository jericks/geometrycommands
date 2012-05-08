package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Lineal;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.linearref.LengthIndexedLine;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.InterpolatePointCommand.InterpolatePointOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to interpolate the location of a Point on the input linear Geometry given
 * a percentage position along the Geometry.
 * @author Jared Erickson
 */
public class InterpolatePointCommand extends GeometryCommand<InterpolatePointOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "interpolatepoint";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Interpolate the location of a point on the input linear geometry given a percentage position.";
    }

    /**
     * Get a new InterpolatePointOptions
     * @return A new InterpolatePointOptions
     */
    @Override
    public InterpolatePointOptions getOptions() {
        return new InterpolatePointOptions();
    }

    /**
     * Interpolate the location of a Point on the input linear Geometry given
     * a percentage position along the Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, InterpolatePointOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof Lineal)) {
            throw new IllegalArgumentException("The input geometry a LineString or a MultiLineString!");
        }
        LengthIndexedLine indexedLine = new LengthIndexedLine(geometry);
        double length = geometry.getLength();
        Coordinate coord = indexedLine.extractPoint(options.getPosition() * length);
        GeometryFactory factory = new GeometryFactory();
        Point point = factory.createPoint(coord);
        writer.write(writeGeometry(point, options));
    }

    /**
     * The InterpolatePointOptions
     */
    public static class InterpolatePointOptions extends GeometryOptions {

        /**
         * The position between 0 and 1
         */
        @Option(name = "-p", aliases = "--position", usage = "The position between 0 and 1", required = true)
        private double position;

        /**
         * Get the position between 0 and 1
         * @return The position between 0 and 1
         */
        public double getPosition() {
            return position;
        }

        /**
         * Set the position between 0 and 1
         * @param position The position between 0 and 1
         */
        public void setPosition(double position) {
            this.position = position;
        }
    }
}
