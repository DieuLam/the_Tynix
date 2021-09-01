import java.io.*;
import java.text.*;
import java.util.*;

class GetTotalDates {
    public static ArrayList<String[]> getAllDates(Data Fvalue) throws IOException, ParseException {
        ArrayList<String[]> countrylist = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine();
        while (line != null) {
            String[] splitData = line.split(",");
            if (Fvalue.country.equals(splitData[2])) {
                while (true) {
                    String[] countryData = new String[4];
                    for (int i = 0; i < countryData.length; i++) {
                        countryData[i] = splitData[i + 3];
                    }
                    countrylist.add(countryData);
                    line = reader.readLine();
                    splitData = line.split(",");
                    if (!Fvalue.country.equals(splitData[2])) {
                        break;
                    }
                }
                break;
            } else {
                line = reader.readLine();
            }
        }
        reader.close();
        return countrylist;
    }

    public static ArrayList<String[]> getTotalDays(Data obj) throws IOException, ParseException {

        ArrayList<String[]> dateList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine();

        while (line != null) {
            String[] splitData = line.split(",");
            // if start date is found on the data.csv
            if (splitData[3].equals(obj.startDate) && obj.country.equals(splitData[2])) {
                // loop to add all dates from start date to end date
                while (true) {
                    // add date to the list
                    String[] dateData = new String[4];
                    for (int i = 0; i < dateData.length; i++) {
                        if (splitData[i + 3].equals("")) {
                            dateData[i] = "0";
                        } else {
                            dateData[i] = splitData[i + 3];
                        }
                    }
                    dateList.add(dateData);
                    line = reader.readLine();
                    splitData = line.split(",");
                    // if end date is found on the data.csv
                    if (dateList.get(dateList.size() - 1)[0].equals(obj.endDate)) {
                        // stop the loop
                        break;
                    }
                }
                break;
            } else {
                line = reader.readLine();
            }
        }
        reader.close();
        return dateList;
    }
}