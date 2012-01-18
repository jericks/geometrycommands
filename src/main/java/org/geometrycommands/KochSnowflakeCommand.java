package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.shape.fractal.KochSnowflakeBuilder;
import org.geometrycommands.KochSnowflakeCommand.KochSnowflakeOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to create a Koch snow flake as a Geometry.
 * @author Jared Erickson
 */
public class KochSnowflakeCommand extends GeometryCommand<KochSnowflakeOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "kochsnowflake";
    }

    /**
     * Get the new KochSnowflakeOptions
     * @return The new KochSnowflakeOptions
     */
    @Override
    public KochSnowflakeOptions getOptions() {
        return new KochSnowflakeOptions();
    }

    /**
     * Create a Koch snow flake as a Geometry.
     * @param geometry The input Geometry
     * @param options The KochSnowflakeOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, KochSnowflakeOptions options) throws Exception {
        KochSnowflakeBuilder builder = new KochSnowflakeBuilder(new GeometryFactory());
        builder.setExtent(geometry.getEnvelopeInternal());
        builder.setNumPoints(options.getNumberOfPoints());
        Geometry outputGeometry = builder.getGeometry();
        System.out.println(writeGeometry(outputGeometry, options));
    }

    /**
     * The KochSnowflakeOptions
     */
    public static class KochSnowflakeOptions extends GeometryOptions {

        /**
         * The number of points.
         */
        @Option(name = "-n", usage = "The number of points.", required = true)
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
