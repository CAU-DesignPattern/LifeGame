package com.LifeGame.view;

import java.awt.*;

public interface Cell {

    /**
     * Access a specific contained cell located at the edge of the
     * composite cell.
     *
     * @param row    The requested row. Must be on the edge of
     *               the block.
     * @param column The requested column. Must be on the edge
     *               of the block.
     * @return true    if the the state changed.
     */
    Cell edge(int row, int column);

    /**
     * Redraw yourself in the indicated
     * rectangle on the indicated Graphics object if ncessary. This
     * method is meant for a conditional redraw, where some of the
     * cells might not be refreshed (if they haven't changed state,
     * for example).
     *
     * @param g       redraw using this graphics,
     * @param here    a rectangle that describes the bounds of the
     *                current cell.
     * @param drawAll if true, draw an entire compound cell;
     *                otherwise, draw only the subcells that need to be redrawn.
     */
    void redraw(Graphics g, Rectangle here, boolean drawAll);

    /**
     * A user has clicked somewhere within you.
     *
     * @param here The position of the click relative to the bounding
     *             rectangle of the current Cell.
     */
    void userClicked(Point here, Rectangle surface);

    /**
     * Return the specified width plus the current cell's width
     */
    int widthInCells();

    Cell create();
}

