package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command that determines if the input Geometry is a rectangle or not.
 * @author Jared Erickson
 */
public class IsRectangleCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isrectangle";
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
     * Determine if the input Geometry is a rectangle or not.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        boolean rectangle = geometry.isRectangle();
        System.out.println(rectangle);
    }
}
