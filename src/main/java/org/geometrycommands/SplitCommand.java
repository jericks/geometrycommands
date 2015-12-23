package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;
import org.geometrycommands.SplitCommand.SplitOptions;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Split a Geometry based on another Geometry
 * @author Jared Erickson
 */
public class SplitCommand extends OtherGeometryCommand<SplitOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "split";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Split a Geometry by another Geometry";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public SplitOptions getOptions() {
        return new SplitOptions();
    }

    /**
     * Process the input Geometry and another Geometry
     * @param geometry The input Geometry
     * @param other    The other Geometry
     * @param options  The OtherGeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, SplitOptions options, Reader reader, Writer writer) throws Exception {
        Geometry polygons = polygonize(geometry.getBoundary().union(other));
        List<Polygon> polygonsToKeep = new ArrayList<Polygon>();
        int num = polygons.getNumGeometries();
        for(int i=0; i<num; i++) {
            Geometry g = polygons.getGeometryN(i);
            if (polygons.contains(g.getInteriorPoint())) {
                polygonsToKeep.add((Polygon) g);
            }
        }
        Geometry splitGeom = polygonsToKeep.size() > 1 ? geometry.getFactory().createMultiPolygon(toPolygonArray(polygonsToKeep)) : (Geometry) polygonsToKeep.get(0);
        writer.write(writeGeometry(splitGeom, options));
    }

    private MultiPolygon polygonize(Geometry lineString) {
        Polygonizer polygonizer = new Polygonizer();
        int num = lineString.getNumGeometries();
        for(int i=0; i<num; i++) {
            polygonizer.add(lineString.getGeometryN(i));
        }
        Collection<Polygon> polygons = polygonizer.getPolygons();
        return lineString.getFactory().createMultiPolygon(toPolygonArray(polygons));
    }

    private Polygon[] toPolygonArray(Collection<Polygon> polygons) {
        return polygons.toArray(new Polygon[polygons.size()]);
    }

    /**
     * The SplitCommand's OtherGeometryOptions subclass
     */
    public static class SplitOptions extends OtherGeometryOptions {
    }
}
