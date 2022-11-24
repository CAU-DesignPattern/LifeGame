package com.LifeGame.view;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@Component
public class View extends JFrame implements Observer {

    public View() {
        super("The Game of Life. " + "(c)2003 Allen I. Holub <http://www.holub.com>");

        this.initUI();
    }

    private void initUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.setJMenuBar(menuBar);
    }

    public void addPanel(JPanel panel, String layout) {
        this.getContentPane().add(panel, layout);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
