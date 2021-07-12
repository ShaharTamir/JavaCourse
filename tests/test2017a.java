

public class test2017a
{
    public static void main(String[] args)
    {
        //testEdit();
        testTriplets();
    }

    private static void testEdit()
    {
        String str1 = "geek";
        String str2 = "gesek";
        String str3 = "sunday";
        String str4 = "saturday";
        String str5 = "dayursat";
        String str6 = "abc";
        String str7 = "cab";

        System.out.println(str1 + " compare to " + str2 + " result: " + edit(str1, str2));
        System.out.println(str3 + " compare to " + str4 + " result: " + edit(str3, str4));
        System.out.println(str5 + " compare to " + str4 + " result: " + edit(str5, str4));
        System.out.println(str6 + " compare to " + str7 + " result: " + edit(str6, str7));
    }

    private static void testTriplets()
    {
        int arr[] = {1, 3, 4, 5, 7, 8};
        int num = 12;
        int expect = 4;

        System.out.println("********TEST TRIPLETS**********");
        System.out.println("test " + num + " expect " + expect + " got " + countTriplets(arr, num));
        num = 4;
        expect = 0;
        System.out.println("test " + num + " expect " + expect + " got " + countTriplets(arr, num));
        num = 10;
        expect = 2;
        System.out.println("test " + num + " expect " + expect + " got " + countTriplets(arr, num));
        num = 11;
        expect = 3;
        System.out.println("test " + num + " expect " + expect + " got " + countTriplets(arr, num));
        num = 15;
        expect = 11;
        System.out.println("test " + num + " expect " + expect + " got " + countTriplets(arr, num));

    }

    public static int edit (String str1, String str2)
    {
        if(null == str1 || null == str2)
            return 0;

        return edit(str1, str2, 0, 0);
        /*
        1. check if str1[i] == str2[j]
        1.1     edit(i+1,j+1);
        2.  else
        2.1     check if str1[i] exist in str2;
        2.1.1       everything in str2 until str1[i] note insert. (++actions);
        2.2     else
        2.2.1       remove str1[i];
         */
    }

    private static int edit(String str1, String str2, int i1, int i2)
    {
        if(i2 == str2.length())
            return str1.length() - i1;

        if(i1 == str1.length())
            return str2.length() - i2;

        if(str1.charAt(i1) == str2.charAt(i2))
            return edit(str1, str2, i1 + 1, i2 + 1);
        
        return 1 + Math.min(edit(str1, str2, i1, i2 + 1), edit(str1, str2, i1 + 1, i2));
    }
    
    public static int edit1(String str1, String str2)
    {
        if(str1.length() == 0)
            return str2.length();
        if(str2.length() == 0)
            return str1.length();
        
        if(str1.charAt(0) == str2.charAt(0))
        {
            return edit1(str1.substring(1), str2.substring(1));
        }

        int r1 = edit1(str1.substring(1), str2);
        int r2 = edit1(str1, str2.substring(1));

        return Math.min(r1, r2) + 1;
    }

    /* 
    *   complexity is O(log^3(n))???
    */
    public static int countTriplets(int [] arr, int num)
    {
        int aI = binSearch(arr, 0, 0, arr.length - 1, num);
        
        if(aI <= 1)
            return 0;
        
        int counter = 0;
        int bI = 0;
        int cI = 0;

        while(aI > 1)
        {
            bI = binSearch(arr, arr[aI], 0, aI - 1, num);

            while(bI > 0)
            {
                cI = binSearch(arr, arr[aI] + arr[bI], 0, bI - 1, num);

                if(cI >= 0 && arr[aI] + arr[bI] + arr[cI] >= num)
                    --cI;

                if(cI >= 0 && arr[aI] + arr[bI] + arr[cI] < num)
                    counter += cI + 1;

                --bI;
            }

            --aI;
        }

        return counter;
    }    

    private static int binSearch(int[] arr, int sum, int low, int high, int num)
    {
        int mid = (low + high) / 2;

        while(low < high)
        {
            if(sum + arr[mid] < num)
                low = mid + 1;
            else if(sum + arr[mid] > num)
                high = mid - 1;
            else
                break;

            mid = (high + low) / 2;
        }

        return mid;
    }

} // class


