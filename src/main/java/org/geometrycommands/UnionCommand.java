package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to calculate the union of the input Geometry and the other Geometry.
 * @author Jared Erickson
 */
public class UnionCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "union";
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
     * Calculate the union of the input Geometry and the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        Geometry unionedGeometry = geometry.union(other);
        System.out.println(writeGeometry(unionedGeometry, options));
    }
}
