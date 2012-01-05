package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to calculate whether the first geometry is covered the other geometry.
 * @author Jared Erickson
 */
public class CoveredByCommand extends OtherGeometryCommand<OtherGeometryOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "coveredby";
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
     * Calculate whether the first geometry is covered the other geometry.
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        boolean coveredBy = geometry.coveredBy(other);
        System.out.println(coveredBy);
    }
}
