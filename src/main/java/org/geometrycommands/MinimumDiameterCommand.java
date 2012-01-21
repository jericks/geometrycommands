package org.geometrycommands;

import com.vividsolutions.jts.algorithm.MinimumDiameter;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the minimum diameter of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumDiameterCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "mindiameter";
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
     * Calculate the minimum diameter of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        MinimumDiameter minimumDiameter = new MinimumDiameter(geometry);
        Geometry outputGeometry = minimumDiameter.getDiameter();
        writer.write(writeGeometry(outputGeometry, options));
    }
}
