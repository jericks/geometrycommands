package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import org.geometrycommands.GetGeometryCommand.GetGeometryCommandOptions;
import org.kohsuke.args4j.Option;

/**
 * A Command to get a sub Geometry from an input Geometry
 * @author Jared Erickson
 */
public class GetGeometryCommand extends GeometryCommand<GetGeometryCommandOptions>{
    
    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "get";
    }

    /**
     * Get a new GetGeometryCommandOptions
     * @return A new GetGeometryCommandOptions
     */
    @Override
    public GetGeometryCommandOptions getOptions() {
        return new GetGeometryCommandOptions();
    }

    /**
     * Get a sub Geometry from the input Geometry.
     * @param geometry The input Geometry
     * @param options The GetGeometryCommandOptions
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GetGeometryCommandOptions options) throws Exception {
        Geometry outputGeometry = geometry.getGeometryN(options.getIndex());
        System.out.println(writeGeoemtry(outputGeometry, options));
    }
    
    /**
     * The GetGeometryCommandOptions
     */
    public static class GetGeometryCommandOptions extends GeometryOptions {
        
        /**
         * The index number of the Geometry
         */
        @Option(name="-n", usage="The index number of the Geometry", required=true)
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
         * @param The index number of the Geometry
         */
        public void setIndex(int index) {
            this.index = index;
        }
        
    }
    
}
