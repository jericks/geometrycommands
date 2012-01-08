package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.IsWithDistanceCommand.IsWithDistanceOptions;
import org.kohsuke.args4j.Option;

/**
 * Calculate the intersection between two geometries.
 * @author Jared Erickson
 */
public class IsWithDistanceCommand extends OtherGeometryCommand<IsWithDistanceOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "iswithdistance";
    }

    /**
     * Get a new IsWithDistanceOptions
     * @return A new IsWithDistanceOptions
     */
    @Override
    public IsWithDistanceOptions getOptions() {
        return new IsWithDistanceOptions();
    }

    /**
     * Calculate the intersection between the two geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The IsWithDistanceOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometries(Geometry geometry, Geometry other, IsWithDistanceOptions options) throws Exception {
        boolean isWithDistance = geometry.isWithinDistance(other, options.getDistance());
        System.out.println(isWithDistance);
    }

    /**
     * IsWithDistanceOptions
     */
    public static class IsWithDistanceOptions extends OtherGeometryOptions {

        /**
         * The distance
         */
        @Option(name = "-d", usage = "The distance", required = true)
        private double distance;

        /**
         * Get the distance
         * @return The distance
         */
        public double getDistance() {
            return distance;
        }

        /**
         * Set the distance
         * @param distance The distance
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
