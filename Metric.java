import java.io.*;
import java.text.*;
import java.util.*;

class MetricOption {

    static String[] Metric = { "Positive case", "Death case", "Vaccinated" };
    static String[] Method = { "New total", "Up to" };

    // new total
    public static void CasesNewTotal(Data cases, int metric, int type) throws IOException, ParseException {
        ArrayList<String[]> caseNum = getVaccinatedValue(GetTotalDates.getTotalDays(cases), cases);
        cases.method = Method[type - 1];
        // loop number of groups
        for (int i = 0; i < cases.DataGroups.length; i++) {
            cases.DataGroups[i].metric = Metric[metric - 1];
            // loop the data in group
            for (int j = 0; j < cases.DataGroups[i].totalDays.length; j++) {
                int new_total = Integer.parseInt(caseNum.get(0)[metric]);
                // check the data and asign new value
                if (cases.DataGroups[i].value == 0) {
                    cases.DataGroups[i].value = new_total;
                    caseNum.remove(0);

                } else {
                    cases.DataGroups[i].value += new_total;
                    caseNum.remove(0);
                }
            }
        }
    }

    // calculate Up to
    public static void CasesUpTo(Data cases, int metric, int type) throws IOException, ParseException {
        String checkValue;
        ArrayList<String[]> caseNum = getVaccinatedValue(GetTotalDates.getTotalDays(cases), cases);
        cases.method = Method[type - 1];
        for (int i = 0; i < cases.DataGroups.length; i++) {
            cases.DataGroups[i].metric = Metric[metric - 1];
            while (true) {
                int fvalue;
                fvalue = Integer.parseInt(caseNum.get(0)[metric]);
                // check the data and asign new value
                if (cases.DataGroups[i].value == 0) {
                    cases.DataGroups[i].value = fvalue;
                    checkValue = caseNum.get(0)[0];
                    caseNum.remove(0);
                } else {
                    cases.DataGroups[i].value += fvalue;
                    checkValue = caseNum.get(0)[0];
                    caseNum.remove(0);
                }
                if (checkValue.equals(cases.DataGroups[i].totalDays[cases.DataGroups[i].totalDays.length - 1])) {
                    break;
                }
            }
        }
    }

    public static ArrayList<String[]> getVaccinatedValue(ArrayList<String[]> dateList, Data Vcases)
            throws IOException, ParseException {
        ArrayList<String[]> fdate = GetTotalDates.getAllDates(Vcases);
        ArrayList<String[]> caseNum = GetTotalDates.getTotalDays(Vcases);
        String[] day = new String[4];

        for (int i = 0; i < fdate.size(); i++) {
            if (fdate.get(i)[0].equals(caseNum.get(0)[0]) && !fdate.get(0)[0].equals(caseNum.get(0)[0])) {
                day[3] = fdate.get(i - 1)[3];
                day[0] = fdate.get(i - 1)[0];
                break;
            } else {
                day[0] = caseNum.get(0)[0];
                day[3] = "0";
            }
        }
        caseNum.add(0, day);

        for (int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < fdate.size(); j++) {
                int vaccine;

                if (fdate.get(j)[3].equals("")) {
                    vaccine = 0;
                } else {
                    vaccine = Integer.parseInt(fdate.get(j)[3]);
                }
                if (Integer.parseInt(caseNum.get(1)[3]) > vaccine) {
                    caseNum.get(1)[3] = caseNum.get(1)[3];
                } else {
                    caseNum.get(1)[3] = Integer.toString(vaccine);
                }
                if (fdate.get(j)[0].equals(dateList.get(i)[0])) {
                    break;
                }
            }
            int firstVaccine = Integer.parseInt(caseNum.get(0)[3]);
            int lastVaccine = Integer.parseInt(caseNum.get(1)[3]);
            dateList.get(i)[3] = Integer.toString(lastVaccine - firstVaccine);
            caseNum.remove(0);
        }

        return dateList;
    }
}