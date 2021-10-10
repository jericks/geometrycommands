package org.geometrycommands;

import org.geometrycommands.SierpinskiCarpetCommand.SierpinskiCarpetOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The SierpinskiCarpetCommand UnitTest
 * @author Jared Erickson
 */
public class SierpinskiCarpetCommandTest extends BaseTest {

    private final String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";

    private final String resultGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0), (1.1111111111111112 "
            + "1.1111111111111112, 2.2222222222222223 1.1111111111111112, "
            + "2.2222222222222223 2.2222222222222223, 1.1111111111111112 "
            + "2.2222222222222223, 1.1111111111111112 1.1111111111111112), "
            + "(4.444444444444445 1.1111111111111112, 5.555555555555555 1.1111111111111112, "
            + "5.555555555555555 2.2222222222222223, 4.444444444444445 2.2222222222222223, "
            + "4.444444444444445 1.1111111111111112), (7.777777777777779 1.1111111111111112, "
            + "8.88888888888889 1.1111111111111112, 8.88888888888889 2.2222222222222223, "
            + "7.777777777777779 2.2222222222222223, 7.777777777777779 1.1111111111111112), "
            + "(1.1111111111111112 4.444444444444445, 2.2222222222222223 4.444444444444445, "
            + "2.2222222222222223 5.555555555555555, 1.1111111111111112 5.555555555555555, "
            + "1.1111111111111112 4.444444444444445), (7.777777777777779 4.444444444444445, "
            + "8.88888888888889 4.444444444444445, 8.88888888888889 5.555555555555555, "
            + "7.777777777777779 5.555555555555555, 7.777777777777779 4.444444444444445), "
            + "(1.1111111111111112 7.777777777777779, 2.2222222222222223 7.777777777777779, "
            + "2.2222222222222223 8.88888888888889, 1.1111111111111112 8.88888888888889, 1.1111111111111112 "
            + "7.777777777777779), (4.444444444444445 7.777777777777779, 5.555555555555555 7.777777777777779, "
            + "5.555555555555555 8.88888888888889, 4.444444444444445 8.88888888888889, 4.444444444444445 "
            + "7.777777777777779), (7.777777777777779 7.777777777777779, 8.88888888888889 7.777777777777779, "
            + "8.88888888888889 8.88888888888889, 7.777777777777779 8.88888888888889, 7.777777777777779 "
            + "7.777777777777779), (3.3333333333333335 3.3333333333333335, 6.666666666666667 "
            + "3.3333333333333335, 6.666666666666667 6.666666666666667, 3.3333333333333335 "
            + "6.666666666666667, 3.3333333333333335 3.3333333333333335))";

    @Test 
    public void execute() throws Exception {
        
        SierpinskiCarpetOptions options = new SierpinskiCarpetOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfPoints(30);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        SierpinskiCarpetCommand command = new SierpinskiCarpetCommand();
        command.execute(options, reader, writer);
        assertEquals(resultGeometry, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "sierpinskicarpet",
                "-g", inputGeometry,
                "-n", "30"
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "sierpinskicarpet",
                "-n", "30"
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}
