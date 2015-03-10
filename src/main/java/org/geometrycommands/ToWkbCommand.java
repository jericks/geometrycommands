package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKBWriter;
import org.geometrycommands.ToWkbCommand.ToWkbOptions;
import org.kohsuke.args4j.Option;

import java.io.Reader;
import java.io.Writer;

/**
 * Write a Geometry to WKB
 * @author Jared Erickson
 */
public class ToWkbCommand extends GeometryCommand<ToWkbOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "towkb";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Write a Geometry to WKB.";
    }

    /**
     * Get a new ToWkbOptions
     * @return A new ToWkbOptions
     */
    @Override
    public ToWkbOptions getOptions() {
        return new ToWkbOptions();
    }

    /**
     * Write a Geometry to WKB.
     * @param geometry The input Geometry
     * @param options The ToWkbOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ToWkbOptions options, Reader reader, Writer writer) throws Exception {
        WKBWriter wkbWriter = new WKBWriter();
        writer.write(WKBWriter.toHex(wkbWriter.write(geometry)));
    }

    /**
     * The WbkOptions
     */
    public static class ToWkbOptions extends GeometryOptions {

        /**
         * The output dimension (2 or 3)
         */
        @Option(name = "-d", aliases = "--dimension", usage = "The output dimension (2 or 3)", required = false)
        private int outputDimension = 2;

        /**
         * The byte order (1 = big endian, 2 = little endian)
         */
        @Option(name = "-b", aliases = "--byte-order", usage = "The byte order (1 = big endian, 2 = little endian)", required = false)
        private int byteOrder = 1;

        /**
         * Get the output dimension (2 or 3)
         * @return The output dimension (2 or 3)
         */
        public int getOutputDimension() {
            return outputDimension;
        }

        /**
         * Set the output dimension (2 or 3)
         * @param outputDimension The output dimension (2 or 3)
         */
        public void setOutputDimension(int outputDimension) {
            this.outputDimension = outputDimension;
        }

        /**
         * Get the byte order (1 = big endian, 2 = little endian)
         * @return The byte order (1 = big endian, 2 = little endian)
         */
        public int getByteOrder() {
            return byteOrder;
        }

        /**
         * Set the byte order (1 = big endian, 2 = little endian)
         * @param byteOrder The byte order (1 = big endian, 2 = little endian)
         */
        public void setByteOrder(int byteOrder) {
            this.byteOrder = byteOrder;
        }
    }
}
