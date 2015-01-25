package org.geometrycommands;

import org.geometrycommands.SquircleCommand.SquircleOptions;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create a squircle
 * @author Jared Erickson
 */
public class SquircleCommand extends ShapeFactoryCommand<SquircleOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "squircle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a squircle.";
    }

    /**
     * Get the SquircleOptions
     * @return The SquircleOptions
     */
    @Override
    public SquircleOptions getOptions() {
        return new SquircleOptions();
    }

    /**
     * Create a squircle Geometry 
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input Geometry
     * @param options The SquircleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, SquircleOptions options, Reader reader, Writer writer) throws Exception {
        // There is a bug in JTS when creating Squircles with base
        if (geometry instanceof Point && !options.isCenter()) {
            Point point = (Point) geometry;
            Coordinate coord = new Coordinate(point.getX() + options.getWidth() / 2, point.getY() + options.getHeight() / 2);
            shapeFactory.setCentre(coord);
        }
        Geometry outputGeometry = shapeFactory.createSquircle();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The SquircleOptions
     */
    public static class SquircleOptions extends ShapeFactoryOptions {
    }
}
