package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 8)
public class MediumAction implements Action {

    @Autowired
    public MediumAction(MenuController menuController) {
        menuController.addMenuItem("Go", "Medium", this);
    }

    @Override
    public void action() {

    }
}
