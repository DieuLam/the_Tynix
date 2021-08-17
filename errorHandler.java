import java.io.*;

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

    public static void checkDate() {

    }
}