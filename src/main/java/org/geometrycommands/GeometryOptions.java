package org.geometrycommands;

import org.kohsuke.args4j.Option;

/**
 * An Options subclass that adds input Geometry.
 * @author Jared Erickson
 */
public class GeometryOptions extends Options {

    /**
     * The input geometry
     */
    @Option(name = "-g", aliases = "--geometry", usage = "The input geometry", required = false)
    private String geometry;

    /**
     * Get the input geometry
     * @return The input geometry
     */
    public String getGeometry() {
        return geometry;
    }

    /**
     * Set the input geometry
     * @param geometry The input geometry
     */
    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
