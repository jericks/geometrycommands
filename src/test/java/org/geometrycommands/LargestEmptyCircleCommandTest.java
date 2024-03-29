package org.geometrycommands;

import org.geometrycommands.LargestEmptyCircleCommand.LargestEmptyCircleOptions;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The LargestEmptyCircleCommand UnitTest
 * @author Jared Erickson
 */
public class LargestEmptyCircleCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "MULTIPOINT ((-122.3935317993164 47.57571508225466)," +
                "(-122.38838195800781 47.57444120741259)," +
                "(-122.39061355590819 47.5823155737249)," +
                "(-122.38357543945312 47.58034709317149)," +
                "(-122.38237380981445 47.57756793579513)," +
                "(-122.38666534423827 47.58521026361082)," +
                "(-122.39473342895508 47.581157652951454))";
        LargestEmptyCircleOptions options = new LargestEmptyCircleOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        LargestEmptyCircleCommand command = new LargestEmptyCircleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((-122.385094264244 47.57953640127443, -122.38516073478301 47.57886151456605, " +
                "-122.38535759197453 47.578212563375395, -122.38567727070705 47.577614486532774, " +
                "-122.3861074859062 47.57709026779586, -122.38663170464311 47.57666005259672, " +
                "-122.38722978148573 47.576340373864184, -122.38787873267638 47.57614351667268, " +
                "-122.38855361938477 47.57607704613366, -122.38922850609315 47.57614351667268, " +
                "-122.3898774572838 47.576340373864184, -122.39047553412642 47.57666005259672, " +
                "-122.39099975286334 47.57709026779586, -122.39142996806248 47.577614486532774, " +
                "-122.391749646795 47.578212563375395, -122.39194650398652 47.57886151456605, " +
                "-122.39201297452553 47.57953640127443, -122.39194650398652 47.58021128798281, " +
                "-122.391749646795 47.58086023917347, -122.39142996806248 47.58145831601609, " +
                "-122.39099975286334 47.581982534753, -122.39047553412642 47.58241274995214, " +
                "-122.3898774572838 47.58273242868468, -122.38922850609315 47.58292928587618, " +
                "-122.38855361938477 47.5829957564152, -122.38787873267638 47.58292928587618, " +
                "-122.38722978148573 47.58273242868468, -122.38663170464311 47.58241274995214, " +
                "-122.3861074859062 47.581982534753, -122.38567727070705 47.58145831601609, " +
                "-122.38535759197453 47.58086023917347, -122.38516073478301 47.58021128798281, " +
                "-122.385094264244 47.57953640127443))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "largestemptycircle",
                "-g", "MULTIPOINT ((-122.3935317993164 47.57571508225466)," +
                "(-122.38838195800781 47.57444120741259)," +
                "(-122.39061355590819 47.5823155737249)," +
                "(-122.38357543945312 47.58034709317149)," +
                "(-122.38237380981445 47.57756793579513)," +
                "(-122.38666534423827 47.58521026361082)," +
                "(-122.39473342895508 47.581157652951454))",
                "-t", "1.1"
        }, null);
        assertEquals("POLYGON ((-122.385094264244 47.57953640127443, -122.38516073478301 47.57886151456605, " +
                "-122.38535759197453 47.578212563375395, -122.38567727070705 47.577614486532774, " +
                "-122.3861074859062 47.57709026779586, -122.38663170464311 47.57666005259672, " +
                "-122.38722978148573 47.576340373864184, -122.38787873267638 47.57614351667268, " +
                "-122.38855361938477 47.57607704613366, -122.38922850609315 47.57614351667268, " +
                "-122.3898774572838 47.576340373864184, -122.39047553412642 47.57666005259672, " +
                "-122.39099975286334 47.57709026779586, -122.39142996806248 47.577614486532774, " +
                "-122.391749646795 47.578212563375395, -122.39194650398652 47.57886151456605, " +
                "-122.39201297452553 47.57953640127443, -122.39194650398652 47.58021128798281, " +
                "-122.391749646795 47.58086023917347, -122.39142996806248 47.58145831601609, " +
                "-122.39099975286334 47.581982534753, -122.39047553412642 47.58241274995214, " +
                "-122.3898774572838 47.58273242868468, -122.38922850609315 47.58292928587618, " +
                "-122.38855361938477 47.5829957564152, -122.38787873267638 47.58292928587618, " +
                "-122.38722978148573 47.58273242868468, -122.38663170464311 47.58241274995214, " +
                "-122.3861074859062 47.581982534753, -122.38567727070705 47.58145831601609, " +
                "-122.38535759197453 47.58086023917347, -122.38516073478301 47.58021128798281, " +
                "-122.385094264244 47.57953640127443))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "largestemptycircle",
                "-t", "0.1"
        }, "MULTIPOINT ((-122.3935317993164 47.57571508225466)," +
                "(-122.38838195800781 47.57444120741259)," +
                "(-122.39061355590819 47.5823155737249)," +
                "(-122.38357543945312 47.58034709317149)," +
                "(-122.38237380981445 47.57756793579513)," +
                "(-122.38666534423827 47.58521026361082)," +
                "(-122.39473342895508 47.581157652951454))");
        assertEquals("POLYGON ((-122.385094264244 47.57953640127443, -122.38516073478301 47.57886151456605, " +
                "-122.38535759197453 47.578212563375395, -122.38567727070705 47.577614486532774, " +
                "-122.3861074859062 47.57709026779586, -122.38663170464311 47.57666005259672, " +
                "-122.38722978148573 47.576340373864184, -122.38787873267638 47.57614351667268, " +
                "-122.38855361938477 47.57607704613366, -122.38922850609315 47.57614351667268, " +
                "-122.3898774572838 47.576340373864184, -122.39047553412642 47.57666005259672, " +
                "-122.39099975286334 47.57709026779586, -122.39142996806248 47.577614486532774, " +
                "-122.391749646795 47.578212563375395, -122.39194650398652 47.57886151456605, " +
                "-122.39201297452553 47.57953640127443, -122.39194650398652 47.58021128798281, " +
                "-122.391749646795 47.58086023917347, -122.39142996806248 47.58145831601609, " +
                "-122.39099975286334 47.581982534753, -122.39047553412642 47.58241274995214, " +
                "-122.3898774572838 47.58273242868468, -122.38922850609315 47.58292928587618, " +
                "-122.38855361938477 47.5829957564152, -122.38787873267638 47.58292928587618, " +
                "-122.38722978148573 47.58273242868468, -122.38663170464311 47.58241274995214, " +
                "-122.3861074859062 47.581982534753, -122.38567727070705 47.58145831601609, " +
                "-122.38535759197453 47.58086023917347, -122.38516073478301 47.58021128798281, " +
                "-122.385094264244 47.57953640127443))", result);
    }
}
