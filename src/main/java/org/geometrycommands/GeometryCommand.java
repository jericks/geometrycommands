package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKTReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The base class Command that deals with at least one Geometry
 * @author Jared Erickson
 */
public abstract class GeometryCommand<T extends GeometryOptions> implements Command<T> {

    /**
     * The WKTReader
     */
    private final WKTReader wktReader = new WKTReader();
    
    /**
     * The WKBReader
     */
    private final WKBReader wkbReader = new WKBReader();

    /**
     * Execute the Command against at least one input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(T options) throws Exception {
        // Get Geometry from the GeometryOption
        Geometry geometry;
        if (options.getGeometry() != null && options.getGeometry().trim().length() > 0) {
            geometry = readGeometry(options.getGeometry(), options);
        } else {
            // Or Read it from the Standard Inputstream
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = inputStreamReader.readLine()) != null) {
                builder.append(str);
            };
            String text = builder.toString();
            geometry = wktReader.read(text);
        }
        // Process the Geometry
        processGeometry(geometry, options);
    }

    /**
     * Read a Geometry.
     * @param geometry A Geometry encoded as a String
     * @param options The GeometryOptions
     * @return The Geometry
     * @throws Exception if an error occurs
     */
    protected Geometry readGeometry(String geometry, T options) throws Exception {
        return wktReader.read(geometry);
    }

    /**
     * Write a Geometry
     * @param geometry A Geometry
     * @param options The GeometryOptions
     * @return The Geometry encoded as a String
     * @throws Exception if an error occurs
     */
    protected String writeGeoemtry(Geometry geometry, T options) throws Exception {
        return geometry.toText();
    }

    /**
     * Process the input Geometry
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @throws Exception if an error occurs
     */
    protected abstract void processGeometry(Geometry geometry, T options) throws Exception;

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public abstract String getName();

    /**
     * Get a new GeometryOptions
     * @return A new GeometryOptions
     */
    @Override
    public abstract T getOptions();
}
