package org.geometrycommands;

import com.vividsolutions.jts.awt.FontGlyphReader;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKBReader;
import org.geometrycommands.FromWkbCommand.FromWkbOptions;
import org.kohsuke.args4j.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Convert a WKB to a WKT Geometry
 * @author Jared Erickson
 */
public class FromWkbCommand implements Command<FromWkbOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "fromwkb";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Convert a WKB to a WKT Geometry.";
    }

    /**
     * Get a new FromWkbOptions
     * @return The new FromWkbOptions
     */
    @Override
    public FromWkbOptions getOptions() {
        return new FromWkbOptions();
    }

    /**
     * Create a Polygon Geometry from the text and Font.
     * @param options The FromWkbOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(FromWkbOptions options, Reader reader, Writer writer) throws Exception {
        WKBReader wkbReader = new WKBReader();
        Geometry geometry = wkbReader.read(WKBReader.hexToBytes(options.getWkb() != null ? options.getWkb() : readWkb(reader)));
        writer.write(geometry.toText());
    }

    /**
     * Read a WKB hex String from the Reader
     * @param reader The Reader
     * @return A WKB hex String
     * @throws IOException if an IOException occurs
     */
    protected String readWkb(Reader reader) throws IOException {
        BufferedReader inputStreamReader = new BufferedReader(reader);
        String str;
        StringBuilder builder = new StringBuilder();
        while ((str = inputStreamReader.readLine()) != null) {
            builder.append(str);
        }
        return builder.toString();
    }

    /**
     * The FromWkbOptions
     */
    public static class FromWkbOptions extends Options {

        /**
         * The WKB text
         */
        @Option(name = "-b", aliases = "--wkb", usage = "The WKB text", required = false)
        private String wkb;

        /**
         * Get the WKB text
         * @return The WKB text
         */
        public String getWkb() {
            return wkb;
        }

        /**
         * Set the WKB text
         * @param wkb The WKB text
         */
        public void setWkb(String wkb) {
            this.wkb = wkb;
        }
    }

}
