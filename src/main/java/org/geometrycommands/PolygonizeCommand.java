package org.geometrycommands;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;
import org.kohsuke.args4j.Option;
import org.geometrycommands.PolygonizeCommand.PolygonizeOptions;
import java.io.Reader;
import java.io.Writer;

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
    @Override
    protected void processGeometry(Geometry geometry, PolygonizeOptions options, Reader reader, Writer writer) throws Exception {
        Polygonizer polygonizer = new Polygonizer();
        int n = geometry.getNumGeometries();
        for (int i = 0; i < n; i++) {
            Geometry g = geometry.getGeometryN(i);
            polygonizer.add(g);
        }
        Geometry geomToWrite;
        MultiPolygon multiPolygon = geometry.getFactory().createMultiPolygon((Polygon[]) polygonizer.getPolygons().toArray(new Polygon[]{}));
        if (!options.isFull()) {
            geomToWrite = multiPolygon;
        } else {
            MultiLineString cutEdges = geometry.getFactory().createMultiLineString((LineString[]) polygonizer.getCutEdges().toArray(new LineString[]{}));
            MultiLineString dangles = geometry.getFactory().createMultiLineString((LineString[]) polygonizer.getDangles().toArray(new LineString[]{}));
            MultiLineString invalidRingLines = geometry.getFactory().createMultiLineString((LineString[]) polygonizer.getInvalidRingLines().toArray(new LineString[]{}));
            geomToWrite = geometry.getFactory().createGeometryCollection(new Geometry[]{
                    multiPolygon, cutEdges, dangles, invalidRingLines
            });
        }
        writer.write(writeGeometry(geomToWrite, options));
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
