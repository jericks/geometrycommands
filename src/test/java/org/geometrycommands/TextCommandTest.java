package org.geometrycommands;

import org.geometrycommands.TextCommand.TextOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The TextCommand UnitTest
 * @author Jared Erickson
 */
public class TextCommandTest {

    @Test
    public void execute() throws Exception {

        TextOptions options = new TextOptions();
        options.setText("J");

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        TextCommand command = new TextCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((-2.15625 -3.22265625, -2.15625 -1.04296875, "
                + "-0.83203125 -1.3857421875, 0.328125 -1.5, 0.9781494140625 "
                + "-1.4498291015625, 1.52197265625 -1.29931640625, 1.9595947265625 "
                + "-1.0484619140625, 2.291015625 -0.697265625, 2.5345458984375 "
                + "-0.2208251953125, 2.70849609375 0.40576171875, 2.8128662109375 "
                + "1.1824951171875, 2.84765625 2.109375, 2.84765625 17.34375, "
                + "5.30859375 17.34375, 5.30859375 2.19140625, 5.22491455078125 "
                + "0.86480712890625, 4.973876953125 -0.284912109375, 4.55548095703125 "
                + "-1.25775146484375, 3.9697265625 -2.0537109375, 3.6140899658203125 "
                + "-2.3853607177734375, 3.21661376953125 -2.67279052734375, 2.296142578125 "
                + "-3.114990234375, 1.20831298828125 -3.38031005859375, -0.046875 -3.46875, "
                + "-0.984375 -3.4072265625, -2.15625 -3.22265625))",
                writer.getBuffer().toString());
    }
}
