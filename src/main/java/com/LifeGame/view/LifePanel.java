package com.LifeGame.view;

import com.LifeGame.controller.action.Action;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LifePanel extends JPanel {

    private final Cell outermostCell;

    public LifePanel() {
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

    public void clearCell() {
        this.outermostCell.clear();
    }

    public void setComponentListener(Action action) {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                action.action();
            }
        });
    }

    public void setMouseListener(Action action) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                action.action(e.getPoint());
            }
        });
    }
}
