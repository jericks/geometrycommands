package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
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
        if (options.isShowingMessage()) {
            writer.write(op.getValidationError().getMessage());
        } else {
            writer.write(String.valueOf(valid));
        }
    }

    /**
     * The IsValidOptions
     */
    public static class IsValidOptions extends GeometryOptions {

        /**
         * The flag to show the validation error message.
         */
        @Option(name = "-msg", usage = "The flag to show the validation error message.", required = false)
        private boolean showingMessage;

        /**
         * Get the flag to show the validation error message.
         * @return The flag to show the validation error message.
         */
        public boolean isShowingMessage() {
            return showingMessage;
        }

        /**
         * Set the flag to show the validation error message.
         * @param showingMessage The flag to show the validation error message.
         */
        public void setShowingMessage(boolean showingMessage) {
            this.showingMessage = showingMessage;
        }
    }
}
