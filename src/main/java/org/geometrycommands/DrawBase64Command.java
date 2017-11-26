package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.apache.commons.codec.binary.Base64;
import org.geometrycommands.DrawBase64Command.DrawOptions;
import org.kohsuke.args4j.Option;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to draw the input Geometry to a Base64 encoded String
 *
 * @author Jared Erickson
 */
public class DrawBase64Command extends AbstractDrawCommand<DrawOptions> {

    /**
     * Get the command name
     *
     * @return The command name
     */
    @Override
    public String getName() {
        return "drawbase64";
    }

    /**
     * Get the description of what the Command does
     *
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Draw the input geometry to a base 64 encoded string.";
    }

    /**
     * Get a new DrawToFileOptions
     *
     * @return A new DrawToFileOptions
     */
    @Override
    public DrawOptions getOptions() {
        return new DrawOptions();
    }

    /**
     * Process the BufferedImage
     *
     * @param image    The BufferedImage
     * @param geometry The input Geometry
     * @param options  The DrawOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     */
    @Override
    protected void processImage(BufferedImage image, Geometry geometry, DrawOptions options, Reader reader, Writer writer) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, options.getType(), out);
        out.close();
        String base64str = Base64.encodeBase64String(out.toByteArray());
        if (options.isIncludingPrefix()) {
            base64str = "data:image/" + options.getType().toLowerCase() + ";base64," + base64str;
        }
        writer.write(base64str);
    }

    /**
     * The DrawOptions
     */
    public static class DrawOptions extends AbstractDrawCommand.DrawOptions {

        /**
         * The image type (png or jpeg)
         */
        @Option(name = "-y", aliases = "--type", usage = "The image type (png or jpeg)", required = false)
        private String type = "png";

        /**
         * The flag to include the prefix (data:image/png;base64,) or not
         */
        @Option(name = "-p", aliases = "--prefix", usage = "The flag to include the prefix (data:image/png;base64,) or not", required = false)
        private boolean includingPrefix;

        /**
         * Get the image type (png or jpeg)
         * @return The image type (png or jpeg)
         */
        public String getType() {
            return type;
        }

        /**
         * Set the image type (png or jpeg)
         * @param type The image type (png or jpeg)
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Get the flag to include the prefix (data:image/png;base64,) or not
         * @return The flag to include the prefix (data:image/png;base64,) or not
         */
        public boolean isIncludingPrefix() {
            return includingPrefix;
        }

        /**
         * Set the flag to include the prefix (data:image/png;base64,) or not
         * @param includingPrefix The flag to include the prefix (data:image/png;base64,) or not
         */
        public void setIncludingPrefix(boolean includingPrefix) {
            this.includingPrefix = includingPrefix;
        }
    }
}
