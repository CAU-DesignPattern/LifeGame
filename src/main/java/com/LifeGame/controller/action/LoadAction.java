package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
public class LoadAction implements Action {

    @Autowired
    public LoadAction(MenuController menuController) {
        menuController.addMenuItem("Grid", "Load", this);
    }

    @Override
    public void action() {

    }
}
