package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.util.GeometryCombiner;
import com.vividsolutions.jts.io.WKTReader;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * A Command that takes Geometries on separate lines and creates a GeometryCollection
 * from them.
 * @author Jared Erickson
 */
public class CombineCommand implements Command<Options> {

    /**
     * The WKTReader
     */
    private final WKTReader wktReader = new WKTReader();

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "combine";
    }

    /**
     * Get the Options
     * @return The Options
     */
    @Override
    public Options getOptions() {
        return new Options();
    }

    /**
     * Collect all Geometries on separate lines and create a GeometryCollection.
     * @param options The Options
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(Options options, Reader reader, Writer writer) throws Exception {
        List<Geometry> geometries = new ArrayList<Geometry>();
        BufferedReader bReader = new BufferedReader(reader);
        String str;
        while ((str = bReader.readLine()) != null) {
            geometries.add(wktReader.read(str));
        }
        GeometryCombiner combiner = new GeometryCombiner(geometries);
        Geometry outputGeometry = combiner.combine();
        writer.write(outputGeometry.toText());
    }
}
