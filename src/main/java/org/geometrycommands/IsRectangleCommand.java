package org.geometrycommands;

import org.geometrycommands.IsRectangleCommand.IsRectangleOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is a rectangle or not.
 * @author Jared Erickson
 */
public class IsRectangleCommand extends GeometryCommand<IsRectangleOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isrectangle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is rectangular or not.";
    }

    /**
     * Get a new IsRectangleOptions
     * @return A new IsRectangleOptions
     */
    @Override
    public IsRectangleOptions getOptions() {
        return new IsRectangleOptions();
    }

    /**
     * Determine if the input Geometry is a rectangle or not.
     * @param geometry The input Geometry
     * @param options The IsRectangleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsRectangleOptions options, Reader reader, Writer writer) throws Exception {
        boolean rectangle = geometry.isRectangle();
        writer.write(String.valueOf(rectangle));
    }

    /**
     * The IsRectangleOptions
     */
    public static class IsRectangleOptions extends GeometryOptions {
    }
}
