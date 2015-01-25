package org.geometrycommands;

import org.geometrycommands.RectangleCommand.RectangleOptions;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create a Rectangle 
 * @author Jared Erickson
 */
public class RectangleCommand extends ShapeFactoryCommand<RectangleOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "rectangle";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create a rectangle from the input geometry.";
    }

    /**
     * Get the RectangleOptions
     * @return The RectangleOptions
     */
    @Override
    public RectangleOptions getOptions() {
        return new RectangleOptions();
    }

    /**
     * Create a Rectangle Geometry 
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input Geometry
     * @param options The RectangleOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, RectangleOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = shapeFactory.createRectangle();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The RectangleOptions
     */
    public static class RectangleOptions extends ShapeFactoryOptions {
    }
}
