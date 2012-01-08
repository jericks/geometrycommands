package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command that determines if the input Geometry is empty or not.
 * @author Jared Erickson
 */
public class IsEmptyCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isempty";
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
     * Determine if the input Geometry is empty or not.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        boolean empty = geometry.isEmpty();
        System.out.println(empty);
    }
}
