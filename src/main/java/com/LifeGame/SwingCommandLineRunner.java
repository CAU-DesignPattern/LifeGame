package com.LifeGame;

import com.LifeGame.view.ExampleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SwingCommandLineRunner implements CommandLineRunner {
    private final ExampleView exampleView;

    @Autowired
    public SwingCommandLineRunner(ExampleView exampleView) {
        this.exampleView = exampleView;
    }

    @Override
    public void run(String... args) throws Exception {
        EventQueue.invokeLater(() -> this.exampleView.setVisible(true));
    }
}
