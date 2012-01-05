package org.geometrycommands;

import com.vividsolutions.jts.awt.PointShapeFactory;
import com.vividsolutions.jts.awt.PointTransformation;
import com.vividsolutions.jts.awt.ShapeWriter;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
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
import java.io.IOException;
import javax.imageio.ImageIO;
import org.geometrycommands.DrawCommand.DrawOptions;
import org.kohsuke.args4j.Option;

/**
 *
 * @author jericks
 */
public class DrawCommand extends GeometryCommand<DrawOptions> {

    @Override
    public String getName() {
        return "draw";
    }

    @Override
    public DrawOptions getOptions() {
        return new DrawOptions();
    }

    private Color getColor(String value) {
        String[] parts = value.split(",");
        int r = Integer.parseInt(parts[0]);
        int g = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        return new Color(r, g, b);
    }

    @Override
    public void processGeometry(Geometry geometry, DrawOptions options) throws Exception {

        final int imageWidth = options.getWidth();
        final int imageHeight = options.getHeight();

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(getColor(options.getBackgroundColor()));
        g2d.fillRect(0, 0, imageWidth, imageHeight);

        final Envelope env = geometry.getEnvelopeInternal();
        env.expandBy(env.getWidth() * 0.1);

        Color strokeColor = getColor(options.getStrokeColor());
        Color fillColor = getColor(options.getFillColor());

        String shape = options.getShape();
        double markerSize = 8;
        PointShapeFactory pointShapeFactory;
        if (shape.equalsIgnoreCase("cirlce")) {
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
        });

        Composite strokeComposite = g2d.getComposite();
        Composite fillComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);

        Shape shp = shapeWriter.toShape(geometry);
        g2d.setComposite(fillComposite);
        // Fill
        g2d.setComposite(fillComposite);
        g2d.setColor(fillColor);
        g2d.fill(shp);
        // Stroke
        g2d.setComposite(strokeComposite);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(strokeColor);
        g2d.draw(shp);

        boolean drawCoordinates = true;

        if (drawCoordinates) {
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

    public static class DrawOptions extends GeometryOptions {

        @Option(name = "-w", required = false)
        private int width = 400;

        @Option(name = "-h", required = false)
        private int height = 400;

        @Option(name = "-file", required = false)
        private File file;

        @Option(name = "-background", required = false)
        private String backgroundColor = "255,255,255";

        @Option(name = "-stroke", required = false)
        private String strokeColor = "99,99,99";

        @Option(name = "-fill", required = false)
        private String fillColor = "206,206,206";

        @Option(name = "-shape", required = false)
        private String shape = "circle";

        @Option(name = "-markerSize", required = false)
        private double markerSize;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public double getMarkerSize() {
            return markerSize;
        }

        public void setMarkerSize(double markerSize) {
            this.markerSize = markerSize;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public String getFillColor() {
            return fillColor;
        }

        public void setFillColor(String fillColor) {
            this.fillColor = fillColor;
        }

        public String getStrokeColor() {
            return strokeColor;
        }

        public void setStrokeColor(String strokeColor) {
            this.strokeColor = strokeColor;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }
    }
}
