package org.geometrycommands;

import org.geometrycommands.UnionCommand.UnionOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate the union of the input Geometry and the other Geometry.
 * @author Jared Erickson
 */
public class UnionCommand extends OtherGeometryCommand<UnionOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "union";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the union between two geometries.";
    }

    /**
     * Get a new UnionOptions
     * @return A new UnionOptions
     */
    @Override
    public UnionOptions getOptions() {
        return new UnionOptions();
    }

    /**
     * Calculate the union of the input Geometry and the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The UnionOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, UnionOptions options, Reader reader, Writer writer) throws Exception {
        Geometry unionedGeometry = geometry.union(other);
        writer.write(writeGeometry(unionedGeometry, options));
    }

    /**
     * The UnionOptions
     */
    public static class UnionOptions extends OtherGeometryOptions {
    }
}
