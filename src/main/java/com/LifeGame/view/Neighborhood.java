package com.LifeGame.view;

import java.awt.*;


/***
 * A group of {@link Cell} objects. Cells are grouped into neighborhoods
 * to make board updates more efficient. When a neighborhood is
 * quiescent (none of the Cells it contains are active), then it
 * ignores any requests to update itself.
 *
 * <h3>History</h3>
 * <p>11-29-04
 * Alexy Marinichev fixed the disapearing-glider problem by clearing
 * the active edges in transistion() rather then figureNextState().
 * The original call is commented out and the new line is marked
 * with "(1)"
 *
 * @include /etc/license.txt
 */

public final class Neighborhood implements Cell {

    /**
     * Returns true only if none of the cells in the Neighborhood
     * changed state during the last transition.
     */
    private boolean amActive = false;

    /**
     * The actual grid of Cells contained within this neighborhood.
     */
    private final Cell[][] grid;

    /**
     * The neighborhood is square, so gridSize is both the horizontal
     * and vertical size.
     */
    private final int gridSize;

    /**
     * Create a new Neigborhood containing gridSize-by-gridSize
     * clones of the prototype. The Protype is deliberately
     * not put into the grid, so you can reuse it if you like.
     */

    public Neighborhood(int gridSize, Cell prototype) {
        this.gridSize = gridSize;
        this.grid = new Cell[gridSize][gridSize];

        for (int row = 0; row < gridSize; ++row)
            for (int column = 0; column < gridSize; ++column)
                grid[row][column] = prototype.create();
    }

    /**
     * The "clone" method used to create copies of the current
     * neighborhood. This method is called from the containing
     * neighborhood's constructor. (The current neighborhood
     * is passed into the containing-neighborhood constructor
     * as the "prototype" argument.
     */
    public Cell create() {
        return new Neighborhood(gridSize, grid[0][0]);
    }

    /**
     * Redraw the current neighborhood only if necessary (something
     * changed in the last transition).
     *
     * @param g       Draw onto this graphics.
     * @param here    Bounding rectangle for current Neighborhood.
     * @param drawAll force a redraw, even if nothing has changed.
     * @see //#transition
     */
    public void redraw(Graphics g, Rectangle here, boolean drawAll) {
        // If the current neighborhood is stable (nothing changed
        // in the last transition stage), then there's nothing
        // to do. Just return. Otherwise, update the current block
        // and all sub-blocks. Since this algorithm is applied
        // recursively to sublocks, only those blocks that actually
        // need to update will actually do so.

        int compoundWidth = here.width;
        Rectangle subcell = new Rectangle(here.x, here.y, here.width / gridSize, here.height / gridSize);

        // Check to see if we can paint. If not, just return. If
        // so, actually wait for permission (in case there's
        // a race condition, then paint.

        for (int row = 0; row < gridSize; ++row) {
            for (int column = 0; column < gridSize; ++column) {
                grid[row][column].redraw(g, subcell, drawAll);    // {=Neighborhood.redraw3}
                subcell.translate(subcell.width, 0);
            }
            subcell.translate(-compoundWidth, subcell.height);
        }

        g = g.create();
        g.setColor(Colors.LIGHT_ORANGE);
        g.drawRect(here.x, here.y, here.width, here.height);

        if (amActive) {
            g.setColor(Color.BLUE);
            g.drawRect(here.x + 1, here.y + 1, here.width - 2, here.height - 2);
        }

        g.dispose();
    }

    /**
     * Return the edge cell in the indicated row and column.
     */
    public Cell edge(int row, int column) {
        assert (row == 0 || row == gridSize - 1) || (column == 0 || column == gridSize - 1) : "central cell requested from edge()";

        return grid[row][column];
    }

    /**
     * Notification of a mouse click. The point is relative to the
     * upper-left corner of the surface.
     */
    public void userClicked(Point here, Rectangle surface) {
        int pixelsPerCell = surface.width / gridSize;
        int row = here.y / pixelsPerCell;
        int column = here.x / pixelsPerCell;
        int rowOffset = here.y % pixelsPerCell;
        int columnOffset = here.x % pixelsPerCell;

        Point position = new Point(columnOffset, rowOffset);
        Rectangle subcell = new Rectangle(0, 0, pixelsPerCell, pixelsPerCell);

        grid[row][column].userClicked(position, subcell);
        amActive = true;
    }

    public int widthInCells() {
        return gridSize * grid[0][0].widthInCells();
    }

    public void clear() {
        for (int row = 0; row < gridSize; ++row) {
            for (int column = 0; column < gridSize; ++column) {
                grid[row][column].clear();
            }
        }
        amActive = false;
    }

    public Cell getCell(int x, int y) {
        int row = y / 8;
        int column = x / 8;
        int rowOffset = y % 8;
        int columnOffset = x % 8;

        return this.grid[row][column].getCell(columnOffset, rowOffset);
    }
}
