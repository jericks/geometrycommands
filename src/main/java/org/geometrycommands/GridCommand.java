package org.geometrycommands;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.GridCommand.GridOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to create a vector grid 
 * @author Jared Erickson
 */
public class GridCommand extends GeometryCommand<GridOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "grid";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate a grid around the input geometry.";
    }

    /**
     * Get the GridOptions
     * @return The GridOptions
     */
    @Override
    public GridOptions getOptions() {
        return new GridOptions();
    }

    /**
     * Create a vector grid 
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GridOptions options, Reader reader, Writer writer) throws Exception {
        
        // Get the input Geometry's Envelope
        Envelope env = geometry.getEnvelopeInternal();

        // Extract the min X and Y
        double minX = env.getMinX();
        double minY = env.getMinY();

        // Calculate hte envelop's width and height
        double w = env.getWidth();
        double h = env.getHeight();

        // Get the number of columns and rows
        int numberOfColumns = options.getNumberOfColumns();
        int numberOfRows = options.getNumberOfRows();

        // Calculate the cell width and height
        double cellWidth = w / options.getNumberOfColumns();
        double cellHeight = h / options.getNumberOfRows();

        // Create the cells
        GeometryFactory geometryFactory = new GeometryFactory();
        Geometry[] geoms = new Geometry[numberOfRows * numberOfColumns];
        int i = 0;
        for (int r = 0; r < numberOfRows; r++) {
            for (int c = 0; c < numberOfColumns; c++) {
                double x = minX + (c * cellWidth);
                double y = minY + (r * cellHeight);
                Envelope e = new Envelope(x, x + cellWidth, y, y + cellHeight);
                geoms[i] = geometryFactory.toGeometry(e);
                i++;
            }
        }

        // Create the Geometry grid
        Geometry grid = geometryFactory.createGeometryCollection(geoms);
        
        // Write the Geometry grid
        writer.write(writeGeometry(grid, options));
    }

    /**
     * The GridOptions
     */
    public static class GridOptions extends GeometryOptions {

        /**
         * The number of columns
         */
        @Option(name = "-c", aliases = "--columns", usage = "The number of columns", required = true)
        private int numberOfColumns;

        /**
         * The number of rows
         */
        @Option(name = "-r", aliases = "--rows", usage = "The number of rows", required = true)
        private int numberOfRows;

        /**
         * Get the number of columns
         * @return The number of columns
         */
        public int getNumberOfColumns() {
            return numberOfColumns;
        }

        /**
         * Set the number of columns
         * @param numberOfColumns The number of columns 
         */
        public void setNumberOfColumns(int numberOfColumns) {
            this.numberOfColumns = numberOfColumns;
        }

        /**
         * Get the number of rows
         * @return The number of rows
         */
        public int getNumberOfRows() {
            return numberOfRows;
        }

        /**
         * Set the number of rows
         * @param numberOfRows The number of rows 
         */
        public void setNumberOfRows(int numberOfRows) {
            this.numberOfRows = numberOfRows;
        }
    }
}
