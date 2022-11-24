package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.service.Service;
import com.LifeGame.view.LifePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Order(value = 2)
public class LoadAction extends Action {

    private final Service service;
    private final LifePanel lifePanel;
    private final Model model;

    @Autowired
    public LoadAction(MenuController menuController, Service service, LifePanel lifePanel, Model model) {
        this.service = service;
        this.lifePanel = lifePanel;
        this.model = model;

        menuController.addMenuItem("Grid", "Load", this);
    }

    @Override
    public void action() {
        try {
            int[][] liveCells = this.service.load();

            this.lifePanel.clearCell();
            Rectangle bounds = this.lifePanel.getBounds();
            bounds.x = 0;
            bounds.y = 0;
            for (int[] liveCell : liveCells) {
                this.lifePanel.getOutermostCell().userClicked(new Point(liveCell[0], liveCell[1]), bounds);
            }
            this.lifePanel.repaint();
            this.model.clearMap();
            this.model.toggle(liveCells);
        } catch (NullPointerException e) {
            this.lifePanel.repaint();
            this.model.clearMap();
        }
    }
}
