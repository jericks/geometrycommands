package org.geometrycommands;

import org.locationtech.jts.geom.Geometry;
import org.geometrycommands.AsciiArtCommand.AsciiArtOptions;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Get the Geometry as WKT ASCII Art
 * @author Jared Erickson
 */
public class AsciiArtCommand extends GeometryCommand<AsciiArtOptions> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "asciiart";
    }

    /**
     * Get the description of what the Command does
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the Geometry as WKT ASCII Art";
    }


    /**
     * Get a new GeometryOptions
     *
     * @return A new GeometryOptions
     */
    @Override
    public AsciiArtOptions getOptions() {
        return new AsciiArtOptions();
    }

    /**
     * Process the input Geometry
     * @param geometry The input Geometry
     * @param options  The GeometryOptions
     * @param reader   The java.io.Reader
     * @param writer   The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    protected void processGeometry(Geometry geometry, AsciiArtOptions options, Reader reader, Writer writer) throws Exception {
        String wkt = geometry.toText();
        List<String[]> letters = new ArrayList<String[]>();
        char[] chars = wkt.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            String[] characters = art.get(String.valueOf(c));
            if (characters != null) {
                letters.add(characters);
            }
        }
        String NEW_LINE = System.getProperty("line.separator");
        for (int i = 0; i < 5; i++) {
            for (String[] lines : letters) {
                writer.write(lines[i]);
            }
            writer.write(NEW_LINE);
        }
    }

    /**
     * The AsciiArtOptions
     */
    public static class AsciiArtOptions extends GeometryOptions {
    }

    private static final String[] zero = new String[]{
            "  ___  ",
            " / _ \\ ",
            "| | | |",
            "| |_| |",
            " \\___/ ",
    };

    private static final String[] one = new String[]{
            " _ ",
            "/ |",
            "| |",
            "| |",
            "|_|"
    };
    private static final String[] two = new String[]{
            " ____  ",
            "|___ \\ ",
            "  __) |",
            " / __/ ",
            "|_____|"
    };

    private static final String[] three = new String[]{
            " _____ ",
            "|___ / ",
            "  |_ \\ ",
            " ___) |",
            "|____/ "
    };

    private static final String[] four = new String[]{
            " _  _   ",
            "| || |  ",
            "| || |_ ",
            "|__   _|",
            "   |_|  "
    };

    private static final String[] five = new String[]{
            " ____  ",
            "| ___| ",
            "|___ \\ ",
            " ___) |",
            "|____/ "
    };

    private static final String[] six = new String[]{
            "  __   ",
            " / /_  ",
            "| '_ \\ ",
            "| (_) |",
            " \\___/ "
    };

    private static final String[] seven = new String[]{
            "  _____ ",
            " |___  |",
            "    / / ",
            "   / /  ",
            "  /_/   "
    };

    private static final String[] eight = new String[]{
            "  ___  ",
            " ( _ ) ",
            " / _ \\ ",
            "| (_) |",
            " \\___/ "
    };

    private static final String[] nine = new String[]{
            "  ___  ",
            " / _ \\ ",
            "| (_) |",
            " \\__, |",
            "   /_/ "
    };

    private static final String[] p = new String[]{
            " ____  ",
            "|  _ \\ ",
            "| |_) |",
            "|  __/ ",
            "|_|    ",
    };

    private static final String[] o = new String[]{
            "  ___  ",
            " / _ \\ ",
            "| | | |",
            "| |_| |",
            " \\___/ "
    };


    private static final String[] i = new String[]{
            " ___ ",
            "|_ _|",
            " | | ",
            " | | ",
            "|___|"
    };

    private static final String[] n = new String[]{
            " _   _ ",
            "| \\ | |",
            "|  \\| |",
            "| |\\  |",
            "|_| \\_|"

    };

    private static final String[] t = new String[]{
            " _____ ",
            "|_   _|",
            "  | |  ",
            "  | |  ",
            "  |_|  "
    };

    private static final String[] l = new String[]{
            " _     ",
            "| |    ",
            "| |    ",
            "| |___ ",
            "|_____|"
    };

    private static final String[] e = new String[]{
            " _____ ",
            "| ____|",
            "|  _|  ",
            "| |___ ",
            "|_____|"
    };

    private static final String[] g = new String[]{
            "  ____ ",
            " / ___|",
            "| |  _ ",
            "| |_| |",
            " \\____|"
    };

    private static final String[] r = new String[]{
            " ____  ",
            "|  _ \\ ",
            "| |_) |",
            "|  _ < ",
            "|_| \\_\\"
    };

    private static final String[] s = new String[]{
            " ____  ",
            "/ ___| ",
            "\\___ \\ ",
            " ___) |",
            "|____/ ",
    };

    private static String[] y = new String[]{
            "__   __",
            "\\ \\ / /",
            " \\ V / ",
            "  | |  ",
            "  |_|  ",
    };

    private static final String[] m = new String[]{
            " __  __ ",
            "|  \\/  |",
            "| |\\/| |",
            "| |  | |",
            "|_|  |_|",
    };

    private static final String[] u = new String[]{
            " _   _ ",
            "| | | |",
            "| | | |",
            "| |_| |",
            " \\___/ "
    };

    private static final String[] c = new String[]{
            "  ____ ",
            " / ___|",
            "| |    ",
            "| |___ ",
            " \\____|"
    };

    private static final String[] space = new String[]{
            "  ",
            "  ",
            "  ",
            "  ",
            "  "
    };

    private static final String[] leftparan = new String[]{
            "  __",
            " / /",
            "| | ",
            "| | ",
            " \\_\\"
    };

    private static final String[] rightparan = new String[]{
            "__  ",
            "\\ \\ ",
            " | |",
            " | |",
            "/_/ "
    };

    private static final String[] comma = new String[]{
            "   ",
            "   ",
            " _ ",
            "( )",
            "|/ "
    };

    private static final String[] period = new String[]{
            "   ",
            "   ",
            "   ",
            " _ ",
            "(_)"
    };

    private static final Map<String, String[]> art = new HashMap<String, String[]>();

    static {
        art.put("0", zero);
        art.put("1", one);
        art.put("2", two);
        art.put("3", three);
        art.put("4", four);
        art.put("5", five);
        art.put("6", six);
        art.put("7", seven);
        art.put("8", eight);
        art.put("9", nine);
        art.put(",", comma);
        art.put(".", period);
        art.put(" ", space);
        art.put("P", p);
        art.put("O", o);
        art.put("I", i);
        art.put("N", n);
        art.put("T", t);
        art.put("L", l);
        art.put("E", e);
        art.put("S", s);
        art.put("R", r);
        art.put("G", g);
        art.put("Y", y);
        art.put("M", m);
        art.put("U", u);
        art.put("C", c);
        art.put("(", leftparan);
        art.put(")", rightparan);
    }
}
