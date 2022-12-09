package com.LifeGame.controller.action;

import com.LifeGame.view.PaletteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HideAction implements Action {

    private final PaletteView paletteView;

    @Autowired
    public HideAction(PaletteView paletteView) {
        this.paletteView = paletteView;
    }

    @Override
    public void action() {
        this.paletteView.setVisible(false);
    }
}
