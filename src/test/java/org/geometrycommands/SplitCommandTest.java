package org.geometrycommands;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.SplitCommand.SplitOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The SplitCommand Unit Test
 * @author Jared Erickson
 */
public class SplitCommandTest {

    @Test
    public void execute() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "LINESTRING (0 0, 10 10)";
        SplitOptions options = new SplitOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        SplitCommand command = new SplitCommand();
        command.execute(options, reader, writer);
        Geometry splitGeom = new WKTReader().read(writer.getBuffer().toString());
        assertNotNull(splitGeom);
        assertEquals(2, splitGeom.getNumGeometries());
    }

}
