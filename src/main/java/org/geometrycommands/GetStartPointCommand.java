package org.geometrycommands;

import org.geometrycommands.GetStartPointCommand.GetStartPointOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Lineal;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.Point;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to get the start point of a LineString or a MultiLineString.
 * @author Jared Erickson
 */
public class GetStartPointCommand extends GeometryCommand<GetStartPointOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "startpoint";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the start point of a LineString or MultiLineString.";
    }

    /**
     * Get the GetStartPointOptions
     * @return The GetStartPointOptions
     */
    @Override
    public GetStartPointOptions getOptions() {
        return new GetStartPointOptions();
    }

    /**
     * Get the start Point of a LineString or a MultiLineString
     * @param geometry The Geometry
     * @param options The GetStartPointOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs especially if the input Geometry is not Lineal
     */
    @Override
    protected void processGeometry(Geometry geometry, GetStartPointOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof Lineal)) {
            throw new IllegalArgumentException("The input geometry must be a LineString or a MultiLineString!");
        }
        LineString lineString;
        if (geometry instanceof MultiLineString) {
            lineString = (LineString)(geometry).getGeometryN(0);
        }
        else {
            lineString = (LineString)geometry;
        }
        Point point = lineString.getStartPoint();
        writer.write(writeGeometry(point, options));
    }

    /**
     * The GetStartOptions
     */
    public static class GetStartPointOptions extends GeometryOptions {
    }
}
