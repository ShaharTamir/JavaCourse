import java.util.Scanner;

public class Number
{
    /***************************************************
    *  Description:                                    *
    *  Receive a 4 digits number and let user choose,  *
    *  out of 3 options, how to modify it:             *
    *   1. reverse sign                                *
    *   2. revese number (1234 = 4321, 1200 = 21)      *
    *   3. reverse both                                *
    *                                                  *
    *  if one of the user inputs is illigal -          *
    *   means not a 4 digit number or                  *
    *   not an option from menu (1,2 or 3)             *
    *  then the program exist, with output describing  *
    *  what went wrong.                                *
    ***************************************************/
    public static void main(String[] args)
    {
        // reverse calcuation consts
        final int RADIX = 10;   // decimal base

        // menu consts
        final int REVERSE_SIGN = 1;
        final int REVERSE_NUM = 2;
        final int REVERSE_BOTH = 3;

        // num range consts
        final int MAX_POSITIVE = 9999;
        final int MIN_POSITIVE = 1000;
        final int MAX_NEGATIVE = -9999;
        final int MIN_NEGATIVE = -1000;

        Scanner userInput = new Scanner(System.in); // standard input stream
        
        // receive num from user
        System.out.println("Please enter a 4 digit number:");
        int num = userInput.nextInt();

        // verify input
        if((MAX_POSITIVE >= num && MIN_POSITIVE <= num) ||
            (MAX_NEGATIVE <= num && MIN_NEGATIVE >= num))
        {
            // here means num is valid
            // print menu
            System.out.println("1. Reverse Sign.");
            System.out.println("2. Reverse number.");
            System.out.println("3. Reverse sign and number.");
            System.out.println("Please choose an option:");

            // receive functionallilty
            int choosenFunc = userInput.nextInt();

            userInput.close();  // no longer in use

            switch(choosenFunc)
            {
                case REVERSE_SIGN:
                    num = -num;
                    System.out.println("The result is");
                    System.out.print(num);
                    break;

                case REVERSE_BOTH:
                    num = -num; // no break on purpose(!), so that reverse calculation is written once.
                case REVERSE_NUM:
                    int reversedNum = num % RADIX; // get num curr first digit
                    reversedNum *= RADIX;          // shift left (make room for next digit)
                    num /= RADIX;                  // shift num right - curr first digit is now the origin num second digit
                    reversedNum += num % RADIX;    // repeat process until reversed
                    reversedNum *= RADIX;
                    num /= RADIX;
                    reversedNum += num % RADIX;
                    reversedNum *= RADIX;
                    num /= RADIX;
                    reversedNum += num;          // origin num most left digit is current first and only digit.
                    System.out.println("The result is");
                    System.out.print(reversedNum);
                    break;
                
                default:
                    System.out.println("Illegal option – you must choose 1, 2 or 3");
            } // switch
        } // if
        else // illigal number
            System.out.println("Illegal number – you must enter a 4 digit number");

    } // end of method main

} // end of class Number