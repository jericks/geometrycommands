package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Lineal;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to get the end point of a LineString or a MultiLineString.
 * @author Jared Erickson
 */
public class GetEndPointCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "endpoint";
    }

    /**
     * Get the GeometryOptions
     * @return The GeometryOptions
     */
    @Override
    public GeometryOptions getOptions() {
        return new GeometryOptions();
    }

    /**
     * Get the end Point of a LineString or a MultiLineString
     * @param geometry The Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs especially if the input Geometry is not Lineal
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof Lineal)) {
            throw new IllegalArgumentException("The input geometry a LineString or a MultiLineString!");
        }
        LineString lineString;
        if (geometry instanceof MultiLineString) {
            int i = geometry.getNumGeometries() - 1;
            lineString = (LineString)((MultiLineString)geometry).getGeometryN(i);
        }
        else {
            lineString = (LineString)geometry;
        }
        Point point = lineString.getEndPoint();
        writer.write(writeGeometry(point, options));
    }
}
