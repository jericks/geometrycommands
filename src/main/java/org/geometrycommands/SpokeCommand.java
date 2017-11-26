package org.geometrycommands;

import org.locationtech.jts.geom.*;
import org.geometrycommands.SpokeCommand.SpokeOptions;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create a spoke diagram with lines between a single Geometry to other Geometries.
 * @author Jared Erickson
 */
public class SpokeCommand extends OtherGeometryCommand<SpokeOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "spoke";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a spoke diagram with lines between a single Geometry to other Geometries";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public SpokeOptions getOptions() {
        return new SpokeOptions();
    }

    /**
     * Process the input Geometry and another Geometry
     * @param geometry The input Geometry
     * @param other    The other Geometry
     * @param options  The OtherGeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, SpokeOptions options, Reader reader, Writer writer) throws Exception {
        GeometryFactory factory = new GeometryFactory();
        Coordinate center = other.getCentroid().getCoordinate();
        int number = geometry.getNumGeometries();
        LineString[] lines = new LineString[number];
        for (int i = 0; i < number; i++) {
            Geometry g = geometry.getGeometryN(i);
            lines[i] = factory.createLineString(new Coordinate[]{center, g.getCoordinate()});
        }
        MultiLineString multiLineString = factory.createMultiLineString(lines);
        writer.write(writeGeometry(multiLineString, options));
    }

    /**
     * The SpokeOptions
     */
    public static class SpokeOptions extends OtherGeometryOptions {
    }
}
