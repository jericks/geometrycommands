package org.geometrycommands;

import org.geometrycommands.IsRingCommand.IsRingOptions;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that determines if the input Geometry is a ring or not.
 * @author Jared Erickson
 */
public class IsRingCommand extends GeometryCommand<IsRingOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isring";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine if the input geometry is a ring or not.";
    }

    /**
     * Get a new IsRingOptions
     * @return A new IsRingOptions
     */
    @Override
    public IsRingOptions getOptions() {
        return new IsRingOptions();
    }

    /**
     * Determine if the input Geometry is a ring or not.
     * @param geometry The input Geometry
     * @param options The IsRingOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsRingOptions options, Reader reader, Writer writer) throws Exception {
        if (!(geometry instanceof LineString)) {
            throw new IllegalArgumentException("The input geometry must be a LineString!");
        }
        boolean isRing = ((LineString) geometry).isRing();
        writer.write(String.valueOf(isRing));
    }

    /**
     * The IsRingOptions
     */
    public static class IsRingOptions extends GeometryOptions {
    }
}
