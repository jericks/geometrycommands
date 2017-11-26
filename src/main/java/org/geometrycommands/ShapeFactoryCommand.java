package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;

/**
 * An abstract base class for creating geometric shapes from input geometry.
 * @author Jared Erickson
 */
public abstract class ShapeFactoryCommand<T extends ShapeFactoryOptions> extends GeometryCommand<T> {

    /**
     * Set up a GeometricShapeFactory to process the input Geometry
     * @param geometry The input geometry
     * @param options The SineStarOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, T options, Reader reader, Writer writer) throws Exception {
        // Validate arguments
        if (geometry instanceof Point) {
            if (options.getWidth() <= 0 || options.getHeight() <= 0) {
                throw new IllegalArgumentException("When the Geometry is a point, then width and height are required!");
            }
        }
        GeometricShapeFactory shapeFactory = createGeometricShapeFactory();
        shapeFactory.setNumPoints(options.getNumberOfPoints());
        shapeFactory.setRotation(options.getRotation());
        if (geometry instanceof Point) {
            shapeFactory.setWidth(options.getWidth());
            shapeFactory.setHeight(options.getHeight());
            if (options.isCenter()) {
                shapeFactory.setCentre(geometry.getCoordinate());
            } else {
                shapeFactory.setBase(geometry.getCoordinate());
            }
        } else {
            shapeFactory.setEnvelope(geometry.getEnvelopeInternal());
        }
        processGeometryWithGeometricShapeFactory(shapeFactory, geometry, options, reader, writer);
    }

    /**
     * Create a GeometricShapeFactory or a subclass
     * @return A GeometricShapeFactory
     */
    protected GeometricShapeFactory createGeometricShapeFactory() {
        return new GeometricShapeFactory();
    }

    /**
     * Process the input Geometry with the preconfigured GeometricShapeFactory
     * @param shapeFactory The preconfigured GeometricShapeFactory
     * @param geometry The input geometry
     * @param options The SineStarOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    protected abstract void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, T options, Reader reader, Writer writer) throws Exception;
}
