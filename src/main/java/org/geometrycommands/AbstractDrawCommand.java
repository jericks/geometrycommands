package org.geometrycommands;

import org.locationtech.jts.awt.PointShapeFactory;
import org.locationtech.jts.awt.PointTransformation;
import org.locationtech.jts.awt.ShapeWriter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Lineal;
import org.geometrycommands.AbstractDrawCommand.DrawOptions;
import org.kohsuke.args4j.Option;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * An abstract base class for Commands that draw a geometry to an image
 * @author Jared Erickson
 */
public abstract class AbstractDrawCommand<T extends DrawOptions> extends GeometryCommand<T> {

    /**
     * Process the BufferedImage
     * @param image The BufferedImage
     * @param geometry The input Geometry
     * @param options The DrawOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     */
    protected abstract void processImage(BufferedImage image, Geometry geometry, T options, Reader reader, Writer writer) throws Exception;

    /**
     * Draw the input Geometry to an image File
     * @param geometry The input Geometry
     * @param options The DrawOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     */
    @Override
    protected void processGeometry(Geometry geometry, T options, Reader reader, Writer writer) throws Exception {

        final int imageWidth = options.getWidth();
        final int imageHeight = options.getHeight();

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
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

        ShapeWriter shapeWriter = new ShapeWriter(new PointTransformation() {

            @Override
            public void transform(Coordinate coord, Point2D point) {
                double imageX = (1 - (env.getMaxX() - coord.x) / env.getWidth()) * imageWidth;
                double imageY = ((env.getMaxY() - coord.y) / env.getHeight()) * imageHeight;
                point.setLocation(imageX, imageY);
            }
        }, pointShapeFactory);

        Composite strokeComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, options.getStrokeOpacity());
        Composite fillComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, options.getFillOpacity());

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
        g2d.setStroke(new BasicStroke(options.getStrokeWidth()));
        g2d.setColor(strokeColor);
        g2d.draw(shp);

        if (options.isDrawingCoordinates()) {
            g2d.setStroke(new BasicStroke(options.getStrokeWidth()));
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

        processImage(image, geometry, options, reader, writer);
    }

    /**
     * Get a java.awt.Color from a String
     * @param value A String value
     * @return The java.awt.Color
     */
    protected Color getColor(String value) {
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
     * Get an Envelope either from the Geometry or from the DrawOptions bounds property
     * @param geometry The input Geometry
     * @param options The DrawOptions
     * @return An Envelope
     */
    protected Envelope getEnvelope(Geometry geometry, T options) {
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
        if (env.getWidth() == 0 || env.getHeight() == 0) {
            if (env.getHeight() > 0) {
                double h = env.getHeight() / 2.0;
                env = new Envelope(env.getMinX() - h, env.getMaxX() + h, env.getMinY(), env.getMaxY());
            }
            else if (env.getWidth() > 0) {
                double w = env.getWidth() / 2.0;
                env = new Envelope(env.getMinX(), env.getMaxX(), env.getMinY() - w, env.getMaxY() + w);
            }
            else {
                env = geometry.getFactory().createPoint(new Coordinate(env.getMinX(), env.getMinY())).buffer(0.1).getEnvelopeInternal();
            }
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
         * The stroke opacity
         */
        @Option(name = "-t", aliases = "--strokeOpacity", usage = "The stroke opacity", required = false)
        private float strokeOpacity = 1.0f;

        /**
         * The stroke width
         */
        @Option(name = "-r", aliases = "--strokeWidth", usage = "The stroke width", required = false)
        private float strokeWidth = 1.0f;

        /**
         * The fill Color
         */
        @Option(name = "-l", aliases = "--fill", usage = "The fill Color", required = false)
        private String fillColor = "206,206,206,100";

        /**
         * The fill opacity
         */
        @Option(name = "-o", aliases = "--fillOpacity", usage = "The fill opacity", required = false)
        private float fillOpacity = 0.75f;

        /**
         * The marker shape
         */
        @Option(name = "-m", aliases = "--shape", usage = "The marker shape (circle, square, ect..)", required = false)
        private String shape = "circle";

        /**
         * The marker size
         */
        @Option(name = "-z", aliases = "--size", usage = "The marker size", required = false)
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

        /**
         * Get the background image
         * @return The background image
         */
        public String getBackgroundImage() {
            return backgroundImage;
        }

        /**
         * Set the background image
         * @param backgroundImage The background image
         */
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
         * Get the fill opacity
         * @return The fill opacity
         */
        public float getFillOpacity() {
            return fillOpacity;
        }

        /**
         * Set the fill opacity
         * @param opacity The fill opacity
         */
        public void setFillOpacity(float opacity) {
            this.fillOpacity = opacity;
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
         * Get the stroke opacity
         * @return The stroke opacity
         */
        public float getStrokeOpacity() {
            return strokeOpacity;
        }

        /**
         * Set the stroke opacity
         * @param opacity The stroke opacity
         */
        public void setStrokeOpacity(float opacity) {
            this.strokeOpacity = opacity;
        }

        /**
         * Get the stroke width
         * @return The stroke width
         */
        public float getStrokeWidth() {
            return strokeWidth;
        }

        /**
         * Set the stroke width
         * @param width The stroke width
         */
        public void setStrokeWidth(float width) {
            this.strokeWidth = width;
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
