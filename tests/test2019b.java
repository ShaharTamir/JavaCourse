public class test2019b
{
    public static void main(String[] args)
    {
        //testSum();
        testRoad();        
    }

    public static boolean isSum(int[] a, int num)
    {
        return isSum(a, num, 0, 0, 0);
    }

    /* time complexity - O(n) */
    public static int shortestRoad(int[] road1, int[] road2)
    {
        int minTime = 0;
        int aSumLeft = 0;
        int aSumRight = 0;
        int bSumLeft = 0;
        int bSumRight = 0;

        for(int i = 0; i < road1.length; ++i)
        {
            aSumLeft += road1[i];
            bSumLeft += road2[i];
        }

        minTime = aSumLeft > bSumLeft ? aSumLeft : bSumLeft;

        for(int i = road1.length - 1; i >= 0; --i)
        {
            aSumRight += road1[i];
            bSumRight += road2[i];

            minTime = minTime > (aSumLeft - aSumRight + bSumRight) ? (aSumLeft - aSumRight + bSumRight) : minTime;  
            minTime = minTime > (bSumLeft - bSumRight + aSumRight) ? (bSumLeft - bSumRight + aSumRight) : minTime;  
        }

        return minTime;
    }

    private static boolean isSum(int[] a, int sum, int currSum, int index, int countNear)
    {
        if(countNear == 3)
            return false;

        if(currSum == sum)
            return  true;

        if(index == a.length)
            return false;
        
        if(isSum(a, sum, currSum + a[index], index + 1, countNear + 1))
            return true;
        else
            return isSum(a, sum, currSum, index + 1, 0);
    }

    private static void testSum()
    {
        int[] a = {5, 4, 2, 1, 3};
        int num = 0;
        String testNum = "test num = ";
        String gap = ": ";

        System.out.println(testNum + num + gap + isSum(a, num));
        num = 8;
        System.out.println(testNum + num + gap + isSum(a, num));
        num = 9;
        System.out.println(testNum + num + gap + isSum(a, num));
        num = 2;
        System.out.println(testNum + num + gap + isSum(a, num));
        num = 11;
        System.out.println(testNum + num + gap + isSum(a, num));
        num = 17;
        System.out.println(testNum + num + gap + isSum(a, num));
    }

    private static void testRoad()
    {
        int[] road1 = {5,4,5,8,12,9,9,3};
        int[] road2 = {7,3,3,12,10,2,10,7};

        System.out.println("shortest road is: " + shortestRoad(road1, road2));
    }
}