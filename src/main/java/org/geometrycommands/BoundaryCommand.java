package org.geometrycommands;

import org.geometrycommands.BoundaryCommand.BoundaryOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the boundary of a Geometry.
 * @author Jared Erickson
 */
public class BoundaryCommand extends GeometryCommand<BoundaryOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "boundary";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the boundary of a Geometry.";
    }

    /**
     * Get a new BoundaryOptions
     * @return A new BoundaryOptions
     */
    @Override
    public BoundaryOptions getOptions() {
        return new BoundaryOptions();
    }

    /**
     * Calculate the boundary of the input geometry
     * @param geometry The input geometry
     * @param options The BoundaryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, BoundaryOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.getBoundary();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The BoundaryOptions
     */
    public static class BoundaryOptions extends GeometryOptions {
    }
}
