import java.io.*;
import java.text.*;
import java.util.*;

class GroupingOption {

    public static void groupByNumGroups(Data data) throws IOException, ParseException {
        System.out.println("\nEnter number of groups");
        int numGroup = Integer.parseInt(Main.input.nextLine());
        data.DataGroups = new Group[numGroup];
        GroupingMethods.groupingMethod_2(getTotalDays(data), numGroup, 0, data.DataGroups);
    }

    public static void groupByNumDays(Data data) throws IOException, ParseException {
        System.out.println("\nEnter number of days");
        int numDay = Integer.parseInt(Main.input.nextLine());
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

class GroupingMethods {
    public static void groupingMethod_1() {

    }

    public static void groupingMethod_2(ArrayList<String> list, int numGroup, int idx, Group[] groups)
            throws IOException, ParseException {

        int totalDays = list.size();
        int numDaysInGroup = totalDays / numGroup;
        String[] groupList = new String[numDaysInGroup];

        for (int i = 0; i < numDaysInGroup; i++) {
            groupList[i] = (list.get(0));
            list.remove(0);
        }

        groups[idx] = new Group(groupList);
        while (!list.isEmpty()) {
            groupingMethod_2(list, numGroup - 1, idx + 1, groups);
        }
    }
}