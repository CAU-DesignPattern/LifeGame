package com.LifeGame.view;

import com.LifeGame.view.menu.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class View extends JFrame {

    private final LifePanel lifePanel;
    private final MenuBar menuBar;
    private final StatusLabel statusLabel;
    private final PaletteView paletteView;

    @Autowired
    public View(LifePanel lifePanel, MenuBar menuBar, StatusLabel statusLabel, PaletteView paletteView) {
        super("The Game of Life. " + "(c)2003 Allen I. Holub <http://www.holub.com>");

        this.lifePanel = lifePanel;
        this.menuBar = menuBar;
        this.statusLabel = statusLabel;
        this.paletteView = paletteView;

        this.initMenu();
        this.initUI();
    }

    private void initMenu() {
        this.menuBar.addMenu("Grid", "Clear");
        this.menuBar.addMenu("Grid", "Load");
        this.menuBar.addMenu("Grid", "Store");
        this.menuBar.addMenu("Grid", "Exit");

        this.menuBar.addMenu("Go", "Halt");
        this.menuBar.addMenu("Go", "Tick (Single Step)");
        this.menuBar.addMenu("Go", "Agonizing");
        this.menuBar.addMenu("Go", "Slow");
        this.menuBar.addMenu("Go", "Medium");
        this.menuBar.addMenu("Go", "Fast");

        this.menuBar.addMenu("Palette", "Show");
        this.menuBar.addMenu("Palette", "Hide");
    }

    private void initUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setJMenuBar(this.menuBar);
        this.getContentPane().add(this.lifePanel, BorderLayout.CENTER);
        this.getContentPane().add(this.statusLabel, BorderLayout.SOUTH);

        Point point = this.getLocation();
        Dimension dimension = this.getPreferredSize();
        point.x += dimension.width;
        this.paletteView.setLocation(point);
        dimension.width = 100;
        dimension.height = 100 * 6;
        this.paletteView.setPreferredSize(dimension);
    }
}
