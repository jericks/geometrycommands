package org.geometrycommands;

import org.geometrycommands.CentroidCommand.CentroidOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the centroid of a Geometry.
 * @author Jared Erickson
 */
public class CentroidCommand extends GeometryCommand<CentroidOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "centroid";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the centroid of a Geometry.";
    }

    /**
     * Get a new CentroidOptions
     * @return A new CentroidOptions
     */
    @Override
    public CentroidOptions getOptions() {
        return new CentroidOptions();
    }

    /**
     * Calculate the centroid of the input geometry
     * @param geometry The input geometry
     * @param options The CentroidOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, CentroidOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.getCentroid();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The CentroidOptions
     */
    public static class CentroidOptions extends GeometryOptions {
    }
}
