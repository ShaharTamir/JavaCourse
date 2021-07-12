public class minChess
{
    public static void main (String [] args){
        char [][] minChess = {{'0','0','0','0'},
                              {'0','0','K','0'},
                              {'0','0','0','0'},
                              {'H','0','0','0'}};

        System.out.println(minPath(minChess,0,3));
    }
    public static int minPath(char [][] minChess, int i,int j){
        int res = minPath(minChess,i,j,0);
        minChess[i][j] = 'H';

        return res;
    }

    public static int minPath(char [][] minChess, int i, int j, int count){
        if (i < minChess.length && j < minChess[0].length && i >= 0 && j >= 0 && minChess[i][j] != '1'){ // check if within array bounds
            
            if (minChess[i][j] == 'K') { // base success case - found king
                return count;
            }

            minChess[i][j] = '1';
            int[] paths = new int[8];
            
            paths[0] = minPath(minChess,i+2,j+1,count+1); // down right
            paths[1] = minPath(minChess,i+2,j-1,count+1); // down left
            paths[2] = minPath(minChess,i-2,j+1,count+1); // up right
            paths[3] = minPath(minChess,i-2,j-1,count+1); // up left
            paths[4] = minPath(minChess,i-1,j+2,count+1); // right up
            paths[5] = minPath(minChess,i+1,j+2,count+1); // right down
            paths[6] = minPath(minChess,i-1,j-2,count+1); // left up
            paths[7] = minPath(minChess,i+1,j-2,count+1); // left down

            minChess[i][j] = '0';

           for(int d = 0; d < paths.length; ++d)
           {
               if (paths[d] != -1 && count == 0){
                   count = paths[d];
               }
               else if(paths[d] != -1)
                   count = Math.min(count, paths[d]);
           }

            if(count != 0)
                return count;                
        }
        
        return -1;
    }
}


