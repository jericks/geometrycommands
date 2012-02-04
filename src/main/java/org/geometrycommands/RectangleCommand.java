package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create a Rectangle 
 * @author Jared Erickson
 */
public class RectangleCommand extends ShapeFactoryCommand<ShapeFactoryOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "rectangle";
    }

    /**
     * Get the ShapeFactoryOptions
     * @return The ShapeFactoryOptions
     */
    @Override
    public ShapeFactoryOptions getOptions() {
        return new ShapeFactoryOptions();
    }

    /**
     * Create a Rectangle Geometry 
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input Geometry
     * @param options The ShapeFactoryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, ShapeFactoryOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = shapeFactory.createRectangle();
        writer.write(writeGeometry(outputGeometry, options));
    }
}
