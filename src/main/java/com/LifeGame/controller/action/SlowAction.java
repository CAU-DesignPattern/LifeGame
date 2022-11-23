package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 7)
public class SlowAction extends Action {

    @Autowired
    public SlowAction(MenuController menuController) {
        menuController.addMenuItem("Go", "Slow", this);
    }

    @Override
    public void action() {

    }
}
