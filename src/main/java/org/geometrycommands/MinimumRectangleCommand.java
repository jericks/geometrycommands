package org.geometrycommands;

import com.vividsolutions.jts.algorithm.MinimumDiameter;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Calculate the minimum rectangle of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumRectangleCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "minrect";
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
     * Calculate the minimum rectangle of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        MinimumDiameter minimumDiameter = new MinimumDiameter(geometry);
        Geometry outputGeometry = minimumDiameter.getMinimumRectangle();
        System.out.println(writeGeoemtry(outputGeometry, options));
    }
}
