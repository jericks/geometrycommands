package org.geometrycommands;

import org.geometrycommands.MinimumRectangleCommand.MinimumRectangleOptions;
import com.vividsolutions.jts.algorithm.MinimumDiameter;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the minimum rectangle of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumRectangleCommand extends GeometryCommand<MinimumRectangleOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "minrect";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the minimum rectangle of the input geometry.";
    }

    /**
     * Get a new MinimumRectangleOptions
     * @return A new MinimumRectangleOptions
     */
    @Override
    public MinimumRectangleOptions getOptions() {
        return new MinimumRectangleOptions();
    }

    /**
     * Calculate the minimum rectangle of the input Geometry.
     * @param geometry The input Geometry
     * @param options The MinimumRectangleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, MinimumRectangleOptions options, Reader reader, Writer writer) throws Exception {
        MinimumDiameter minimumDiameter = new MinimumDiameter(geometry);
        Geometry outputGeometry = minimumDiameter.getMinimumRectangle();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The MinimumRectangleOptions
     */
    public static class MinimumRectangleOptions extends GeometryOptions {
    }
}
