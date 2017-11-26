package org.geometrycommands;

import org.geometrycommands.IsEmptyCommand.IsEmptyOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is empty or not.
 * @author Jared Erickson
 */
public class IsEmptyCommand extends GeometryCommand<IsEmptyOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isempty";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is empty or not.";
    }

    /**
     * Get a new IsEmptyOptions
     * @return A new IsEmptyOptions
     */
    @Override
    public IsEmptyOptions getOptions() {
        return new IsEmptyOptions();
    }

    /**
     * Determine if the input Geometry is empty or not.
     * @param geometry The input Geometry
     * @param options The IsEmptyOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsEmptyOptions options, Reader reader, Writer writer) throws Exception {
        boolean empty = geometry.isEmpty();
        writer.write(String.valueOf(empty));
    }

    /**
     * The IsEmptyOptions
     */
    public static class IsEmptyOptions extends GeometryOptions {
    }
}
