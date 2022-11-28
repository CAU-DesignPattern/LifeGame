package com.LifeGame.controller.action;

import com.LifeGame.model.Model;
import com.LifeGame.service.InvalidFileLoadedException;
import com.LifeGame.service.MapData;
import com.LifeGame.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class LoadAction implements Action {

    private final Service service;
    private final Model model;

    @Autowired
    public LoadAction(Service service, Model model) {
        this.service = service;
        this.model = model;
    }

    @Override
    public void action() {
        try {
            MapData mapData = this.service.load();
            this.model.clearMap();
            this.model.setMapSize(mapData.getMapSize());
            this.model.setMap(mapData.getMap());
        } catch (InvalidFileLoadedException e) {
            this.model.clearMap();
            // TODO: 실패 시 에러 Alert 표시
        }
    }
}
