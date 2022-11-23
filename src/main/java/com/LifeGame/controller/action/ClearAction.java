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
    public ClearAction(LifePanel lifePanel, Model model, MenuController menuController) {
        this.lifePanel = lifePanel;
        this.model = model;

        menuController.addMenuItem("Grid", "Clear", this);
    }

    @Override
    public void action() {
        this.lifePanel.getOutermostCell().clear();
        this.lifePanel.repaint();
        this.model.clearMap();
    }
}
