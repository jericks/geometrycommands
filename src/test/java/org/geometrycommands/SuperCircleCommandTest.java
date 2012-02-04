package org.geometrycommands;

import org.geometrycommands.SuperCircleCommand.SuperCircleOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SuperCircleCommand UnitTest
 * @author Jared Erickson
 */
public class SuperCircleCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        SuperCircleOptions options = new SuperCircleOptions();
        options.setGeometry(inputGeometry);
        options.setPower(3.2);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        SuperCircleCommand command = new SuperCircleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((125 140, 141.10490331949254 136.10490331949254, "
                + "145 120, 141.10490331949254 103.89509668050746, 125 100, "
                + "108.89509668050746 103.89509668050745, 105 120, "
                + "108.89509668050745 136.10490331949254, 125 140))",
                writer.getBuffer().toString());
    }
}
