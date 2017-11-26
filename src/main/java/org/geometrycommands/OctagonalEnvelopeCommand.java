package org.geometrycommands;

import org.geometrycommands.OctagonalEnvelopeCommand.OctagonalEnvelopeOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.OctagonalEnvelope;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the octagonal envelope of the input Geometry.
 * @author Jared Erickson
 */
public class OctagonalEnvelopeCommand extends GeometryCommand<OctagonalEnvelopeOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "octagonalenvelope";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the octagonal envelope of the input geometry.";
    }

    /**
     * Get a new OctagonalEnvelopeOptions
     * @return A new OctagonalEnvelopeOptions
     */
    @Override
    public OctagonalEnvelopeOptions getOptions() {
        return new OctagonalEnvelopeOptions();
    }

    /**
     * Calculate the octagonal envelope of the input Geometry.
     * @param geometry The input Geometry
     * @param options The OctagonalEnvelopeOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, OctagonalEnvelopeOptions options, Reader reader, Writer writer) throws Exception {
        OctagonalEnvelope octagonalEnvelope = new OctagonalEnvelope(geometry);
        Geometry outputGeometry = octagonalEnvelope.toGeometry(geometry.getFactory());
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The OctagonalEnvelopeOptions
     */
    public static class OctagonalEnvelopeOptions extends GeometryOptions {
    }
}
