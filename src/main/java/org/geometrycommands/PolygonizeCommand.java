package org.geometrycommands;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;
import org.kohsuke.args4j.Option;
import org.geometrycommands.PolygonizeCommand.PolygonizeOptions;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

/**
 * A Command to create Polygons from Lines
 * @author Jared Erickson
 */
public class PolygonizeCommand extends GeometryCommand<PolygonizeOptions> {

    /**
     * Get the name of the command
     *
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "polygonize";
    }

    /**
     * Get the description of what the Command does
     *
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Creates polygons from lines.";
    }

    /**
     * Get a new GeometryOptions
     *
     * @return A new GeometryOptions
     */
    @Override
    public PolygonizeOptions getOptions() {
        return new PolygonizeOptions();
    }

    /**
     * Create Polygons from the input Geometry.  Often the input Geometry
     * needs to be noded first.
     *
     * @param geometry The input Geometry
     * @param options  The PolygonizeOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void processGeometry(Geometry geometry, PolygonizeOptions options, Reader reader, Writer writer) throws Exception {
        Polygonizer polygonizer = new Polygonizer();
        int n = geometry.getNumGeometries();
        for (int i = 0; i < n; i++) {
            Geometry g = geometry.getGeometryN(i);
            polygonizer.add(g);
        }
        Geometry geomToWrite;
        Collection polys = polygonizer.getPolygons();
        MultiPolygon multiPolygon = geometry.getFactory().createMultiPolygon((Polygon[]) polys.toArray(new Polygon[polys.size()]));
        if (!options.isFull()) {
            geomToWrite = multiPolygon;
        } else {
            MultiLineString cutEdges = geometry.getFactory().createMultiLineString(convert(polygonizer.getCutEdges()));
            MultiLineString dangles = geometry.getFactory().createMultiLineString(convert(polygonizer.getDangles()));
            MultiLineString invalidRingLines = geometry.getFactory().createMultiLineString(convert(polygonizer.getInvalidRingLines()));
            geomToWrite = geometry.getFactory().createGeometryCollection(new Geometry[]{
                    multiPolygon, cutEdges, dangles, invalidRingLines
            });
        }
        writer.write(writeGeometry(geomToWrite, options));
    }

    /**
     * Convert a Collection of LinesStrings to an Array of LineStrings
     * @param lines The Collection of LineStrings
     * @return An Array of LineStrings
     */
    private LineString[] convert(Collection<LineString> lines) {
        return lines.toArray(new LineString[lines.size()]);
    }

    /**
     * The GeometryOptions subclass for the PolygonizeCommand
     */
    public static class PolygonizeOptions extends GeometryOptions {

        /**
         * The flag to include the full report
         */
        @Option(name = "-f", aliases = "--full", usage = "Whether to include a full report (polygons, cutEdges, dangles, and invalidRingLines)", required = false)
        private boolean full = false;

        /**
         * Get the full report flag
         *
         * @return The full report flag
         */
        public boolean isFull() {
            return full;
        }

        /**
         * Set the full report flag
         *
         * @param full The full report flag
         */
        public void setFull(boolean full) {
            this.full = full;
        }
    }
}
