public class Ex14 {

    /**
    *  Find the maximum difference between a high number followed by a small number in an array.<p>
    *   Complexity:<p>
    *   The time complexity is O(n) (or O(2n)),
    *   because iterating through the array only once and each iteration has two actions.<p>
    *   The space complexity is O(1), because only allocating three variables,
    *   without any relation to <tt>a</tt> size.
    *
    *   @param a integers array to find it's maximum diff
    *   @return maximum difference between a high number followed by a small number in an array.
    */
    public static int maximalDrop(int[] a)
    {
        if (a.length == 0)
            return 0;

        int maxNum = a[0];  // largest number in array until this point in array.
        int maxDiff = 0;    // biggest difference found.

        for(int i = 1; i < a.length; ++i)
        {
            if(a[i] >= maxNum)
                maxNum = a[i];
            else if(maxNum - a[i] > maxDiff) // found a bigger difference than the current
                maxDiff = maxNum - a[i];
        }

        return maxDiff;
    }

    /**
     * Find if a given matrix contains a sink.
     *  The matrix should contian 1's and 0's only.<p>
     *  A sink is when an 'i' row index in matrix is filled with 0's only,
     *  and the same 'i' column index in matrix is filled with 1's, except [i][i] position which is 0.<p>
     *  The method returns the index of the first sink found in matrix, or -1 if none exist.<p>
     * Complexity:<p>
     *  The time complexity is O(n^2) - worst case scenario is when the matrix is filled with 0
     *  except the last column filled with 1. Means iterate once through the whole matrix.<p>
     *  The space complexity is O(1) becuase allocating only three variables.
     * 
     * @param mat matrix to look for sinks at, containing 0's and 1's only.
     * @return the index of the first sink found in matrix, or -1 if none exist.
     */
    public static int isSink(int[][] mat)
    {
        int zeroRowsCount = 0;
        int i = 0;
        int j;

        while(i < mat.length)
        { 
            j = 0;
            while(j < mat[0].length && 0 == mat[i][j]) // find if row is filled with 0
                ++j;

            if(j == mat[0].length) // whole row is filled with 0
            {
                if(zeroRowsCount > 0) // all columns contain at least two 0's, means not possible to contain a sink
                    return -1;
                    
                ++zeroRowsCount;
                j = 0;

                while(j < mat.length && (1 == mat[j][i] || j == i)) // find if column is filled with 1, except [i][i] pos
                    ++j;

                if(j == mat.length) // found a sink
                    return i;
            }//if

            ++i;
        } //while
    
        return -1;
    }

    /**
     * Returns the size of a spot in matrix, if exists, in given (x,y) index.<p>
     *  A spot is a group of cells in matrix that are adjacent and are all 'true' in the boolean matrix.<p>
     * 
     * @param mat a boolean matrix to look for a spot at.
     * @param x row index in <tt>mat</tt>.
     * @param y column index in <tt>mat</tt>.
     * @return If the position is valid - the size of a spot found in a given position. else - 0.
     */
    public static int size(boolean[][] mat, int x, int y)
    {
        if(!validPos(mat, x, y) || !mat[x][y]) 
            return 0;

        boolean[][] trace = new boolean[mat.length][mat[0].length]; // all false by default

        return size(mat, trace, x, y);
    }

    /**
     * Find if two given integer arrays are a permutation of one another.<p>
     * 
     * @param a first integers array
     * @param b second integers array to compare to
     * @return true if <tt>a</tt> is a permutation of <tt>b</tt>. else return false.
     */
    public static boolean isPermutation(int[] a, int[] b)
    {
        if(a.length != b.length)
            return false;

        boolean[] bTrace = new boolean[b.length];

        return isPermutation(a, b, bTrace, 0, 0);
    }

    /****************************
    *       PRIVATE METHODS     *
    ****************************/

    /* 
    *   Overload to the public 'size' method and does exactly as described there.
    *   Only added the trace in the params that size gets.
    *
    *   mat - a boolean matrix to look for a spot at
    *   trace - mark each cell that have been checked
    *   x - row index
    *   y - column index
    *
    *   return - the size of a spot found. not found or invalid pos - return 0.
    */
    private static int size(boolean[][] mat, boolean[][] trace, int x, int y)
    {
        if(!validPos(mat, x, y) || trace[x][y])
            return 0;

        trace[x][y] = true; // mark each cell visited to check it only once

        if(mat[x][y])
        {
            return 1 + size(mat, trace, x + 1, y) + size(mat, trace, x - 1, y) + //down & up
                size(mat, trace, x, y + 1) + size(mat, trace, x, y - 1) +  // right & left
                size(mat, trace, x + 1, y + 1) + size(mat, trace, x - 1, y - 1) + // diag down-right & up-left
                size(mat, trace, x + 1, y - 1) + size(mat, trace, x - 1, y + 1); // diag down-left & up-right
        }

        return 0;
    }

    /*
    *   Verify that the index (x,y) is in within the matrix boundaries
    *
    *   mat - the matrix
    *   x - row index
    *   y - column index
    *
    *   return - true if position is valid
    */
    private static boolean validPos(boolean[][] mat, int x, int y)
    {
        return x < mat.length && x >= 0 && y < mat[0].length && y >= 0;
    }

    /*
    *   Overload to the public isPermutation, and implements it's discription.
    *   Only added parameters to the function call.
    *
    *   a - first array
    *   b - array to compare to
    *   bTrace - marks each cell in b that has been used as a match to a.
    *   aIndex - 'a' current index
    *   bIndex - 'b' current index
    */
    private static boolean isPermutation(int[] a, int[] b, boolean[] bTrace, int aIndex, int bIndex)
    {
        if(aIndex == a.length) // means found for every cell in 'a' it's match in 'b'
            return true;
        
        if(bIndex == b.length) // means did not find a match for a cell in 'a'
            return false;

        if(a[aIndex] == b[bIndex] && !bTrace[bIndex])
        {
            bTrace[bIndex] = true; // mark this index in b as used
            return isPermutation(a, b, bTrace, aIndex + 1, 0); // look for a match to the next index in 'a'
        }

        return isPermutation(a, b, bTrace, aIndex, bIndex + 1); // this bIndex is not a match to 'a' curr aIndex, try the next one
    }
}
