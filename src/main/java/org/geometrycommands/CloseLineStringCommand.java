package org.geometrycommands;

import org.locationtech.jts.geom.*;

import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.CloseLineStringCommand.CloseLineStringOptions;

/**
 * Close an open LineString.
 * @author Jared Erickson
 */
public class CloseLineStringCommand extends GeometryCommand<CloseLineStringOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "closelinestring";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Close an open LineString.";
    }

    /**
     * Get a new CentroidOptions
     * @return A new CentroidOptions
     */
    @Override
    public CloseLineStringOptions getOptions() {
        return new CloseLineStringOptions();
    }

    /**
     * Calculate the centroid of the input geometry
     * @param geometry The input geometry
     * @param options The CloseOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, CloseLineStringOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof LineString)) {
            throw new IllegalArgumentException("Input geometry must be a LineString!");
        }
        LineString lineString = (LineString)geometry;
        if (lineString.getCoordinates().length < 3) {
            throw new IllegalArgumentException("You need at least three points to close a LineString!");
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        LinearRing linearRing;
        if (lineString.isClosed()) {
            linearRing = geometryFactory.createLinearRing(lineString.getCoordinates());
        } else {
            Coordinate[] coords = lineString.getCoordinates();
            Coordinate[] closedCoords = new Coordinate[coords.length + 1];
            CoordinateArrays.copyDeep(coords, 0, closedCoords, 0, coords.length);
            closedCoords[coords.length] = coords[0];
            linearRing = geometryFactory.createLinearRing(closedCoords);
        }
        writer.write(writeGeometry(linearRing, options));
    }

    /**
     * The CloseOptions
     */
    public static class CloseLineStringOptions extends GeometryOptions {
    }
}
