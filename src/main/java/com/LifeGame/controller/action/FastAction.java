package com.LifeGame.controller.action;

import com.LifeGame.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FastAction implements Action {

    private Worker worker;

    @Autowired
    public FastAction(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void action() {
        this.worker.setSpeed(30);
        this.worker.startThread();
    }
}
