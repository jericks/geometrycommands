package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.operation.distance.DistanceOp;

/**
 * A Command to calculate a LineString representing the shortest distance between
 * two geometries
 * @author Jared Erickson
 */
public class DistanceLineStringCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "distanceline";
    }

    /**
     * Get a new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public OtherGeometryOptions getOptions() {
        return new OtherGeometryOptions();
    }

    /**
     * Calculate a LineString representing the shortest distance between
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        DistanceOp op = new DistanceOp(geometry, other);
        Coordinate[] coords = op.nearestPoints();
        GeometryFactory factory = new GeometryFactory();
        LineString line = factory.createLineString(coords);
        System.out.println(writeGeometry(line, options));
    }
}
