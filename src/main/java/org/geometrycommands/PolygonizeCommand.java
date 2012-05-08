package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

/**
 * A Command to create Polygons from Lines
 * @author jericks
 */
public class PolygonizeCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "polygonize";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Creates polygons from lines.";
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
     * Create Polygons from the input Geometry.  Often the input Geometry
     * needs to be noded first.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        Polygonizer polygonizer = new Polygonizer();
        int n = geometry.getNumGeometries();
        for (int i = 0; i < n; i++) {
            Geometry g = geometry.getGeometryN(i);
            polygonizer.add(g);
        }
        Collection polygons = polygonizer.getPolygons();
        MultiPolygon multiPolygon = geometry.getFactory().createMultiPolygon((Polygon[]) polygons.toArray(new Polygon[]{}));
        writer.write(writeGeometry(multiPolygon, options));
    }
}
