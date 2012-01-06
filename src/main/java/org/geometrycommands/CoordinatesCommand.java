package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to get the Coordinates of the input Geometry.
 * @author Jared Erickson
 */
public class CoordinatesCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "coordinates";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public GeometryOptions getOptions() {
        return new GeometryOptions();
    }

    /**
     * Get the coordinates of the input Geometry
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        Coordinate[] coords = geometry.getCoordinates();
        Geometry outputGeometry = geometry.getFactory().createMultiPoint(coords);
        System.out.println(writeGeoemtry(outputGeometry, options));
    }
}
