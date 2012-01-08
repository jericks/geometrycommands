package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command that determines if the input Geometry intersects the other Geometry.
 * @author Jared Erickson
 */
public class IntersectsCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "intersects";
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
     * Determine if the input Geometry intersects the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        boolean intersects = geometry.intersects(other);
        System.out.println(intersects);
    }
}
