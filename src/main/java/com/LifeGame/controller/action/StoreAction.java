package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 3)
public class StoreAction implements Action {

    @Autowired
    public StoreAction(MenuController menuController) {
        menuController.addMenuItem("Grid", "Store", this);
    }

    @Override
    public void action() {

    }
}
