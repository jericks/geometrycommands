package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;

/**
 * The base class Command that deals with at least one Geometry
 * @param <T> A GeometryOptions or subclass
 * @author Jared Erickson
 */
public abstract class GeometryCommand<T extends GeometryOptions> implements Command<T> {

    /**
     * The WKTReader
     */
    private final WKTReader wktReader = new WKTReader();

    /**
     * Execute the Command against at least one input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(T options, Reader reader, Writer writer) throws Exception {
        // Get Geometry from the GeometryOption
        Geometry geometry;
        if (options.getGeometry() != null && options.getGeometry().trim().length() > 0) {
            geometry = readGeometry(options.getGeometry(), options);
        } else {
            // Or Read it from the Reader
            BufferedReader inputStreamReader = new BufferedReader(reader);
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = inputStreamReader.readLine()) != null) {
                builder.append(str);
            }
            String text = builder.toString();
            geometry = readGeometry(text, options);
        }
        // Process the Geometry
        processGeometry(geometry, options, reader, writer);
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
    protected String writeGeometry(Geometry geometry, T options) throws Exception {
        return geometry.toText();
    }

    /**
     * Process the input Geometry
     * @param geometry The input Geometry
     * @param options The GeometryOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer
     * @throws Exception if an error occurs
     */
    protected abstract void processGeometry(Geometry geometry, T options, Reader reader, Writer writer) throws Exception;

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
