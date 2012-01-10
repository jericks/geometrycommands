package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;

/**
 * A Command to determine if the input Geometry touches the other Geometry.
 * @author Jared Erickson
 */
public class TouchesCommand extends OtherGeometryCommand<OtherGeometryOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "touches";
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
     * determine if the input Geometry touches the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The OtherGeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void processGeometries(Geometry geometry, Geometry other, OtherGeometryOptions options) throws Exception {
        boolean touches = geometry.touches(other);
        System.out.println(touches);
    }
}
