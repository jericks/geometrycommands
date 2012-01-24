package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.operation.valid.IsValidOp;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.IsValidCommand.IsValidOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command that determines if the input Geometry is valid or not.
 * @author Jared Erickson
 */
public class IsValidCommand extends GeometryCommand<IsValidOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "isvalid";
    }

    /**
     * Get a new IsValidOptions
     * @return A new IsValidOptions
     */
    @Override
    public IsValidOptions getOptions() {
        return new IsValidOptions();
    }

    /**
     * Determine if the input Geometry is valid or not.
     * @param geometry The input Geometry
     * @param options The IsValidOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, IsValidOptions options, Reader reader, Writer writer) throws Exception {
        IsValidOp op = new IsValidOp(geometry);
        boolean valid = op.isValid();
        if (options.getType().equalsIgnoreCase("msg") || options.getType().equalsIgnoreCase("message")) {
            writer.write(op.getValidationError().getMessage());
        } else if (options.getType().equalsIgnoreCase("loc") || options.getType().equalsIgnoreCase("location")) {
            Coordinate coord = op.getValidationError().getCoordinate();
            GeometryFactory factory = new GeometryFactory();
            Point point = factory.createPoint(coord);
            writer.write(writeGeometry(point, options));
        } else if (options.getType().equalsIgnoreCase("val") || options.getType().equalsIgnoreCase("validity")) {
            writer.write(String.valueOf(valid));
        } else {
            throw new IllegalArgumentException("Unknown type!");
        }
    }

    /**
     * The IsValidOptions
     */
    public static class IsValidOptions extends GeometryOptions {

        /**
         * The flag to show the validation error message, the error location, or validity (msg, loc, or val).
         */
        @Option(name = "-t", usage = "The flag to show the validation error message, the error location, or validity (msg, loc, or val)", required = false)
        private String type = "val";

        /**
         * Get the flag to show the validation error message, the error location, or validity (msg, loc, or val).
         * @return The flag to show the validation error message, the error location, or validity (msg, loc, or val).
         */
        public String getType() {
            return type;
        }

        /**
         * Set the flag to show the validation error message, the error location, or validity (msg, loc, or val).
         * @param type The flag to show the validation error message, the error location, or validity (msg, loc, or val).
         */
        public void setType(String type) {
            this.type = type;
        }
    }
}
