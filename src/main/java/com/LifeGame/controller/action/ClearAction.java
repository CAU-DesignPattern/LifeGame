package com.LifeGame.controller.action;

import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearAction implements Action {

    private final Model model;

    @Autowired
    public ClearAction(Model model) {
        this.model = model;
    }

    @Override
    public void action() {
        this.model.clearMap();
    }
}
