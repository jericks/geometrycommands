package org.geometrycommands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsTest {

    @Test
    public void options() {
        Options options = new Options();
        options.setName("list");
        options.setHelp(false);
        options.setWebHelp(false);
        assertEquals("list", options.getName());
        assertFalse(options.isHelp());
        assertFalse(options.isWebHelp());
    }

}
