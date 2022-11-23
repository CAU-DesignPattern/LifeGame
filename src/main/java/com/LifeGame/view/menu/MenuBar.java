package com.LifeGame.view.menu;

import com.LifeGame.controller.action.Action;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class MenuBar extends JMenuBar {

    private final HashMap<String, Menu> menus = new HashMap<>();

    public MenuBar() {
        super();
    }

    public void addMenu(String name) {
        Menu menu = new Menu(name);
        this.add(menu);
        this.menus.put(name, menu);
    }

    public void addMenu(String name, String menuItem, Action action) {
        try {
            this.menus.get(name).addMenuItem(menuItem, action);
        } catch (NullPointerException e) {
            Menu menu = new Menu(name);
            menu.addMenuItem(menuItem, action);
            this.add(menu);
            this.menus.put(name, menu);
        }
    }

    public void addMenu(String name, HashMap<String, Action> menuItems) {
        Menu menu = new Menu(name);
        for (String menuItem : menuItems.keySet()) {
            menu.addMenuItem(menuItem, menuItems.get(menuItem));
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
