import java.io.*;
import java.util.*;

class errorHandler {
    public static boolean checkCountry(Data date, String country) throws IOException {
        boolean valid = false;
        // make string lowercase
        String formatCountry = country.toLowerCase();
        // read file
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine(); // read each line
        // loop through file
        while (line != null) {
            String[] splitData = line.split(",");
            if (splitData[2].toLowerCase().equals(formatCountry)) {
                date.country = splitData[2];
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

    public static String convertDate(String date) throws IOException {
        // split date string to remove 0
        String[] dateSplit = date.split("/");
        int month = Integer.valueOf(dateSplit[0]);
        int day = Integer.valueOf(dateSplit[1]);
        int year = Integer.valueOf(dateSplit[2]);
        // cconvert back to String
        String formatDate = month + "/" + day + "/" + year;
        return formatDate;
    }

    public static boolean checkDate(String country, String startDate, String endDate) throws IOException {
        boolean valid = false;
        boolean startValid = false;
        boolean endValid = false;
        // re-format date
        String start = convertDate(startDate);
        String end = convertDate(endDate);
        // read file
        Scanner input = new Scanner(new File("data.csv"));
        while (input.hasNext()) {
            String[] line = input.nextLine().split(",");
            if (country.equals(line[2]) && start.equals(line[3])) {
                startValid = true;
            } else if (country.equals(line[2]) && end.equals(line[3])) {
                endValid = true;
            }
        }
        if (startValid == true && endValid == true) {
            valid = true;
        }
        return valid;
    }

}