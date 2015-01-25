package org.geometrycommands;

import org.geometrycommands.WithinCommand.WithinOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to determine if the input Geometry is within the other Geometry.
 * @author Jared Erickson
 */
public class WithinCommand extends OtherGeometryCommand<WithinOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "within";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is within the other geometry.";
    }

    /**
     * Get a new WithinOptions
     * @return A new WithinOptions
     */
    @Override
    public WithinOptions getOptions() {
        return new WithinOptions();
    }

    /**
     * determine if the input Geometry is within the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The WithinOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, WithinOptions options, Reader reader, Writer writer) throws Exception {
        boolean within = geometry.within(other);
        writer.write(String.valueOf(within));
    }

    /**
     * The WithinOptions
     */
    public static class WithinOptions extends OtherGeometryOptions {
    }
}
