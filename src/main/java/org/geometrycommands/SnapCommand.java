package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.operation.overlay.snap.GeometrySnapper;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.SnapCommand.SnapOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command for snapping the input geometry to another geometry.
 * @author Jared Erickson
 */
public class SnapCommand extends OtherGeometryCommand<SnapOptions> {

    /**
     * The command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "snap";
    }

    /**
     * Get the SnapOptions
     * @return The SnapOptions
     */
    @Override
    public SnapOptions getOptions() {
        return new SnapOptions();
    }

    /**
     * Snap the input geometry to another geometry
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The SnapOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, SnapOptions options, Reader reader, Writer writer) throws Exception {
        Geometry[] geoms = GeometrySnapper.snap(geometry, other, options.getDistance());
        GeometryCollection geometryCollection = geometry.getFactory().createGeometryCollection(geoms);
        writer.write(writeGeometry(geometryCollection, options));
    }

    /**
     * The SnapOptions
     */
    public static class SnapOptions extends OtherGeometryOptions {

        /**
         * The distance/tolerance
         */
        @Option(name = "-d", usage = "The distance/tolerance", required = true)
        private double distance;

        /**
         * Get the distance/tolerance
         * @return The distance/tolerance
         */
        public double getDistance() {
            return distance;
        }

        /**
         * Set the distance/tolerance
         * @param distance The distance/tolerance
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
