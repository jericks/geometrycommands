package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is simple or not.
 * @author Jared Erickson
 */
public class IsSimpleCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "issimple";
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
     * Determine if the input Geometry is simple or not.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        boolean simple = geometry.isSimple();
        writer.write(String.valueOf(simple));
    }
}
