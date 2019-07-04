package org.geometrycommands;

import org.geometrycommands.PointsAlongLineCommand.PointsAlongLineOptions;
import org.kohsuke.args4j.Option;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.linearref.LengthIndexedLine;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Create Points along a LineString.
 * @author Jared Erickson
 */
public class PointsAlongLineCommand extends GeometryCommand<PointsAlongLineOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "pointsalong";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Place points along a line";
    }

    /**
     * Get a new PointAlongLineOptions
     * @return The new PointAlongLineOptions
     */
    @Override
    public PointsAlongLineOptions getOptions() {
        return new PointsAlongLineOptions();
    }

    /**
     * PointAlongLine a Geometry by a distance.
     * @param geometry The Geometry
     * @param options The PointAlongLineOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, PointsAlongLineOptions options, Reader reader, Writer writer) throws Exception {
        MultiPoint multiPoint = geometry.getFactory().createMultiPoint(createPointsAlongGeometry(geometry, options.getDistance()).toArray(new Point[]{}));
        writer.write(writeGeometry(multiPoint, options));
    }

    private List<Point> createPointsAlongGeometry(Geometry geometry, double distance) {
        if (geometry instanceof MultiLineString) {
            List<Point> points = new ArrayList<>();
            MultiLineString multiLineString = (MultiLineString) geometry;
            for(int i = 0; i < multiLineString.getNumGeometries(); i++) {
                Geometry subGeometry = multiLineString.getGeometryN(i);
                points.addAll(createPointsAlongGeometry(subGeometry, distance));
            }
            return points;
        } else if (geometry instanceof LineString) {
            return createPointsAlongLineString((LineString) geometry, distance);
        } else {
            throw new IllegalArgumentException("Unsupported Geometry Type!");
        }
    }

    private List<Point> createPointsAlongLineString(LineString lineString, double distance) {
        double lineLength = lineString.getLength();
        LengthIndexedLine lengthIndexedLine = new LengthIndexedLine(lineString);
        List<Point> points = new ArrayList<>();
        points.add(lineString.getStartPoint());
        double distanceAlongLine = distance;
        while(distanceAlongLine < lineLength) {
            Coordinate coordinate = lengthIndexedLine.extractPoint(distanceAlongLine);
            Point point = lineString.getFactory().createPoint(coordinate);
            points.add(point);
            distanceAlongLine = distanceAlongLine + distance;
        }
        return points;
    }

    /**
     * Options for the PointAlongLineCommand
     */
    public static class PointsAlongLineOptions extends GeometryOptions {

        /**
         * The distance between points
         */
        @Option(name = "-d", aliases = "--distance", usage = "The distance between points", required = true)
        private double distance;


        /**
         * Get the distance between points
         * @return The distance between points
         */
        public double getDistance() {
            return distance;
        }

        /**
         * Set the distance between points
         * @param distance The distance between points
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }

    }
}
