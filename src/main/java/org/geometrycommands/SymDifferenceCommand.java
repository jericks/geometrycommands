package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command for calculating the symetric difference between two Geometries
 * @author jericks
 */
public class SymDifferenceCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "symdifference";
    }

    /**
     * Get the new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public OtherGeometryOptions getOptions() {
        return new OtherGeometryOptions();
    }

    /**
     * Calculate the symetric difference between two Geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        Geometry symDifferenceGeometry = geometry.symDifference(other);
        System.out.println(writeGeoemtry(symDifferenceGeometry, options));
    }
}
