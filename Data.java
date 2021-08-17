import java.io.*;
import java.text.*;
import java.util.*;

// create an object
class Data {
    String country;
    String startDate;
    String endDate;
    
    Data() {

    }

    // create a constructor
    Data(String country, String startDate, String endDate) {
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

class dateOption {
    public static Data option_1(Data date) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        
        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.print("End date (MM/dd/yyyy): ");
        String endDate = input.nextLine();
        input.close();

        // assign to object
        date.startDate = startDate;
        date.endDate = endDate;
        return date;
    }

    public static Data option_2(Data date) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);

        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.printf("How many days from %s: ", startDate);
        int days = Integer.parseInt(input.nextLine());
        input.close();   

        // calculate end date;   
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(startDate)); // convert from String to Date
        c.add(Calendar.DATE, days); // add days
        Date newDate = c.getTime(); 
        String endDate = formatter.format(newDate); // covert back to string

         // assign to object
         date.startDate = startDate;
         date.endDate = endDate;
         return date;
    }

    public static Data option_3(Data date) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);

        // get date
        System.out.print("\nStart date (MM/dd/yyyy): ");
        String startDate = input.nextLine();
        System.out.printf("How many weeks from %s: ", startDate);
        int weeks = Integer.parseInt(input.nextLine());
        input.close();   

        // calculate end date; 
        int days = weeks * 7;   
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(startDate)); // convert from String to Date
        c.add(Calendar.DATE, days); // add days
        Date newDate = c.getTime();
        String endDate = formatter.format(newDate);  // covert back to string

        // assign to object
        date.startDate = startDate;
        date.endDate = endDate;
        return date;
    }
}