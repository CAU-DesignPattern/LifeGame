package com.LifeGame.controller;

import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MenuController {

    private final Model model;

    private final HashMap<String, Runnable> actions = new HashMap<>();

    @Autowired
    public MenuController(Model model) {
        this.model = model;
    }

    public void addAction(String name, Runnable action) {
        this.actions.put(name, action);
    }

    public void actionHandler(String name) {
        this.actions.get(name);
    }
}
