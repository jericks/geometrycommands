package org.geometrycommands;

import org.locationtech.jts.algorithm.Angle;
import org.locationtech.jts.geom.Geometry;

import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.AngleCommand.AngleOptions;
import org.kohsuke.args4j.Option;

/**
 * The command to calculate the angle between two Points.
 * @author Jared Erickson
 */
public class AngleCommand extends OtherGeometryCommand<AngleOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "angle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the angle between two Points";
    }

    /**
     * Get a new AngleOptions
     * @return A new AngleOptions
     */
    @Override
    public AngleOptions getOptions() {
        return new AngleOptions();
    }

    /**
     * Process the input Geometry and another Geometry
     *
     * @param geometry The input Geometry
     * @param other    The other Geometry
     * @param options  The OtherGeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, AngleOptions options, Reader reader, Writer writer) throws Exception {
        double angle = Angle.angle(geometry.getCoordinate(), other.getCoordinate());
        if (options.getType().equalsIgnoreCase("degrees")) {
            angle = Angle.toDegrees(angle);
        }
        writer.write(String.valueOf(angle));
    }

    /**
     * The AngleCommand Options
     */
    public static class AngleOptions extends OtherGeometryOptions {

        /**
         * The type can either be degrees or radians
         */
        @Option(name = "-t", aliases = "--type", usage = "The type can be degrees (default) or radians", required = false)
        private String type = "degrees";

        /**
         * Get the type
         * @return The type
         */
        public String getType() {
            return type;
        }

        /**
         * Set the type
         * @param type The type
         */
        public void setType(String type) {
            this.type = type;
        }
    }
}
