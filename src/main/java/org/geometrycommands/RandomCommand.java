package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygonal;
import com.vividsolutions.jts.shape.random.RandomPointsBuilder;
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
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, RandomCommandOptions options) throws Exception {
        if (!(geometry instanceof Polygonal)) {
            geometry = geometry.getEnvelope();
        }
        if (!(geometry instanceof Polygonal)) {
            throw new IllegalArgumentException("Geometry must be a Polygon or MultiPolygon!");
        }
        RandomPointsBuilder builder = new RandomPointsBuilder();
        builder.setExtent(geometry);
        builder.setNumPoints(options.getNumber());
        Geometry randomGeometry = builder.getGeometry();
        System.out.println(writeGeoemtry(randomGeometry, options));
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
    }
}
