import java.io.*;
import java.text.*;
import java.util.*;

class GroupingOption {
    public static void groupByNumGroups(Data data) throws IOException, ParseException {
        // get number of groups from user
        System.out.print("\nHow many groups: ");
        int numGroup = Integer.parseInt(Main.input.nextLine());
        if (numGroup <= GetTotalDates.getTotalDays(data).size()) {
            data.DataGroups = new Group[numGroup];
            GroupingMethods.groupingMethod_2(GetTotalDates.getTotalDays(data), numGroup, 0, data.DataGroups);
        } else {
            System.out.println("\nThe number of groups exceeds the number of days");
        }
    }

    public static void groupByNumDays(Data data) throws IOException, ParseException {
        // get number of days from user
        System.out.print("\nHow many days per group: ");
        int numDay = Integer.parseInt(Main.input.nextLine());
        GroupingMethods.groupingMethod_1(GetTotalDates.getTotalDays(data), numDay, 0, data);
    }

    // add all selected dates to a list
}

class GroupingMethods {
    public static void groupingMethod_1(ArrayList<String[]> list, int numDay, int idx, Data data)
            throws IOException, ParseException {
        int totalDays = list.size();
        int numGroup = totalDays / numDay;
        if (totalDays % numDay == 0) {
            data.DataGroups = new Group[numGroup];
            groupingMethod_2(list, numGroup, idx, data.DataGroups);
        } else {
            System.out.println("\nCannot divide groups equally");
            System.exit(0);
        }
    }

    // divde the dates into numbers of groups
    public static void groupingMethod_2(ArrayList<String[]> list, int numGroup, int idx, Group[] groups)
            throws IOException, ParseException {

        int totalDays = list.size();
        int numDaysInGroup = totalDays / numGroup;
        // temporary group to store the dates in one group
        String[] groupList = new String[numDaysInGroup];

        // loop number-of-day times to add dates to group
        for (int i = 0; i < numDaysInGroup; i++) {
            // add dates from list to temp group
            groupList[i] = (list.get(0)[0]);
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