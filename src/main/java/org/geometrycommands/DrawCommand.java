package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import org.geometrycommands.DrawCommand.DrawOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to draw the input Geometry to an image File
 * @author Jared Erickson
 */
public class DrawCommand extends AbstractDrawCommand<DrawOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "draw";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Draw the input geometry to an image file.";
    }

    /**
     * Get a new DrawToFileOptions
     * @return A new DrawToFileOptions
     */
    @Override
    public DrawOptions getOptions() {
        return new DrawOptions();
    }

    /**
     * Process the BufferedImage
     * @param image The BufferedImage
     * @param geometry The input Geometry
     * @param options The DrawOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     */
    @Override
    protected void processImage(BufferedImage image, Geometry geometry, DrawOptions options, Reader reader, Writer writer) throws Exception {
        String fileName = options.getFile().getName();
        String imageFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
        FileOutputStream out = new FileOutputStream(options.getFile());
        ImageIO.write(image, imageFormat, out);
        out.close();
    }

    /**
     * The DrawOptions
     */
    public static class DrawOptions extends AbstractDrawCommand.DrawOptions {

        /**
         * The output File
         */
        @Option(name = "-f", aliases = "--file", usage = "The output File", required = false)
        private File file = new File("image.png");

        /**
         * Get the output File
         * @return The output File
         */
        public File getFile() {
            return file;
        }

        /**
         * Set the output File
         * @param file The output File
         */
        public void setFile(File file) {
            this.file = file;
        }

    }
}
