package org.geometrycommands;

import org.geometrycommands.ConcaveHullOfPolygonsCommand.ConcaveHullOfPolygonsOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.algorithm.hull.ConcaveHullOfPolygons;
import org.locationtech.jts.geom.Geometry;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate the concave hull of the input geometry
 * @author Jared Erickson
 */
public class ConcaveHullOfPolygonsCommand extends GeometryCommand<ConcaveHullOfPolygonsOptions> {

     /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "concaveHullOfPolygons";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the concave hull of Polygons.";
    }

    /**
     * Get the new ConcaveHullOfPolygonsOptions
     * @return A new ConcaveHullOfPolygonsOptions
     */
    @Override
    public ConcaveHullOfPolygonsOptions getOptions() {
        return new ConcaveHullOfPolygonsOptions();
    }

    /**
     * Calculate the concave hull of the input geometry
     * @param geometry The input geometry
     * @param options The ConcaveHullOfPolygonsOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ConcaveHullOfPolygonsOptions options, Reader reader, Writer writer) throws Exception {
        ConcaveHullOfPolygons concaveHull = new ConcaveHullOfPolygons(geometry);
        concaveHull.setHolesAllowed(options.isHolesAllowed());
        concaveHull.setTight(options.isTight());
        concaveHull.setMaximumEdgeLength(options.getMaximumEdgeLength());
        Geometry outputGeometry = concaveHull.getHull();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The ConcaveHullOfPolygonsOptions
     */
    public static class ConcaveHullOfPolygonsOptions extends GeometryOptions {

        @Option(name = "-l", aliases = "--max-edge-length", usage = "The maximum edge length", required = false)
        private double maximumEdgeLength = 0.0;

        @Option(name = "-a", aliases = "--holes-allowed", usage = "Whether holes are allowed or not", required = false)
        private boolean holesAllowed = false;

        @Option(name = "-t", aliases = "--tight", usage = "Whether it should be tight or now", required = false)
        private boolean isTight = false;

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

        public boolean isTight() {
            return isTight;
        }

        public void setTight(boolean tight) {
            isTight = tight;
        }
    }
}
