package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.OctagonalEnvelopeCommand.OctagonalEnvelopeOptions;

/**
 * The OctagonalEnvelopeCommand UnitTest
 * @author Jared Erickson
 */
public class OctagonalEnvelopeCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))";
        OctagonalEnvelopeOptions options = new OctagonalEnvelopeOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        OctagonalEnvelopeCommand command = new OctagonalEnvelopeCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((6.857126638942733 8.653503037443446, "
                + "6.857126638942733 10.683571984179956, 9.260874914207697 "
                + "13.087320259444919, 11.923578267022961 13.087320259444919, "
                + "13.737894633461437 11.273003893006443, 13.737894633461437 "
                + "7.658802439672621, 13.571898988321964 7.492806794533148, "
                + "8.017822881853032 7.492806794533148, 6.857126638942733 "
                + "8.653503037443446))", 
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "octagonalenvelope",
                "-g", "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))",
        }, null);
        assertEquals("POLYGON ((6.857126638942733 8.653503037443446, "
                        + "6.857126638942733 10.683571984179956, 9.260874914207697 "
                        + "13.087320259444919, 11.923578267022961 13.087320259444919, "
                        + "13.737894633461437 11.273003893006443, 13.737894633461437 "
                        + "7.658802439672621, 13.571898988321964 7.492806794533148, "
                        + "8.017822881853032 7.492806794533148, 6.857126638942733 "
                        + "8.653503037443446))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "octagonalenvelope"
        }, "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))");
        assertEquals("POLYGON ((6.857126638942733 8.653503037443446, "
                + "6.857126638942733 10.683571984179956, 9.260874914207697 "
                + "13.087320259444919, 11.923578267022961 13.087320259444919, "
                + "13.737894633461437 11.273003893006443, 13.737894633461437 "
                + "7.658802439672621, 13.571898988321964 7.492806794533148, "
                + "8.017822881853032 7.492806794533148, 6.857126638942733 "
                + "8.653503037443446))", result);
    }
}
