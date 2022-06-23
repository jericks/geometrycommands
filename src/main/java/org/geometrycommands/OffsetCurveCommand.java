package org.geometrycommands;

import org.geometrycommands.OffsetCurveCommand.OffsetCurveOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.buffer.BufferParameters;
import org.locationtech.jts.operation.buffer.OffsetCurve;

import java.io.Reader;
import java.io.Writer;

/**
 * A Command for calculating the offsetCurve of a Geometry.
 * @author Jared Erickson
 */
public class OffsetCurveCommand extends GeometryCommand<OffsetCurveOptions> {

    /**
     * Get the command name
     * @return The command name
     */
    @Override
    public String getName() {
        return "offsetCurve";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Calculate the offsetCurve of a Geometry.";
    }

    /**
     * Get a new OffsetCurveOptions
     * @return A new OffsetCurveOptions
     */
    @Override
    public OffsetCurveOptions getOptions() {
        return new OffsetCurveOptions();
    }

    /**
     * Calculate the offsetCurve of the input geometry
     * @param geometry The input geometry
     * @param options The OffsetCurveOptions
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, OffsetCurveOptions options, Reader reader, Writer writer) throws Exception {
        BufferParameters params = BufferCommand.getBufferParametersg(options);
        OffsetCurve offsetCurve = new OffsetCurve(geometry, options.getDistance(), params);
        Geometry outputGeometry = offsetCurve.getCurve();
        writer.write(writeGeometry(outputGeometry, options));
    }

    /**
     * The OffsetCurveOptions
     */
    public static class OffsetCurveOptions extends BufferCommand.BufferOptions {
    }
}
