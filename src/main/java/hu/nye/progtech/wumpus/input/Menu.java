package hu.nye.progtech.wumpus.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu for game.
 */
public class Menu {
    List<MenuItem> menuList = new ArrayList<>();
    private final int width;

    public Menu(int width) {
        this.width = width;
    }

    public void addItem(MenuItem menuItem) {
        this.menuList.add(menuItem);
    }

    public List<MenuItem> getMenuList() {
        return menuList;
    }

    public int getWidth() {
        return width;
    }
}
