package com.LifeGame.view;

import com.LifeGame.controller.LifeController;
import com.LifeGame.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

@Component
public class LifePanel extends JPanel implements Observer {

    private final LifeController lifeController;
    private final Cell outermostCell;

    @Autowired
    public LifePanel(LifeController lifeController) {

        this.lifeController = lifeController;

        this.lifeController.addObserver(this);

        /**
         * The default height and width of a Neighborhood in cells.
         * If it's too big, you'll run too slowly because
         * you have to update the entire block as a unit, so there's more
         * to do. If it's too small, you have too many blocks to check.
         * I've found that 8 is a good compromise.
         */

        int DEFAULT_GRID_SIZE = 8;
        this.outermostCell = new Neighborhood(DEFAULT_GRID_SIZE, new Neighborhood(DEFAULT_GRID_SIZE, new Resident()));

        /**
         * The size of the smallest "atomic" cell---a Resident object.
         * This size is extrinsic to a Resident (It's passed into the
         * Resident's "draw yourself" method.
         */

        int DEFAULT_CELL_SIZE = 8;
        final Dimension PREFERRED_SIZE = new Dimension(this.outermostCell.widthInCells() * DEFAULT_CELL_SIZE, this.outermostCell.widthInCells() * DEFAULT_CELL_SIZE);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Rectangle bounds = getBounds();
                bounds.x = 0;
                bounds.y = 0;
                int pixelsPerCell = bounds.width / (DEFAULT_GRID_SIZE * DEFAULT_GRID_SIZE);
                int row = e.getPoint().y / pixelsPerCell;
                int column = e.getPoint().x / pixelsPerCell;

                lifeController.mouseAction(row, column);
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Rectangle bounds = getBounds();
                bounds.height /= getWidthInCells();
                bounds.height *= getWidthInCells();
                bounds.width = bounds.height;
                setBounds(bounds);
            }
        });

        setBackground(Color.white);
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
        setMinimumSize(PREFERRED_SIZE);
        setOpaque(true);
    }

    /**
     * Override paint to ask the outermost Neighborhood
     * (and any subcells) to draw themselves recursively.
     * All knowledge of screen size is also encapsulated.
     * (The size is passed into the outermost <code>Cell</code>.)
     */
    public void paint(Graphics g) {
        Rectangle panelBounds = getBounds();

        // The panel bounds is relative to the upper-left
        // corner of the screen. Pretend that it's at (0,0)
        panelBounds.x = 0;
        panelBounds.y = 0;
        this.outermostCell.redraw(g, panelBounds, true);        //{=Universe.redraw1}
    }

    public Cell getOutermostCell() {
        return this.outermostCell;
    }
    public int getWidthInCells() {
        return this.outermostCell.widthInCells();
    }

    public void clear() {
        this.outermostCell.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            Rectangle bounds = this.getBounds();
            bounds.x = 0;
            bounds.y = 0;
            int pixelsPerCell = bounds.width / 64;
            this.clear();
            int[][] cells = ((Model) o).getMap();
            for (int i = 0; i < 64; i++) {
                for (int j = 0; j < 64; j++) {
                    if (cells[i][j] == 1) {
                        this.outermostCell.userClicked(new Point(j * pixelsPerCell, i * pixelsPerCell), bounds);
                    }
                }
            }
            this.repaint();
        }
    }
}
