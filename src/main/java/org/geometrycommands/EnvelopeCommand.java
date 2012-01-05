package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Calculate the input Geometries Envelope
 * @author Jared Erickson
 */
public class EnvelopeCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "envelope";
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
     * Calculate the input Geometries Envelope
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options) throws Exception {
        Geometry outputGeometry = geometry.getEnvelope();
        System.out.println(writeGeoemtry(outputGeometry, options));
    }
}
