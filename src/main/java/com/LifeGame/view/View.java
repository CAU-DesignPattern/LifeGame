package com.LifeGame.view;

import com.LifeGame.controller.MenuController;
import com.LifeGame.view.menu.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@Component
public class View extends JFrame implements Observer {

    private final LifePanel lifePanel;
    private final MenuBar menuBar;

    @Autowired
    public View(LifePanel lifePanel, MenuBar menuBar) {
        super("The Game of Life. " + "(c)2003 Allen I. Holub <http://www.holub.com>");

        this.lifePanel = lifePanel;
        this.menuBar = menuBar;

        this.initMenu();
        this.initUI();
    }

    private void initMenu(){
        this.menuBar.addMenu(new AnnotationConfigApplicationContext().getBean(MenuController.class).getMenus());

        //this.menuBar.addMenu("Grid", new String[]{"Clear", "Load", "Store", "Exit"});
        //this.menuBar.addMenu("Go", new String[]{"Halt", "Tick (Single Step)", "Agonizing", "Slow", "Medium", "Fast"});
    }

    private void initUI() {
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
