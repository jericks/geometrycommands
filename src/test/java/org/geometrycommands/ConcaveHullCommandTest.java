package org.geometrycommands;

import org.geometrycommands.ConcaveHullCommand.ConcaveHullOptions;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The ConcaveHullCommand UnitTest
 * @author Jared Erickson
 */
public class ConcaveHullCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTIPOINT (" +
            "(-122.38657951354979 47.58451555263637), "+
            "(-122.38649368286131 47.5772205307505), "+
            "(-122.39078521728516 47.58167872046887), "+
            "(-122.38177299499512 47.5823155737249), "+
            "(-122.3876953125 47.5828366297174), "+
            "(-122.38494873046875 47.58301031389572), "+
            "(-122.3876953125 47.58121554959838), "+
            "(-122.38486289978027 47.5812734461813)"+
        ")";
        ConcaveHullOptions options = new ConcaveHullOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        ConcaveHullCommand command = new ConcaveHullCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((-122.3876953125 47.58121554959838, -122.39078521728516 47.58167872046887, " +
                "-122.3876953125 47.5828366297174, -122.38657951354979 47.58451555263637, " +
                "-122.38494873046875 47.58301031389572, -122.38177299499512 47.5823155737249, " +
                "-122.38486289978027 47.5812734461813, -122.38649368286131 47.5772205307505, " +
                "-122.3876953125 47.58121554959838))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "concavehull",
                "-g", "MULTIPOINT (" +
                    "(-122.38657951354979 47.58451555263637), "+
                    "(-122.38649368286131 47.5772205307505), "+
                    "(-122.39078521728516 47.58167872046887), "+
                    "(-122.38177299499512 47.5823155737249), "+
                    "(-122.3876953125 47.5828366297174), "+
                    "(-122.38494873046875 47.58301031389572), "+
                    "(-122.3876953125 47.58121554959838), "+
                    "(-122.38486289978027 47.5812734461813)"+
                    ")"
        }, null);
        assertEquals("POLYGON ((-122.3876953125 47.58121554959838, -122.39078521728516 47.58167872046887, " +
                "-122.3876953125 47.5828366297174, -122.38657951354979 47.58451555263637, " +
                "-122.38494873046875 47.58301031389572, -122.38177299499512 47.5823155737249, " +
                "-122.38486289978027 47.5812734461813, -122.38649368286131 47.5772205307505, " +
                "-122.3876953125 47.58121554959838))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "concavehull"
        }, "MULTIPOINT (" +
                "(-122.38657951354979 47.58451555263637), "+
                "(-122.38649368286131 47.5772205307505), "+
                "(-122.39078521728516 47.58167872046887), "+
                "(-122.38177299499512 47.5823155737249), "+
                "(-122.3876953125 47.5828366297174), "+
                "(-122.38494873046875 47.58301031389572), "+
                "(-122.3876953125 47.58121554959838), "+
                "(-122.38486289978027 47.5812734461813)"+
                ")");
        assertEquals("POLYGON ((-122.3876953125 47.58121554959838, -122.39078521728516 47.58167872046887, " +
                "-122.3876953125 47.5828366297174, -122.38657951354979 47.58451555263637, " +
                "-122.38494873046875 47.58301031389572, -122.38177299499512 47.5823155737249, " +
                "-122.38486289978027 47.5812734461813, -122.38649368286131 47.5772205307505, " +
                "-122.3876953125 47.58121554959838))", result);
    }
}
