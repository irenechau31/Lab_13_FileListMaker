import javax.print.DocFlavor;
import java.util.Scanner;

public class SafeInput {
    /**
     * // <= this is a special javadoc comment block.  You need one before each method to document it
     * Gets a String from the user that has to be at least one character or more
     *
     * @param pipe   Scanner to use for input set to the console with System.in
     * @param prompt the prompt for the user the methods adds the ": " and uses System.print
     * @return After looping until the user enters something a non zero String...
     */
    // Part A: getNonZeroLenString
    public static String getNonZeroLengthString(Scanner pipe, String prompt) {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do {
            System.out.print(" " + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    /**
     * Get an integer value from the user with no constraints
     *
     * @param pipe   Scanner to use for input
     * @param prompt User prompt
     * @return an int value provided by the user
     */
    // PART B: METHOD TO READ INTEGER FROM CONSOLE
    public static int getInt(Scanner pipe, String prompt) {
        int integerInput = 0;
        String trash = "";
        boolean done = false;
        //Prompt to user
        do {
            System.out.println(prompt);
            if (pipe.hasNextInt()) // if user input integer
            {
                integerInput = pipe.nextInt();//read input
                pipe.nextLine(); //clear the buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Enter a valid integer, not: " + trash);
            }
        } while (!done);
        return integerInput;
    }// end of getInt

    // PART C: METHOD TO READ DOUBLE
    public static double getDouble(Scanner pipe, String prompt) {
        double doubleInput = 0.0;
        String trash = "";
        boolean done = false;
        //Prompt to user
        do {
            System.out.println(prompt);
            if (pipe.hasNextDouble()) // if user input double
            {
                doubleInput = pipe.nextDouble();//read input
                pipe.nextLine(); //clear the buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Enter a valid double, not: " + trash);
            }
        } while (!done);
        return doubleInput;
    }//end of getDouble();

    /**
     * Get an integer value from the user within a specified inclusive low - high range
     *
     * @param pipe   Scanner to use for input
     * @param prompt User prompt
     * @param low    low value for the range
     * @param high   high value for the range
     * @return an int value provided by the user within the specified range
     */
    // PART D: METHOD TO READ INT BETWEEN RANGE
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int input = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.println(prompt + " as integer [" + low + "-" + high + "]:");
            if (pipe.hasNextInt())// if user input integer
            {
                input = pipe.nextInt();//read integer input
                // Check if the integer input in the given range
                if (input >= low && input <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + input);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Enter a valid integer, no: " + trash);
            }
        } while (!done);
        return input;
    }// End of getRangedInt();

    /**
     * Get a double value from the user within a specified inclusive low - high range
     *
     * @param pipe   Scanner to use for input
     * @param prompt User prompt
     * @param low    low value for the range
     * @param high   high value for the range
     * @return an int value provided by the user within the specified range
     */
    //PART E: METHOD TO READ DOUBLE IN RANGE
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double doubleInput = 0;
        String trash = "";
        boolean done = false;
        //Prompt to user
        do {
            System.out.println(prompt + "[" + low + "-" + high + "]:");
            if (pipe.hasNextDouble()) // if user input integer
            {
                doubleInput = pipe.nextDouble();//read input
                // Check if the input in the given range
                if (doubleInput >= low && doubleInput <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + doubleInput);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Enter a valid double, not: " + trash);
            }
        } while (!done);
        return doubleInput;
    }// End of getRangeDouble();


    // PART F: METHOD TO READ Y/N
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input = "";
        boolean validInput;
        do {
            System.out.println(prompt);
            input = pipe.nextLine();
            validInput = input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N");
            if (!validInput) {
                System.out.println("Invalid input. Please enter among y, Y, n, and N");
            }
        }
        while (!validInput);
        return input.equalsIgnoreCase("y");
    }


    //PART G: getRegExStringmethod to return String which matches to given regEx
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern) {
        String value = "";
        boolean gotValue = false;

        do {// show the prompt
            System.out.print(prompt + ": ");
            value = pipe.nextLine();  // input the data
            // test to see if it is correct
            if (value.matches(regExPattern)) {// We have a match this String passes!
                gotValue = true;
            } else {
                System.out.println("Invalid input: " + value);
            }
        } while (!gotValue);
        return value;
    }

    //PART H: String Msg, create header figure
    public static void prettyHeader(String msg) {
        int totalWidth = 60;
        int msgLength = msg.length();
        int spaceCount = (totalWidth - msgLength - 6) / 2; // Calculate space for centering the message

        // Print the top line of stars
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print the second line with centered message
        System.out.print("***");
        for (int i = 0; i < spaceCount; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < spaceCount; i++) {
            System.out.print(" ");
        }
        // If the message length is odd, add one more space for even padding
        if (msgLength % 2 != 0) {
            System.out.print(" ");
        }
        System.out.println("***");

        // Print the bottom line of stars
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}