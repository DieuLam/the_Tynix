import java.io.*;
import java.text.*;
import java.util.*;

class MetricOption {
    
    static String[] Metric = { "Positive case", "Death case", "Vaccinated" };
    static String[] Method = { "New total", "Up to" };

    // new total
    // calculate both new cases and death cases new total
    public static void CasesNewTotal(Data cases, int metric, int type) throws IOException, ParseException {
        ArrayList<String[]> casenum = GroupingOption.getTotalDays(cases);

        cases.method = Method[type-1];
        // loop number of groups
        for (int i = 0; i < cases.DataGroups.length; i++) {
            cases.DataGroups[i].metric = Metric[metric - 1];
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
    public static void VaccineNew(Data Vcases, int type) throws IOException, ParseException {
        ArrayList<String[]> casenum = GroupingOption.getTotalDays(Vcases);

        Vcases.method = Method[type-1];
        // loop the number of group
        for (int i = 0; i < Vcases.DataGroups.length; i++) {
            Vcases.DataGroups[i].metric = Metric[2];
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
        }
    }

    // calculate Up to
    // new cases and death case Up to
    public static void CasesUpTo(Data cases, int metric, int type) throws IOException, ParseException {
        String checkValue;

        cases.method = Method[type-1];
        for (int i = 0; i < cases.DataGroups.length; i++) {
            cases.DataGroups[i].metric = Metric[metric - 1];
            ArrayList<String[]> fdate = readfile.GetFirstValue(cases);
            while (true) {
                int fvalue;
                // check if data is null or not
                if (fdate.get(0)[metric].equals("")) {
                    fvalue = 0;
                } else {
                    fvalue = Integer.parseInt(fdate.get(0)[metric]);
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
                if (checkValue.equals(cases.DataGroups[i].totalDays[cases.DataGroups[i].totalDays.length - 1])) {
                    break;
                }
            }
        }
    }

    public static void VaccinatedUpTo(Data Vcases, int type) throws IOException, ParseException {
        String checkValue;
        ArrayList<String[]> fdate = readfile.GetFirstValue(Vcases);

        Vcases.method = Method[type-1];
        // loop the number of group
        for (int i = 0; i < Vcases.DataGroups.length; i++) {
            Vcases.DataGroups[i].metric = Metric[2];
            while (true) {
                int Lvaccine;
                // check the data if it null
                if (fdate.get(0)[3].equals("")) {
                    Lvaccine = 0;
                } else {
                    Lvaccine = Integer.parseInt(fdate.get(0)[3]);
                }
                if (Vcases.DataGroups[i].value > Lvaccine) {
                    Vcases.DataGroups[i].value = Vcases.DataGroups[i].value;
                    checkValue = fdate.get(0)[0];
                    fdate.remove(0);

                } else {
                    Vcases.DataGroups[i].value = Lvaccine;
                    checkValue = fdate.get(0)[0];
                    fdate.remove(0);
                }
                if (checkValue.equals(Vcases.DataGroups[i].totalDays[Vcases.DataGroups[i].totalDays.length - 1])) {
                    break;
                }
            }
        }
    }
}