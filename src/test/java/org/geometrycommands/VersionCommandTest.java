package org.geometrycommands;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

import org.geometrycommands.VersionCommand.VersionOptions;

import static org.junit.Assert.assertEquals;

public class VersionCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        VersionOptions options = new VersionOptions();
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        VersionCommand command = new VersionCommand();
        command.execute(options, reader, writer);
        assertEquals(getExpectedVersion(), writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        String result = runApp(new String[]{
            "Version"
        }, null);
        assertEquals(getExpectedVersion(), result);
    }

    private String getExpectedVersion() throws IOException {
        String expectedVersion = null;
        try(InputStream inputStream = VersionCommand.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            expectedVersion = properties.getProperty("version");
        }
        return expectedVersion;
    }

}
