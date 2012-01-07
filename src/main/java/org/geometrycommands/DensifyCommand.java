package org.geometrycommands;

import com.vividsolutions.jts.densify.Densifier;
import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.DensifyCommand.DensifyOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to densify the Coordinates of the input Geometry
 * @author Jared Erickson
 */
public class DensifyCommand extends GeometryCommand<DensifyOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "densify";
    }

    /**
     * Get a new DensifyOptions
     * @return A new DensifyOptions
     */
    @Override
    public DensifyOptions getOptions() {
        return new DensifyOptions();
    }

    /**
     * Densify the Geometry
     * @param geometry The input Geometry
     * @param options The DensifyOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, DensifyOptions options) throws Exception {
        Geometry outputGeometry = Densifier.densify(geometry, options.getDistanceTolerance());
        System.out.println(writeGeoemtry(outputGeometry, options));
    }

    /**
     * The DensifyOptions
     */
    public static class DensifyOptions extends GeometryOptions {
        
        /**
         * The distance tolerance
         */
        @Option(name="-d", usage="The distance tolerance", required=true)
        private double distanceTolerance;

        /**
         * Get the distance tolerance
         * @return The distance tolerance
         */
        public double getDistanceTolerance() {
            return distanceTolerance;
        }

        /**
         * Set the distance tolerance
         * @param distanceTolerance The distance tolerance
         */
        public void setDistanceTolerance(double distanceTolerance) {
            this.distanceTolerance = distanceTolerance;
        }
    }
}
