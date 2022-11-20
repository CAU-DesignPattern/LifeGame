package com.LifeGame.view.menu;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashMap;

@Component
public class MenuBar extends JMenuBar {

    private HashMap<String, Menu> menus = new HashMap<>();

    public MenuBar() {
        super();
    }

    public void addMenu(String name) {
        Menu menu = new Menu(name);
        this.add(menu);
        this.menus.put(name, menu);
    }

    public void addMenu(String name, String[] menuItems) {
        Menu menu = new Menu(name);
        for (String menuItem : menuItems) {
            menu.addMenuItem(menuItem);
        }
        this.add(menu);
        this.menus.put(name, menu);
    }

    public void removeMenu(String name) {
        this.remove(this.menus.get(name));
        this.menus.remove(name);
    }

    public void changeName(String name, String newName) {
        this.menus.get(name).setText(newName);
        this.menus.put(newName, this.menus.get(name));
        this.menus.remove(name);
    }

    public Menu getMenu(String name) {
        return this.menus.get(name);
    }
}
