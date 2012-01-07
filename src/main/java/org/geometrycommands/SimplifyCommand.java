package org.geometrycommands;

import com.vividsolutions.jts.densify.Densifier;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import com.vividsolutions.jts.simplify.TopologyPreservingSimplifier;
import org.geometrycommands.SimplifyCommand.SimplifyOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to simplify the Coordinates of the input Geometry
 * @author Jared Erickson
 */
public class SimplifyCommand extends GeometryCommand<SimplifyOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "simplify";
    }

    /**
     * Get a new SimplifyOptions
     * @return A new SimplifyOptions
     */
    @Override
    public SimplifyOptions getOptions() {
        return new SimplifyOptions();
    }

    /**
     * Densify the Geometry
     * @param geometry The input Geometry
     * @param options The SimplifyOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, SimplifyOptions options) throws Exception {
        Geometry outputGeometry;
        if (options.getAlgorithm().equalsIgnoreCase("douglaspeucker")
                || options.getAlgorithm().equalsIgnoreCase("dp")) {
            outputGeometry = DouglasPeuckerSimplifier.simplify(geometry, options.getDistanceTolerance());
        } else if (options.getAlgorithm().equalsIgnoreCase("topologypreserving")
                || options.getAlgorithm().equalsIgnoreCase("tp")) {
            outputGeometry = TopologyPreservingSimplifier.simplify(geometry, options.getDistanceTolerance());
        } else {
            throw new IllegalArgumentException("Unknown simplifier algorithm!");
        }
        System.out.println(writeGeoemtry(outputGeometry, options));
    }

    /**
     * The SimplifyOptions
     */
    public static class SimplifyOptions extends GeometryOptions {

        /**
         * The algorithm (douglaspeucker/dp or topologypreserving/tp)
         */
        @Option(name = "-algorithm", usage = "The distance tolerance (douglaspeucker/dp or topologypreserving/tp)", required = true)
        private String algorithm;

        /**
         * The distance tolerance
         */
        @Option(name = "-d", usage = "The distance tolerance", required = true)
        private double distanceTolerance;

        /**
         * Get the distance tolerance
         * @return The distance tolerance
         */
        public double getDistanceTolerance() {
            return distanceTolerance;
        }

        /**
         * Set the distance tolerance
         * @param distanceTolerance The distance tolerance
         */
        public void setDistanceTolerance(double distanceTolerance) {
            this.distanceTolerance = distanceTolerance;
        }

        /**
         * Get the algorithm (douglaspeucker or topologypreserving)
         * @return The algorithm (douglaspeucker or topologypreserving)
         */
        public String getAlgorithm() {
            return algorithm;
        }

        /**
         * Set the algorithm (douglaspeucker or topologypreserving)
         * @param algorithm The algorithm (douglaspeucker or topologypreserving)
         */
        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }
}
