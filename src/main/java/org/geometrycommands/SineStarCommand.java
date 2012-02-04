package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.util.SineStarFactory;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.SineStarCommand.SineStarOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command for creating sine star Geometries.
 * @author Jared Erickson
 */
public class SineStarCommand extends ShapeFactoryCommand<SineStarOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "sinestar";
    }

    /**
     * Get a new SineStarOptions
     * @return A new SineStarOptions
     */
    @Override
    public SineStarOptions getOptions() {
        return new SineStarOptions();
    }

    /**
     * Create a SineStarFactory
     * @return A SineStarFactory
     */
    @Override
    protected GeometricShapeFactory createGeometricShapeFactory() {
        return new SineStarFactory();
    }

    /**
     * Create a sine star with a preconfigured SineStarFactory.
     * @param shapeFactory The preconfigured SineStarFactory.
     * @param geometry The input Geometry
     * @param options The SineStarOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometryWithGeometricShapeFactory(GeometricShapeFactory shapeFactory, Geometry geometry, SineStarOptions options, Reader reader, Writer writer) throws Exception {
        SineStarFactory sineStarFactory = (SineStarFactory) shapeFactory;
        sineStarFactory.setNumPoints(options.getNumberOfPoints());
        sineStarFactory.setArmLengthRatio(options.getArmLengthRatio());
        sineStarFactory.setNumArms(options.getNumberOfArms());
        Geometry outputGeometry = sineStarFactory.createSineStar();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * SineStarOptions
     */
    public static class SineStarOptions extends ShapeFactoryOptions {

        /**
         * The number of arms
         */
        @Option(name = "-numArms", usage = "The number of arms", required = true)
        private int numberOfArms;

        /**
         * The arm length ratio
         */
        @Option(name = "-armLengthRatio", usage = "The arm length ratio", required = true)
        private double armLengthRatio;

        /**
         * Get the arm length ratio
         * @return The arm length ratio
         */
        public double getArmLengthRatio() {
            return armLengthRatio;
        }

        /**
         * Set the arm length ratio
         * @param armLengthRatio The arm length ratio
         */
        public void setArmLengthRatio(double armLengthRatio) {
            this.armLengthRatio = armLengthRatio;
        }

        /**
         * Get the number of arms
         * @return The number of arms
         */
        public int getNumberOfArms() {
            return numberOfArms;
        }

        /**
         * Set the number of arms
         * @param numberOfArms The number of arms
         */
        public void setNumberOfArms(int numberOfArms) {
            this.numberOfArms = numberOfArms;
        }
    }
}
