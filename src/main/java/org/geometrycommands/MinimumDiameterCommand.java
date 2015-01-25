package org.geometrycommands;

import org.geometrycommands.MinimumDiameterCommand.MinimumDiameterOptions;
import com.vividsolutions.jts.algorithm.MinimumDiameter;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * Calculate the minimum diameter of the input Geometry.
 * @author Jared Erickson
 */
public class MinimumDiameterCommand extends GeometryCommand<MinimumDiameterOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "mindiameter";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the minimum diameter of the input geometry.";
    }

    /**
     * Get a new MinimumDiameterOptions
     * @return A new MinimumDiameterOptions
     */
    @Override
    public MinimumDiameterOptions getOptions() {
        return new MinimumDiameterOptions();
    }

    /**
     * Calculate the minimum diameter of the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, MinimumDiameterOptions options, Reader reader, Writer writer) throws Exception {
        MinimumDiameter minimumDiameter = new MinimumDiameter(geometry);
        Geometry outputGeometry = minimumDiameter.getDiameter();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The MinimumDiameterOptions
     */
    public static class MinimumDiameterOptions extends GeometryOptions {
    }
}
