package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
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
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, BufferOptions options) throws Exception {
        int capStyle;
        if (options.getEndCapStyle().equalsIgnoreCase("butt")
                || options.getEndCapStyle().equalsIgnoreCase("flat")) {
            capStyle = BufferParameters.CAP_FLAT;
        } else if (options.getEndCapStyle().equalsIgnoreCase("square")) {
            capStyle = BufferParameters.CAP_SQUARE;
        } else {
            capStyle = BufferParameters.CAP_ROUND;
        }
        Geometry bufferedGeometry = geometry.buffer(options.getDistance(), options.getQuadrantSegements(), capStyle);
        System.out.println(writeGeoemtry(bufferedGeometry, options));
    }

    /**
     * Options for the BufferCommand
     */
    public static class BufferOptions extends GeometryOptions {
            
        /**
         * The buffer distance
         */
        @Option(name = "-d", usage = "The buffer distance", required = true)
        private double distance;

        /**
         * The number of quadrant segments
         */
        @Option(name = "-quadrantSegments", usage = "The number of quadrant segments", required = false)
        private int quadrantSegements = 8;

        /**
         * The end cap style (round, flat/butt, square)
         */
        @Option(name = "-endCapStyle", usage = "The end cap style (round, flat/butt, square)", required = false)
        private String endCapStyle = "round";

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
    }
}
