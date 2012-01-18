package org.geometrycommands;

import com.vividsolutions.jts.algorithm.distance.DiscreteHausdorffDistance;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Calculate the discrete hausdorff distance between two Geometries
 * @author Jared Erickson
 */
public class DiscreteHausdorffDistanceCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "hausdorffdistance";
    }

    /**
     * Get a new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public OtherGeometryOptions getOptions() {
        return new OtherGeometryOptions();
    }

    /**
     * Calculate the discrete hausdorff distance between two Geometries
     * @param geometry The Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        double distance = DiscreteHausdorffDistance.distance(geometry, other);
        System.out.println(distance);
    }
}
