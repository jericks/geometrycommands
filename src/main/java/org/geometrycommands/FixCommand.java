package org.geometrycommands;

import org.geometrycommands.FixCommand.FixOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.GeometryFixer;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command to fix an invalid geometry
 * @author Jared Erickson
 */
public class FixCommand extends GeometryCommand<FixOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "fix";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Fix an invalid Geometry.";
    }

    /**
     * Get the new FixOptions
     * @return A new FixOptions
     */
    @Override
    public FixOptions getOptions() {
        return new FixOptions();
    }

    /**
     * Calculate the convex hull of the input geometry
     * @param geometry The input geometry
     * @param options The FixOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, FixOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = GeometryFixer.fix(geometry);
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The FixOptions
     */
    public static class FixOptions extends GeometryOptions {
    }
}
