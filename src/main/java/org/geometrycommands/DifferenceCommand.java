package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the difference between two Geometries
 * @author jericks
 */
public class DifferenceCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the Command
     * @return The name of the Command
     */
    @Override
    public String getName() {
        return "difference";
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
     * Calculate the difference between two Geometries
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options, Reader reader, Writer writer) throws Exception {
        Geometry differenceGeometry = geometry.difference(other);
        writer.write(writeGeometry(differenceGeometry, options));
    }
}
