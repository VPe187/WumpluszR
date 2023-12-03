package hu.nye.progtech.wumpus.input;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<MenuItem> menuList = new ArrayList<>();

    public Menu() {

    }

    public void addItem(MenuItem menuItem) {
        this.menuList.add(menuItem);
    }

    public List<MenuItem> getMenuList() {
        return menuList;
    }
}
