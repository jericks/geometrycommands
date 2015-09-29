package org.geometrycommands;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsTest {

    @Test
    public void options() {
        Options options = new Options();
        options.setName("list");
        options.setHelp(false);
        assertEquals("list", options.getName());
        assertFalse(options.isHelp());
    }

}
