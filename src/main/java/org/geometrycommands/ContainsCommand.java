package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether the first geometry contains the other geometry
 * @author Jared Erickson
 */
public class ContainsCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "contains";
    }

    /**
     * Get the new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public OtherGeometryOptions getOptions() {
        return new OtherGeometryOptions();
    }

    /**
     * Calculate whether the first geometry contains the other geometry
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The OtherGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options, Reader reader, Writer writer) throws Exception {
        boolean contains = geometry.contains(other);
        writer.write(String.valueOf(contains));
    }
}
