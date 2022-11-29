package com.LifeGame.controller.action;

import org.springframework.stereotype.Component;

@Component
public class ExitAction implements Action {
    @Override
    public void action() {
        System.exit(0);
    }
}
