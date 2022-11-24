package com.LifeGame.controller;

import com.LifeGame.controller.action.Action;
import com.LifeGame.view.LifePanel;
import com.LifeGame.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class LifeController {

    private final View view;
    private final LifePanel lifePanel;

    @Autowired
    public LifeController(View view, LifePanel lifePanel) {
        this.view = view;
        this.lifePanel = lifePanel;

        this.view.addPanel(this.lifePanel, BorderLayout.CENTER);
    }

    public void addMouseAction(Action action) {
        this.lifePanel.setMouseListener(action);
    }

    public void addComponentAction(Action action) {
        this.lifePanel.setComponentListener(action);
    }
}
