package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;
import org.geometrycommands.GetGeometryCommand.GetGeometryOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to get a sub Geometry from an input Geometry
 * @author Jared Erickson
 */
public class GetGeometryCommand extends GeometryCommand<GetGeometryOptions>{
    
    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "get";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get a sub geometry from a geometry collection by index.";
    }

    /**
     * Get a new GetGeometryOptions
     * @return A new GetGeometryOptions
     */
    @Override
    public GetGeometryOptions getOptions() {
        return new GetGeometryOptions();
    }

    /**
     * Get a sub Geometry from the input Geometry.
     * @param geometry The input Geometry
     * @param options The GetGeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GetGeometryOptions options, Reader reader, Writer writer) throws Exception {
        Geometry outputGeometry = geometry.getGeometryN(options.getIndex());
        writer.write(writeGeometry(outputGeometry, options));
    }
    
    /**
     * The GetGeometryOptions
     */
    public static class GetGeometryOptions extends GeometryOptions {
        
        /**
         * The index number of the Geometry
         */
        @Option(name="-n", aliases = "--index", usage="The index number of the Geometry", required=true)
        private int index;
        
        /**
         * Get the index number of the Geometry
         * @return The index number of the Geometry
         */
        public int getIndex() {
            return index;
        }
        
        /**
         * Set the index number of the Geometry
         * @param index The index number of the Geometry
         */
        public void setIndex(int index) {
            this.index = index;
        }
        
    }
    
}
