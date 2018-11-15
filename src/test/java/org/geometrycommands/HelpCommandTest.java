package org.geometrycommands;

import org.geometrycommands.HelpCommand.HelpOptions;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class HelpCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        HelpOptions options = new HelpOptions();
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        HelpCommand command = new HelpCommand();
        command.execute(options, reader, writer);
        assertEquals(getExpectedHelp(), writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        String result = runApp(new String[]{
            "Help"
        }, null);
        assertEquals(getExpectedHelp(), result);
    }

    private String getExpectedHelp() throws IOException {
        String expectedHelp = "Usage: geom <command> <args>" + NEW_LINE +
            NEW_LINE +
            "'geom list' lists available commands." + NEW_LINE +
            NEW_LINE +
            "Use 'geom <command> --help' for help using a specific command.";
        return expectedHelp;
    }

}
