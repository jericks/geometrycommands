package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to determine if the input Geometry is within the other Geometry.
 * @author Jared Erickson
 */
public class WithinCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "within";
    }

    /**
     * Get a new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public OtherGeometryOptions getOptions() {
        return new OtherGeometryOptions();
    }

    /**
     * determine if the input Geometry is within the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options, Reader reader, Writer writer) throws Exception {
        boolean within = geometry.within(other);
        writer.write(String.valueOf(within));
    }
}
