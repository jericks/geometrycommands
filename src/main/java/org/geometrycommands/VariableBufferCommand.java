package org.geometrycommands;

import org.geometrycommands.VariableBufferCommand.VariableBufferOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.buffer.VariableBuffer;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * A Command for calculating a variable buffer around a Geometry.
 * @author Jared Erickson
 */
public class VariableBufferCommand extends GeometryCommand<VariableBufferOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "variablebuffer";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate a variable buffer around a Geometry.";
    }

    /**
     * Get a new VariableBufferOptions
     * @return A new VariableBufferOptions
     */
    @Override
    public VariableBufferOptions getOptions() {
        return new VariableBufferOptions();
    }

    /**
     * Calculate the variable buffer of the input geometry
     * @param geometry The input geometry
     * @param options The VariableBufferOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, VariableBufferOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = null;
        double[] distances = options.getDistances().stream().mapToDouble(Double::doubleValue).toArray();
        if (distances.length == 2) {
            outputGeometry = VariableBuffer.buffer(geometry, distances[0], distances[1]);
        } else if (distances.length == 3) {
            outputGeometry = VariableBuffer.buffer(geometry, distances[0], distances[1], distances[2]);
        }  else {
            outputGeometry = VariableBuffer.buffer(geometry, distances);
        }
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The VariableBufferOptions
     */
    public static class VariableBufferOptions extends GeometryOptions {

        /**
         * The buffer distances
         */
        @Option(name = "-d", aliases = "--distance", usage = "The buffer distance", required = true)
        private List<Double> distances;

        public List<Double> getDistances() {
            return distances;
        }

        public void setDistances(List<Double> distances) {
            this.distances = distances;
        }
    }
}
