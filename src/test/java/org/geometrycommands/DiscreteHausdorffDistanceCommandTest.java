package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DiscreteHausdorffDistanceCommand UnitTest
 * @author Jared Erickson
 */
public class DiscreteHausdorffDistanceCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (1 1)";
        String otherGeometry = "POINT (20 23)";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DiscreteHausdorffDistanceCommand command = new DiscreteHausdorffDistanceCommand();
        command.execute(options, reader, writer);
        assertEquals("29.068883707497267", writer.getBuffer().toString());
    }
}
