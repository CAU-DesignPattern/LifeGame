package com.LifeGame.view;

import com.LifeGame.controller.PaletteController;
import com.LifeGame.model.PatternModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class PatternImagePanel extends JPanel implements Observer {

    private final PaletteController paletteController;

    private final int id;
    private final Image image;
    private final int[][] bluePrint;

    public PatternImagePanel(PaletteController paletteController, int id, InputStream inputStream, int[][] bluePrint) throws IOException {
        this.paletteController = paletteController;
        this.bluePrint = bluePrint;
        this.paletteController.addObserver(this);

        this.id = id;
        this.image = new ImageIcon(ImageIO.read(inputStream)).getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                paletteController.clickAction(id, bluePrint);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (this.getWidth() - image.getWidth(null)) / 2;
        int y = (this.getHeight() - image.getHeight(null)) / 2;
        g2d.drawImage(image, x, y, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PatternModel) {
            if (((PatternModel) o).getId() == this.id) {
                this.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
            } else {
                this.setBorder(null);
            }
        }
    }
}
