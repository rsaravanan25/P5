import java.util.*;
public class PercolationUF implements IPercolate {
    private boolean[][] myGrid;
    private IUnionFind myFinder;
    private final int VTOP;
    private final int VBOTTEM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size)
    {
        myGrid = new boolean[size][size];
        for(int r = 0; r < myGrid.length; r++)
        {
            for(int c = 0; c < myGrid[r].length; c++)
            {
                myGrid[r][c] = false;
            }
        }
        finder.initialize(size * size + 2);
        myFinder = finder;

        VTOP = size * size;
        VBOTTEM = size * size + 1;
        myOpenCount = 0;
    }

    //helper method
    protected boolean inBounds(int row, int col)
    {
        if ( (row < 0 || row >= myGrid.length) || (col < 0 || col >= myGrid[0].length))
        {
            return false;
        }
        return true;
    }

    public void open(int row, int col)
    {
        if(!inBounds(row,col)) throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        if(isOpen(row,col))
        {
            return;
        }
        myGrid[row][col] = true;
        myOpenCount++;

        int cell = row*myGrid.length + col;
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, -1, 1};

        if(row == 0) myFinder.union(cell, VTOP);
        if(row == myGrid.length - 1) myFinder.union(cell, VBOTTEM);

        for(int i = 0; i < r.length; i++)
        {
            int nrow = row + r[i];
            int ncol = col + c[i];

            if(inBounds(nrow, ncol) && isOpen(nrow, ncol))
            {
                myFinder.union(cell, nrow * myGrid.length + ncol);
            }
        }

    }

    public boolean isFull(int row, int col)
    {
        if (!inBounds(row,col)) throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        else {
            int cell = row*myGrid.length + col;
            return myFinder.connected(cell, VTOP);
        }
    }

    public boolean isOpen(int row, int col)
    {
        if (!inBounds(row,col)) throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        else { return myGrid[row][col]; }
    }


    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTEM);
    }

    public int numberOfOpenSites()
    {
        return myOpenCount;
    }


}
