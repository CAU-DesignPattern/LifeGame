package com.LifeGame;

import com.LifeGame.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SwingCommandLineRunner implements CommandLineRunner {
    private final View view;

    @Autowired
    public SwingCommandLineRunner(View view) {
        this.view = view;
    }

    @Override
    public void run(String... args) {
        EventQueue.invokeLater(() -> this.view.setVisible(true));
    }
}
