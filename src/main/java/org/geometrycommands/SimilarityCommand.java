package org.geometrycommands;

import com.vividsolutions.jts.algorithm.match.AreaSimilarityMeasure;
import com.vividsolutions.jts.algorithm.match.HausdorffSimilarityMeasure;
import com.vividsolutions.jts.algorithm.match.SimilarityMeasure;
import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.SimilarityCommand.SimilarityOptions;
import org.kohsuke.args4j.Option;

/**
 * Calculate the degree of similarity between two Geometries
 * @author Jared Erickson
 */
public class SimilarityCommand extends OtherGeometryCommand<SimilarityOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "similarity";
    }

    /**
     * Get a new SimilarityOptions
     * @return A new SimilarityOptions
     */
    @Override
    public SimilarityOptions getOptions() {
        return new SimilarityOptions();
    }

    /**
     * Calculate the degree of similarity between two Geometries
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The SimilarityOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, SimilarityOptions options) throws Exception {
        SimilarityMeasure similarityMeasure;
        if (options.getAlgorithm().equalsIgnoreCase("area") || options.getAlgorithm().equalsIgnoreCase("a")) {
            similarityMeasure = new AreaSimilarityMeasure();
        } else if (options.getAlgorithm().equalsIgnoreCase("hausdorff") || options.getAlgorithm().equalsIgnoreCase("h")) {
            similarityMeasure = new HausdorffSimilarityMeasure();
        } else {
            throw new IllegalArgumentException("Unknown similarity measure algorithm!");
        }
        double similarity = similarityMeasure.measure(geometry, other);
        System.out.println(similarity);
    }

    /**
     * The SimilarityOptions
     */
    public static class SimilarityOptions extends OtherGeometryOptions {

        /**
         * The algorithm (area/a or hausdorff/h)
         */
        @Option(name = "-a", usage = "The algorithm (area/a or hausdorff/h)", required = true)
        private String algorithm;

        /**
         * Get the algorithm
         * @return The algorithm
         */
        public String getAlgorithm() {
            return algorithm;
        }

        /**
         * Set the algorithm
         * @param algorithm The algorithm
         */
        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }
}
