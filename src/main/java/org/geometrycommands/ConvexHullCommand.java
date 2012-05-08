package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

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
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the convex hull of a Geometry.";
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
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.convexHull();
        writer.write(writeGeometry(outputGeometry, options));
    }
}
