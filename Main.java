package the_Tynix;

import java.io.*;
import java.text.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException{
        getObject(numberOfCountries());
    }

    public static int numberOfCountries() throws IOException {
        // read file
        Scanner reader = new Scanner(new File("the_Tynix/data.csv"));
        // country count
        int countryCount = 0;
        String country = "";
        // loop through file
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] splitData = line.split(",");
            if (!splitData[2].equals(country)) {
                countryCount ++;
                country = splitData[2];
            }
        }
        reader.close();
        int count = countryCount - 2;
        return count; // remove the first line
    }

    public static void getObject(int countryCount) throws IOException, ParseException{
        // create an empty object array
        Data[] dataList = new Data[countryCount];
        // read file
        BufferedReader reader = new BufferedReader(new FileReader("the_Tynix/data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine(); // read each line
        // set 1st value
        String[] firstData = line.split(",");
        String country = firstData[2];
        String startDate = firstData[3];
        String endDate = firstData[3];
        int i = 0;
        // loop through file
        while (line != null && i < countryCount) {
            String[] splitData = line.split(",");

            if (!splitData[2].equals(country)) {
                dataList[i] = new Data(country, startDate, endDate);
                country = splitData[2];
                startDate = splitData[3];
                i++;
            } else {
                endDate = splitData[3];
                // next line
                line = reader.readLine();
            }
        }
        reader.close();
        // print object
        System.out.println("Country, Start Date, End Date");
        for (int x = 0; x < dataList.length; x++) {
            System.out.printf("\n%s, %s, %s", dataList[x].country, dataList[x].startDate, dataList[x].endDate);
        }
    }
}

class Data {
    String country;
    String startDate;
    String endDate;

    // create a constructor
    Data(String country, String startDate, String endDate) {
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}