package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.precision.MinimumClearance;
import java.io.Reader;
import java.io.Writer;

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
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the minimum clearance of the input geometry.";
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
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        MinimumClearance minimumClearance = new MinimumClearance(geometry);
        Geometry outputGeometry = minimumClearance.getLine();
        writer.write(writeGeometry(outputGeometry, options));
    }
}
