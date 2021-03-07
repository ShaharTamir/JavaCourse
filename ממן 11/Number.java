import java.util.Scanner;

public class Number
{
    public static void main(String[] args)
    {
        // reverse calcuation consts
        final int TEN = 10;

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

            userInput.close();  // not longer in use

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
                    int reversedNum = num % TEN; // get num curr first digit
                    reversedNum *= TEN;          // shift left (make room for next digit)
                    num /= TEN;                  // shift num right - curr first digit is now the origin num second digit
                    reversedNum += num % TEN;    // repeat process until reversed
                    reversedNum *= TEN;
                    num /= TEN;
                    reversedNum += num % TEN;
                    reversedNum *= TEN;
                    num /= TEN;
                    reversedNum += num;          // origin num most left digit is current first and only digit.
                    System.out.println("The result is");
                    System.out.print(reversedNum);
                    break;
                
                default:
                    System.out.println("Illegal option – you must choose 1, 2 or 3");
                    break;
            }
        }
        else // illigal number
        {
            System.out.println("Illegal number – you must enter a 4 digit number");
        }
    } // end of method main

} // end of class Number