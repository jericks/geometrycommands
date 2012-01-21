package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Lineal;
import com.vividsolutions.jts.linearref.LengthIndexedLine;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.SubLineCommand.SubLineOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to extract a sub-line from a linear Geometry.
 * @author Jared Erickson
 */
public class SubLineCommand extends GeometryCommand<SubLineOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "subline";
    }

    /**
     * Get a new SubLineOptions
     * @return A new SubLineOptions
     */
    @Override
    public SubLineOptions getOptions() {
        return new SubLineOptions();
    }

    /**
     * Extract a sub-line from a linear Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, SubLineOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof Lineal)) {
            throw new IllegalArgumentException("The input geometry a LineString or a MultiLineString!");
        }
        if (options.getStartPosition() > options.getEndPosition()) {
            throw new IllegalArgumentException("The start position must be less than the end position!");
        }
        LengthIndexedLine indexedLine = new LengthIndexedLine(geometry);
        double length = geometry.getLength();
        Geometry subLine = indexedLine.extractLine(options.getStartPosition() * length, options.getEndPosition() * length);
        writer.write(writeGeometry(subLine, options));
    }

    /**
     * The SubLineOptions
     */
    public static class SubLineOptions extends GeometryOptions {

        /**
         * The start position between 0 and 1
         */
        @Option(name = "-s", usage = "The start position between 0 and 1", required = true)
        private double startPosition;

        /**
         * The end position between 0 and 1
         */
        @Option(name = "-e", usage = "The end position between 0 and 1", required = true)
        private double endPosition;

        /**
         * Get the start position between 0 and 1
         * @return The start position between 0 and 1
         */
        public double getStartPosition() {
            return startPosition;
        }

        /**
         * Set the start position between 0 and 1
         * @param position The start position between 0 and 1
         */
        public void setStartPosition(double position) {
            this.startPosition = position;
        }

        /**
         * Get the end position between 0 and 1
         * @return The end position between 0 and 1
         */
        public double getEndPosition() {
            return endPosition;
        }

        /**
         * Set the end position between 0 and 1
         * @param position The end position between 0 and 1
         */
        public void setEndPosition(double position) {
            this.endPosition = position;
        }
    }
}
