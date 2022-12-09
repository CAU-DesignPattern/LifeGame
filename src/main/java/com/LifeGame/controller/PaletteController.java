package com.LifeGame.controller;

import com.LifeGame.model.PatternModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observer;

@Component
public class PaletteController {

    private final PatternModel patternModel;

    @Autowired
    public PaletteController(PatternModel patternModel) {
        this.patternModel = patternModel;
    }

    public void addObserver(Observer o) {
        this.patternModel.addObserver(o);
    }

    public void clickAction(int id, int[][] bluePrint) {
        this.patternModel.changePattern(id, bluePrint);
    }
}
