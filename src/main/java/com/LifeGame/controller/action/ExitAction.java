package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 4)
public class ExitAction extends Action {

    @Autowired
    public ExitAction(MenuController menuController) {
        menuController.addMenuItem("Grid", "Exit", this);
    }

    @Override
    public void action() {
        System.exit(0);
    }
}
