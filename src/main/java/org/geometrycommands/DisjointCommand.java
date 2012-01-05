package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Determine whether the first geometry is disjoint from the other geometry.
 * @author Jared Erickson
 */
public class DisjointCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "disjoint";
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
     * Determine whether the first geometry is disjoint from the other geometry.
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        boolean disjoint = geometry.disjoint(other);
        System.out.println(disjoint);
    }
}
