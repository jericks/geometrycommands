package org.geometrycommands;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.geometrycommands.RandomWalkCommand.RandomWalkOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to simulate a random walk
 * @author Jared Erickson
 */
public class RandomWalkCommand extends GeometryCommand<RandomWalkOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "randomwalk";
    }

    /**
     * Get the RandomWalkOptions
     * @return The RandomWalkOptions
     */
    @Override
    public RandomWalkOptions getOptions() {
        return new RandomWalkOptions();
    }

    /**
     * Generate a random walk
     * @param geometry The input Geometry that serves as the starting Point
     * @param options The RandomWalkOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, RandomWalkOptions options, Reader reader, Writer writer) throws Exception {

        Coordinate start = geometry.getCoordinate();

        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(start);

        int previousAngle = -1;
        Coordinate previousCoord = start;
        int angleRange = 360 / options.getAngleIncrement();

        for (int i = 0; i < options.getNumberOfWalks(); i++) {
            Random random = new Random();
            double r = random.nextDouble();
            boolean changeDirection = (r <= options.getProbability()) ? true : false;
            int angle = previousAngle;
            if (previousAngle > -1 || changeDirection) {
                while ((angle = random.nextInt(angleRange) * options.getAngleIncrement()) == getOpposite(previousAngle)) {
                    // Don't go back
                }
            } else {
                angle = random.nextInt(angleRange) * options.getAngleIncrement();
            }
            previousAngle = angle;
            previousCoord = getCoordinateAtAngle(previousCoord, angle, options.getDistance());
            coordinates.add(previousCoord);
        }
        LineString lineString = geometry.getFactory().createLineString((Coordinate[]) coordinates.toArray(new Coordinate[]{}));
        writer.write(writeGeometry(lineString, options));

    }

    /**
     * Get a new Coordinate at the given angle and distance from the input Coordinate
     * @param coord The input Coordinate
     * @param angle The angle (in degrees)
     * @param distance The distance
     * @return A new Coordinate
     */
    private Coordinate getCoordinateAtAngle(Coordinate coord, double angle, double distance) {
        double angleAsRadians = Math.toRadians(angle);
        double x = coord.x + (distance * Math.cos(angleAsRadians));
        double y = coord.y + (distance * Math.sin(angleAsRadians));
        return new Coordinate(x, y);
    }

    /**
     * Get the opposite of the given angle
     * @param angle The angle (in degrees)
     * @return The opposite angle (+/- 180)
     */
    private double getOpposite(double angle) {
        if (angle >= 180) {
            return angle - 180;
        } else {
            return 180 + angle;
        }
    }

    /**
     * The RandomWalkOptions
     */
    public static class RandomWalkOptions extends GeometryOptions {

        /**
         * The number of walks
         */
        @Option(name = "-n", usage = "The number of walks", required = true)
        private int numberOfWalks;

        /**
         * The distance between Coordinates
         */
        @Option(name = "-d", usage = "The distance between Coordinates", required = true)
        private double distance;

        /**
         * The probability of changing direction
         */
        @Option(name = "-p", usage = "The probability of changing direction", required = false)
        private double probability = 0.75;

        /**
         * The angle increment (in degrees) when changing direction.
         */
        @Option(name = "-a", usage = "The angle increment (in degrees) when changing direction", required = false)
        private int angleIncrement = 90;

        /**
         * Get the angle increment (in degrees) when changing direction.
         * @return The angle increment (in degrees) when changing direction.
         */
        public int getAngleIncrement() {
            return angleIncrement;
        }

        /**
         * Set the angle increment (in degrees) when changing direction.
         * @param angleIncrement The angle increment (in degrees) when changing direction.
         */
        public void setAngleIncrement(int angleIncrement) {
            this.angleIncrement = angleIncrement;
        }

        /**
         * Get the distance between Coordinates
         * @return The distance between Coordinates
         */
        public double getDistance() {
            return distance;
        }

        /**
         * Set the distance between Coordinates
         * @param distance The distance between Coordinates
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }

        /**
         * Ge the number of walks
         * @return The number of walks
         */
        public int getNumberOfWalks() {
            return numberOfWalks;
        }

        /**
         * Set the number of walks
         * @param numberOfWalks The number of walks
         */
        public void setNumberOfWalks(int numberOfWalks) {
            this.numberOfWalks = numberOfWalks;
        }

        /**
         * Get the probability of changing direction
         * @return The probability of changing direction
         */
        public double getProbability() {
            return probability;
        }

        /**
         * Set the probability of changing direction
         * @param probability The probability of changing direction
         */
        public void setProbability(double probability) {
            this.probability = probability;
        }
    }
}
