package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The UnionCommand UnitTest
 * @author Jared Erickson
 */
public class UnionCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((10 10, 10 14, 14 14, 14 10, 10 10))";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        UnionCommand command = new UnionCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOLYGON (((0 0, 0 10, 10 10, 10 0, 0 0)), "
                + "((10 10, 10 14, 14 14, 14 10, 10 10)))", writer.getBuffer().toString());
    }
}
