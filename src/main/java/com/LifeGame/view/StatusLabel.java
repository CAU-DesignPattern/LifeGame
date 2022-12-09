package com.LifeGame.view;

import com.LifeGame.controller.LifeController;
import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@Component
public class StatusLabel extends JLabel implements Observer {

    private final LifeController lifeController;

    private int generation = 0;
    private int liveCells = 0;

    @Autowired
    public StatusLabel(LifeController lifeController) {
        this.lifeController = lifeController;
        this.lifeController.addObserver(this);

        this.setText("  generation: " + this.generation + " | live cells: " + this.liveCells);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            this.generation = ((Model) o).getGeneration();
            this.liveCells = ((Model) o).getLivecell();
            this.setText("  generation: " + this.generation + " | live cells: " + this.liveCells);
        }
    }
}
