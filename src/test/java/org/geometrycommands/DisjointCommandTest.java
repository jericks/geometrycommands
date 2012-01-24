package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DisjointCommand UnitTest
 * @author Jared Erickson
 */
public class DisjointCommandTest {

    @Test
    public void execute() throws Exception {

        // false
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "LINESTRING (5 5, 5 15)";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DisjointCommand command = new DisjointCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());

        // true
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "LINESTRING (15 15, 20 20)";
        options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new DisjointCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
    }
}
