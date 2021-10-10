package org.geometrycommands;

import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.LineDissolverCommand.LineDissolverOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The LineDissolverCommand Unit Test
 * @author Jared Erickson
 */
public class LineDissolverCommandTest extends BaseTest {

    private static final String inputGeometry = "LINESTRING (1143429.5177049513 646812.5700195221, 1148620.8088546866 646812.5700195221, " +
            "1149092.7444137533 643980.956665121, 1150980.4866500208 640677.4077516531, 1156171.777799756 640677.4077516531, " +
            "1156171.777799756 643037.0855469874, 1156171.777799756 648700.3122557894, 1161835.004508558 658610.958996193, " +
            "1163250.8111857586 652003.8611692573, 1163250.8111857586 646812.5700195221, 1162778.8756266916 639733.5366335195, " +
            "1160891.1333904243 637373.8588381852, 1154755.9711225554 635486.1166019179, 1146733.0666184193 633598.3743656506, " +
            "1144845.3243821517 637373.8588381852, 1141069.839909617 642093.2144288537, 1138710.1621142828 650116.1189329899, " +
            "1136350.4843189488 649644.1833739231, 1134934.6776417482 647756.4411376558, 1134934.6776417482 645868.6989013883, " +
            "1136350.4843189488 639261.6010744526, 1139654.0332324165 637373.8588381852)";

    private static final String expectedOutputGeometry = "LINESTRING (1139654.0332324165 637373.8588381852, " +
            "1136350.4843189488 639261.6010744526, 1134934.6776417482 645868.6989013883, 1134934.6776417482 647756.4411376558, " +
            "1136350.4843189488 649644.1833739231, 1138710.1621142828 650116.1189329899, 1141069.839909617 642093.2144288537, " +
            "1144845.3243821517 637373.8588381852, 1146733.0666184193 633598.3743656506, 1154755.9711225554 635486.1166019179, " +
            "1160891.1333904243 637373.8588381852, 1162778.8756266916 639733.5366335195, 1163250.8111857586 646812.5700195221, " +
            "1163250.8111857586 652003.8611692573, 1161835.004508558 658610.958996193, 1156171.777799756 648700.3122557894, " +
            "1156171.777799756 643037.0855469874, 1156171.777799756 640677.4077516531, 1150980.4866500208 640677.4077516531, " +
            "1149092.7444137533 643980.956665121, 1148620.8088546866 646812.5700195221, 1143429.5177049513 646812.5700195221)";

    @Test
    public void executeWithOption() throws Exception {
        LineDissolverOptions options = new LineDissolverOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        LineDissolverCommand command = new LineDissolverCommand();
        command.execute(options, reader, writer);
        assertEquals(expectedOutputGeometry, writer.getBuffer().toString());
    }

    @Test
    public void executeWithReader() throws Exception {
        LineDissolverOptions options = new LineDissolverOptions();

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        LineDissolverCommand command = new LineDissolverCommand();
        command.execute(options, reader, writer);
        assertEquals(expectedOutputGeometry, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "linedissolve",
                "-g", inputGeometry
        }, null);
        assertEquals(expectedOutputGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "linedissolve"
        }, inputGeometry);
        assertEquals(expectedOutputGeometry, result);
    }
    
}
