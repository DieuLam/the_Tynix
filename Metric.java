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
        //loop through number of groups
        for (int i = 0; i < cases.DataGroups.length; i++) {
            cases.DataGroups[i].metric = Metric[metric - 1];
            // loop through the data in group
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
        ArrayList<String[]> caseNum = getVaccinatedValue(GetTotalDates.getAllDates(cases), cases);
        cases.method = Method[type - 1];
        int UptoValue = 0;
        //loop through number of groups
        for (int i = 0; i < cases.DataGroups.length; i++) {
            cases.DataGroups[i].metric = Metric[metric - 1];
            while (true) {
                int fvalue;
                fvalue = Integer.parseInt(caseNum.get(0)[metric]);
                // check the data and asign new value
                if (UptoValue == 0) {
                    UptoValue = fvalue;
                    checkValue = caseNum.get(0)[0];
                    caseNum.remove(0);
                } else {
                    UptoValue += fvalue;
                    checkValue = caseNum.get(0)[0];
                    caseNum.remove(0);
                }
                //check the last date of group to break if it match the last date in file
                if (checkValue.equals(cases.DataGroups[i].totalDays[cases.DataGroups[i].totalDays.length - 1])) {
                    break;
                }
            }
            cases.DataGroups[i].value = UptoValue;
        }
    }

    public static ArrayList<String[]> getVaccinatedValue(ArrayList<String[]> dateList, Data Vcases)
            throws IOException, ParseException {
        ArrayList<String[]> fdate = GetTotalDates.getAllDates(Vcases);
        ArrayList<String[]> caseNum = new ArrayList<String[]>();
        
        for (String[] d : dateList) {
            caseNum.add(d.clone());
        }
                
        String[] day = new String[4];

        // loop to find the date before the start date
        for (int i = 0; i < fdate.size(); i++) {
            // check if we found start date and if there is a date before it
            if (fdate.get(i)[0].equals(caseNum.get(0)[0]) && !fdate.get(0)[0].equals(caseNum.get(0)[0])) {
                // assign the data of the date before start date to a variable
                day[3] = fdate.get(i - 1)[3];
                day[0] = fdate.get(i - 1)[0];
                break;
            } else {
                // assign "0" to the vaccinated column
                day[0] = caseNum.get(0)[0];
                day[3] = "0";
            }
        }
        // add the date before start date to the first position of the list
        caseNum.add(0, day);

        // loop through all the dates selected by users
        for (int i = 0; i < dateList.size(); i++) {
            // loop through all dates of the selected country
            for (int j = 0; j < fdate.size(); j++) {
                // get the vaccinated value of all dates in that country
                int vaccine = Integer.parseInt(fdate.get(j)[3]);
                // check if the vaccinated value of the first date in the list is larger than
                // vaccinated value of all dates in that country (exclude the date before start
                // date)
                if (Integer.parseInt(caseNum.get(1)[3]) > vaccine) {
                    // keep the current vaccinated value
                    caseNum.get(1)[3] = caseNum.get(1)[3];
                } else {
                    // assign the new vaccinated value to the first date
                    caseNum.get(1)[3] = Integer.toString(vaccine);
                }
                // check if we reach the first date of the list
                if (fdate.get(j)[0].equals(dateList.get(i)[0])) {
                    break;
                }
            }
            int firstVaccine = Integer.parseInt(caseNum.get(0)[3]);
            int lastVaccine = Integer.parseInt(caseNum.get(1)[3]);
            // calculate the number of people get vaccinated on that date
            dateList.get(i)[3] = Integer.toString(lastVaccine - firstVaccine);
            // remove the first date of the group
            caseNum.remove(0);
        }
        return dateList;
    }
}