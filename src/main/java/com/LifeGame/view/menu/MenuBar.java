package com.LifeGame.view.menu;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashMap;

@Component
public class MenuBar extends JMenuBar {

    private final HashMap<String, Menu> menus = new HashMap<>();
    private final MenuController menuController;

    @Autowired
    public MenuBar(MenuController menuController) {
        super();
        this.menuController = menuController;
    }

    public void addMenu(String name) {
        Menu menu = new Menu(name, menuController);
        this.add(menu);
        this.menus.put(name, menu);
    }

    public void addMenu(String name, String menuItem) {
        try {
            this.menus.get(name).addMenuItem(menuItem);
        } catch (NullPointerException e) {
            Menu menu = new Menu(name, menuController);
            menu.addMenuItem(menuItem);
            this.add(menu);
            this.menus.put(name, menu);
        }
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
