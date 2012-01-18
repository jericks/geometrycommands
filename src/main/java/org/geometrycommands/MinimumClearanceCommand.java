package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.precision.MinimumClearance;

/**
 * Calculate the minimum clearance of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumClearanceCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "minclearance";
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
     * Calculate the minimum clearance of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        MinimumClearance minimumClearance = new MinimumClearance(geometry);
        Geometry outputGeometry = minimumClearance.getLine();
        System.out.println(writeGeometry(outputGeometry, options));
    }
}
