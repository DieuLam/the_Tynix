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

            System.out.println("------------------------------------------------------");

            // DATA
            System.out.println(
                    "\n1: Start date & end date \n2: Number of days from a date \n3: Number of weeks from a date\n");
            System.out.print("Please choose a time range method (1/2/3): ");
            int option = Integer.parseInt(input.nextLine());
            // get option
            switch (option) {
                case 1:
                    dateOption.option_1(date.country, date);
                    break;
                case 2:
                    dateOption.option_2(date.country, date);
                    break;
                case 3:
                    dateOption.option_3(date.country, date);
                    break;
                default:
                    System.out.println("invalid option");
                    System.exit(0);
            }

            System.out.println("------------------------------------------------------");

            // SUMMARY 2.1
            System.out.println("\n1: No grouping \n2: Number of groups \n3: Number of days\n");
            System.out.print("Please choose a grouping method (1/2/3): ");
            int groupingMethod = Integer.parseInt(input.nextLine());
            // get option
            switch (groupingMethod) {
                case 1:
                    date.DataGroups = new Group[1];
                    GroupingMethods.groupingMethod_2(GroupingOption.getTotalDays(date), 1, 0, date.DataGroups);
                    break;
                case 2:
                    GroupingOption.groupByNumGroups(date);
                    break;
                case 3:
                    GroupingOption.groupByNumDays(date);
                    break;
                default:
                    System.out.println("invalid option");
                    System.exit(0);
            }

            System.out.println("------------------------------------------------------");

            // SUMMARY 2.2
            // THUAN put your code here

        } else {
            System.out.println("\nWe can't find the country you are looking for");
        }
        
        System.out.println(date);
        // System.out.println(.dateList);
        input.close();
    }
}