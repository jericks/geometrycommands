package org.geometrycommands;

import org.geometrycommands.ConcaveHullCommand.ConcaveHullOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.algorithm.hull.ConcaveHull;
import org.locationtech.jts.geom.Geometry;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate the concave hull of the input geometry
 * @author Jared Erickson
 */
public class ConcaveHullCommand extends GeometryCommand<ConcaveHullOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "concaveHull";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the concave hull of a Geometry.";
    }

    /**
     * Get the new ConcaveHullOptions
     * @return A new ConcaveHullOptions
     */
    @Override
    public ConcaveHullOptions getOptions() {
        return new ConcaveHullOptions();
    }

    /**
     * Calculate the concave hull of the input geometry
     * @param geometry The input geometry
     * @param options The ConcaveHullOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ConcaveHullOptions options, Reader reader, Writer writer) throws Exception {
        ConcaveHull concaveHull = new ConcaveHull(geometry);
        concaveHull.setHolesAllowed(options.isHolesAllowed());
        concaveHull.setMaximumEdgeLength(options.getMaximumEdgeLength());
        Geometry outputGeometry = concaveHull.getHull();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The ConcaveHullOptions
     */
    public static class ConcaveHullOptions extends GeometryOptions {

        @Option(name = "-m", aliases = "--max-edge-length", usage = "The maximum edge length", required = false)
        private double maximumEdgeLength = 0.0;

        @Option(name = "-a", aliases = "--holes-allowed", usage = "Whether holes are allowed or not", required = false)
        private boolean holesAllowed = false;

        public double getMaximumEdgeLength() {
            return maximumEdgeLength;
        }

        public void setMaximumEdgeLength(double maximumEdgeLength) {
            this.maximumEdgeLength = maximumEdgeLength;
        }

        public boolean isHolesAllowed() {
            return holesAllowed;
        }

        public void setHolesAllowed(boolean holesAllowed) {
            this.holesAllowed = holesAllowed;
        }
    }
}
