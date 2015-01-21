package org.geometrycommands;

import org.geometrycommands.CrossesCommand.CrossesOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether one geometry crosses another
 * @author Jared Erickson
 */
public class CrossesCommand extends OtherGeometryCommand<CrossesOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "crosses";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine whether the first geometry crosses the other geometry.";
    }

    /**
     * Get the new CrossesOptions
     * @return A new CrossesOptions
     */
    @Override
    public CrossesOptions getOptions() {
        return new CrossesOptions();
    }
    
    /**
     * Calculate whether the first geometry crosses the other geometry.
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The CrossesOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, CrossesOptions options, Reader reader, Writer writer) throws Exception {
        boolean crosses = geometry.crosses(other);
        writer.write(String.valueOf(crosses));
    }

    /**
     * The CrossesOptions
     */
    public static class CrossesOptions extends OtherGeometryOptions {
    }
}
