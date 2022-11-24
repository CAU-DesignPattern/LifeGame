package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 3)
public class StoreAction extends Action {

    private final Service service;
    private final Model model;

    @Autowired
    public StoreAction(MenuController menuController, Service service, Model model) {
        this.service = service;
        this.model = model;

        menuController.addMenuItem("Grid", "Store", this);
    }

    @Override
    public void action() {
        this.service.store(this.model.getLiveCells());
    }
}
