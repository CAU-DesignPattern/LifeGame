package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ClearAction implements Action {

    @Autowired
    public ClearAction(MenuController menuController) {
        menuController.addMenuItem("Grid", "Clear", this);
    }

    @Override
    public void action() {

    }
}
