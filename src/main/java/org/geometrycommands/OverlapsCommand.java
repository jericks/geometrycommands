package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to calculate whether the first geometry overlaps the other geometry
 * @author Jared Erickson
 */
public class OverlapsCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "overlaps";
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
     * Calculate whether the first geometry overlaps the other geometry
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        boolean contains = geometry.overlaps(other);
        System.out.println(contains);
    }
}
