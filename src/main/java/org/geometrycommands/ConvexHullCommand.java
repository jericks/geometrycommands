package org.geometrycommands;

import org.geometrycommands.ConvexHullCommand.ConvexHullOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate the convex hull of the input geometry
 * @author Jared Erickson
 */
public class ConvexHullCommand extends GeometryCommand<ConvexHullOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "convexHull";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the convex hull of a Geometry.";
    }

    /**
     * Get the new ConvexHullOptions
     * @return A new ConvexHullOptions
     */
    @Override
    public ConvexHullOptions getOptions() {
        return new ConvexHullOptions();
    }

    /**
     * Calculate the convex hull of the input geometry
     * @param geometry The input geometry
     * @param options The ConvexHullOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ConvexHullOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.convexHull();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The ConvexHullOptions
     */
    public static class ConvexHullOptions extends GeometryOptions {
    }
}
