package com.LifeGame.controller;

import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Observer;

@Component
public class LifeController {

    private final Model model;

    @Autowired
    public LifeController(Model model) {
        this.model = model;

        this.model.setMapSize(64);
    }

    public void addObserver(Observer o) {
        this.model.addObserver(o);
    }

    public void mouseAction(int x, int y) {
        this.model.toggle(x, y);
    }
}
