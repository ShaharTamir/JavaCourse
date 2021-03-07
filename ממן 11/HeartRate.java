import java.util.Scanner;

public class HeartRate {

    /****************************************
    *   Receive age from user and calculate *
    *   target heart rate while exercising  *
    ****************************************/
    public static void main(String[] args)
    {
        final int MIN_PULSE_PRECENTAGE = 65;
        final int MAX_PULSE_PRECENTAGE = 85;
        final int TO_PRECENTAGE = 100;
        final int DEFAULT_MAX_PULSE = 220;

        Scanner userInput = new Scanner(System.in); // construct standard input stream scanner

        System.out.println ("This program calculates your " + 
                "target heart rate while exercising.");
        System.out.print ("Enter your age: ");

        int age = userInput.nextInt();  // receive age from user
        userInput.close(); // no longer in use
        
        // calculate pulse range
        int minPulseTgt = (DEFAULT_MAX_PULSE - age) * MIN_PULSE_PRECENTAGE / TO_PRECENTAGE;
        int maxPulseTgt = (DEFAULT_MAX_PULSE - age) * MAX_PULSE_PRECENTAGE / TO_PRECENTAGE;

        System.out.println ("Your estimated target heart rate zone is " +
                minPulseTgt + " - " + maxPulseTgt + " beats per minute.");
    
    } // end of method main

};  // end of class HeartRate
