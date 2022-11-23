package com.LifeGame.controller.action;

import com.LifeGame.controller.LifeController;
import com.LifeGame.view.LifePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class LifeAction extends Action {

    private final LifePanel lifePanel;

    @Autowired
    public LifeAction(LifeController lifeController, LifePanel lifePanel) {
        this.lifePanel = lifePanel;

        lifeController.addMouseAction(this);
    }

    @Override
    public void action(Point point) {
        Rectangle bounds = this.lifePanel.getBounds();
        bounds.x = 0;
        bounds.y = 0;
        this.lifePanel.getOutermostCell().userClicked(point, bounds);
        this.lifePanel.repaint();
    }
}
