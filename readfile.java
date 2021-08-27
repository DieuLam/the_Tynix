import java.io.*;
import java.text.*;
import java.util.*;

class readfile {
    
    public static ArrayList<String[]> GetFirstValue(Data Fvalue) throws IOException, ParseException {
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

    public static void PrintArrayList(Data arr) throws IOException, ParseException {
        ArrayList<String[]> countryArr = readfile.GetFirstValue(arr);
        for (int i = 0; i < countryArr.size(); i++) {
            System.out.println(Arrays.toString(countryArr.get(i)));
        } 
    }
}