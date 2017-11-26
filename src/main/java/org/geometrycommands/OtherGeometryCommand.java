package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that executes against two Geometries.  This is a good base class
 * for predicates and overlays.
 * @param <T> The OtherGeometryOptions or subclass
 * @author Jared Erickson
 */
public abstract class OtherGeometryCommand<T extends OtherGeometryOptions> extends GeometryCommand<T> {

    /**
     * Execute with the given Geometry and Options
     * @param geometry The input Geometry
     * @param options The OtherGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, T options, Reader reader, Writer writer) throws Exception {
        Geometry otherGeometry = readGeometry(options.getOtherGeometry(), options);
        processGeometries(geometry, otherGeometry, options, reader, writer);
    }

    /**
     * Process the input Geometry and another Geometry
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    protected abstract void processGeometries(Geometry geometry, Geometry other, T options, Reader reader, Writer writer) throws Exception;
}
