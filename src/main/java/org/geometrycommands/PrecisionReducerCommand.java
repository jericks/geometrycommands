package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.precision.GeometryPrecisionReducer;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.PrecisionReducerCommand.PrecisionReducerOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command for reducing the precision of the input Geometry.
 * @author Jared Erickson
 */
public class PrecisionReducerCommand extends GeometryCommand<PrecisionReducerOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "reduceprecision";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Reduce the precision of the input geometry.";
    }

    /**
     * Get the PrecisionReducerOptions
     * @return The PrecisionReducerOptions
     */
    @Override
    public PrecisionReducerOptions getOptions() {
        return new PrecisionReducerOptions();
    }

    /**
     * Reduce the precision of the input Geometry
     * @param geometry The input Geometry
     * @param options The PrecisionReducerOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, PrecisionReducerOptions options, Reader reader, Writer writer) throws Exception {

        PrecisionModel precisionModel;
        if (options.getPrecisionModelType().equalsIgnoreCase("fixed")) {
            precisionModel = new PrecisionModel(options.getPrecisionModelScale());
        } else if (options.getPrecisionModelType().equalsIgnoreCase("floating")) {
            precisionModel = new PrecisionModel(PrecisionModel.FLOATING);
        } else if (options.getPrecisionModelType().equalsIgnoreCase("floating_single")) {
            precisionModel = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);
        } else {
            throw new IllegalArgumentException("Unsupported Precision Model Type: '" + options.getPrecisionModelType() + "'!");
        }

        GeometryPrecisionReducer reducer = new GeometryPrecisionReducer(precisionModel);
        reducer.setPointwise(options.isPointwise());
        reducer.setRemoveCollapsedComponents(options.isRemoveCollapsed());
        Geometry reducedGeoemtry = reducer.reduce(geometry);

        writer.write(writeGeometry(reducedGeoemtry, options));
    }

    /**
     * The PrecisionReducerOptions
     */
    public static class PrecisionReducerOptions extends GeometryOptions {

        /**
         * The precision model type (FIXED, FLOATING, FLOATING_SINGLE)
         */
        @Option(name = "-t", aliases = "--type", usage = "The precision model type (FIXED, FLOATING, FLOATING_SINGLE)", required = true)
        private String precisionModelType;

        /**
         * The precision model scale when type is FLOATING
         */
        @Option(name = "-s", aliases = "--scale", usage = "The precision model scale when type is FLOATING", required = false)
        private double precisionModelScale;

        /**
         * Whether the precision reducer operates pointwise 
         */
        @Option(name = "-p", aliases = "--pointWise", usage = "Whether the precision reducer operates pointwise", required = false)
        private boolean pointwise = false;

        /**
         * Whether the precision reducer should remove collapsed geometry
         */
        @Option(name = "-r", aliases = "--removeCollapsed", usage = "Whether the precision reducer should remove collapsed geometry", required = false)
        private boolean removeCollapsed = false;

        /**
         * Get whether the precision reducer operates pointwise 
         * @return Whether the precision reducer operates pointwise 
         */
        public boolean isPointwise() {
            return pointwise;
        }

        /**
         * Set whether the precision reducer operates pointwise 
         * @param pointwise Whether the precision reducer operates pointwise 
         */
        public void setPointwise(boolean pointwise) {
            this.pointwise = pointwise;
        }

        /**
         * Get the precision model scale when type is FLOATING
         * @return The precision model scale when type is FLOATING
         */
        public double getPrecisionModelScale() {
            return precisionModelScale;
        }

        /**
         * Set the precision model scale when type is FLOATING
         * @param precisionModelScale The precision model scale when type is FLOATING
         */
        public void setPrecisionModelScale(double precisionModelScale) {
            this.precisionModelScale = precisionModelScale;
        }

        /**
         * Get the precision model type (FIXED, FLOATING, FLOATING_SINGLE)
         * @return The precision model type (FIXED, FLOATING, FLOATING_SINGLE)
         */
        public String getPrecisionModelType() {
            return precisionModelType;
        }

        /**
         * Set the precision model type (FIXED, FLOATING, FLOATING_SINGLE)
         * @param precisionModelType The precision model type (FIXED, FLOATING, FLOATING_SINGLE)
         */
        public void setPrecisionModelType(String precisionModelType) {
            this.precisionModelType = precisionModelType;
        }

        /**
         * Get whether the precision reducer should remove collapsed geometry
         * @return Whether the precision reducer should remove collapsed geometry
         */
        public boolean isRemoveCollapsed() {
            return removeCollapsed;
        }

        /**
         * Set whether the precision reducer should remove collapsed geometry
         * @param removeCollapsed Whether the precision reducer should remove collapsed geometry
         */
        public void setRemoveCollapsed(boolean removeCollapsed) {
            this.removeCollapsed = removeCollapsed;
        }
    }
}
