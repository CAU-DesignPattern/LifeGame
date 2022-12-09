package com.LifeGame.worker;

import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class Worker {

    private Model model;

    private Thread thread;

    private int speed;

    @Autowired
    public Worker(Model model) {
        this.model = model;
    }

    public int getSpeed() {
        return this.speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void startThread() {
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.interrupt();
        }

        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        model.nextState();
                        sleep(speed);
                    }
                } catch (InterruptedException e) {
                }
            }
        });
        this.thread.start();
    }

    public void stopThread() {
        if (this.thread != null && thread.isAlive()) {
            this.thread.interrupt();
        }
    }
}
