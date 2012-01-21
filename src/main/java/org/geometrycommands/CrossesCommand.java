package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to calculate whether one geometry crosses another
 * @author Jared Erickson
 */
public class CrossesCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "covers";
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
     * Calculate whether the first geometry crosses the other geometry.
     * @param geometry The input geometry
     * @param other The other geometry
     * @param options The OtherGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options, Reader reader, Writer writer) throws Exception {
        boolean covers = geometry.covers(other);
        writer.write(String.valueOf(covers));
    }
}
