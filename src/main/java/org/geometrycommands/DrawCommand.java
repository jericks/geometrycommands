package org.geometrycommands;

import com.vividsolutions.jts.awt.PointShapeFactory;
import com.vividsolutions.jts.awt.PointTransformation;
import com.vividsolutions.jts.awt.ShapeWriter;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Lineal;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import javax.imageio.ImageIO;
import org.geometrycommands.DrawCommand.DrawOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to draw the input Geometry to an image File
 * @author Jared Erickson
 */
public class DrawCommand extends GeometryCommand<DrawOptions> {

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
     * Get a new DrawOptions
     * @return A new DrawOptions
     */
    @Override
    public DrawOptions getOptions() {
        return new DrawOptions();
    }

    /**
     * Get a java.awt.Color from a String 
     * @param value A String value
     * @return The java.awt.Color
     */
    private Color getColor(String value) {
        String[] parts = value.split(",");
        int r = Integer.parseInt(parts[0]);
        int g = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int a = 255;
        if (parts.length > 3) {
            a = Integer.parseInt(parts[3]);
        }
        return new Color(r, g, b, a);
    }

    /**
     * Draw the input Geometry to an image File
     * @param geometry The input Geometry
     * @param options The DrawOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     */
    @Override
    protected void processGeometry(Geometry geometry, DrawOptions options, Reader reader, Writer writer) throws Exception {

        final int imageWidth = options.getWidth();
        final int imageHeight = options.getHeight();

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(getColor(options.getBackgroundColor()));
        g2d.fillRect(0, 0, imageWidth, imageHeight);

        // Draw background image
        if (options.getBackgroundImage() != null && options.getBackgroundImage().trim().length() > 0) {
            String backgroundImagePath = options.getBackgroundImage().trim();
            BufferedImage backgroundImage;
            if (backgroundImagePath.startsWith("http")) {
                URL url = new URL(backgroundImagePath);
                backgroundImage = ImageIO.read(url);
            }
            else {
                File file = new File(backgroundImagePath);
                backgroundImage = ImageIO.read(file);
            }
            g2d.drawImage(backgroundImage, 0, 0, null);
        }
        
        final Envelope env = getEnvelope(geometry, options);

        Color strokeColor = getColor(options.getStrokeColor());
        Color fillColor = getColor(options.getFillColor());

        String shape = options.getShape();
        double markerSize = options.getMarkerSize();
        PointShapeFactory pointShapeFactory;
        if (shape.equalsIgnoreCase("circle")) {
            pointShapeFactory = new PointShapeFactory.Circle(markerSize);
        } else if (shape.equalsIgnoreCase("cross")) {
            pointShapeFactory = new PointShapeFactory.Cross(markerSize);
        } else if (shape.equalsIgnoreCase("star")) {
            pointShapeFactory = new PointShapeFactory.Star(markerSize);
        } else if (shape.equalsIgnoreCase("Triangle")) {
            pointShapeFactory = new PointShapeFactory.Triangle(markerSize);
        } else if (shape.equalsIgnoreCase("X")) {
            pointShapeFactory = new PointShapeFactory.X(markerSize);
        } else {
            pointShapeFactory = new PointShapeFactory.Square(markerSize);
        }

        float opacity = 0.75f;
        float strokeWidth = 1.0f;

        ShapeWriter shapeWriter = new ShapeWriter(new PointTransformation() {

            @Override
            public void transform(Coordinate coord, Point2D point) {
                double imageX = (1 - (env.getMaxX() - coord.x) / env.getWidth()) * imageWidth;
                double imageY = ((env.getMaxY() - coord.y) / env.getHeight()) * imageHeight;
                point.setLocation(imageX, imageY);
            }
        }, pointShapeFactory);

        Composite strokeComposite = g2d.getComposite();
        Composite fillComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);

        Shape shp = shapeWriter.toShape(geometry);
        g2d.setComposite(fillComposite);
        // Fill
        if (!(geometry instanceof Lineal)) {
            g2d.setComposite(fillComposite);
            g2d.setColor(fillColor);
            g2d.fill(shp);
        }
        // Stroke
        g2d.setComposite(strokeComposite);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(strokeColor);
        g2d.draw(shp);

        if (options.isDrawingCoordinates()) {
            g2d.setStroke(new BasicStroke(strokeWidth));
            Coordinate[] coords = geometry.getCoordinates();
            for (Coordinate c : coords) {
                Shape coordinateShp = shapeWriter.toShape(geometry.getFactory().createPoint(c));
                // Fill
                g2d.setComposite(fillComposite);
                g2d.setColor(fillColor);
                g2d.fill(coordinateShp);
                // Stroke
                g2d.setComposite(strokeComposite);
                g2d.setColor(strokeColor);
                g2d.draw(coordinateShp);
            }
        }

        g2d.dispose();

