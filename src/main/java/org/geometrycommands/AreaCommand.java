package org.geometrycommands;

import org.geometrycommands.AreaCommand.AreaOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the area of a Geometry
 * @author Jared Erickson
 */
public class AreaCommand extends GeometryCommand<AreaOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "area";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the area of a Geometry.";
    }

    /**
     * Get a new AreaOptions
     * @return A new AreaOptions
     */
    @Override
    public AreaOptions getOptions() {
        return new AreaOptions();
    }

    /**
     * Calculate the area of a Geometry
     * @param geometry The Geometry
     * @param options The AreaOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, AreaOptions options, Reader reader, Writer writer) throws Exception {
        double distance = geometry.getArea();
        writer.write(String.valueOf(distance));
    }

    /**
     * The AreaOptions
     */
    public static class AreaOptions extends GeometryOptions {
    }
}
