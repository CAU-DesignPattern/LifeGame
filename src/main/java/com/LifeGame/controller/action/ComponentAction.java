package com.LifeGame.controller.action;

import com.LifeGame.controller.LifeController;
import com.LifeGame.view.LifePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ComponentAction extends Action {

    private final LifePanel lifePanel;

    @Autowired
    public ComponentAction(LifeController lifeController, LifePanel lifePanel) {
        this.lifePanel = lifePanel;
        lifeController.addComponentAction(this);
    }

    @Override
    public void action() {
        Rectangle bounds = this.lifePanel.getBounds();
        bounds.height /= this.lifePanel.getWidthInCells();
        bounds.height *= this.lifePanel.getWidthInCells();
        bounds.width = bounds.height;
        this.lifePanel.setBounds(bounds);
    }
}
