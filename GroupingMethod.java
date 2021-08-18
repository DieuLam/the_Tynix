import java.io.*;
import java.text.*;
import java.util.*;

class GroupingMethod {
    public static void groupingMethod_2(Data data) throws IOException, ParseException {
        // get number of groups from users
        System.out.println("\nEnter number of groups");
        int numGroup = Integer.parseInt(Main.input.nextLine());

        data.DataGroups = new Group[numGroup];

        ArrayList<String> dateList = getTotalDays(data);
        int totalDays = dateList.size();
        String[] groupingList = new String[totalDays / numGroup];
        int count = 0;

        // if number of days in groups are equal
        if (totalDays % numGroup == 0) {
            // loop through numbers of groups
            for (int j = 0; j < numGroup; j++) {
                // loop through numbers of days in one group
                for (int i = 0; i < totalDays / numGroup; i++) {
                    // append days to the temporary group list
                    groupingList[i] = (dateList.get(count));
                    count++;
                }
                // create new array with the same value with the temporary group list
                String[] finalGroupingList = new String[groupingList.length];
                for (int x = 0; x < groupingList.length; x++) {
                    finalGroupingList[x] = groupingList[x];
                }
                // append new group object to the group list of data
                data.DataGroups[j] = new Group(finalGroupingList);
                
            }
            System.out.println(Arrays.toString(data.DataGroups));
        }

        // if (totalDays / numGroup >= 2) {
        //     for (int j = 0; j < numGroup - 1; j++) {
        //         for (int i = 0; i < totalDays / numGroup; i++) {
        //             groupingList[i] = (dateList.get(count));
        //             count++;
        //         }
        //         String[] finalGroupingList = new String[groupingList.length];
        //         finalGroupingList = groupingList;
        //         data.DataGroups[j] = new Group(finalGroupingList);
        //     }
        //     for (int i = 0; i < totalDays % numGroup; i++) {
        //         groupingList[i] = (dateList.get(count));
        //         count++;
        //     }
        //     String[] finalGroupingList = new String[groupingList.length];
        //     finalGroupingList = groupingList;
        //     data.DataGroups[data.DataGroups.length - 1] = new Group(finalGroupingList);
        // } 
        else {
            System.out.println("error");
        }
        
    }

    public static ArrayList<String> getTotalDays(Data obj) throws IOException, ParseException {
        ArrayList<String> dateList = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine();

        while (line != null) {
            String[] splitData = line.split(",");
            if (splitData[3].equals(obj.startDate)) {
                while (true) {
                    dateList.add(splitData[3]);
                    line = reader.readLine();
                    splitData = line.split(",");
                    if (splitData[3].equals(obj.endDate)) {
                        dateList.add(splitData[3]);
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