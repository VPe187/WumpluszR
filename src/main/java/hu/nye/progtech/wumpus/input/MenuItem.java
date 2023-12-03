package hu.nye.progtech.wumpus.input;

/**
 * Menu element with label and hotkey as String
 */
public class MenuItem {
    private String label;
    private String hotKey;

    public MenuItem(String label, String hotkey) {
        this.label = label;
        this.hotKey = hotkey;
    }

    public String getLabel() {
        return label;
    }

    public String getHotKey() {
        return hotKey;
    }
}
