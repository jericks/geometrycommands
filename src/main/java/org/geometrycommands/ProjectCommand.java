package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.geometrycommands.ProjectCommand.ProjectOptions;
import org.kohsuke.args4j.Option;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;

/**
 * A Command to project the input Geometry from one coordinate system to another
 * @author Jared Erickson
 */
public class ProjectCommand extends GeometryCommand<ProjectOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "project";
    }

    /**
     * Get a new ProjectOptions
     * @return The new ProjectOptions
     */
    @Override
    public ProjectOptions getOptions() {
        return new ProjectOptions();
    }

    /**
     * Project the input Geometry from one coordinate system to another
     * @param geometry The Geometry
     * @param options The ProjectOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, ProjectOptions options, Reader reader, Writer writer) throws Exception {

        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CRSFactory csFactory = new CRSFactory();

        CoordinateReferenceSystem sourceCrs;
        if (isName(options.getSource())) {
            sourceCrs = csFactory.createFromName(options.getSource());
        } else {
            sourceCrs = csFactory.createFromParameters(null, options.getSource());
        }
        CoordinateReferenceSystem targetCrs;
        if (isName(options.getTarget())) {
            targetCrs = csFactory.createFromName(options.getTarget());
        } else {
            targetCrs = csFactory.createFromParameters(null, options.getTarget());
        }  

        CoordinateTransform transform = ctFactory.createTransform(sourceCrs, targetCrs);
        Geometry projectedGeometry = transformGeometry(transform, geometry);
        writer.write(writeGeometry(projectedGeometry, options));
    }

    private boolean isName(String projString) {
        final List<String> authorities = new ArrayList<String>(Arrays.asList(new String[]{"world", "nad83", "nad27", "esri", "epsg"}));
        int i = projString.indexOf(":");
        if (i > -1) {
            String authority = projString.substring(0, i).toLowerCase();
            String code = projString.substring(i + 1);
            if (authority.trim().length() > 0 && code.trim().length() > 0) {
                return authorities.contains(authority);
            }
        }
        return false;
    }
    
    private Geometry transformGeometry(CoordinateTransform transform, Geometry g) {
        if (g instanceof Point) {
            return transformPoint(transform, (Point) g);
        } else if (g instanceof LinearRing) {
            return transformLinearRing(transform, (LinearRing) g);
        } else if (g instanceof LineString) {
            return transformLineString(transform, (LineString) g);
        } else if (g instanceof Polygon) {
            return transformPolygon(transform, (Polygon) g);
        } else if (g instanceof MultiPoint) {
            return transformMultiPoint(transform, (MultiPoint) g);
        } else if (g instanceof MultiPoint) {
            return transformMultiPoint(transform, (MultiPoint) g);
        } else if (g instanceof MultiLineString) {
            return transformMultiLineString(transform, (MultiLineString) g);
        } else if (g instanceof MultiPolygon) {
            return transformMultiPolygon(transform, (MultiPolygon) g);
        } else if (g instanceof GeometryCollection) {
            return transformGeometryCollection(transform, (GeometryCollection) g);
        } else {
            return g;
        }
    }

    private Coordinate transformCoordinate(CoordinateTransform transform, Coordinate c) {
        ProjCoordinate in = new ProjCoordinate(c.x, c.y);
        ProjCoordinate out = new ProjCoordinate();
        transform.transform(in, out);
        return new Coordinate(out.x, out.y);
    }

    private Coordinate[] transformCoordinates(CoordinateTransform transform, Coordinate[] coords) {
        Coordinate[] projectedCoords = new Coordinate[coords.length];
        for (int i = 0; i < coords.length; i++) {
            projectedCoords[i] = transformCoordinate(transform, coords[i]);
        }
        return projectedCoords;
    }

    private Point transformPoint(CoordinateTransform transform, Point pt) {
        Coordinate c = transformCoordinate(transform, pt.getCoordinate());
        return pt.getFactory().createPoint(c);
    }

    private MultiPoint transformMultiPoint(CoordinateTransform transform, MultiPoint mp) {
        int num = mp.getNumGeometries();
        Point[] points = new Point[num];
        for (int i = 0; i < num; i++) {
            points[i] = transformPoint(transform, (Point) mp.getGeometryN(i));
        }
        return mp.getFactory().createMultiPoint(points);
    }

    private LineString transformLineString(CoordinateTransform transform, LineString line) {
        Coordinate[] coords = line.getCoordinates();
        Coordinate[] projectedCoords = new Coordinate[coords.length];
        for (int i = 0; i < coords.length; i++) {
            projectedCoords[i] = transformCoordinate(transform, coords[i]);
        }
        return line.getFactory().createLineString(projectedCoords);
    }
    
    private LinearRing transformLinearRing(CoordinateTransform transform, LinearRing ring) {
        Coordinate[] coords = ring.getCoordinates();
        Coordinate[] projectedCoords = new Coordinate[coords.length];
        for (int i = 0; i < coords.length; i++) {
            projectedCoords[i] = transformCoordinate(transform, coords[i]);
        }
        return ring.getFactory().createLinearRing(projectedCoords);
    }
    
    private MultiLineString transformMultiLineString(CoordinateTransform transform, MultiLineString ml) {
        int num = ml.getNumGeometries();
        LineString[] lines = new LineString[num];
        for (int i = 0; i < num; i++) {
            lines[i] = transformLineString(transform, (LineString) ml.getGeometryN(i));
        }
        return ml.getFactory().createMultiLineString(lines);
    }

    private Polygon transformPolygon(CoordinateTransform transform, Polygon polygon) {
        GeometryFactory factory = polygon.getFactory();
        LinearRing shell = factory.createLinearRing(transformCoordinates(transform, polygon.getExteriorRing().getCoordinates()));
        int num = polygon.getNumInteriorRing();
        LinearRing[] holes = new LinearRing[num];
        for (int i = 0; i < num; i++) {
            holes[i] = factory.createLinearRing(transformCoordinates(transform, polygon.getInteriorRingN(i).getCoordinates()));
        }
        return polygon.getFactory().createPolygon(shell, holes);
    }

    private MultiPolygon transformMultiPolygon(CoordinateTransform transform, MultiPolygon ml) {
        int num = ml.getNumGeometries();
        Polygon[] polygons = new Polygon[num];
        for (int i = 0; i < num; i++) {
            polygons[i] = transformPolygon(transform, (Polygon) ml.getGeometryN(i));
        }
        return ml.getFactory().createMultiPolygon(polygons);
    }
    
    private GeometryCollection transformGeometryCollection(CoordinateTransform transform, GeometryCollection gc) {
        int num = gc.getNumGeometries();
        Geometry[] geometries = new Geometry[num];
        for (int i = 0; i < num; i++) {
            geometries[i] = transformGeometry(transform, (Geometry) gc.getGeometryN(i));
        }
        return gc.getFactory().createGeometryCollection(geometries);
    }
    
    /**
     * Options for the ProjectOptions
     */
    public static class ProjectOptions extends GeometryOptions {

        /**
         * The source projection
         */
        @Option(name = "-s", usage = "The source projection", required = true)
        private String source;

        /**
         * The target projection
         */
        @Option(name = "-t", usage = "The target projection", required = true)
        private String target;

        /**
         * Get the source projection
         * @return The source projection
         */
        public String getSource() {
            return source;
        }

        /**
         * Set the source projection
         * @param source The source projection
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         * Get the target projection
         * @return The target projection
         */
        public String getTarget() {
            return target;
        }

        /**
         * Set the target projection
         * @param target The target projection
         */
        public void setTarget(String target) {
            this.target = target;
        }
    }
}
