package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.IsWithinDistanceCommand.IsWithinDistanceOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to determine if the input Geometry is with in the given distance of the other Geometry.
 * @author Jared Erickson
 */
public class IsWithinDistanceCommand extends OtherGeometryCommand<IsWithinDistanceOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "iswithindistance";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is within the given distance of the other geometry.";
    }

    /**
     * Get a new IsWithinDistanceOptions
     * @return A new IsWithinDistanceOptions
     */
    @Override
    public IsWithinDistanceOptions getOptions() {
        return new IsWithinDistanceOptions();
    }

    /**
     * Determine if the input Geometry is with in the given distance of the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The IsWithinDistanceOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, IsWithinDistanceOptions options, Reader reader, Writer writer) throws Exception {
        boolean isWithDistance = geometry.isWithinDistance(other, options.getDistance());
        writer.write(String.valueOf(isWithDistance));
    }

    /**
     * IsWithinDistanceOptions
     */
    public static class IsWithinDistanceOptions extends OtherGeometryOptions {

        /**
         * The distance
         */
        @Option(name = "-d", aliases = "--distance", usage = "The distance", required = true)
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
