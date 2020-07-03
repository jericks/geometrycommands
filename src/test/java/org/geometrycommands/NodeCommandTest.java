package org.geometrycommands;

import org.geometrycommands.NodeCommand.NodeOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The NodeCommand UnitTest
 * @author Jared Erickson
 */
public class NodeCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "LINESTRING (5.19775390625 51.07421875, 7.52685546875 53.7548828125, 11.65771484375 49.931640625, "
                + "7.52685546875 47.20703125, 9.50439453125 54.501953125, 7.35107421875 52.4365234375, 4.53857421875 "
                + "52.65625, 6.38427734375 50.634765625)";
        NodeOptions options = new NodeOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfDecimalPlaces(5);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        NodeCommand command = new NodeCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTILINESTRING ((5.19775390625 51.07421875, 5.6 51.6), (5.6 51.6, 6.4 52.6), " +
                "(6.4 52.6, 7.52685546875 53.7548828125, 8.2 53.2), (8.2 53.2, 9 52.4), " +
                "(9 52.4, 11.65771484375 49.931640625, 7.52685546875 47.20703125, 9 52.4), " +
                "(9 52.4, 9.50439453125 54.501953125, 8.2 53.2), (8.2 53.2, 7.35107421875 52.4365234375, 6.4 52.6), " +
                "(6.4 52.6, 4.53857421875 52.65625, 5.6 51.6), (5.6 51.6, 6.38427734375 50.634765625))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "node",
                "-g", "LINESTRING (5.19775390625 51.07421875, 7.52685546875 53.7548828125, 11.65771484375 49.931640625, "
                + "7.52685546875 47.20703125, 9.50439453125 54.501953125, 7.35107421875 52.4365234375, 4.53857421875 "
                + "52.65625, 6.38427734375 50.634765625)",
                "-n", "5"
        }, null);
        assertEquals("MULTILINESTRING ((5.19775390625 51.07421875, 5.6 51.6), (5.6 51.6, 6.4 52.6), " +
                "(6.4 52.6, 7.52685546875 53.7548828125, 8.2 53.2), (8.2 53.2, 9 52.4), " +
                "(9 52.4, 11.65771484375 49.931640625, 7.52685546875 47.20703125, 9 52.4), " +
                "(9 52.4, 9.50439453125 54.501953125, 8.2 53.2), (8.2 53.2, 7.35107421875 52.4365234375, 6.4 52.6), " +
                "(6.4 52.6, 4.53857421875 52.65625, 5.6 51.6), (5.6 51.6, 6.38427734375 50.634765625))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "node",
                "-n", "5"
        }, "LINESTRING (5.19775390625 51.07421875, 7.52685546875 53.7548828125, 11.65771484375 49.931640625, "
                + "7.52685546875 47.20703125, 9.50439453125 54.501953125, 7.35107421875 52.4365234375, 4.53857421875 "
                + "52.65625, 6.38427734375 50.634765625)");
        assertEquals("MULTILINESTRING ((5.19775390625 51.07421875, 5.6 51.6), (5.6 51.6, 6.4 52.6), " +
                "(6.4 52.6, 7.52685546875 53.7548828125, 8.2 53.2), (8.2 53.2, 9 52.4), " +
                "(9 52.4, 11.65771484375 49.931640625, 7.52685546875 47.20703125, 9 52.4), " +
                "(9 52.4, 9.50439453125 54.501953125, 8.2 53.2), (8.2 53.2, 7.35107421875 52.4365234375, 6.4 52.6), " +
                "(6.4 52.6, 4.53857421875 52.65625, 5.6 51.6), (5.6 51.6, 6.38427734375 50.634765625))", result);
    }
}