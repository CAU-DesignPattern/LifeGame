package com.LifeGame.view;

import java.awt.*;

public final class Resident implements Cell {
    private static final Color BORDER_COLOR = Colors.DARK_YELLOW;
    private static final Color LIVE_COLOR = Color.RED;
    private static final Color DEAD_COLOR = Colors.LIGHT_YELLOW;

    private boolean amAlive = false;

    /**
     * This cell is monetary, so it's at every edge of itself. It's
     * an internal error for any position except for (0,0) to be
     * requsted since the width is 1.
     */
    public Cell edge(int row, int column) {
        assert row == 0 && column == 0;
        return this;
    }

    public void redraw(Graphics g, Rectangle here, boolean drawAll) {
        g = g.create();
        g.setColor(amAlive ? LIVE_COLOR : DEAD_COLOR);
        g.fillRect(here.x + 1, here.y + 1, here.width - 1, here.height - 1);

        // Doesn't draw a line on the far right and bottom of the
        // grid, but that's life, so to speak. It's not worth the
        // code for the special case.

        g.setColor(BORDER_COLOR);
        g.drawLine(here.x, here.y, here.x, here.y + here.height);
        g.drawLine(here.x, here.y, here.x + here.width, here.y);
        g.dispose();
    }

    public void userClicked(Point here, Rectangle surface) {
        amAlive = !amAlive;
    }

    public Cell create() {
        return new Resident();
    }

    public int widthInCells() {
        return 1;
    }

    public void	clear()	{
        amAlive = false;
    }
}

