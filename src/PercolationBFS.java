import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDefault {

    public PercolationBFS (int size)
    {
        super(size);
    }

    @Override
    protected void search(int row, int col) {
    
        if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}

        if(myGrid[row][col] != OPEN)
        {
            return;
        }

        int[] r = {-1,1,0,0};
        int[] c = {0,0,-1,1};

        myGrid[row][col] = FULL;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {row,col});

        while(que.size() != 0)
        {
            int[] front = que.remove();
            for(int i = 0; i < r.length; i++)
            {
                row = front[0] + r[i];
                col = front[1] + c[i];
                
                if( (row < myGrid.length && row >= 0) && (col < myGrid.length && col >= 0) )
                {
                    if(myGrid[row][col] == OPEN)
                    {
                        myGrid[row][col] = FULL;
                        que.add(new int[]{row,col});
                    }
                }

            }
        }
    }

}
    
