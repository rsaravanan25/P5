import java.util.*;

public class PercolationDFS extends PercolationDefault {



    public PercolationDFS(int size) {
        super(size);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void search(int row, int col) {
        if( !inBounds(row, col) || isFull(row, col) || !isOpen(row, col) )
        {
            return;
        }

        Stack< int[] > sta = new Stack<>();
        sta.push(new int[] {row, col} );
        myGrid[row][col] = FULL;

        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, -1, 1};


        while(!sta.isEmpty())
        {
            int[] top = sta.pop();

            for(int i = 0; i < r.length; i++)
            {
                int nrow = top[0] + r[i];
                int ncol = top[1] + c[i];

                if(inBounds(nrow, ncol) && (isOpen(nrow, ncol)) && (!isFull(nrow, ncol)))
                {
                    sta.push(new int[] {nrow, ncol} );
                    myGrid[nrow][ncol] = FULL;
                }
            }
        }

    }

}
