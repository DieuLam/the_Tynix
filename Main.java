import java.io.*;
import java.text.*;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        // create an empty object
        Data date = new Data();
        // get country

        System.out.print("Please choose an area: ");
        String country = input.nextLine();
        // if country is valid
        if (errorHandler.checkCountry(date, country) == true) {
            // get date option
            System.out.println(
                    "\n1: Start date & end date \n2: Number of days from a date \n3: Number of weeks from a date\n");
            System.out.print("Please choose a time range method (1/2/3): ");
            int option = Integer.parseInt(input.nextLine());
            // option 1
            if (option == 1) {
                dateOption.option_1(date.country, date); // call
                // option 2
            } else if (option == 2) {
                dateOption.option_2(date.country, date); // call
                // option 3
            } else if (option == 3) {
                dateOption.option_3(date.country, date); // call
            } else {
                System.out.println("invalid option");
            }
            // SUMMARY 2.1
            System.out.println("\n1: No grouping \n2: Number of groups \n3: Number ofdays\n");
            System.out.print("Please choose a grouping method (1/2/3): ");
            int groupingMethod = Integer.parseInt(input.nextLine());

            if (groupingMethod == 1) {
                System.out.println("\nWe can't find the country you are looking for");
            }
            if (groupingMethod == 2) {
                GroupingMethod.groupingMethod_2(date);
            }
            if (groupingMethod == 3) {
                System.out.println("\nWe can't find the country you are looking for");
            }

        } else {
            System.out.println("\nWe can't find the country you are looking for");
        }
        System.out.println(date);
        // System.out.println(.dateList);
        input.close();
    }
}