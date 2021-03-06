package org.geometrycommands;

import org.geometrycommands.LocatePointCommand.LocatePointOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Lineal;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.linearref.LengthIndexedLine;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to locate the position of a Point on the input linear Geometry as a percentage of the distance.
 * @author Jared Erickson
 */
public class LocatePointCommand extends OtherGeometryCommand<LocatePointOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "locatepoint";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Locate the position of a point on the linear geometry as a percentage of the distance.";
    }

    /**
     * Get a new LocatePointOptions
     * @return A new LocatePointOptions
     */
    @Override
    public LocatePointOptions getOptions() {
        return new LocatePointOptions();
    }

    /**
     * Locate the position of a Point on the input linear Geometry as a percentage of the distance.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The LocatePointOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, LocatePointOptions options, Reader reader, Writer writer) throws Exception {
        
        Point point = null;
        Geometry line = null;
        
        if (geometry instanceof Point) {
            point = (Point)geometry;
        } else if (other instanceof Point) {
            point = (Point)other;
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
        double length = line.getLength();
        double percentAlong = position / length;
        writer.write(String.valueOf(percentAlong));
    }

    /**
     * The LocatePointOptions
     */
    public static class LocatePointOptions extends OtherGeometryOptions {
    }
}
