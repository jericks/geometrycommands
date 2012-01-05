package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.triangulate.ConformingDelaunayTriangulationBuilder;
import com.vividsolutions.jts.triangulate.DelaunayTriangulationBuilder;
import org.geometrycommands.DelaunayTriangulationCommand.DelaunayTriangulationOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that generates a Delaunay Triangulation for the input Geometry.
 * @author Jared Erickson
 */
public class DelaunayTriangulationCommand extends GeometryCommand<DelaunayTriangulationOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "delaunay";
    }

    /**
     * Get a new DelaunayTriangulationOptions
     * @return A new DelaunayTriangulationOptions
     */
    @Override
    public DelaunayTriangulationOptions getOptions() {
        return new DelaunayTriangulationOptions();
    }

    /**
     * Calculate a Delaunay Triangulation of the input Geometry
     * @param geometry The input Geometry
     * @param options The DelaunayTriangulationOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, DelaunayTriangulationOptions options) throws Exception {
        Geometry outputGeometry;
        if (options.isConforming()) {
            ConformingDelaunayTriangulationBuilder builder = new ConformingDelaunayTriangulationBuilder();
            builder.setSites(geometry);
            outputGeometry = builder.getTriangles(geometry.getFactory());
        } else {
            DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
            builder.setSites(geometry);
            outputGeometry = builder.getTriangles(geometry.getFactory());
        }
        System.out.println(writeGeoemtry(outputGeometry, options));
    }

    /**
     * Options for the DelaunayTriangulationCommand
     */
    public static class DelaunayTriangulationOptions extends GeometryOptions {

        /**
         * The flag for whether to use conforming algorithm
         */
        @Option(name = "-conforming", usage = "The flag for whether to use conforming algorithm", required = false)
        private boolean conforming;

        /**
         * Get the flag for whether to use conforming algorithm
         * @return The flag for whether to use conforming algorithm
         */
        public boolean isConforming() {
            return conforming;
        }

        /**
         * Set the flag for whether to use conforming algorithm
         * @param conforming The flag for whether to use conforming algorithm
         */
        public void setConforming(boolean conforming) {
            this.conforming = conforming;
        }
    }
}
