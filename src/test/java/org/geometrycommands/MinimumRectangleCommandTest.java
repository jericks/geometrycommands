package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.MinimumRectangleCommand.MinimumRectangleOptions;

/**
 * The MinimumRectangleCommand UnitTest
 * @author Jared Erickson
 */
public class MinimumRectangleCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))";
        MinimumRectangleOptions options = new MinimumRectangleOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        MinimumRectangleCommand command = new MinimumRectangleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((13.737894633461437 7.658802439672627, "
                + "13.576725239178604 13.212565604281293, 6.7354542973976805 "
                + "13.014032922722615, 6.896623691680514 7.460269758113947, "
                + "13.737894633461437 7.658802439672627))", 
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "minrect",
                "-g", "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))",
        }, null);
        assertEquals("POLYGON ((13.737894633461437 7.658802439672627, "
                + "13.576725239178604 13.212565604281293, 6.7354542973976805 "
                + "13.014032922722615, 6.896623691680514 7.460269758113947, "
                + "13.737894633461437 7.658802439672627))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "minrect"
        }, "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))");
        assertEquals("POLYGON ((13.737894633461437 7.658802439672627, "
                + "13.576725239178604 13.212565604281293, 6.7354542973976805 "
                + "13.014032922722615, 6.896623691680514 7.460269758113947, "
                + "13.737894633461437 7.658802439672627))", result);
    }
}
