package org.geometrycommands;

import org.geometrycommands.ContainsCommand.ContainsOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether the first geometry contains the other geometry
 * @author Jared Erickson
 */
public class ContainsCommand extends OtherGeometryCommand<ContainsOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "contains";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate whether the first geometry contains the other geometry.";
    }

    /**
     * Get the new ContainsOptions
     * @return A new ContainsOptions
     */
    @Override
    public ContainsOptions getOptions() {
        return new ContainsOptions();
    }

    /**
     * Calculate whether the first geometry contains the other geometry
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The ContainsOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, ContainsOptions options, Reader reader, Writer writer) throws Exception {
        boolean contains = geometry.contains(other);
        writer.write(String.valueOf(contains));
    }

    /**
     * The ContainsOptions
     */
    public static class ContainsOptions extends OtherGeometryOptions {
    }
}
