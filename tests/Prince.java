public class Prince {

    private static final int BEEN_HERE = -5;

    public static void main(String[] args)
    {
        int[][] drm = {{2, 0, 1, 2, 3},
                        {2, 3, 5, 5, 4},
                        {8, -1, 6, 8, 7},
                        {3, 4, 7, 2, 4},
                        {2, 4, 3, 1, 2}};

        System.out.println(prince(drm, 0, 2));
    }

    public static int prince(int[][] drm, int i, int j)
    {
        if(!validIndex(drm.length, i, j))
            return -1;

        return prince(drm, i, j, drm[i][j]);
    }

    private static int prince(int[][] drm, int i, int j, int prev)
    {
        if(validIndex(drm.length, i, j))
        {
            if(-1 == drm[i][j])
                return 1;


            if(validGap(drm[i][j], prev))
            {
                int temp = drm[i][j];
                drm[i][j] = BEEN_HERE;
                int east = prince(drm, i, j + 1, temp);
                int west = prince(drm, i, j - 1, temp);
                int north = prince(drm, i - 1, j, temp);
                int south = prince(drm, i + 1, j, temp);
                drm[i][j] = temp;

                int res = quadMinimumAboveZero(east, west, north, south);
                if(res != -1)
                    return 1 + res;
            }
        }

        return -1;
    }

    private static boolean validIndex(int size, int i, int j)
    {
        return i >= 0 && i < size && j >= 0 && j < size;
    }

    private static boolean validGap(int currHeight, int prevHeight)
    {
        return (currHeight - prevHeight <= 1 && currHeight - prevHeight >= 0) ||
                (prevHeight - currHeight <= 2 && prevHeight - currHeight >= 0);
    }

    private static int quadMinimumAboveZero(int a, int b, int c, int d)
    {
        return localMin(localMin(a, b), localMin(c, d));
    }

    private static int localMin(int a, int b)
    {
        return (a != -1 && b != -1) ? Math.min(a, b) : (a == -1 ? b : a);
    }
}
