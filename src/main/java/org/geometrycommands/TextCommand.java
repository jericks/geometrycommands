package org.geometrycommands;

import com.vividsolutions.jts.awt.FontGlyphReader;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.TextCommand.TextOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command for creating a Polygon Geometry from a String and Font.
 * @author Jared Erickson
 */
public class TextCommand implements Command<TextOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "text";
    }

    /**
     * Get a new TextOptions
     * @return The new TextOptions
     */
    @Override
    public TextOptions getOptions() {
        return new TextOptions();
    }

    /**
     * Create a Polygon Geometry from the text and Font.
     * @param options The TextOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(TextOptions options, Reader reader, Writer writer) throws Exception {
        GeometryFactory factory = new GeometryFactory();
        Geometry geometry = FontGlyphReader.read(options.getText(), options.getFontName(), options.getPointSize(), factory);
        writer.write(geometry.toText());
    }

    /**
     * The TextOptions
     */
    public static class TextOptions extends Options {

        /**
         * The text
         */
        @Option(name = "-t", usage = "The text", required = true)
        private String text;

        /**
         * The font name
         */
        @Option(name = "-f", usage = "The font name", required = false)
        private String fontName = FontGlyphReader.FONT_SANSERIF;

        /**
         * The font size
         */
        @Option(name = "-s", usage = "The font size", required = false)
        private int pointSize = 24;

        /**
         * Get the font name
         * @return The font name
         */
        public String getFontName() {
            return fontName;
        }

        /**
         * Set the font name
         * @param fontName The font name
         */
        public void setFontName(String fontName) {
            this.fontName = fontName;
        }

        /**
         * Get the font size
         * @return The font size
         */
        public int getPointSize() {
            return pointSize;
        }

        /**
         * Set the font size
         * @param pointSize The font size
         */
        public void setPointSize(int pointSize) {
            this.pointSize = pointSize;
        }

        /**
         * Get the text
         * @return The text
         */
        public String getText() {
            return text;
        }

        /**
         * Set the text
         * @param text The text
         */
        public void setText(String text) {
            this.text = text;
        }
    }
} 
