package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command for reversing the coordinates in the input Geometry.
 * @author Jared Erickson
 */
public class ReverseCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "reverse";
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
     * Reverse the coordinates in the input Geometry
     * @param geometry The input geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        System.out.println(writeGeometry(geometry.reverse(), options));
    }
}
