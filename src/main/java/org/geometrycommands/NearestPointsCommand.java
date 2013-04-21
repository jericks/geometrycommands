package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.operation.distance.DistanceOp;
import org.geometrycommands.NearestPointsCommand.NearestPointsOptions;
import java.io.Reader;
import java.io.Writer;

/**
 * Get the nearest points between two geometries.
 * @author Jared Erickson
 */
public class NearestPointsCommand extends OtherGeometryCommand<NearestPointsOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "nearestpoints";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the nearest points between two geometries";
    }

    /**
     * Get a new GeometryOptions
     *
     * @return A new GeometryOptions
     */
    @Override
    public NearestPointsOptions getOptions() {
        return new NearestPointsOptions();
    }

    /**
     * Process the input Geometry and another Geometry
     *
     * @param geometry The input Geometry
     * @param other    The other Geometry
     * @param options  The OtherGeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometries(Geometry geometry, Geometry other, NearestPointsOptions options, Reader reader, Writer writer) throws Exception {
        Coordinate[] coords = DistanceOp.nearestPoints(geometry, other);
        GeometryFactory geometryFactory = new GeometryFactory();
        MultiPoint multiPoint = geometryFactory.createMultiPoint(coords);
        writer.write(writeGeometry(multiPoint, options));
    }

    /**
     * The NearestPointsOptions
     */
    public static class NearestPointsOptions extends OtherGeometryOptions {
    }
}
