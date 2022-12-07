package com.LifeGame.controller.drawBehavior;

import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DefaultDrawBehavior implements DrawBehavior {

    private final Model model;

    @Autowired
    public DefaultDrawBehavior(Model model) {
        this.model = model;
    }

    @Override
    public void draw(int x, int y) {
        this.model.toggle(x, y);
    }
}
