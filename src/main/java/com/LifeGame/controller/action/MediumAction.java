package com.LifeGame.controller.action;

import com.LifeGame.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediumAction implements Action {

    private Worker worker;

    @Autowired
    public MediumAction(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void action() {
        this.worker.setSpeed(70);
        this.worker.startThread();
    }
}
