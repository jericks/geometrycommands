package org.geometrycommands;

import org.geometrycommands.CoveredByCommand.CoveredByOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether the first geometry is covered the other geometry.
 * @author Jared Erickson
 */
public class CoveredByCommand extends OtherGeometryCommand<CoveredByOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "coveredby";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine whether the first geometry is covered by the other geometry.";
    }

    /**
     * Get the new CoveredByOptions
     * @return A new CoveredByOptions
     */
    @Override
    public CoveredByOptions getOptions() {
        return new CoveredByOptions();
    }

    /**
     * Calculate whether the first geometry is covered the other geometry.
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The CoveredByOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, CoveredByOptions options, Reader reader, Writer writer) throws Exception {
        boolean coveredBy = geometry.coveredBy(other);
        writer.write(String.valueOf(coveredBy));
    }

    /**
     * The CoveredByOptions
     */
    public static class CoveredByOptions extends OtherGeometryOptions {
    }
}
