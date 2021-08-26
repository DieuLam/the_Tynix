import java.io.*;
import java.text.*;
import java.util.*;

//-------------------------------------- OBJECT -------------------------------------------

class Data {
    String country;
    String startDate;
    String endDate;
    String method;
    Group[] DataGroups;

    public String toString() {
        return String.format("Country: %s \nStart date: %s \nEnd date: %s \nMethod: %s  \nGroups: \n%s", country, startDate, endDate, method, Arrays.toString(DataGroups));
    }
}

class Group {
    String[] totalDays;
    String metric;
    int value;

    Group(String[] totalDays) {
        this.totalDays = totalDays;
    }

    public String toString() {
        return String.format("Dates: %s \nMetric: %s \nValue: %d\n", Arrays.toString(totalDays), metric, value);
    }
}

//---------------------------------------------------------------------------------------

class dateOption {
    public static Data option_1(String country, Data date) throws IOException, ParseException {

        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = Main.input.nextLine();
        System.out.print("\nEnd date (MM/dd/yyyy): ");
        String endDate = Main.input.nextLine();

        // valid and assign date to object
        assignDate(date, country, startDate, endDate);
        return date;
    }

    public static Data option_2(String country, Data date) throws IOException, ParseException {

        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = Main.input.nextLine();
        System.out.printf("\nHow many days from %s: ", startDate);
        int days = Integer.parseInt(Main.input.nextLine());
   
        // calculate end date;
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(startDate)); // convert from String to Date
        c.add(Calendar.DATE, days); // add days
        Date newDate = c.getTime();
        String endDate = formatter.format(newDate); // covert back to string

        // valid and assign date to object
        assignDate(date, country, startDate, endDate);
        return date;
    }

    public static Data option_3(String country, Data date) throws IOException, ParseException {
        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = Main.input.nextLine();
        System.out.printf("\nHow many weeks from %s: ", startDate);
        int weeks = Integer.parseInt(Main.input.nextLine());

        // calculate end date;
        int days = weeks * 7;
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(startDate)); // convert from String to Date
        c.add(Calendar.DATE, days); // add days
        Date newDate = c.getTime();
        String endDate = formatter.format(newDate); // covert back to string

        // valid and assign date to object
        assignDate(date, country, startDate, endDate);
        return date;
    }

    public static Data assignDate(Data date, String country, String startDate, String endDate) throws IOException {
        // valid date and country in the file
        if (errorHandler.checkDate(country, startDate, endDate) == true) {
            // assign to object
            date.startDate = errorHandler.convertDate(startDate);
            date.endDate = errorHandler.convertDate(endDate);
        } else {
            System.out.println("\nWe can't find the date you are looking for");
            System.exit(0);
        }
        return date;
    }
}