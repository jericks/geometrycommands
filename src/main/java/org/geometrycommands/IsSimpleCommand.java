package org.geometrycommands;

import org.geometrycommands.IsSimpleCommand.IsSimpleOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is simple or not.
 * @author Jared Erickson
 */
public class IsSimpleCommand extends GeometryCommand<IsSimpleOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "issimple";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is simple or not.";
    }

    /**
     * Get a new IsSimpleOptions
     * @return A new IsSimpleOptions
     */
    @Override
    public IsSimpleOptions getOptions() {
        return new IsSimpleOptions();
    }

    /**
     * Determine if the input Geometry is simple or not.
     * @param geometry The input Geometry
     * @param options The IsSimpleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsSimpleOptions options, Reader reader, Writer writer) throws Exception {
        boolean simple = geometry.isSimple();
        writer.write(String.valueOf(simple));
    }

    /**
     * The IsSimpleOptions
     */
    public static class IsSimpleOptions extends GeometryOptions {
    }
}
