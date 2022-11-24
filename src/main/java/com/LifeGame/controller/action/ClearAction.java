package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.view.LifePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ClearAction extends Action {

    private final LifePanel lifePanel;
    private final Model model;

    @Autowired
    public ClearAction(MenuController menuController, LifePanel lifePanel, Model model) {
        this.lifePanel = lifePanel;
        this.model = model;

        menuController.addMenuItem("Grid", "Clear", this);
    }

    @Override
    public void action() {
        this.lifePanel.clearCell();
        this.lifePanel.repaint();
        this.model.clearMap();
    }
}
