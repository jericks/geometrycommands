package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.buffer.BufferOp;
import org.locationtech.jts.operation.buffer.BufferParameters;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.BufferCommand.BufferOptions;
import org.kohsuke.args4j.Option;

/**
 * Buffer a Geometry by a distance.
 * @author Jared Erickson
 */
public class BufferCommand extends GeometryCommand<BufferOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "buffer";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Buffer a geometry by a distance.";
    }

    /**
     * Get a new BufferOptions
     * @return The new BufferOptions
     */
    @Override
    public BufferOptions getOptions() {
        return new BufferOptions();
    }

    /**
     * Buffer a Geometry by a distance.
     * @param geometry The Geometry
     * @param options The BufferOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, BufferOptions options, Reader reader, Writer writer) throws Exception {
        int capStyle;
        if (options.getEndCapStyle().equalsIgnoreCase("butt")
                || options.getEndCapStyle().equalsIgnoreCase("flat")) {
            capStyle = BufferParameters.CAP_FLAT;
        } else if (options.getEndCapStyle().equalsIgnoreCase("square")) {
            capStyle = BufferParameters.CAP_SQUARE;
        } else {
            capStyle = BufferParameters.CAP_ROUND;
        }

        int joinStyle;
        if (options.getJoinStyle().equalsIgnoreCase("mitre")) {
            joinStyle = BufferParameters.JOIN_MITRE;
        } else if (options.getJoinStyle().equalsIgnoreCase("bevel")) {
            joinStyle = BufferParameters.JOIN_BEVEL;
        } else {
            joinStyle = BufferParameters.JOIN_ROUND;
        }

        BufferParameters params = new BufferParameters();
        params.setSingleSided(options.isSingleSided());
        params.setQuadrantSegments(options.getQuadrantSegements());
        params.setEndCapStyle(capStyle);
        params.setSimplifyFactor(options.getSimplifyFactor());
        params.setMitreLimit(options.getMitreLimit());
        params.setJoinStyle(joinStyle);
        
        BufferOp bufferOp = new BufferOp(geometry, params);
        Geometry bufferedGeometry = bufferOp.getResultGeometry(options.getDistance());

        writer.write(writeGeometry(bufferedGeometry, options));
    }

    /**
     * Options for the BufferCommand
     */
    public static class BufferOptions extends GeometryOptions {

        /**
         * The buffer distance
         */
        @Option(name = "-d", aliases = "--distance", usage = "The buffer distance", required = true)
        private double distance;

        /**
         * The number of quadrant segments
         */
        @Option(name = "-q", aliases = "--quadrantSegments", usage = "The number of quadrant segments", required = false)
        private int quadrantSegements = 8;

        /**
         * The end cap style (round, flat/butt, square)
         */
        @Option(name = "-c", aliases = "--endCapStyle", usage = "The end cap style (round, flat/butt, square)", required = false)
        private String endCapStyle = "round";

        /**
         * The flag for whether the buffer should be single sided
         */
        @Option(name = "-s", aliases = "--singleSided", usage = "The flag for whether the buffer should be single sided", required = false)
        private boolean singleSided;

        /**
         * The simplify factor
         */
        @Option(name = "-f", aliases = "--simplifyFactor", usage = "The simplify factor", required = false)
        private double simplifyFactor = BufferParameters.DEFAULT_SIMPLIFY_FACTOR;

        /**
         * The mitre limit
         */
        @Option(name = "-m", aliases = "--mitreLimit", usage = "The mitre limit", required = false)
        private double mitreLimit = BufferParameters.DEFAULT_MITRE_LIMIT;

        /**
         * The join style
         */
        @Option(name = "-j", aliases = "--joinStyle", usage = "The join style (round, mitre, bevel)", required = false)
        private String joinStyle = "round";

        /**
         * Get the buffer distance
         * @return The buffer distance
         */
        public double getDistance() {
            return distance;
        }

        /**
         * Set the buffer distance
         * @param distance The buffer distance
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }

        /**
         * Get the end cap style (round, flat/butt, square)
         * @return The end cap style (round, flat/butt, square)
         */
        public String getEndCapStyle() {
            return endCapStyle;
        }

        /**
         * Set the end cap style (round, flat/butt, square)
         * @param endCapStyle The end cap style (round, flat/butt, square)
         */
        public void setEndCapStyle(String endCapStyle) {
            this.endCapStyle = endCapStyle;
        }

        /**
         * Get the number of quadrant segments
         * @return The number of quadrant segments
         */
        public int getQuadrantSegements() {
            return quadrantSegements;
        }

        /**
         * Set the number of quadrant segments
         * @param quadrantSegements The number of quadrant segments
         */
        public void setQuadrantSegements(int quadrantSegements) {
            this.quadrantSegements = quadrantSegements;
        }

        /**
         * Get the flag for whether the buffer should be single sided
         * @return The flag for whether the buffer should be single sided
         */
        public boolean isSingleSided() {
            return singleSided;
        }

        /**
         * Set the flag for whether the buffer should be single sided
         * @param singleSided The flag for whether the buffer should be single sided
         */
        public void setSingleSided(boolean singleSided) {
            this.singleSided = singleSided;
        }

        /**
         * Get the simplify factor
         * @return The simplify factor
         */
        public double getSimplifyFactor() {
            return simplifyFactor;
        }

        /**
         * Set the simplify factor
         * @param simplifyFactor
         */
        public void setSimplifyFactor(double simplifyFactor) {
            this.simplifyFactor = simplifyFactor;
        }

        /**
         * Get the mitre limit
         * @return The mitre limit
         */
        public double getMitreLimit() {
            return mitreLimit;
        }

        /**
         * Set the mitre limit
         * @param mitreLimit The mitre limit
         */
        public void setMitreLimit(double mitreLimit) {
            this.mitreLimit = mitreLimit;
        }

        /**
         * Get the join style
         * @return The join style
         */
        public String getJoinStyle() {
            return joinStyle;
        }

        /**
         * Set the join style
         * @param joinStyle The join style
         */
        public void setJoinStyle(String joinStyle) {
            this.joinStyle = joinStyle;
        }
    }
}
