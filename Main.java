import java.io.*;
import java.text.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        // create an empty object
        Data date = new Data();
        // get country
        Scanner input = new Scanner(System.in);
        System.out.print("Please choose a country: ");
        String country = input.nextLine();
        // if country is valid
        if (errorHandler.checkCountry(date, country) == true) {
            // get date option
            System.out.println("\n1: start date & end date \n2: number of days from a date \n3: number of weeks from a date");
            System.out.print("Please choose a time range method (1/2/3): ");
            int option = Integer.parseInt(input.nextLine());
            // option 1
            if (option == 1) {
                dateOption.option_1(date); // call
            // option 2
            } else if (option == 2) {
                dateOption.option_2(date); // call
            // option 3
            } else if (option == 3) {
                dateOption.option_3(date); // call
            } else {
                System.out.println("invalid option");
            }
        } else {
            System.out.println("\nWe can't find the country you are looking for");
        }
        input.close();

        // PASS DATA TO SUMMARY
        // USE date.country, date.startDate, date.endDate as parameters
        System.out.printf("\ncountry: %s, start: %s, end: %s", date.country, date.startDate, date.endDate);
    }
}

class errorHandler {
    public static boolean checkCountry(Data date, String country) throws IOException {
        boolean valid = false;
        // capitalize 1st letter
        String formatCountry = country.substring(0, 1).toUpperCase() + country.substring(1, country.length()).toLowerCase();
         // read file
         BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
         reader.readLine(); // skip 1st line
         String line = reader.readLine(); // read each line
         // loop through file
         while (line != null) {
             String[] splitData = line.split(",");
             if (splitData[2].equals(formatCountry)) {
                 date.country = formatCountry; // assign to object if valid
                 valid = true;
                 break;
             } else {
                 line = reader.readLine();
                 continue;
             }
         }
         reader.close();
         return valid;
    }
}