        String fileName = options.getFile().getName();
        String imageFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
        FileOutputStream out = new FileOutputStream(options.getFile());
        ImageIO.write(image, imageFormat, out);
        out.close();
    }

    /**
     * Get an Envelope either from the Geometry or from the DrawOptions bounds property
     * @param geometry The input Geometry
     * @param options The DrawOptions
     * @return An Envelope
     */
    private Envelope getEnvelope(Geometry geometry, DrawOptions options) {
        Envelope env;
        String bounds = options.getBounds();
        if (bounds != null && bounds.split(",").length == 4) {
            String[] parts = bounds.split(",");
            double minX = Double.parseDouble(parts[0].trim());
            double minY = Double.parseDouble(parts[1].trim());
            double maxX = Double.parseDouble(parts[2].trim());
            double maxY = Double.parseDouble(parts[3].trim());
            env = new Envelope(minX, maxX, minY, maxY);
        } else {
            env = geometry.getEnvelopeInternal();
            env.expandBy(env.getWidth() * 0.1);
        }
        return env;
    }

    /**
     * The DrawOptions
     */
    public static class DrawOptions extends GeometryOptions {

        /**
         * The image width
         */
        @Option(name = "-w", aliases = "--width", usage = "The image width", required = false)
        private int width = 400;

        /**
         * The image height
         */
        @Option(name = "-h", aliases = "--height", usage = "The image height", required = false)
        private int height = 400;

        /**
         * The output File
         */
        @Option(name = "-f", aliases = "--file", usage = "The output File", required = false)
        private File file = new File("image.png");

        /**
         * The background Color
         */
        @Option(name = "-b", aliases = "--background", usage = "The background color", required = false)
        private String backgroundColor = "255,255,255";
        
        /**
         * The background image url or file
         */
        @Option(name = "-i", aliases = "--backgroundImage", usage = "The background image url or file", required = false)
        private String backgroundImage;
        
        /**
         * The stroke Color
         */
        @Option(name = "-s", aliases = "--stroke", usage = "The stroke Color", required = false)
        private String strokeColor = "99,99,99";

        /**
         * The fill Color
         */
        @Option(name = "-l", aliases = "--fill", usage = "The fill Color", required = false)
        private String fillColor = "206,206,206,100";

        /**
         * The marker shape
         */
        @Option(name = "-m", aliases = "--shape", usage = "The marker shape (circle, square, ect..)", required = false)
        private String shape = "circle";

        /**
         * The marker size
         */
        @Option(name = "-s", aliases = "--size", usage = "The marker size", required = false)
        private double markerSize = 8;

        /**
         * The flag for drawing coordinates or not
         */
        @Option(name = "-c", aliases = "--drawCoords", usage = "The flag for drawing coordinates or not", required = false)
        private boolean drawingCoordinates;

        /**
         * The geographical bounds (minx, miny, maxx, maxy)
         */
        @Option(name = "-e", aliases = "--envelope", usage = "The geographical bounds (minx, miny, maxx, maxy)", required = false)
        private String bounds;

        /**
         * Get the geographical bounds (minx, miny, maxx, maxy)
         * @return The geographical bounds (minx, miny, maxx, maxy)
         */
        public String getBounds() {
            return bounds;
        }

        /**
         * Set the geographical bounds (minx, miny, maxx, maxy)
         * @param bounds The geographical bounds (minx, miny, maxx, maxy)
         */
        public void setBounds(String bounds) {
            this.bounds = bounds;
        }

        public String getBackgroundImage() {
            return backgroundImage;
        }

        public void setBackgroundImage(String backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        
        /**
         * Get the flag for drawing coordinates or not
         * @return The flag for drawing coordinates or not
         */
        public boolean isDrawingCoordinates() {
            return drawingCoordinates;
        }

        /**
         * Set the flag for drawing coordinates or not
         * @param drawingCoordinates The flag for drawing coordinates or not
         */
        public void setDrawingCoordinates(boolean drawingCoordinates) {
            this.drawingCoordinates = drawingCoordinates;
        }

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

        /**
         * Get the marker size
         * @return The marker size
         */
        public double getMarkerSize() {
            return markerSize;
        }

        /**
         * Set the marker size
         * @param markerSize The marker size
         */
        public void setMarkerSize(double markerSize) {
            this.markerSize = markerSize;
        }

        /**
         * Get the image height
         * @return The image height
         */
        public int getHeight() {
            return height;
        }

        /**
         * Set the image height
         * @param height The image height
         */
        public void setHeight(int height) {
            this.height = height;
        }

        /**
         * Get the image width
         * @return The image width
         */
        public int getWidth() {
            return width;
        }

        /**
         * Set the image width
         * @param width The image width
         */
        public void setWidth(int width) {
            this.width = width;
        }

        /**
         * Get the background Color
         * @return The background Color
         */
        public String getBackgroundColor() {
            return backgroundColor;
        }

        /**
         * Set the background Color
         * @param backgroundColor The background Color
         */
        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        /**
         * Get the fill Color
         * @return The fill Color
         */
        public String getFillColor() {
            return fillColor;
        }

        /**
         * Set the fill Color
         * @param fillColor The fill Color
         */
        public void setFillColor(String fillColor) {
            this.fillColor = fillColor;
        }

        /**
         * Get the stroke Color
         * @return The stroke Color
         */
        public String getStrokeColor() {
            return strokeColor;
        }

        /**
         * Set the stroke Color
         * @param strokeColor The stroke Color
         */
        public void setStrokeColor(String strokeColor) {
            this.strokeColor = strokeColor;
        }

        /**
         * Get the marker shape
         * @return The marker shape
         */
        public String getShape() {
            return shape;
        }

        /**
         * Set the marker shape
         * @param shape The marker shape
         */
        public void setShape(String shape) {
            this.shape = shape;
        }
    }
}
