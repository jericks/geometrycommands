package org.geometrycommands;

import org.geometrycommands.DifferenceCommand.DifferenceOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the difference between two Geometries
 * @author jericks
 */
public class DifferenceCommand extends OtherGeometryCommand<DifferenceOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "difference";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the difference between the input geometries.";
    }

    /**
     * Get the new DifferenceOptions
     * @return A new DifferenceOptions
     */
    @Override
    public DifferenceOptions getOptions() {
        return new DifferenceOptions();
    }

    /**
     * Calculate the difference between two Geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The DifferenceOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, DifferenceOptions options, Reader reader, Writer writer) throws Exception {
        Geometry differenceGeometry = geometry.difference(other);
        writer.write(writeGeometry(differenceGeometry, options));
    }

    /**
     * The DifferenceOptions
     */
    public static class DifferenceOptions extends OtherGeometryOptions {
    }
}
