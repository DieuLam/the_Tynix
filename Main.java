import java.io.*;
import java.text.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        // get country
        System.out.print("Please choose a country: ");
        String country = input.nextLine();
        // get date option
        System.out.println("\n1: start date & end date \n2: number of days from a date \n3: number of weeks from a date");
        System.out.print("Please choose a time range method (1/2/3): ");
        int option = Integer.parseInt(input.nextLine());
        // option 1
        if (option == 1) {
            dateOption.option_1(country); // call
        // option 2
        } else if (option == 2) {
            dateOption.option_2(country); // call
        // option 3
        } else if (option == 3) {
            dateOption.option_3(country); // call
        } else {
            System.out.println("invalid option");
        }
        input.close();
    }
}

class dateOption {
    public static void option_1(String country) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.print("End date (MM/dd/yyyy): ");
        String endDate = input.nextLine();
        input.close();
        // calculate days range
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");   
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate); 
        long days = ((end.getTime() - start.getTime()) / (1000*60*60*24)) % 365; // covert time to days
        int totalDay = (int) days + 1; // convert long to int
        // call
        getData.getObject(country, startDate, totalDay);
    }

    public static void option_2(String country) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.printf("How many days from %s: ", startDate);
        int days = Integer.parseInt(input.nextLine());
        input.close();
        int totalDay = days + 1;   
        // call
        getData.getObject(country, startDate, totalDay);
    }

    public static void option_3(String country) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.printf("How many week from %s: ", startDate);
        int weeks = Integer.parseInt(input.nextLine());
        input.close();
        int totalDay = weeks * 7 + 1;   
        // call
        getData.getObject(country, startDate, totalDay);
    }
}

class getData {

    public static Data[] getObject(String country, String startDate, int totalDay) throws ParseException, IOException{
        // create an empty array
        Data[] dataList = new Data[totalDay];
            // read file
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine(); // read each line
        // loop through file
        int i = 0;
        while (line != null && i < totalDay) {
            String[] splitData = line.split(",");
            String countryName = splitData[2];
            String date = splitData[3];

            if (countryName.equals(country) && date.equals(startDate)) {
                dataList[i] = new Data(countryName, date);
                i++;
                line = reader.readLine(); // next line
                 continue;
            } else if (countryName.equals(country) && dataList[0] != null) {
                dataList[i] = new Data(countryName, date);
                i++;
                line = reader.readLine(); // next line
                continue;
            } else {
                line = reader.readLine(); // next line
                continue;
            }
        }
        reader.close();
        // print
        for (int x = 0; x < dataList.length; x++) {
            System.out.printf("\nCountry: %s, Date: %s", dataList[x].country, dataList[x].date);
        }
        return dataList;
    }
}

class Data {
    String country;
    String date;

    // create a constructor
    Data(String country, String date) {
        this.country = country;
        this.date = date;
    }
}