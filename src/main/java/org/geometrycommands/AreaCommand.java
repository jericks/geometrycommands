package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the area of a Geometry
 * @author Jared Erickson
 */
public class AreaCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "area";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public GeometryOptions getOptions() {
        return new GeometryOptions();
    }

    /**
     * Calculate the area of a Geometry
     * @param geometry The Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        double distance = geometry.getArea();
        writer.write(String.valueOf(distance));
    }
}
