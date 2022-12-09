package com.LifeGame.controller.drawBehavior;

import com.LifeGame.model.Model;
import com.LifeGame.model.PatternModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatternDrawBehavior implements DrawBehavior {

    private final Model model;
    private final PatternModel patternModel;

    @Autowired
    public PatternDrawBehavior(Model model, PatternModel patternModel) {
        this.model = model;
        this.patternModel = patternModel;
    }

    @Override
    public void draw(int x, int y) {
        this.model.draw(this.patternModel.getBluePrint(), x, y);
    }
}
