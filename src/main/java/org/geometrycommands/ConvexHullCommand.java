package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to calculate the convex hull of the input geometry
 * @author Jared Erickson
 */
public class ConvexHullCommand extends GeometryCommand<GeometryOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "convexHull";
    }

    /**
     * Get the new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public GeometryOptions getOptions() {
        return new GeometryOptions();
    }

    /**
     * Calculate the convex hull of the input geometry
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        Geometry outputGeometry = geometry.convexHull();
        System.out.println(writeGeometry(outputGeometry, options));
    }
}
