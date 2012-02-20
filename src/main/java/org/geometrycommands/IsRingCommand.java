package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is a ring or not.
 * @author Jared Erickson
 */
public class IsRingCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isring";
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
     * Determine if the input Geometry is a ring or not.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof LineString)) {
            throw new IllegalArgumentException("Input geometry must be a LineString!");
        }
        boolean isRing = ((LineString) geometry).isRing();
        writer.write(String.valueOf(isRing));
    }
}
