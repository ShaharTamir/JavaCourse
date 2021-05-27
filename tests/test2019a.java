public class test2019a {

    public static void main(String[] args)
    {
        int[] arr = {1, 3, 2, 3, 10, 10, 3, 2, 1};

        System.out.println(longestPalindrome(arr));
        arr[7] = 78;
        System.out.println(longestPalindrome(arr));
        arr[6] = 4;
        System.out.println(longestPalindrome(arr));
        arr[3] = 11;
        System.out.println(longestPalindrome(arr));
        arr[5] = 12;
        System.out.println(longestPalindrome(arr));
    }

    public static int longestPalindrome(int[] arr)
    {
        return longestPalindrome(arr, 0, arr.length - 1, 1);
    }

    private static int longestPalindrome(int[] arr, int leftIndex, int rightIndex, int longest)
    {
        if(leftIndex == arr.length - 1 || longest > arr.length / 2)
            return longest;

        if(leftIndex == rightIndex || leftIndex > rightIndex)
            return longestPalindrome(arr, leftIndex + 1, arr.length - 1, longest);
        
        int currPosPalindromeLength = isPalindrome(arr, leftIndex, rightIndex, 1);
        
        if(currPosPalindromeLength > 0)
        {
            longest = Math.max(currPosPalindromeLength, longest);
            return longestPalindrome(arr, leftIndex + 1, arr.length - 1, longest);
        }
        
        return longestPalindrome(arr, leftIndex, rightIndex - 1, longest);
    }

    private static int isPalindrome(int[] arr, int leftIndex, int rightIndex, int count)
    {
        if(leftIndex > rightIndex) // start from 1
            return count - 1;
        if(leftIndex == rightIndex)
            return count;
        
        if(arr[leftIndex] == arr[rightIndex])
            return isPalindrome(arr, leftIndex + 1, rightIndex - 1, count + 2);

        return 0;
    }
}
