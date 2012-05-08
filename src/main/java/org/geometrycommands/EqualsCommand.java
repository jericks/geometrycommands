package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.EqualsCommand.EqualsOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that determines if the input Geometry equals the other Geometry.
 * @author Jared Erickson
 */
public class EqualsCommand extends OtherGeometryCommand<EqualsOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "equals";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Determine whether the first geometry equals the second geometry.";
    }

    /**
     * Get a new OtherGeometryOptions
     * @return A new OtherGeometryOptions
     */
    @Override
    public EqualsOptions getOptions() {
        return new EqualsOptions();
    }

    /**
     * Determine if the input Geometry equals the other Geometry.
     * @param geometry The input Geometry
     * @param other The other Geometry
     * @param options The EqualsOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, EqualsOptions options, Reader reader, Writer writer) throws Exception {
        boolean equals;
        if (options.getType() != null && options.getType().equalsIgnoreCase("exact")) {
            if (options.getTolerance() > 0) {
                equals = geometry.equalsExact(other, options.getTolerance());
            } else {
                equals = geometry.equalsExact(other);
            }
        } else if (options.getType() != null && options.getType().equalsIgnoreCase("norm")) {
            equals = geometry.equalsNorm(other);
        } else if (options.getType() != null && options.getType().equalsIgnoreCase("topo")) {
            equals = geometry.equalsTopo(other);
        } else {
            equals = geometry.equals(other);
        }
        writer.write(String.valueOf(equals));
    }

    /**
     * The EqualsOptions
     */
    public static class EqualsOptions extends OtherGeometryOptions {

        /**
         * The type of equals (exact, norm, topo)
         */
        @Option(name = "-t", aliases = "--type", usage = "The type of equals (exact, norm, topo)", required = false)
        private String type;

        /**
         * The tolerance when type is exact
         */
        @Option(name = "-l", aliases = "--tolerance", usage = "The tolerance when type is exact", required = false)
        private double tolerance;

        /**
         * Get the type of equals (exact, norm, topo)
         * @return The type of equals (exact, norm, topo)
         */
        public String getType() {
            return type;
        }

        /**
         * Set the type of equals (exact, norm, topo)
         * @param type The type of equals (exact, norm, topo)
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Get the tolerance when type is exact
         * @return The tolerance when type is exact
         */
        public double getTolerance() {
            return tolerance;
        }

        /**
         * Set the tolerance when type is exact
         * @param tolerance The tolerance when type is exact
         */
        public void setTolerance(double tolerance) {
            this.tolerance = tolerance;
        }
    }
}
