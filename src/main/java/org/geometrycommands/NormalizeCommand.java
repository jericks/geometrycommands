package org.geometrycommands;

import org.geometrycommands.NormalizeCommand.NormalizeOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to normalize the input Geometry
 * @author Jared Erickson
 */
public class NormalizeCommand extends GeometryCommand<NormalizeOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "normalize";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the normalized version of the input geometry.";
    }

    /**
     * Get a new NormalizeOptions
     * @return A new NormalizeOptions
     */
    @Override
    public NormalizeOptions getOptions() {
        return new NormalizeOptions();
    }

    /**
     * Normalize the input Geometry.
     * @param geometry The input Geometry
     * @param options The NormalizeOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, NormalizeOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.norm();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The NormalizeOptions
     */
    public static class NormalizeOptions extends GeometryOptions {
    }
}
