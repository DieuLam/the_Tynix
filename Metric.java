import java.io.*;
import java.text.*;
import java.util.*;



class MetricOption {
    public static void PositiveCases(Data Pcases) throws IOException, ParseException {
        ArrayList<String[]> casenum = GroupingOption.getTotalDays(Pcases);
        for (int i = 0; i < Pcases.DataGroups.length; i++) {
            
            for (int j = 0; j < Pcases.DataGroups[i].totalDays.length; j++) {
                String new_total;
                new_total = casenum.get(0)[1];
                if (Pcases.DataGroups[i].value == 0) {
                    Pcases.DataGroups[i].value = Integer.parseInt(new_total);
                    casenum.remove(0);
                }else {
                    Pcases.DataGroups[i].value += Integer.parseInt(new_total);
                    casenum.remove(0);
                }  
            }
            System.out.println(Pcases.DataGroups[i].value);  
        }
    }
}
