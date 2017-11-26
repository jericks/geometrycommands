package org.geometrycommands;

import org.geometrycommands.GeometryTypeCommand.GeometryTypeOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to get the geometry type of the input Geometry
 * @author Jared Erickson
 */
public class GeometryTypeCommand extends GeometryCommand<GeometryTypeOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "type";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the type of the geometry.";
    }

    /**
     * Get a new GeometryTypeOptions
     * @return A new GeometryTypeOptions
     */
    @Override
    public GeometryTypeOptions getOptions() {
        return new GeometryTypeOptions();
    }

    /**
     * Count the number of Points in the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryTypeOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryTypeOptions options, Reader reader, Writer writer) throws Exception {
        writer.write(geometry.getGeometryType());
    }

    /**
     * The GeometryTypeOptions
     */
    public static class GeometryTypeOptions extends GeometryOptions {
    }
}
