package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.operation.linemerge.LineMerger;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

/**
 * A Command to merge the lines of the input Geometry.
 * @author Jared Erickson
 */
public class LineMergeCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "linemerge";
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
     * Merge the lines of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        LineMerger lineMerger = new LineMerger();
        int n = geometry.getNumGeometries();
        for (int i = 0; i < n; i++) {
            Geometry g = geometry.getGeometryN(i);
            lineMerger.add(g);
        }
        Collection lines = lineMerger.getMergedLineStrings();
        MultiLineString multiLineString = geometry.getFactory().createMultiLineString((LineString[]) lines.toArray(new LineString[]{}));
        writer.write(writeGeometry(multiLineString, options));
    }
}
