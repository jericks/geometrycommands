package org.geometrycommands;

import org.geometrycommands.AsciiArtCommand.AsciiArtOptions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The AsciiArtCommand Unit Test
 * @author Jared Erickson
 */
public class AsciiArtCommandTest extends BaseTest {

    private static final String NEW_LINE = System.getProperty("line.separator");
    
    @Test
    public void execute() throws Exception {
        AsciiArtCommand cmd = new AsciiArtCommand();
        AsciiArtOptions options = new AsciiArtOptions();
        options.setGeometry("POINT (46.801 53.972)");
        StringReader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        cmd.execute(options, reader, writer);
        String result = writer.toString();
        String expected = " ____    ___   ___  _   _  _____     __ _  _     __        ___    ___   _    ____   _____      ___    _____  ____  __  " + NEW_LINE +
                "|  _ \\  / _ \\ |_ _|| \\ | ||_   _|   / /| || |   / /_      ( _ )  / _ \\ / |  | ___| |___ /     / _ \\  |___  ||___ \\ \\ \\ " + NEW_LINE +
                "| |_) || | | | | | |  \\| |  | |    | | | || |_ | '_ \\     / _ \\ | | | || |  |___ \\   |_ \\    | (_) |    / /   __) | | |" + NEW_LINE +
                "|  __/ | |_| | | | | |\\  |  | |    | | |__   _|| (_) | _ | (_) || |_| || |   ___) | ___) | _  \\__, |   / /   / __/  | |" + NEW_LINE +
                "|_|     \\___/ |___||_| \\_|  |_|     \\_\\   |_|   \\___/ (_) \\___/  \\___/ |_|  |____/ |____/ (_)   /_/   /_/   |_____|/_/ " + NEW_LINE;
        assertEquals(expected, result);
    }

    @Test
    public void run() throws Exception {
        String result = " " + runApp(new String[]{
                "asciiart"
        }, "LINESTRING (0 0, 10 10)");
        String expected = " _      ___  _   _  _____  ____   _____  ____   ___  _   _   ____     __  ___      ___        _   ___     _   ___  __  " + NEW_LINE +
                "| |    |_ _|| \\ | || ____|/ ___| |_   _||  _ \\ |_ _|| \\ | | / ___|   / / / _ \\    / _ \\      / | / _ \\   / | / _ \\ \\ \\ " + NEW_LINE +
                "| |     | | |  \\| ||  _|  \\___ \\   | |  | |_) | | | |  \\| || |  _   | | | | | |  | | | | _   | || | | |  | || | | | | |" + NEW_LINE +
                "| |___  | | | |\\  || |___  ___) |  | |  |  _ <  | | | |\\  || |_| |  | | | |_| |  | |_| |( )  | || |_| |  | || |_| | | |" + NEW_LINE +
                "|_____||___||_| \\_||_____||____/   |_|  |_| \\_\\|___||_| \\_| \\____|   \\_\\ \\___/    \\___/ |/   |_| \\___/   |_| \\___/ /_/";
        assertEquals(expected, result);
    }

}
