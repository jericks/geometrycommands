package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to count the number of Geometries in the input Geometry
 * @author Jared Erickson
 */
public class CountGeometriesCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "count";
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
     * Count the number of Geometries in the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        System.out.println(geometry.getNumGeometries());
    }
}
