package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.RelateCommand.RelateOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to determine if the input Geometry and the other Geometry are related 
 * according to the DE-9IM intersection matrix or calculate the DE-9IM 
 * @author Jared Erickson
 */
public class RelateCommand extends OtherGeometryCommand<RelateOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "relate";
    }

    /**
     * Get the new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public RelateOptions getOptions() {
        return new RelateOptions();
    }

    /**
     * Either determine if the input Geometry and the other Geometry are related 
     * according to the DE-9IM intersection matrix or calculate the DE-9IM 
     * intersection matrix.
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The RelateOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, RelateOptions options) throws Exception {
        if (options.getIntersectionMatrix() != null) {
            boolean related = geometry.relate(other, options.getIntersectionMatrix());
            System.out.println(related);
        } else {
            String matrix = geometry.relate(other).toString();
            System.out.println(matrix);
        }
    }

    /**
     * The RelateOptions
     */
    public static class RelateOptions extends OtherGeometryOptions {

        /**
         * The DE-9IM intersection matrix
         */
        @Option(name = "-matrix", usage = "The DE-9IM intersection matrix", required = false)
        private String intersectionMatrix;

        /**
         * Get the DE-9IM intersection matrix
         * @return The DE-9IM intersection matrix
         */
        public String getIntersectionMatrix() {
            return intersectionMatrix;
        }

        /**
         * Set the DE-9IM intersection matrix
         * @param intersectionMatrix The DE-9IM intersection matrix
         */
        public void setIntersectionMatrix(String intersectionMatrix) {
            this.intersectionMatrix = intersectionMatrix;
        }
    }
}
