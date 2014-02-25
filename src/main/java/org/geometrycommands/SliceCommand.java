package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geometrycommands.SliceCommand.SliceOptions;
import org.kohsuke.args4j.Option;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Get a subset of geometries using a start and end index.
 * @author Jared Erickson
 */
public class SliceCommand extends GeometryCommand<SliceOptions>{

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "slice";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get a subset of geometries using a start and end index.";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public SliceOptions getOptions() {
        return new SliceOptions();
    }

    /**
     * Process the input Geometry
     * @param geometry The input Geometry
     * @param options  The GeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, SliceOptions options, Reader reader, Writer writer) throws Exception {
        int len = geometry.getNumGeometries();
        int start = options.getStart();
        if (start < 0) {
            start = len + start;
        }
        if (Math.abs(start) > len) {
            throw new IllegalArgumentException("Start index can not be more than the number of items!");
        }
        int end = options.getEnd() == null ? len : options.getEnd();
        if (end < 0) {
            end = len + end;
        }
        if (Math.abs(end) > len) {
            throw new IllegalArgumentException("End index can not be more than the number of items!");
        }
        List<Geometry> geoms = new ArrayList<Geometry>();
        for(int i = start; i < end; i++) {
            geoms.add(geometry.getGeometryN(i));
        }
        Geometry geom = new GeometryFactory().buildGeometry(geoms);
        writer.write(writeGeometry(geom, options));
    }


    /**
     * The SliceOptions
     */
    public static class SliceOptions extends GeometryOptions {

        /**
         * The start index number of the Geometry
         */
        @Option(name="-s", aliases = "--start", usage="The start index number", required=true)
        private Integer start;

        /**
         * The end index number of the Geometry
         */
        @Option(name="-e", aliases = "--end", usage="The end index number", required=false)
        private Integer end = null;

        /**
         * Get the start index number
         * @return The start index number
         */
        public Integer getStart() {
            return start;
        }

        /**
         * Set the start index number
         * @param start The start index number
         */
        public void setStart(Integer start) {
            this.start = start;
        }

        /**
         * Get the end index number
         * @return The end index number
         */
        public Integer getEnd() {
            return end;
        }

        /**
         * Set the end index number
         * @param end The  end index number
         */
        public void setEnd(Integer end) {
            this.end = end;
        }
    }
}
