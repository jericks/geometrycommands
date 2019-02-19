package org.geometrycommands;

import org.geometrycommands.MortonCurveCommand.MortonCurveOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.shape.fractal.MortonCurveBuilder;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create a Morton Curve Geometry.
 * @author Jared Erickson
 */
public class MortonCurveCommand extends GeometryCommand<MortonCurveOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "mortoncurve";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a morton curve.";
    }

    /**
     * Get the new MortonCurveOptions
     * @return The new MortonCurveOptions
     */
    @Override
    public MortonCurveOptions getOptions() {
        return new MortonCurveOptions();
    }

    /**
     * Create a Morton Curve as a Geometry.
     * @param geometry The input Geometry
     * @param options The MortonCurveOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, MortonCurveOptions options, Reader reader, Writer writer) throws Exception {
        MortonCurveBuilder builder = new MortonCurveBuilder(new GeometryFactory());
        builder.setExtent(geometry.getEnvelopeInternal());
        builder.setNumPoints(options.getNumberOfPoints());
        Geometry outputGeometry = builder.getGeometry();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The MortonCurveOptions
     */
    public static class MortonCurveOptions extends GeometryOptions {

        /**
         * The number of points.
         */
        @Option(name = "-n", aliases = "--number", usage = "The number of points.", required = true)
        private int numberOfPoints;

        /**
         * Get the number of points.
         * @return The number of points.
         */
        public int getNumberOfPoints() {
            return numberOfPoints;
        }

        /**
         * Set the number of points.
         * @param numberOfPoints The number of points. 
         */
        public void setNumberOfPoints(int numberOfPoints) {
            this.numberOfPoints = numberOfPoints;
        }
    }
}
