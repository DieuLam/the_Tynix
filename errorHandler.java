import java.io.*;

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