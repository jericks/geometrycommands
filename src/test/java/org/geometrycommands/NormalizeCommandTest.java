package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.NormalizeCommand.NormalizeOptions;

/**
 * The NormalizeCommand UnitTest
 * @author Jared Erickson
 */
public class NormalizeCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))";
        NormalizeOptions options = new NormalizeOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        NormalizeCommand command = new NormalizeCommand();
        command.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((6.857126638942733 8.821305316892328), "
                + "(8.017822881853032 7.492806794533148), (9.260874914207697 "
                + "13.087320259444919), (12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "normalize",
                "-g", "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))",
        }, null);
        assertEquals("MULTIPOINT ((6.857126638942733 8.821305316892328), "
                + "(8.017822881853032 7.492806794533148), (9.260874914207697 "
                + "13.087320259444919), (12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "normalize"
        }, "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))");
        assertEquals("MULTIPOINT ((6.857126638942733 8.821305316892328), "
                + "(8.017822881853032 7.492806794533148), (9.260874914207697 "
                + "13.087320259444919), (12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621))", result);
    }
}
