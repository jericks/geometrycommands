package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygonal;
import com.vividsolutions.jts.shape.random.RandomPointsBuilder;
import com.vividsolutions.jts.shape.random.RandomPointsInGridBuilder;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.RandomCommand.RandomCommandOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that generates random Points inside the input Geometry or Envelope
 * @author Jared Erickson
 */
public class RandomCommand extends GeometryCommand<RandomCommandOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "random";
    }

    /**
     * Get a new RandomCommandOptions
     * @return The new RandomCommandOptions
     */
    @Override
    public RandomCommandOptions getOptions() {
        return new RandomCommandOptions();
    }

    /**
     * Generate random Points inside the input Geometry or Envelope
     * @param geometry The input Geometry 
     * @param options The RandomCommandOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, RandomCommandOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof Polygonal)) {
            geometry = geometry.getEnvelope();
        }
        if (!(geometry instanceof Polygonal)) {
            throw new IllegalArgumentException("Geometry must be a Polygon or MultiPolygon!");
        }
        Geometry randomGeometry;
        if (options.isGridded()) {
            RandomPointsInGridBuilder builder = new RandomPointsInGridBuilder();
            builder.setExtent(geometry.getEnvelopeInternal());
            builder.setNumPoints(options.getNumber());
            builder.setConstrainedToCircle(options.isConstrainedToCircle());
            if (!Double.isNaN(options.getGutterFraction())) {
                builder.setGutterFraction(options.getGutterFraction());
            }
            randomGeometry = builder.getGeometry();
        } else {
            RandomPointsBuilder builder = new RandomPointsBuilder();
            builder.setExtent(geometry);
            builder.setNumPoints(options.getNumber());
            randomGeometry = builder.getGeometry();
        }

        writer.write(writeGeometry(randomGeometry, options));
    }

    /**
     * The RandomCommandOptions
     */
    public static class RandomCommandOptions extends GeometryOptions {

        /**
         * The number of points
         */
        @Option(name = "-n", usage = "The number of points", required = true)
        private int number;

        /**
         * The flag for whether the random points should be gridded.
         */
        @Option(name = "-gridded", usage = "The flag for whether the random points should be gridded.", required = false)
        private boolean gridded;

        /**
         * The flag for whether the random points should be constrained to a circle when gridded.
         */
        @Option(name = "-constrainedToCircle", usage = "The flag for whether the random points should be constrained to a circle when gridded.", required = false)
        private boolean constrainedToCircle;

        /**
         * The gutter distance or padding for random points when gridded.
         */
        @Option(name = "-gutterFraction", usage = "The gutter distance or padding for random points when gridded.", required = false)
        private double gutterFraction = Double.NaN;

        /**
         * Get the number of points
         * @return The number of points
         */
        public int getNumber() {
            return number;
        }

        /**
         * Set the number of points
         * @param number The number of points
         */
        public void setNumber(int number) {
            this.number = number;
        }

        /**
         * Get the flag for whether the random points should be constrained to a circle when gridded.
         * @return The flag for whether the random points should be constrained to a circle when gridded.
         */
        public boolean isConstrainedToCircle() {
            return constrainedToCircle;
        }

        /**
         * Set the flag for whether the random points should be constrained to a circle when gridded.
         * @param constrainedToCircle The flag for whether the random points should be constrained to a circle when gridded.
         */
        public void setConstrainedToCircle(boolean constrainedToCircle) {
            this.constrainedToCircle = constrainedToCircle;
        }

        /**
         * Get the flag for whether the random points should be gridded.
         * @return The flag for whether the random points should be gridded.
         */
        public boolean isGridded() {
            return gridded;
        }

        /**
         * Set the flag for whether the random points should be gridded.
         * @param gridded The flag for whether the random points should be gridded.
         */
        public void setGridded(boolean gridded) {
            this.gridded = gridded;
        }

        /**
         * Get the gutter distance or padding for random points when gridded.
         * @return The gutter distance or padding for random points when gridded.
         */
        public double getGutterFraction() {
            return gutterFraction;
        }

        /**
         * Set the gutter distance or padding for random points when gridded.
         * @param gutterFraction The gutter distance or padding for random points when gridded.
         */
        public void setGutterFraction(double gutterFraction) {
            this.gutterFraction = gutterFraction;
        }
    }
}
