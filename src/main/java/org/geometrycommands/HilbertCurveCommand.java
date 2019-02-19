package org.geometrycommands;

import org.geometrycommands.HilbertCurveCommand.HilbertCurveOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.shape.fractal.HilbertCurveBuilder;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create a Hilbert Curve Geometry.
 * @author Jared Erickson
 */
public class HilbertCurveCommand extends GeometryCommand<HilbertCurveOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "hilbertcurve";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a hilbert curve.";
    }

    /**
     * Get the new HilbertCurveOptions
     * @return The new HilbertCurveOptions
     */
    @Override
    public HilbertCurveOptions getOptions() {
        return new HilbertCurveOptions();
    }

    /**
     * Create a Hilber Curve as a Geometry.
     * @param geometry The input Geometry
     * @param options The HilbertCurveOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, HilbertCurveOptions options, Reader reader, Writer writer) throws Exception {
        HilbertCurveBuilder builder = new HilbertCurveBuilder(new GeometryFactory());
        builder.setExtent(geometry.getEnvelopeInternal());
        builder.setNumPoints(options.getNumberOfPoints());
        Geometry outputGeometry = builder.getGeometry();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The HilbertCurveOptions
     */
    public static class HilbertCurveOptions extends GeometryOptions {

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
