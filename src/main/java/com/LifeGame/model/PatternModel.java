package com.LifeGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
@Setter @Getter
public class PatternModel extends Observable {

    private int id = 0;
    private int[][] bluePrint;

    public void patternChanged() {
        setChanged();
        notifyObservers();
    }

    public void changePattern(int id, int[][] bluePrint) {
        if (this.id == id) {
            this.id = 0;
        } else {
            this.id = id;
            this.bluePrint = bluePrint;
        }

        this.patternChanged();
    }
}
