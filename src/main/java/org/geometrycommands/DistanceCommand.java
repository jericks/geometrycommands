package org.geometrycommands;

import org.geometrycommands.DistanceCommand.DistanceOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the distance between two Geometries
 * @author Jared Erickson
 */
public class DistanceCommand extends OtherGeometryCommand<DistanceOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "distance";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the distance between the two input geometries.";
    }

    /**
     * Get a new DistanceOptions
     * @return A new DistanceOptions
     */
    @Override
    public DistanceOptions getOptions() {
        return new DistanceOptions();
    }

    /**
     * Calculate the distance between two Geometries
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The DistanceOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, DistanceOptions options, Reader reader, Writer writer) throws Exception {
        double distance = geometry.distance(other);
        writer.write(String.valueOf(distance));
    }

    /**
     * The DistanceOptions
     */
    public static class DistanceOptions extends OtherGeometryOptions {
    }
}
