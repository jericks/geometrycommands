package org.geometrycommands;

import org.geometrycommands.CountGeometriesCommand.CountGeometryOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to count the number of Geometries in the input Geometry
 * @author Jared Erickson
 */
public class CountGeometriesCommand extends GeometryCommand<CountGeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "count";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Count the number of geometries in the input geometry.";
    }

    /**
     * Get a new CountGeometryOptions
     * @return A new CountGeometryOptions
     */
    @Override
    public CountGeometryOptions getOptions() {
        return new CountGeometryOptions();
    }

    /**
     * Count the number of Geometries in the input Geometry.
     * @param geometry The input Geometry
     * @param options The CountGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, CountGeometryOptions options, Reader reader, Writer writer) throws Exception {
        writer.write(String.valueOf(geometry.getNumGeometries()));
    }

    /**
     * The CountGeometryOptions
     */
    public static class CountGeometryOptions extends GeometryOptions {
    }
}
