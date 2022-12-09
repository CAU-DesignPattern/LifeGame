package com.LifeGame.controller;

import com.LifeGame.controller.action.*;
import com.LifeGame.model.Model;
import com.LifeGame.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MenuController {

    private final Service service;
    private final Model model;
    private final ClearAction clearAction;
    private final LoadAction loadAction;
    private final StoreAction storeAction;
    private final ExitAction exitAction;
    private final HaltAction haltAction;
    private final TickAction tickAction;
    private final AgonizingAction agonizingAction;
    private final SlowAction slowAction;
    private final MediumAction mediumAction;
    private final FastAction fastAction;
    private final ShowAction showAction;
    private final HideAction hideAction;
    private final HashMap<String, HashMap<String, Action>> actions = new HashMap<>();

    @Autowired
    public MenuController(Service service, Model model,
                          ClearAction clearAction, LoadAction loadAction, StoreAction storeAction, ExitAction exitAction,
                          HaltAction haltAction, TickAction tickAction, AgonizingAction agonizingAction, SlowAction slowAction, MediumAction mediumAction, FastAction fastAction, ShowAction showAction, HideAction hideAction) {
        this.model = model;
        this.service = service;

        this.clearAction = clearAction;
        this.loadAction = loadAction;
        this.storeAction = storeAction;
        this.exitAction = exitAction;
        this.haltAction = haltAction;
        this.tickAction = tickAction;
        this.agonizingAction = agonizingAction;
        this.slowAction = slowAction;
        this.mediumAction = mediumAction;
        this.fastAction = fastAction;
        this.showAction = showAction;
        this.hideAction = hideAction;

        HashMap<String, Action> gridMenuItems = new HashMap<>();
        gridMenuItems.put("Clear", this.clearAction);
        gridMenuItems.put("Load", this.loadAction);
        gridMenuItems.put("Store", this.storeAction);
        gridMenuItems.put("Exit", this.exitAction);
        this.actions.put("Grid", gridMenuItems);

        HashMap<String, Action> goMenuItems = new HashMap<>();
        goMenuItems.put("Halt", this.haltAction);
        goMenuItems.put("Tick (Single Step)", this.tickAction);
        goMenuItems.put("Agonizing", this.agonizingAction);
        goMenuItems.put("Slow", this.slowAction);
        goMenuItems.put("Medium", this.mediumAction);
        goMenuItems.put("Fast", this.fastAction);
        this.actions.put("Go", goMenuItems);

        HashMap<String, Action> paletteMenuItems = new HashMap<>();
        paletteMenuItems.put("Show", this.showAction);
        paletteMenuItems.put("Hide", this.hideAction);
        this.actions.put("Palette", paletteMenuItems);
    }

    public void action(String menu, String menuItem) {
        this.actions.get(menu).get(menuItem).action();
    }
}
