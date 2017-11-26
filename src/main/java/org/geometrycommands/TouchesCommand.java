package org.geometrycommands;

import org.geometrycommands.TouchesCommand.TouchesOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to determine if the input Geometry touches the other Geometry.
 * @author Jared Erickson
 */
public class TouchesCommand extends OtherGeometryCommand<TouchesOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "touches";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry touches another geometry.";
    }

    /**
     * Get a new TouchesOptions
     * @return A new TouchesOptions
     */
    @Override
    public TouchesOptions getOptions() {
        return new TouchesOptions();
    }

    /**
     * determine if the input Geometry touches the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The TouchesOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, TouchesOptions options, Reader reader, Writer writer) throws Exception {
        boolean touches = geometry.touches(other);
        writer.write(String.valueOf(touches));
    }

    /**
     * The TouchesOptions
     */
    public static class TouchesOptions extends OtherGeometryOptions {
    }
}
