package org.geometrycommands;

import org.geometrycommands.CoversCommand.CoversOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether the first geometry covers the other geometry.
 * @author Jared Erickson
 */
public class CoversCommand extends OtherGeometryCommand<CoversOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "covers";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine whether the first geometry covers the other geometry.";
    }

    /**
     * Get the new CoversOptions
     * @return A new CoversOptions
     */
    @Override
    public CoversOptions getOptions() {
        return new CoversOptions();
    }

    /**
     * Calculate whether the first geometry covers the other geometry.
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The CoversOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, CoversOptions options, Reader reader, Writer writer) throws Exception {
        boolean covers = geometry.covers(other);
        writer.write(String.valueOf(covers));
    }

    /**
     * The CoversOptions
     */
    public static class CoversOptions extends OtherGeometryOptions {
    }
}
