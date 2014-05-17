package org.geometrycommands;

import org.geometrycommands.IsCounterClockwiseCommand.IsCounterClockwiseOptions;
import com.vividsolutions.jts.algorithm.CGAlgorithms;
import com.vividsolutions.jts.geom.Geometry;

import java.io.Reader;
import java.io.Writer;

/**
 * Determine if the geometry's coordinates oriented counter clockwise of not.
 * @author Jared Erickson
 */
public class IsCounterClockwiseCommand extends GeometryCommand<IsCounterClockwiseOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isccw";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Is the geometry's coordinates oriented counter clockwise of not.";
    }

    /**
     * Get a new IsCounterClockwiseOptions
     * @return A new IsCounterClockwiseOptions
     */
    @Override
    public IsCounterClockwiseOptions getOptions() {
        return new IsCounterClockwiseOptions();
    }

    /**
     * Process the input Geometry
     * @param geometry The input Geometry
     * @param options  The GeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsCounterClockwiseOptions options, Reader reader, Writer writer) throws Exception {
        boolean isCcw = CGAlgorithms.isCCW(geometry.getCoordinates());
        writer.write(String.valueOf(isCcw));
    }

    /**
     * The IsCounterClockwiseOptions
     */
    public static class IsCounterClockwiseOptions extends GeometryOptions {
    }
}
