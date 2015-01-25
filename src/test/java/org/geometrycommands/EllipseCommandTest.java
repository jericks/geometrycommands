package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.EllipseCommand.EllipseOptions;

/**
 * The EllipseCommand UnitTest
 * @author Jared Erickson
 */
public class EllipseCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        EllipseOptions options = new EllipseOptions();
        options.setGeometry(inputGeometry);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        EllipseCommand command = new EllipseCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((150 120, 145.22542485937367 131.75570504584945, "
                + "132.72542485937367 139.02113032590307, "
                + "117.27457514062631 139.02113032590307, "
                + "104.77457514062633 131.75570504584945, "
                + "100 120, 104.77457514062631 108.24429495415055, "
                + "117.27457514062631 100.97886967409693, "
                + "132.72542485937367 100.97886967409693, "
                + "145.22542485937367 108.24429495415053, 150 120))",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "ellipse",
                "-g", "POINT (100 100)",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, null);
        assertEquals("POLYGON ((150 120, 145.22542485937367 131.75570504584945, "
                + "132.72542485937367 139.02113032590307, "
                + "117.27457514062631 139.02113032590307, "
                + "104.77457514062633 131.75570504584945, "
                + "100 120, 104.77457514062631 108.24429495415055, "
                + "117.27457514062631 100.97886967409693, "
                + "132.72542485937367 100.97886967409693, "
                + "145.22542485937367 108.24429495415053, 150 120))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "ellipse",
                "-w", "50",
                "-h", "40",
                "-p", "10"
        }, "POINT (100 100)");
        assertEquals("POLYGON ((150 120, 145.22542485937367 131.75570504584945, "
                + "132.72542485937367 139.02113032590307, "
                + "117.27457514062631 139.02113032590307, "
                + "104.77457514062633 131.75570504584945, "
                + "100 120, 104.77457514062631 108.24429495415055, "
                + "117.27457514062631 100.97886967409693, "
                + "132.72542485937367 100.97886967409693, "
                + "145.22542485937367 108.24429495415053, 150 120))", result);
    }
}
