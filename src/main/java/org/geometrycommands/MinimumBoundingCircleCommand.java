package org.geometrycommands;

import com.vividsolutions.jts.algorithm.MinimumBoundingCircle;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the minimum bounding circle of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumBoundingCircleCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "mincircle";
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
     * Calculate the minimum bounding circle of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        MinimumBoundingCircle circle = new MinimumBoundingCircle(geometry);
        Geometry outputGeometry = circle.getCircle();
        writer.write(writeGeometry(outputGeometry, options));
    }
}
