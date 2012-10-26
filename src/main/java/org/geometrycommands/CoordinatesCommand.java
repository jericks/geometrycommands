package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.UniqueCoordinateArrayFilter;
import org.geometrycommands.CoordinatesCommand.CoordinatesOptions;
import org.kohsuke.args4j.Option;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command to get the Coordinates of the input Geometry.
 * @author Jared Erickson
 */
public class CoordinatesCommand extends GeometryCommand<CoordinatesOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "coordinates";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the coordinates of the geometry.";
    }

    /**
     * Get a new CoordinatesOptions
     * @return A new CoordinatesOptions
     */
    @Override
    public CoordinatesOptions getOptions() {
        return new CoordinatesOptions();
    }

    /**
     * Get the coordinates of the input Geometry
     * @param geometry The input Geometry
     * @param options The CoordinatesOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, CoordinatesOptions options, Reader reader, Writer writer) throws Exception {
        Coordinate[] coords = geometry.getCoordinates();
        Geometry outputGeometry = geometry.getFactory().createMultiPoint(coords);
        if (options.isUnique())  {
            UniqueCoordinateArrayFilter coordinateFilter = new UniqueCoordinateArrayFilter();
            outputGeometry.apply(coordinateFilter);
            outputGeometry = geometry.getFactory().createMultiPoint(coordinateFilter.getCoordinates());
        }
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * Options for the CoordinateCommand
     */
    public static class CoordinatesOptions extends GeometryOptions {

        /**
         * The flag to only include unique coordinates
         */
        @Option(name = "-u", aliases = "--unique", usage = "The flag to only include unique coordinates", required = false)
        private boolean unique;

        /**
         * Get the flag to only include unique coordinates
         * @return The flag to only include unique coordinates
         */
        public boolean isUnique() {
            return unique;
        }

        /**
         * Set the flag to only include unique coordinates
         * @param unique The flag to only include unique coordinates
         */
        public void setUnique(boolean unique) {
            this.unique = unique;
        }
    }
}
