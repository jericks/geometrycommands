package org.geometrycommands;

import org.geometrycommands.DumpCommand.DumpOptions;
import org.locationtech.jts.geom.Geometry;
import java.io.Reader;
import java.io.Writer;

/**
 * A Command that takes a GeometryCollection and puts each Geometry
 * on it's own line.
 * @author Jared Erickson
 */
public class DumpCommand extends GeometryCommand<DumpOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "dump";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Put each geometry from a GeometryCollection on it's own line.";
    }

    /**
     * Get the DumpOptions
     * @return The DumpOptions
     */
    @Override
    public DumpOptions getOptions() {
        return new DumpOptions();
    }

    /**
     * Write the input Geometry, one Geometry per line.
     * @param geometry The input Geometry
     * @param options The DumpOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, DumpOptions options, Reader reader, Writer writer) throws Exception {
        write(geometry, options, writer);
    }
    
    /**
     * The new line string
     */
    private final static String NEW_LINE = System.getProperty("line.separator");
    
    /**
     * Recursively write each sub Geometry to it's own line
     * @param geometry The Geometry
     * @param options The DumpOptions
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    private void write(Geometry geometry, DumpOptions options, Writer writer) throws Exception {
        int num = geometry.getNumGeometries();
        if (num == 1) {
            writer.write(writeGeometry(geometry, options));
            writer.write(NEW_LINE);
        }
        else {
            for (int i = 0; i < num; i++) {
                Geometry geom = geometry.getGeometryN(i);
                write(geom, options, writer);
            }
        }
    }

    /**
     * The DumpOptions
     */
    public static class DumpOptions extends GeometryOptions {
    }
}
