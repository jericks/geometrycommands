package org.geometrycommands;

import org.kohsuke.args4j.Option;

/**
 * An Options that adds an other Geometry for predicate or overlay operations.
 * @author Jared Erickson
 */
public class OtherGeometryOptions extends GeometryOptions {

    /**
     * The other Geometry
     */
    @Option(name = "-o", aliases = "--otherGeometry", usage="The other geometry", required = true)
    private String otherGeometry;

    /**
     * Get the other Geometry
     * @return The other Geometry
     */
    public String getOtherGeometry() {
        return otherGeometry;
    }

    /**
     * Set the other Geometry
     * @param otherGeometry The other Geometry
     */
    public void setOtherGeometry(String otherGeometry) {
        this.otherGeometry = otherGeometry;
    }
}
