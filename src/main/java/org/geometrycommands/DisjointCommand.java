package org.geometrycommands;

import org.geometrycommands.DisjointCommand.DisjointOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Determine whether the first geometry is disjoint from the other geometry.
 * @author Jared Erickson
 */
public class DisjointCommand extends OtherGeometryCommand<DisjointOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "disjoint";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine whether the first geometry is disjoint from the other geometry.";
    }

    /**
     * Get a new DisjointOptions
     * @return A new DisjointOptions
     */
    @Override
    public DisjointOptions getOptions() {
        return new DisjointOptions();
    }

    /**
     * Determine whether the first geometry is disjoint from the other geometry.
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The DisjointOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, DisjointOptions options, Reader reader, Writer writer) throws Exception {
        boolean disjoint = geometry.disjoint(other);
        writer.write(String.valueOf(disjoint));
    }

    /**
     * The DisjointOptions
     */
    public static class DisjointOptions extends OtherGeometryOptions {
    }
}
