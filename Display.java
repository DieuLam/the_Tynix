import java.util.ArrayList;

public class Display {
    public static void tabularDisplay(Data data) {
        System.out.printf("%s %35s", "Range", "Value\n");
        System.out.println();
        // loop through each group
        for (int i = 0; i < data.DataGroups.length; i++) {
            String startDate = data.DataGroups[i].totalDays[0];

            String endDate = data.DataGroups[i].totalDays[data.DataGroups[i].totalDays.length - 1];
  
            int value = data.DataGroups[i].value;
            // create a "date" string to store start date and end date
            String date = startDate + " - " + endDate;

            String space = "";
            
            // create a loop to adjust the space between 2 columns 
            for (int j = 0; j < 35 - date.length(); j++) {
                space += " ";
            }
            System.out.println(date + space + value);
        } 

        String country = data.country;
        String method = data.method;
        String startDate = data.DataGroups[0].totalDays[0];
        String endDate = data.DataGroups[0].totalDays[data.DataGroups[0].totalDays.length - 1];

        System.out.println();

        // summarize the data entered by user

        System.out.println("Overview:");
        System.out.println("Country: " + country);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("Method: " + method);
    }
    
    public static int[] createData(Data date) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        // loop through each group 
        for (int i = 0; i < date.DataGroups.length; i++) {
            // add value to array
            data.add(date.DataGroups[i].value);
        }
        // convert arrayList to array
        int[] passData = new int[data.size()];
        // loop through arrayList
        for (int j = 0; j < data.size(); j++) {
            passData[j] = data.get(j);
        }
        return passData;
    }

    public static int[] getMaxMin(int[] data) {
        // get max / min value
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
            if (data[i] < min) {
                min = data[i];
            }
        }
        int[] max_min = {max, min};
        return max_min;
    }

    public static String[][] addToChart(String[][] chart, int[] data) {
        if (data.length <= 79) {
            // calculate horizontal scale
            int groupPoint = (int) Math.floor(79 / data.length); // round down
            // calculate vertical scale
            int max = getMaxMin(data)[0];
            int min = getMaxMin(data)[1];
            int x_scale = (int) Math.ceil((max - min) / 23.0); // round up

            // loop in data length
            for (int i = 0; i < data.length; i++) {
                int chartHeight = 22; // reset after each value is plotted
                // loop row
                for (int row = 0; row < chart.length - 1; row++) {
                    int dataPoint = (x_scale * chartHeight) + min;   // value different between each y-point
                    if (dataPoint - data[i] <= 0) {  // match y-coordinate
                        // loop column
                        for (int col = 1; col < chart[row].length - 1; col++) {
                            if (col - 1 == i * groupPoint) {  // match x-coordinate
                                chart[row][col] = "*";
                            }
                        }
                        break;
                    }   
                    chartHeight -= 1;  // next line          
                }
            }
        } else {
            System.out.println("Data exceeded chart limit");
        }
        return chart;
    }

    public static void displayChart(int[] data) {
        // ------------------------------------------------- Create empty chart -----------------
        String[][] chart = new String[24][80];
        // fill up the chart with space
        for (int row = 0; row < chart.length; row++) {
            for (int col = 0; col < chart[row].length; col++) {
                chart[row][col] = " ";
                chart[chart.length - 1][col] = "_";  // add horizontal border
            }
            chart[row][0] = "|";   // add vertical border
        }
        
        addToChart(chart, data);

        // --------------------------------------------------- Display chart ---------------------
        System.out.println();
        for (int row = 0; row < chart.length; row++) {
            for (int col = 0; col < chart[row].length; col++) {
                System.out.print(chart[row][col]);
            }
            System.out.println();
        } 
    }
}