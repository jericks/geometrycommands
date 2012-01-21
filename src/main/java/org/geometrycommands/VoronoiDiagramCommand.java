package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.triangulate.VoronoiDiagramBuilder;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that generates a Voronoi Diagram for the input Geometry.
 * @author Jared Erickson
 */
public class VoronoiDiagramCommand extends GeometryCommand<GeometryOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "voronoi";
    }

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public GeometryOptions getOptions() {
        return new GeometryOptions();
    }

    /**
     * Generate a Voronoi Diagram for the input Geometry.
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, GeometryOptions options, Reader reader, Writer writer) throws Exception {
        VoronoiDiagramBuilder builder = new VoronoiDiagramBuilder();
        builder.setSites(geometry);
        Geometry outputGeometry = builder.getDiagram(geometry.getFactory());
        writer.write(writeGeometry(outputGeometry, options));
    }
}
