package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to get the geomtry type of the input Geometry
 * @author Jared Erickson
 */
public class GeometryTypeCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "type";
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
     * Count the number of Points in the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        writer.write(geometry.getGeometryType());
    }
}
