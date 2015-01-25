package org.geometrycommands;

import org.geometrycommands.MinimumBoundingCircleCommand.MinimumBoundingCircleOptions;
import com.vividsolutions.jts.algorithm.MinimumBoundingCircle;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the minimum bounding circle of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumBoundingCircleCommand extends GeometryCommand<MinimumBoundingCircleOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "mincircle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the minimum bounding circle of the input geometry.";
    }

    /**
     * Get a new MinimumBoundingCircleOptions
     * @return A new MinimumBoundingCircleOptions
     */
    @Override
    public MinimumBoundingCircleOptions getOptions() {
        return new MinimumBoundingCircleOptions();
    }

    /**
     * Calculate the minimum bounding circle of the input Geometry.
     * @param geometry The input Geometry
     * @param options The MinimumBoundingCircleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, MinimumBoundingCircleOptions options, Reader reader, Writer writer) throws Exception {
        MinimumBoundingCircle circle = new MinimumBoundingCircle(geometry);
        Geometry outputGeometry = circle.getCircle();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The MinimumBoundingCircleOptions
     */
    public static class MinimumBoundingCircleOptions extends GeometryOptions {
    }
}
