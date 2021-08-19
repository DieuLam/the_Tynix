import java.io.*;
import java.text.*;
import java.util.*;

class GroupingOption {
    public static void groupByNumGroups(Data data) throws IOException, ParseException {
        // get number of groups from user
        System.out.print("\nEnter number of groups: ");
        int numGroup = Integer.parseInt(Main.input.nextLine());
        data.DataGroups = new Group[numGroup];
        GroupingMethods.groupingMethod_2(getTotalDays(data), numGroup, 0, data.DataGroups);
    }

    public static void groupByNumDays(Data data) throws IOException, ParseException {
        // System.out.print("\nEnter number of days: ");
        // int numDay = Integer.parseInt(Main.input.nextLine());
    }

    // add all selected dates to a list
    public static ArrayList<String> getTotalDays(Data obj) throws IOException, ParseException {
        ArrayList<String> dateList = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        reader.readLine(); // skip 1st line
        String line = reader.readLine();

        while (line != null) {
            String[] splitData = line.split(",");
            // if start date is found on the data.csv
            if (splitData[3].equals(obj.startDate)) {
                // loop to add all dates from start date to before end date
                while (true) {
                    // add date to the list
                    dateList.add(splitData[3]);
                    line = reader.readLine();
                    splitData = line.split(",");
                    // if end date is found on the data.csv
                    if (splitData[3].equals(obj.endDate)) {
                        // add end date to the list
                        dateList.add(splitData[3]);
                        // escape the loop to stop adding
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

class GroupingMethods {
    public static void groupingMethod_1() {

    }

    // divde the dates into numbers of groups
    public static void groupingMethod_2(ArrayList<String> list, int numGroup, int idx, Group[] groups)
            throws IOException, ParseException {

        int totalDays = list.size();
        int numDaysInGroup = totalDays / numGroup;
        // temporary group to store the dates in one group
        String[] groupList = new String[numDaysInGroup];

        // loop number-of-day times to add dates to group
        for (int i = 0; i < numDaysInGroup; i++) {
            // add dates from list to temp group
            groupList[i] = (list.get(0));
            // when a new date is added to the temp group, remove that date from the list
            list.remove(0);
        }

        // add new group to group list of the data
        groups[idx] = new Group(groupList);
        // repeat until there are no dates left
        while (!list.isEmpty()) {
            groupingMethod_2(list, numGroup - 1, idx + 1, groups);
        }
    }
}