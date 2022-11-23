package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 6)
public class TickAction implements Action {

    @Autowired
    public TickAction(MenuController menuController) {
        menuController.addMenuItem("Go", "Tick (Single Step)", this);
    }

    @Override
    public void action() {

    }
}
