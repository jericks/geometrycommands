package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.shape.fractal.SierpinskiCarpetBuilder;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.SierpinskiCarpetCommand.SierpinskiCarpetOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to create a Sierpinski Carpet Geometry.
 * @author Jared Erickson
 */
public class SierpinskiCarpetCommand extends GeometryCommand<SierpinskiCarpetOptions> {

    /**
     * Get the command's name
     * @return The command's name
     */
    @Override
    public String getName() {
        return "sierpinskicarpet";
    }

    /**
     * Get the new SierpinskiCarpetOptions
     * @return The new SierpinskiCarpetOptions
     */
    @Override
    public SierpinskiCarpetOptions getOptions() {
        return new SierpinskiCarpetOptions();
    }

    /**
     * Create a Sierpinski carpet as a Geometry.
     * @param geometry The input Geometry
     * @param options The KochSnowflakeOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, SierpinskiCarpetOptions options, Reader reader, Writer writer) throws Exception {
        SierpinskiCarpetBuilder builder = new SierpinskiCarpetBuilder(new GeometryFactory());
        builder.setExtent(geometry.getEnvelopeInternal());
        builder.setNumPoints(options.getNumberOfPoints());
        Geometry outputGeometry = builder.getGeometry();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The SierpinskiCarpetOptions
     */
    public static class SierpinskiCarpetOptions extends GeometryOptions {

        /**
         * The number of points.
         */
        @Option(name = "-n", usage = "The number of points.", required = true)
        private int numberOfPoints;

        /**
         * Get the number of points.
         * @return The number of points.
         */
        public int getNumberOfPoints() {
            return numberOfPoints;
        }

        /**
         * Set the number of points.
         * @param numberOfPoints The number of points. 
         */
        public void setNumberOfPoints(int numberOfPoints) {
            this.numberOfPoints = numberOfPoints;
        }
    }
}
