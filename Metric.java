import java.io.*;
import java.text.*;
import java.util.*;

class MetricOption {
    // new total
    // calculate both new cases and death cases new total
    public static void CasesNewTotal(Data cases, int metric) throws IOException, ParseException {
        ArrayList<String[]> casenum = GroupingOption.getTotalDays(cases);
        // loop number of groups
        for (int i = 0; i < cases.DataGroups.length; i++) {
            // loop the data in group
            for (int j = 0; j < cases.DataGroups[i].totalDays.length; j++) {
                int new_total;
                // check if data is null or not
                if (casenum.get(0)[metric].equals("")) {
                    new_total = 0;
                } else {
                    new_total = Integer.parseInt(casenum.get(0)[metric]);
                }
                // check the data and asign new value
                if (cases.DataGroups[i].value == 0) {
                    cases.DataGroups[i].value = new_total;
                    casenum.remove(0);

                } else {
                    cases.DataGroups[i].value += new_total;
                    casenum.remove(0);
                }
            }
        }
    }

    // calculate vaccinated new total
    public static void VaccineNew(Data Vcases) throws IOException, ParseException {
        ArrayList<String[]> casenum = GroupingOption.getTotalDays(Vcases);
        // loop the number of group
        for (int i = 0; i < Vcases.DataGroups.length; i++) {
            int Fvaccine;
            int Lvaccine;
            // check the data if it null
            if (casenum.get(0)[3].equals("")) {
                Fvaccine = 0;
            } else {
                Fvaccine = Integer.parseInt(casenum.get(0)[3]);
            }
            if (casenum.get(Vcases.DataGroups[i].totalDays.length - 1)[3].equals("")) {
                Lvaccine = 0;
            } else {
                Lvaccine = Integer.parseInt(casenum.get(Vcases.DataGroups[i].totalDays.length - 1)[3]);
            }
            // get the vaccinated new total
            Vcases.DataGroups[i].value = Lvaccine - Fvaccine;
            for (int j = 0; j < Vcases.DataGroups[i].totalDays.length; j++) {
                casenum.remove(0);
            }
            System.out.println(Vcases.DataGroups[i].value);
        }
    }

    // calculate Up to
    public static void CasesUpTo(Data cases) throws IOException, ParseException {
        // ArrayList<String[]> casenum = GroupingOption.getTotalDays(cases);
        
        String checkValue;
        for (int i = 0; i < cases.DataGroups.length; i++) {
            ArrayList<String[]> fdate = readfile.GetFirstValue(cases);
            for (int j = 0; j < fdate.size(); j++) {
                int fvalue;
                // check if data is null or not
                if (fdate.get(0)[1].equals("")) {
                    fvalue = 0;
                } else {
                    fvalue = Integer.parseInt(fdate.get(0)[1]);
                }
                // check the data and asign new value
                if (cases.DataGroups[i].value == 0) {
                    cases.DataGroups[i].value = fvalue;
                    checkValue = fdate.get(0)[0];
                    fdate.remove(0);

                } else {
                    cases.DataGroups[i].value += fvalue;
                    checkValue = fdate.get(0)[0];
                    fdate.remove(0); 
                }
                if (checkValue.equals(cases.DataGroups[i].totalDays[cases.DataGroups[i].totalDays.length-1])) {
                    break;
                }
            }
        }
    }
}
