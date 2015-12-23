package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.noding.snapround.GeometryNoder;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.geometrycommands.NodeCommand.NodeOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to node LineStrings from the input Geometry.
 * @author jericks
 */
public class NodeCommand extends GeometryCommand<NodeOptions> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "node";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Node the linestrings from the input geometry.";
    }

    /**
     * Get a new NodeOptions
     * @return A new NodeOptions
     */
    @Override
    public NodeOptions getOptions() {
        return new NodeOptions();
    }

    /**
     * Node the LineStrings from the input Geometry.
     * @param geometry The input Geometry
     * @param options The NodeOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, NodeOptions options, Reader reader, Writer writer) throws Exception {
        PrecisionModel precisionModel = new PrecisionModel(options.getNumberOfDecimalPlaces());
        GeometryNoder noder = new GeometryNoder(precisionModel);
        List<Geometry> geometries = new ArrayList<Geometry>();
        for (int i = 0; i < geometry.getNumGeometries(); i++) {
            geometries.add(geometry.getGeometryN(i));
        }
        List lineStrings = noder.node(geometries);
        Geometry nodedGeometry = geometry.getFactory().createMultiLineString((LineString[]) lineStrings.toArray(new LineString[lineStrings.size()]));
        writer.write(writeGeometry(nodedGeometry, options));
    }

    /**
     * The NodeOpions
     */
    public static class NodeOptions extends GeometryOptions {

        /**
         * The number of decimal places
         */
        @Option(name = "-n", aliases = "--number", usage = "The number of decimal places", required = true)
        private int numberOfDecimalPlaces;

        /**
         * Set the number of decimal places
         * @return The number of decimal places
         */
        public int getNumberOfDecimalPlaces() {
            return numberOfDecimalPlaces;
        }

        /**
         * Get the number of decimal places
         * @param numberOfDecimalPlaces The number of decimal places 
         */
        public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces) {
            this.numberOfDecimalPlaces = numberOfDecimalPlaces;
        }
    }
}
