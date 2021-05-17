
public class Ex14Tester
{
    public static void main(String[] args)
    {
        testDrop();
        testSink();
        testSize();
        testPermutation();
    }

    public static void testDrop()
    {
        int[] test1 = { 5, 21, 3, 22, 12, 7, 27, 6, 4};
        int[] test2 = { 5, 21, 3, 22, 12, 7, 26, 14};
        int[] test3 = { 5, 15, 3, 22, 12, 7, 27, 14};
        int[] test4 = {};
        int[] test5 = new int[6];

        System.out.println("test1: " + Ex14.maximalDrop(test1));
        System.out.println("test2: " + Ex14.maximalDrop(test2));
        System.out.println("test3: " + Ex14.maximalDrop(test3));
        System.out.println("test4: " + Ex14.maximalDrop(test4));
        System.out.println("test5: " + Ex14.maximalDrop(test5));
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

        System.out.println("test1: " + Ex14.isSink(test1));
        System.out.println("test2: " + Ex14.isSink(test2));
        System.out.println("test3: " + Ex14.isSink(test3));
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

        System.out.println("test1: " + Ex14.size(mat, x1, y1));
        System.out.println("test2: " + Ex14.size(mat, x2, y2));
        System.out.println("test3: " + Ex14.size(mat, x3, y3));
        System.out.println("test4: " + Ex14.size(mat, x4, y4));
    }

    public static void testPermutation()
    {
        int[][] a = {{1, 2, 3, 4},
                     {4, 2, 3, 4},
                     {1, 2, 4, 4},
                     {1, 2, 3, 4, 5}};
        int[][] b = {{3, 2, 4, 1},
                     {3, 2, 4, 4},
                     {1, 2, 4, 2},
                     {3, 2, 4, 5},
                     {3, 2, 4, 1, 5}};

        System.out.println("test1: " + Ex14.isPermutation(a[0], b[0]));
        System.out.println("test2: " + Ex14.isPermutation(a[1], b[1]));
        System.out.println("test3: " + Ex14.isPermutation(a[2], b[2]));
        System.out.println("test4: " + Ex14.isPermutation(a[0], b[3]));
        System.out.println("test5: " + Ex14.isPermutation(a[0], b[4]));
        System.out.println("test6: " + Ex14.isPermutation(a[3], b[0]));
    }
}