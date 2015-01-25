package org.geometrycommands;

import org.geometrycommands.EllipseCommand.EllipseOptions;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command to create an Ellipse or Circle 
 * @author Jared Erickson
 */
public class EllipseCommand extends ShapeFactoryCommand<EllipseOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "ellipse";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Create an ellipse of circle around the input geometry.";
    }

    /**
     * Get the EllipseOptions
     * @return The EllipseOptions
     */
    @Override
    public EllipseOptions getOptions() {
        return new EllipseOptions();
    }

    /**
     * Create an Ellipse or Circle Geometry 
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input Geometry
     * @param options The EllipseOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, EllipseOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = shapeFactory.createEllipse();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The EllipseOptions
     */
    public static class EllipseOptions extends ShapeFactoryOptions {
    }
}
