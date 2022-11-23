package com.LifeGame.controller;

import com.LifeGame.controller.action.Action;
import com.LifeGame.view.LifePanel;
import com.LifeGame.view.View;
import com.LifeGame.view.menu.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MenuController {

    private final View view;
    private final MenuBar menuBar;
    private final LifePanel lifePanel;
    private boolean isViewInitialized;

    @Autowired
    public MenuController(View view, MenuBar menuBar, LifePanel lifePanel) {
        this.view = view;
        this.menuBar = menuBar;
        this.lifePanel = lifePanel;
        this.isViewInitialized = false;
    }

    public void addMenuItem(String menu, String menuItem, Action action) {
        this.menuBar.addMenu(menu, menuItem, action);

        if (!this.isViewInitialized) {
            this.isViewInitialized = true;
            this.view.setJMenuBar((JMenuBar) this.menuBar);
            this.view.getContentPane().add(this.lifePanel, BorderLayout.CENTER); //{=life.java.install}
            this.view.pack();
        }
    }
}
