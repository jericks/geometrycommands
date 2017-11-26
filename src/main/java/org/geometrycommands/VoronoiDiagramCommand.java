package org.geometrycommands;

import org.geometrycommands.VoronoiDiagramCommand.VoronoiDiagramOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that generates a Voronoi Diagram for the input Geometry.
 * @author Jared Erickson
 */
public class VoronoiDiagramCommand extends GeometryCommand<VoronoiDiagramOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "voronoi";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Generate a voronoi diagram.";
    }

    /**
     * Get a new VoronoiDiagramOptions
     * @return A new VoronoiDiagramOptions
     */
    @Override
    public VoronoiDiagramOptions getOptions() {
        return new VoronoiDiagramOptions();
    }

    /**
     * Generate a Voronoi Diagram for the input Geometry.
     * @param geometry The input Geometry
     * @param options The VoronoiDiagramOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, VoronoiDiagramOptions options, Reader reader, Writer writer) throws Exception {
        VoronoiDiagramBuilder builder = new VoronoiDiagramBuilder();
        builder.setSites(geometry);
        Geometry outputGeometry = builder.getDiagram(geometry.getFactory());
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The VoronoiDiagramOptions
     */
    public static class VoronoiDiagramOptions extends GeometryOptions {
    }
}
