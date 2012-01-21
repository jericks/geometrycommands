package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.IsWithDistanceCommand.IsWithDistanceOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to determine if the input Geometry is with the given distance of the other Geometry.
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
     * Determine if the input Geometry is with the given distance of the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The IsWithDistanceOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, IsWithDistanceOptions options, Reader reader, Writer writer) throws Exception {
        boolean isWithDistance = geometry.isWithinDistance(other, options.getDistance());
        writer.write(String.valueOf(isWithDistance));
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
