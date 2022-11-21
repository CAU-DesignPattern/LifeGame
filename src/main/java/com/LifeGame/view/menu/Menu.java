package com.LifeGame.view.menu;

import com.LifeGame.controller.MenuController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Menu extends JMenu {

    private String name;
    private final HashMap<String, JMenuItem> menuItems = new HashMap<>();

    public Menu(String name) {
        super(name);
        this.name = name;
    }

    public void addMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnnotationConfigApplicationContext().getBean(MenuController.class).actionHandler(e.getActionCommand());
            }
        });
        this.add(menuItem);
        this.menuItems.put(name, menuItem);
    }

    public void removeMenuItem(String name) {
        this.remove(this.menuItems.get(name));
        this.menuItems.remove(name);
    }

    public void changeName(String name, String newName) {
        this.menuItems.get(name).setText(newName);
        this.menuItems.put(newName, this.menuItems.get(name));
        this.menuItems.remove(name);
    }

    public JMenuItem getMenuItem(String name) {
        return this.menuItems.get(name);
    }
}
