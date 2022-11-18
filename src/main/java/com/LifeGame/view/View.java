package com.LifeGame.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

@Component
public class View extends JFrame implements Observer {

    private LifePanel lifePanel;

    @Autowired
    public View(LifePanel lifePanel) {
        super("The Game of Life. " + "(c)2003 Allen I. Holub <http://www.holub.com>");

        this.lifePanel = lifePanel;
        initUI();
    }

    private void initUI() {
        // Must establish th21e MenuSite very early in case
        // a subcomponent puts menus on it.

        MenuView.establish(this);        //{=life.java.establish}

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(this.lifePanel, BorderLayout.CENTER); //{=life.java.install}

        initMenu();

        pack();
        setVisible(true);
    }

    private void initMenu() {
        // Grid Menu
        MenuView.addLine(this.lifePanel, "Grid", "Clear", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //outermostCell.clear();
                repaint();
            }
        });

        MenuView.addLine(this.lifePanel, "Grid", "Load", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //doLoad();
            }
        });

        MenuView.addLine(this.lifePanel, "Grid", "Store", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //doStore();
            }
        });

        MenuView.addLine(this.lifePanel, "Grid", "Exit", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Go Menu
        MenuView.addLine(this.lifePanel, "Go", "Halt", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        MenuView.addLine(this.lifePanel, "Go", "Tick (Single Step)", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        MenuView.addLine(this.lifePanel, "Go", "Slow", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        MenuView.addLine(this.lifePanel, "Go", "Medium", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        MenuView.addLine(this.lifePanel, "Go", "Fast", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
