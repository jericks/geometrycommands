package org.geometrycommands;

import org.geometrycommands.DistanceLineStringCommand.DistanceLineStringOptions;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.operation.distance.DistanceOp;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate a LineString representing the shortest distance between
 * two geometries
 * @author Jared Erickson
 */
public class DistanceLineStringCommand extends OtherGeometryCommand<DistanceLineStringOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "distanceline";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Generate a LineString representing the shortest distance between two geometries.";
    }

    /**
     * Get a new DistanceLineStringOptions
     * @return A new DistanceLineStringOptions
     */
    @Override
    public DistanceLineStringOptions getOptions() {
        return new DistanceLineStringOptions();
    }

    /**
     * Calculate a LineString representing the shortest distance between
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The DistanceLineStringOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, DistanceLineStringOptions options, Reader reader, Writer writer) throws Exception {
        DistanceOp op = new DistanceOp(geometry, other);
        Coordinate[] coords = op.nearestPoints();
        GeometryFactory factory = new GeometryFactory();
        LineString line = factory.createLineString(coords);
        writer.write(writeGeometry(line, options));
    }

    /**
     * The DistanceLineStringOptions
     */
    public static class DistanceLineStringOptions extends OtherGeometryOptions {
    }

}
