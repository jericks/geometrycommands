package org.geometrycommands;

import org.geometrycommands.NarrowCommand.NarrowOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Convert a geometry collection to it's most specific type.
 * @author Jared Erickson
 */
public class NarrowCommand extends GeometryCommand<NarrowOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "narrow";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Convert a geometry collection to it's most specific type.";
    }

    /**
     * Get the new NarrowOptions
     * @return A new NarrowOptions
     */
    @Override
    public NarrowOptions getOptions() {
        return new NarrowOptions();
    }

    /**
     * Calculate the convex hull of the input geometry
     * @param geometry The input geometry
     * @param options The NarrowOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, NarrowOptions options, Reader reader, Writer writer) throws Exception {
        List<Geometry> geometries = new ArrayList<Geometry>();
        for(int i = 0; i<geometry.getNumGeometries(); i++) {
            geometries.add(geometry.getGeometryN(i));
        }
        Geometry outputGeometry = geometry.getFactory().buildGeometry(geometries);
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The NarrowOptions
     */
    public static class NarrowOptions extends GeometryOptions {
    }
}
