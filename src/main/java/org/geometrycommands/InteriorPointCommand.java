package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to get the interior Point of the input Geometry
 * @author Jared Erickson
 */
public class InteriorPointCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "interiorpoint";
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
     * Get the interior point of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        Geometry outputGeometry = geometry.getInteriorPoint();
        System.out.println(writeGeometry(outputGeometry, options));
    }
}
