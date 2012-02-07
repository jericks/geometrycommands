package org.geometrycommands;

import org.geometrycommands.SnapCommand.SnapOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SnapCommand UnitTest
 * @author Jared Erickson
 */
public class SnapCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11))";
        SnapOptions options = new SnapOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setDistance(1.5);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        SnapCommand command = new SnapCommand();
        command.execute(options, reader, writer);
        assertEquals("GEOMETRYCOLLECTION ("
                + "POLYGON ((0 0, 0 10, 11 11, 10 0, 0 0)), "
                + "POLYGON ((11 11, 11 20, 20 20, 20 11, 11 11)))",
                writer.getBuffer().toString());
    }
}
