package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.OctagonalEnvelope;

/**
 * Calculate the octagonal envelope of the input Geometry.
 * @author Jared Erickson
 */
public class OctagonalEnvelopeCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "octagonalenvelope";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public GeometryOptions getOptions() {
        return new GeometryOptions();
    }

    /**
     * Calculate the octagonal envelope of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        OctagonalEnvelope octagonalEnvelope = new OctagonalEnvelope(geometry);
        Geometry outputGeometry = octagonalEnvelope.toGeometry(geometry.getFactory());
        System.out.println(writeGeometry(outputGeometry, options));
    }
}
