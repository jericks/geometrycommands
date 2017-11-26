package org.geometrycommands;

import org.locationtech.jts.dissolve.LineDissolver;
import org.geometrycommands.LineDissolverCommand.LineDissolverOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for dissolving LineStrings.
 * @author Jared Erickson
 */
public class LineDissolverCommand extends GeometryCommand<LineDissolverOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "linedissolve";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Dissolve LinesStrings";
    }

    /**
     * Get a new LineDissolverOptions
     * @return A new LineDissolverOptions
     */
    @Override
    public LineDissolverOptions getOptions() {
        return new LineDissolverOptions();
    }

    /**
     * Calculate the centroid of the input geometry
     * @param geometry The input geometry
     * @param options The CentroidOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, LineDissolverOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = LineDissolver.dissolve(geometry);
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The LineDissolverCommand Options
     */
    public static class LineDissolverOptions extends GeometryOptions {
    }

}
