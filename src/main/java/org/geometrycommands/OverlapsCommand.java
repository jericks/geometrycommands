package org.geometrycommands;

import org.geometrycommands.OverlapsCommand.OverlapsOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether the first geometry overlaps the other geometry
 * @author Jared Erickson
 */
public class OverlapsCommand extends OtherGeometryCommand<OverlapsOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "overlaps";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine whether the first geometry overlaps with the other geometry.";
    }

    /**
     * Get the new OverlapsOptions
     * @return A new OverlapsOptions
     */
    @Override
    public OverlapsOptions getOptions() {
        return new OverlapsOptions();
    }

    /**
     * Calculate whether the first geometry overlaps the other geometry
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The OverlapsOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OverlapsOptions options, Reader reader, Writer writer) throws Exception {
        boolean overlaps = geometry.overlaps(other);
        writer.write(String.valueOf(overlaps));
    }

    /**
     * The OverlapsOptions
     */
    public static class OverlapsOptions extends OtherGeometryOptions {
    }
}
