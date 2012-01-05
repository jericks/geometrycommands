package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command that executes against two Geometries.  This is a good base class
 * for predicates and overlays.
 * @author Jared Erickson
 */
public abstract class OtherGeometryCommand<T extends OtherGeometryOptions> extends GeometryCommand<T> {

    /**
     * Execute with the given Geometry and Options
     * @param geometry The input Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, T options) throws Exception {
        Geometry otherGeometry = readGeometry(options.getOtherGeometry(), options);
        processGeometries(geometry, otherGeometry, options);
    }

    /**
     * Process the input Geometry and another Geometry
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    protected abstract void processGeometries(Geometry geometry, Geometry other, T options) throws Exception;
}
