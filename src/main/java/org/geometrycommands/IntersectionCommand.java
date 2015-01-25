package org.geometrycommands;

import org.geometrycommands.IntersectionCommand.IntersectionOptions;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the intersection between two geometries.
 * @author Jared Erickson
 */
public class IntersectionCommand extends OtherGeometryCommand<IntersectionOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "intersection";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the intersection between two geometries.";
    }

    /**
     * Get a new IntersectionOptions
     * @return A new IntersectionOptions
     */
    @Override
    public IntersectionOptions getOptions() {
        return new IntersectionOptions();
    }

    /**
     * Calculate the intersection between the two geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The IntersectionOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, IntersectionOptions options, Reader reader, Writer writer) throws Exception {
        Geometry intersectionGeometry = geometry.intersection(other);
        writer.write(writeGeometry(intersectionGeometry, options));
    }

    /**
     * The IntersectionOptions
     */
    public static class IntersectionOptions extends OtherGeometryOptions {
    }
}
