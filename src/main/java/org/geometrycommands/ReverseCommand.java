package org.geometrycommands;

import org.geometrycommands.ReverseCommand.ReverseOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for reversing the coordinates in the input Geometry.
 * @author Jared Erickson
 */
public class ReverseCommand extends GeometryCommand<ReverseOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "reverse";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Reverse the coordinates of the input geometry";
    }

    /**
     * Get a new ReverseOptions
     * @return A new ReverseOptions
     */
    @Override
    public ReverseOptions getOptions() {
        return new ReverseOptions();
    }

    /**
     * Reverse the coordinates in the input Geometry
     * @param geometry The input geometry
     * @param options The ReverseOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ReverseOptions options, Reader reader, Writer writer) throws Exception {
        writer.write(writeGeometry(geometry.reverse(), options));
    }

    /**
     * The ReverseOptions
     */
    public static class ReverseOptions extends GeometryOptions {
    }
}
