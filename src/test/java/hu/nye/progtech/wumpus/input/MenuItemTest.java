package hu.nye.progtech.wumpus.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {
    private final String LABEL = "Label";
    private final String HOTKEY = "l";

    @Test
    public void testMenuItemLabel() {
        // given
        MenuItem underTest = new MenuItem(LABEL, HOTKEY);
        // when
        String result = underTest.getLabel();
        // then
        assertEquals(LABEL, result);
    }

    @Test
    public void testMenuItemHotkey() {
        // given
        MenuItem underTest = new MenuItem(LABEL, HOTKEY);
        // when
        String result = underTest.getHotKey();
        // then
        assertEquals(HOTKEY, result);
    }

}