
public class test2016b
{
    public static void main(String[] args)
    {
        char[][] minChess = {{'0', '0', '0', '0'},
                             {'0', '0', 'K', '0'},
                             {'0', '0', '0', '0'},
                             {'H', '0', '0', '0'}};

        System.out.println("res = " + minPath(minChess, 3, 0));
        printBoard(minChess);
    }

    public static int minPath(char[][] minChess, int i, int j)
    {
        if(!indexValid(minChess.length, i, j) || minChess == null)
            return -1;

        int res = minPathRecurse(minChess, i, j);
        minChess[i][j] = 'H';

        return res;
    }

    private static int minPathRecurse(char[][] minChess, int i, int j)
    {
        if(indexValid(minChess.length, i, j) && minChess[i][j] != '1')
        {
            if(minChess[i][j] == 'K')
                return 0;

            minChess[i][j] = '1';
            int[] dir = new int[8];
            dir[0] = minPathRecurse(minChess, i - 2, j + 1);   // up right
            dir[1] = minPathRecurse(minChess, i - 2, j - 1);   // up left
            dir[2] = minPathRecurse(minChess, i + 2, j + 1);   // down right
            dir[3] = minPathRecurse(minChess, i + 2, j - 1);   // down left
            dir[4] = minPathRecurse(minChess, i - 1, j + 2);   // right up
            dir[5] = minPathRecurse(minChess, i + 1, j + 2);   // right down
            dir[6] = minPathRecurse(minChess, i - 1, j - 2);   // left up
            dir[7] = minPathRecurse(minChess, i + 1, j - 2);   // left down
            minChess[i][j] = '0';

            int res = localMin(localMin(localMin(dir[0], dir[1]), localMin(dir[2], dir[3])), localMin(localMin(dir[4], dir[5]), localMin(dir[6], dir[7])));
            
            if(res != -1)
                return 1 + res;
        }

        return -1;
    }

    private static boolean indexValid(int size, int i, int j)
    {
        return i >= 0 && j >= 0 && i < size && j < size;
    }

    public static void printBoard(char[][] board)
    {
        for(int r = 0; r < board.length; ++r)
        {
            for(int c = 0; c < board.length - 1; ++c)
            {
                System.out.print(board[r][c] + ", ");
            }
            System.out.println(board[r][board.length - 1]);
        }
    }

    private static int localMin(int a, int b)
    {

        return (a != -1 && b != -1) ?  Math.min(a, b) : 
            (a != -1) ? a : b;
    }

}