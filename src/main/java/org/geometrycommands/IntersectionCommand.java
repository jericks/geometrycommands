package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Calculate the intersection between two geometries.
 * @author Jared Erickson
 */
public class IntersectionCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "intersection";
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
     * Calculate the intersection between the two geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        Geometry differenceGeometry = geometry.intersection(other);
        System.out.println(writeGeoemtry(differenceGeometry, options));
    }
}
