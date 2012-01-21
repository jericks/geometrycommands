package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the centroind of a Geometry.
 * @author Jared Erickson
 */
public class CentroidCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "centroid";
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
     * Calculate the centroid of the input geometry
     * @param geometry The input geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.getCentroid();
        writer.write(writeGeometry(outputGeometry, options));
    }
}
