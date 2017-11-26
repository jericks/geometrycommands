package org.geometrycommands;

import org.geometrycommands.CountPointsCommand.CountPointsOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to count the number of Points in the input Geometry
 * @author Jared Erickson
 */
public class CountPointsCommand extends GeometryCommand<CountPointsOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "countpoints";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Count the number of Points in the input Geometry.";
    }

    /**
     * Get a new CountPointsOptions
     * @return A new CountPointsOptions
     */
    @Override
    public CountPointsOptions getOptions() {
        return new CountPointsOptions();
    }

    /**
     * Count the number of Points in the input Geometry.
     * @param geometry The input Geometry
     * @param options The CountPointsOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, CountPointsOptions options, Reader reader, Writer writer) throws Exception {
        writer.write(String.valueOf(geometry.getNumPoints()));
    }

    /**
     * The CountPointsOptions
     */
    public static class CountPointsOptions extends GeometryOptions {
    }
}
