package org.geometrycommands;

import org.geometrycommands.InteriorPointCommand.InteriorPointOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to get the interior Point of the input Geometry
 * @author Jared Erickson
 */
public class InteriorPointCommand extends GeometryCommand<InteriorPointOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "interiorpoint";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the interior point of the input geometry.";
    }

    /**
     * Get a new InteriorPointOptions
     * @return A new InteriorPointOptions
     */
    @Override
    public InteriorPointOptions getOptions() {
        return new InteriorPointOptions();
    }

    /**
     * Get the interior point of the input Geometry.
     * @param geometry The input Geometry
     * @param options The InteriorPointOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, InteriorPointOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.getInteriorPoint();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The InteriorPointOptions
     */
    public static class InteriorPointOptions extends GeometryOptions {
    }
}
