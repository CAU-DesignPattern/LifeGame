package com.LifeGame.controller;

import com.LifeGame.controller.drawBehavior.DefaultDrawBehavior;
import com.LifeGame.controller.drawBehavior.DrawBehavior;
import com.LifeGame.controller.drawBehavior.PatternDrawBehavior;
import com.LifeGame.model.Model;
import com.LifeGame.model.PatternModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class LifeController implements Observer {

    private final Model model;
    private final PatternModel patternModel;

    private DrawBehavior drawBehavior;
    private final DefaultDrawBehavior defaultDrawBehavior;
    private final PatternDrawBehavior patternDrawBehavior;

    @Autowired
    public LifeController(Model model, PatternModel patternModel, DrawBehavior drawBehavior, DefaultDrawBehavior defaultDrawBehavior, PatternDrawBehavior patternDrawBehavior) {
        this.model = model;
        this.model.setMapSize(64);
        this.patternModel = patternModel;
        patternModel.addObserver(this);

        this.drawBehavior = drawBehavior;
        this.defaultDrawBehavior = defaultDrawBehavior;
        this.patternDrawBehavior = patternDrawBehavior;
    }

    public void setDrawBehavior(DrawBehavior drawBehavior) {
        this.drawBehavior = drawBehavior;
    }

    public void addObserver(Observer o) {
        this.model.addObserver(o);
    }

    public void mouseAction(int x, int y) {
        this.drawBehavior.draw(x, y);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PatternModel) {
            if (((PatternModel) o).getId() == 0) {
                this.setDrawBehavior(this.defaultDrawBehavior);
            } else {
                this.setDrawBehavior(this.patternDrawBehavior);
            }
        }
    }
}
