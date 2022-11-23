package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 5)
public class HaltAction extends Action {

    @Autowired
    public HaltAction(MenuController menuController) {
        menuController.addMenuItem("Go", "Halt", this);
    }

    @Override
    public void action() {

    }
}
