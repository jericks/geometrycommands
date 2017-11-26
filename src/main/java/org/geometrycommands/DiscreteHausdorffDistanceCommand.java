package org.geometrycommands;

import org.geometrycommands.DiscreteHausdorffDistanceCommand.DiscreteHausdorffDistanceOptions;
import org.locationtech.jts.algorithm.distance.DiscreteHausdorffDistance;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the discrete hausdorff distance between two Geometries
 * @author Jared Erickson
 */
public class DiscreteHausdorffDistanceCommand extends OtherGeometryCommand<DiscreteHausdorffDistanceOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "hausdorffdistance";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the discrete hausdorff distance between the two input geometries.";
    }

    /**
     * Get a new DiscreteHausdorffDistanceOptions
     * @return A new DiscreteHausdorffDistanceOptions
     */
    @Override
    public DiscreteHausdorffDistanceOptions getOptions() {
        return new DiscreteHausdorffDistanceOptions();
    }

    /**
     * Calculate the discrete hausdorff distance between two Geometries
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The DiscreteHausdorffDistanceOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, DiscreteHausdorffDistanceOptions options, Reader reader, Writer writer) throws Exception {
        double distance = DiscreteHausdorffDistance.distance(geometry, other);
        writer.write(String.valueOf(distance));
    }

    /**
     * The DiscreteHausdorffDistanceOptions
     */
    public static class DiscreteHausdorffDistanceOptions extends OtherGeometryOptions {
    }
}
