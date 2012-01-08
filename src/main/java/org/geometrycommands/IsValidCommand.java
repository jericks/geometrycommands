package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command that determines if the input Geometry is valid or not.
 * @author Jared Erickson
 */
public class IsValidCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isvalid";
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
     * Determine if the input Geometry is valid or not.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        boolean valid = geometry.isValid();
        System.out.println(valid);
    }
}
