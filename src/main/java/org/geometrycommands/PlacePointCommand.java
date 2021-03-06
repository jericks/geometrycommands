package org.geometrycommands;

import org.geometrycommands.PlacePointCommand.PlacePointOptions;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Lineal;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.linearref.LengthIndexedLine;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to place a Point on the input linear Geometry.
 * @author Jared Erickson
 */
public class PlacePointCommand extends OtherGeometryCommand<PlacePointOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "placepoint";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Place a point on the input linear geometry.";
    }

    /**
     * Get a new PlacePointOptions
     * @return A new PlacePointOptions
     */
    @Override
    public PlacePointOptions getOptions() {
        return new PlacePointOptions();
    }

    /**
     * Locate a Point on the input linear Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The PlacePointOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, PlacePointOptions options, Reader reader, Writer writer) throws Exception {

        Point point = null;
        Geometry line = null;

        if (geometry instanceof Point) {
            point = (Point) geometry;
        } else if (other instanceof Point) {
            point = (Point) other;
        }
        if (geometry instanceof Lineal) {
            line = geometry;
        } else if (other instanceof Lineal) {
            line = other;
        }

        if (point == null || line == null) {
            throw new IllegalArgumentException("Please provide a Point and a Linear Geometry!");
        }

        LengthIndexedLine indexedLine = new LengthIndexedLine(line);
        double position = indexedLine.indexOf(point.getCoordinate());
        Coordinate coord = indexedLine.extractPoint(position);
        GeometryFactory factory = new GeometryFactory();
        Point snappedPoint = factory.createPoint(coord);
        writer.write(writeGeometry(snappedPoint, options));
    }

    /**
     * The PlacePointOptions
     */
    public static class PlacePointOptions extends OtherGeometryOptions {
    }
}
