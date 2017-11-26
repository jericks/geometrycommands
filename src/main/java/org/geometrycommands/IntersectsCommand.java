package org.geometrycommands;

import org.geometrycommands.IntersectsCommand.IntersectsOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry intersects the other Geometry.
 * @author Jared Erickson
 */
public class IntersectsCommand extends OtherGeometryCommand<IntersectsOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "intersects";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the first geometry intersects the second geometry.";
    }

    /**
     * Get a new IntersectsOptions
     * @return A new IntersectsOptions
     */
    @Override
    public IntersectsOptions getOptions() {
        return new IntersectsOptions();
    }

    /**
     * Determine if the input Geometry intersects the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The IntersectsOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, IntersectsOptions options, Reader reader, Writer writer) throws Exception {
        boolean intersects = geometry.intersects(other);
        writer.write(String.valueOf(intersects));
    }

    /**
     * The IntersectsOptions
     */
    public static class IntersectsOptions extends OtherGeometryOptions {
    }
}
