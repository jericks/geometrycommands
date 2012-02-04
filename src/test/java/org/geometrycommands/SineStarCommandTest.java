package org.geometrycommands;

import org.geometrycommands.SineStarCommand.SineStarOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SineStarCommand UnitTest
 * @author Jared Erickson
 */
public class SineStarCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        SineStarOptions options = new SineStarOptions();
        options.setGeometry(inputGeometry);
        options.setNumberOfArms(5);
        options.setArmLengthRatio(5);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        SineStarCommand command = new SineStarCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((150 125, 125 125, 132.72542485937367 148.77641290737884, "
                + "125 125, 104.77457514062633 139.69463130731182, 125 125, "
                + "104.77457514062631 110.30536869268818, 125 125, "
                + "132.72542485937367 101.22358709262116, 125 125, 150 125))",
                writer.getBuffer().toString());
    }
}