package com.LifeGame.controller.action;

import com.LifeGame.model.Model;
import com.LifeGame.service.MapData;
import com.LifeGame.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreAction implements Action {

    private final Service service;
    private final Model model;

    @Autowired
    public StoreAction(Service service, Model model) {
        this.service = service;
        this.model = model;
    }

    @Override
    public void action() {
        this.service.store(new MapData(this.model.getMapSize(), this.model.getMap()));
    }
}
