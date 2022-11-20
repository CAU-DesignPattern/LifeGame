package com.LifeGame.view;

import com.LifeGame.view.menu.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@Component
public class View extends JFrame implements Observer {

    private final LifePanel lifePanel;
    private final MenuBar menuBar;

    private final ArrayList<JMenuItem> gridMenuBar = new ArrayList<>();
    private final ArrayList<JMenuItem> goMenuBar = new ArrayList<>();

    @Autowired
    public View(LifePanel lifePanel, MenuBar menuBar) {
        super("The Game of Life. " + "(c)2003 Allen I. Holub <http://www.holub.com>");

        this.lifePanel = lifePanel;
        this.menuBar = menuBar;

        this.menuBar.addMenu("Grid", new String[]{"Clear", "Load", "Store", "Exit"});
        this.menuBar.addMenu("Go", new String[]{"Halt", "Tick (Single Step)", "Agonizing", "Slow", "Medium", "Fast"});

        this.initUI();
    }

    private void initUI() {
        // Must establish th21e MenuSite very early in case
        // a subcomponent puts menus on it.

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.lifePanel, BorderLayout.CENTER); //{=life.java.install}
        this.setJMenuBar((JMenuBar) this.menuBar);

        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
