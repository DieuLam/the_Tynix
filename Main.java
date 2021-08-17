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
        System.out.printf("\ncountry: %s, start: %s, end: %s", date.country, date.startDate, date.endDate);
    }
}

class dateOption {
    public static Data option_1(Data date) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.print("End date (MM/dd/yyyy): ");
        String endDate = input.nextLine();
        input.close();

        // assign to object
        date.startDate = startDate;
        date.endDate = endDate;
        return date;
    }

    public static Data option_2(Data date) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);

        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.printf("How many days from %s: ", startDate);
        int days = Integer.parseInt(input.nextLine());
        input.close();   

        // calculate end date;   
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(startDate)); // convert from String to Date
        c.add(Calendar.DATE, days); // add days
        Date newDate = c.getTime(); 
        String endDate = formatter.format(newDate); // covert back to string

         // assign to object
         date.startDate = startDate;
         date.endDate = endDate;
         return date;
    }

    public static Data option_3(Data date) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);

        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.printf("How many weeks from %s: ", startDate);
        int weeks = Integer.parseInt(input.nextLine());
        input.close();   

        // calculate end date; 
        int days = weeks * 7;   
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(startDate)); // convert from String to Date
        c.add(Calendar.DATE, days); // add days
        Date newDate = c.getTime();
        String endDate = formatter.format(newDate);  // covert back to string
;
        // assign to object
        date.startDate = startDate;
        date.endDate = endDate;
        return date;
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
                 date.country = formatCountry;
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

class Data {
    String country;
    String startDate;
    String endDate;
    
    Data() {

    }

    // create a constructor
    Data(String country, String startDate, String endDate) {
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}