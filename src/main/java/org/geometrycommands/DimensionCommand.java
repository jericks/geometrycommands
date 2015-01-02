package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.DimensionCommand.DimensionOptions;
import java.io.Reader;
import java.io.Writer;

/**
 * Get the dimension of the Geometry.
 * @author Jared Erickson
 */
public class DimensionCommand extends GeometryCommand<DimensionOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "dimension";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the dimension of the Geometry.";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public DimensionOptions getOptions() {
        return new DimensionOptions();
    }

    /**
     * Calculate the area of a Geometry
     * @param geometry The Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, DimensionOptions options, Reader reader, Writer writer) throws Exception {
        double dimension = geometry.getDimension();
        writer.write(String.valueOf(dimension));
    }

    /**
     * The Options for the DimensionCommand
     */
    public static class DimensionOptions extends GeometryOptions {
    }
}
