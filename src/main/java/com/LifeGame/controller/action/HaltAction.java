package com.LifeGame.controller.action;

import com.LifeGame.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HaltAction implements Action {

    private Worker worker;

    @Autowired
    public HaltAction(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void action() {
        this.worker.stopThread();
    }
}
