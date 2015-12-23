package org.geometrycommands;

import org.geometrycommands.LineMergeCommand.LineMergeOptions;
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
public class LineMergeCommand extends GeometryCommand<LineMergeOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "linemerge";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Merge lines of the input geoemtry together.";
    }

    /**
     * Get a new LineMergeOptions
     * @return A new LineMergeOptions
     */
    @Override
    public LineMergeOptions getOptions() {
        return new LineMergeOptions();
    }

    /**
     * Merge the lines of the input Geometry.
     * @param geometry The input Geometry
     * @param options The LineMergeOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, LineMergeOptions options, Reader reader, Writer writer) throws Exception {
        LineMerger lineMerger = new LineMerger();
        int n = geometry.getNumGeometries();
        for (int i = 0; i < n; i++) {
            Geometry g = geometry.getGeometryN(i);
            lineMerger.add(g);
        }
        Collection lines = lineMerger.getMergedLineStrings();
        MultiLineString multiLineString = geometry.getFactory().createMultiLineString((LineString[]) lines.toArray(new LineString[lines.size()]));
        writer.write(writeGeometry(multiLineString, options));
    }

    /**
     * The LineMergeOptions
     */
    public static class LineMergeOptions extends GeometryOptions {
    }
}
