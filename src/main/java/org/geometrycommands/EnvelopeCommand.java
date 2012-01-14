package org.geometrycommands;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.EnvelopeCommand.EnvelopeOptions;
import org.kohsuke.args4j.Option;

/**
 * Calculate the input Geometries Envelope
 * @author Jared Erickson
 */
public class EnvelopeCommand extends GeometryCommand<EnvelopeOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "envelope";
    }

    /**
     * Get a new EnvelopeOptions
     * @return A new EnvelopeOptions
     */
    @Override
    public EnvelopeOptions getOptions() {
        return new EnvelopeOptions();
    }

    /**
     * Calculate the input Geometries Envelope
     * @param geometry The input Geometry
     * @param options The EnvelopeOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, EnvelopeOptions options) throws Exception {
        Envelope env = geometry.getEnvelopeInternal();
        if (options.getExpandBy() > 0) {
            env.expandBy(options.getExpandBy());
        }
        Geometry outputGeometry = geometry.getFactory().toGeometry(env);
        System.out.println(writeGeoemtry(outputGeometry, options));
    }

    /**
     * The EnvelopeOptions
     */
    public static class EnvelopeOptions extends GeometryOptions {

        /**
         * The distance to expand the Envelope
         */
        @Option(name = "-expandBy", usage = "The distance to expand the Envelope", required = false)
        private double expandBy;

        /**
         * Get the distance to expand the Envelope
         * @return The distance to expand the Envelope
         */
        public double getExpandBy() {
            return expandBy;
        }

        /**
         * Set the distance to expand the Envelope
         * @param expandBy The distance to expand the Envelope
         */
        public void setExpandBy(double expandBy) {
            this.expandBy = expandBy;
        }
    }
}
