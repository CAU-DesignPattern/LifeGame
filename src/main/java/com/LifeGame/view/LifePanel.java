package com.LifeGame.view;

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
    /**
     * The default height and width of a Neighborhood in cells.
     * If it's too big, you'll run too slowly because
     * you have to update the entire block as a unit, so there's more
     * to do. If it's too small, you have too many blocks to check.
     * I've found that 8 is a good compromise.
     */
    private static final int DEFAULT_GRID_SIZE = 8;

    /**
     * The size of the smallest "atomic" cell---a Resident object.
     * This size is extrinsic to a Resident (It's passed into the
     * Resident's "draw yourself" method.
     */
    private static final int DEFAULT_CELL_SIZE = 8;

    public LifePanel() {
        outermostCell = new Neighborhood(DEFAULT_GRID_SIZE, new Neighborhood(DEFAULT_GRID_SIZE, new Resident()));
        final Dimension PREFERRED_SIZE = new Dimension(outermostCell.widthInCells() * DEFAULT_CELL_SIZE, outermostCell.widthInCells() * DEFAULT_CELL_SIZE);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // Make sure that the cells fit evenly into the
                // total grid size so that each cell will be the
                // same size. For example, in a 64x64 grid, the
                // total size must be an even multiple of 63.

                Rectangle bounds = getBounds();
                bounds.height /= outermostCell.widthInCells();
                bounds.height *= outermostCell.widthInCells();
                bounds.width = bounds.height;
                setBounds(bounds);
            }
        });

        setBackground(Color.white);
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
        setMinimumSize(PREFERRED_SIZE);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Rectangle bounds = getBounds();
                bounds.x = 0;
                bounds.y = 0;
                outermostCell.userClicked(e.getPoint(), bounds);
                repaint();
            }
        });
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
        outermostCell.redraw(g, panelBounds, true);        //{=Universe.redraw1}
    }
}
