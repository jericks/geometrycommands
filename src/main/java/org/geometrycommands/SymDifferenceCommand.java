package org.geometrycommands;

import org.geometrycommands.SymDifferenceCommand.SymDifferenceOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the symetric difference between two Geometries
 * @author jericks
 */
public class SymDifferenceCommand extends OtherGeometryCommand<SymDifferenceOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "symdifference";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the symetric difference between two geometries";
    }

    /**
     * Get the new SymDifferenceOptions
     * @return A new SymDifferenceOptions
     */
    @Override
    public SymDifferenceOptions getOptions() {
        return new SymDifferenceOptions();
    }

    /**
     * Calculate the symetric difference between two Geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The SymDifferenceOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, SymDifferenceOptions options, Reader reader, Writer writer) throws Exception {
        Geometry symDifferenceGeometry = geometry.symDifference(other);
        writer.write(writeGeometry(symDifferenceGeometry, options));
    }

    /**
     * The SymDifferenceOptions
     */
    public static class SymDifferenceOptions extends OtherGeometryOptions {
    }
}
