package com.LifeGame.controller.action;

import com.LifeGame.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgonizingAction implements Action {

    private Worker worker;

    @Autowired
    public AgonizingAction(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void action() {
        this.worker.setSpeed(500);
        this.worker.startThread();
    }
}
