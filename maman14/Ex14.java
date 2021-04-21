public class Ex14 {

    public static void main(String[] args)
    {
        //testDrop();
        //testSink();
        //testSize();
        testPermutation();
    }

    public static void testDrop()
    {
        int[] test1 = { 5, 21, 3, 22, 12, 7, 27, 6, 4};
        int[] test2 = { 5, 21, 3, 22, 12, 7, 26, 14};
        int[] test3 = { 5, 15, 3, 22, 12, 7, 27, 14};
        int[] test4 = {};
        int[] test5 = new int[6];

        System.out.println("test1: " + maximalDrop(test1));
        System.out.println("test2: " + maximalDrop(test2));
        System.out.println("test3: " + maximalDrop(test3));
        System.out.println("test4: " + maximalDrop(test4));
        System.out.println("test5: " + maximalDrop(test5));
    }

    public static void testSink()
    {
        int[][] test1 = {{0, 1, 0, 1, 1, 0}, 
                         {1, 0, 1, 1, 0, 0}, 
                         {0, 0, 0, 1, 0, 1},
                         {0, 0, 0, 0, 0, 0}, 
                         {1, 0, 1, 1, 0, 0}, 
                         {0, 1, 0, 1, 1, 1}};

        int[][] test2 = {{0, 1, 0, 0, 0, 1}, 
                         {1, 0, 0, 1, 1, 1}, 
                         {0, 0, 0, 0, 0, 0}, 
                         {1, 1, 1, 1, 1, 1},
                         {0, 1, 0, 1, 0, 1},
                         {1, 0, 0, 0, 1, 0}}; 

        int[][] test3 = {{0, 0, 0, 0, 0, 0}, 
                         {1, 0, 1, 1, 0, 0}, 
                         {1, 0, 0, 1, 0, 1},
                         {1, 0, 0, 0, 0, 0}, 
                         {1, 0, 1, 1, 0, 0}, 
                         {1, 1, 0, 1, 1, 1}};

        System.out.println("test1: " + isSink(test1));
        System.out.println("test2: " + isSink(test2));
        System.out.println("test3: " + isSink(test3));
    }

    public static void testSize()
    {
        boolean[][] mat =   {{false, true, false, false, true},
                             {true, false, false, true, true},
                             {false, false, true, true, false},
                             {true, false, false, false, false},
                             {true, true, true, false, false}};
                            
        int x1 = 0;
        int y1 = 0;
        int x2 = 10;
        int y2 = 0;
        int x3 = 1;
        int y3 = 0;
        int x4 = 0;
        int y4 = 4;

        System.out.println("test1: " + size(mat, x1, y1));
        System.out.println("test2: " + size(mat, x2, y2));
        System.out.println("test3: " + size(mat, x3, y3));
        System.out.println("test4: " + size(mat, x4, y4));
    }

    public static void testPermutation()
    {
        int[][] a = {{1, 2, 3, 4},
                     {4, 2, 3, 4},
                     {1, 2, 4, 4}};
        int[][] b = {{3, 2, 4, 1},
                     {3, 2, 4, 4},
                     {1, 2, 4, 2},
                     {3, 2, 4, 5},
                     {3, 2, 4, 1, 5}};

        System.out.println("test1: " + isPermutation(a[0], b[0]));
        System.out.println("test2: " + isPermutation(a[1], b[1]));
        System.out.println("test3: " + isPermutation(a[2], b[2]));
        System.out.println("test4: " + isPermutation(a[0], b[3]));
        System.out.println("test5: " + isPermutation(a[0], b[4]));
    }

    public static int maximalDrop(int[] a)
    {
        if (a.length == 0)
            return 0;

        int maxDiff = 0;
        int maxNum = a[0];

        for(int i = 1; i < a.length; ++i)
        {
            if(a[i] >= maxNum)
                maxNum = a[i];
            else if(maxNum - a[i] > maxDiff)
                maxDiff = maxNum - a[i];
        }

        return maxDiff;
    }

    public static int isSink(int[][] mat)
    {
        int i = 0;
        int j;

        while(i < mat.length)
        { 
            j = 0;
            while(j < mat[0].length && 0 == mat[i][j])
                ++j;

            if(j == mat[0].length)
            {
                j = 0;
                while(j < mat.length && (1 == mat[j][i] || j == i))
                    ++j;

                if(j == mat.length)
                    return i;
            }//if

            ++i;
        } //while
    
        return -1;
    }

    public static int size(boolean[][] mat, int x, int y)
    {
        if(!validPos(mat, x, y) || !mat[x][y])
            return 0;

        boolean[][] trace = new boolean[mat.length][mat[0].length]; // all false by default

        return size(mat, trace, x, y);
    }

    public static boolean isPermutation(int[] a, int[] b)
    {
        if(a.length != b.length)
            return false;

        boolean[] bTrace = new boolean[b.length];

        return isPermutation(a, b, bTrace, 0, 0);
    }

    private static int size(boolean[][] mat, boolean[][] trace, int x, int y)
    {
        if(!validPos(mat, x, y) || trace[x][y])
            return 0;

        trace[x][y] = true;

        if(mat[x][y])
        {
            return 1 + size(mat, trace, x + 1, y) + size(mat, trace, x - 1, y) + //down & up
                size(mat, trace, x, y + 1) + size(mat, trace, x, y - 1) +  // right & left
                size(mat, trace, x + 1, y + 1) + size(mat, trace, x - 1, y - 1) + // diag down-right & up-left
                size(mat, trace, x + 1, y - 1) + size(mat, trace, x - 1, y + 1); // diag down-left & up-right
        }

        return 0;
    }

    private static boolean validPos(boolean[][] mat, int x, int y)
    {
        return x < mat.length && x >= 0 && y < mat[0].length && y >= 0;
    }

    private static boolean isPermutation(int[] a, int[] b, boolean[] bTrace, int aIndex, int bIndex)
    {
        if(aIndex == a.length)
            return true;
        
        if(bIndex == b.length)
            return false;

        if(a[aIndex] == b[bIndex] && !bTrace[bIndex])
        {
            bTrace[bIndex] = true;
            return isPermutation(a, b, bTrace, aIndex + 1, 0);
        }

        return isPermutation(a, b, bTrace, aIndex, bIndex + 1);
    }
}
