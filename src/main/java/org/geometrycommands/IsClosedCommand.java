package org.geometrycommands;

import org.geometrycommands.IsClosedCommand.IsClosedOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Lineal;
import org.locationtech.jts.geom.MultiLineString;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is closed or not.
 * @author Jared Erickson
 */
public class IsClosedCommand extends GeometryCommand<IsClosedOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isclosed";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is closed or not.";
    }

    /**
     * Get a new IsClosedOptions
     * @return A new IsClosedOptions
     */
    @Override
    public IsClosedOptions getOptions() {
        return new IsClosedOptions();
    }

    /**
     * Determine if the input Geometry is closed or not.
     * @param geometry The input Geometry
     * @param options The IsClosedOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsClosedOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof Lineal)) {
            throw new IllegalArgumentException("The input geometry must be a LineString or a MultiLineString!");
        }
        boolean closed;
        if (geometry instanceof LineString) {
            closed = ((LineString) geometry).isClosed();
        } else {
            closed = ((MultiLineString) geometry).isClosed();
        }
        writer.write(String.valueOf(closed));
    }

    /**
     * The IsClosedOptions
     */
    public static class IsClosedOptions extends GeometryOptions {
    }
}
