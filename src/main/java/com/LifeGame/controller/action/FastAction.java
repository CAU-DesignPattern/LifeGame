package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 9)
public class FastAction extends Action {

    @Autowired
    public FastAction(MenuController menuController) {
        menuController.addMenuItem("Go", "Fast", this);
    }

    @Override
    public void action() {

    }
}
