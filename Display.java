public class Display {

    // -------------------------------------------------------------- TABULAR DISPLAY -----------------

    public static void tabularDisplay(Data data) {
        System.out.printf("\n%s", "------------------------------------------------");
        System.out.printf("\n%s %20s %15s", "Range", "|", "Value\n");
        System.out.printf("%s", "------------------------------------------------");
        System.out.println();
        // loop through each group
        for (int i = 0; i < data.DataGroups.length; i++) {
            String startDate = data.DataGroups[i].totalDays[0];
            String endDate = data.DataGroups[i].totalDays[data.DataGroups[i].totalDays.length - 1];
            int value = data.DataGroups[i].value;

            // create a "date" string to store start date and end date
            String date = startDate + " - " + endDate;
            String space = "";

            // for no grouping
            if (startDate == endDate) {
                for (int j = 1; j < 24 - endDate.length(); j++) {
                    space += " ";
                }
                System.out.println(String.format("%s %s %s %15s", endDate, space, "|", value));
            // for grouping
            } else {
                // adjust the space between 2 columns
                for (int j = 0; j <= 22 - date.length(); j++) {
                    space += " ";
                }
                System.out.println(String.format("%s %s %s %15s", date, space, "|", value));
            }
        }
    }
    
    // ---------------------------------------------------------------- CHART DISPLAY -----------------

    public static int[] createData(Data date) {
        // put each group value into array
        int[] passData = new int[date.DataGroups.length];
        // loop through arrayList
        for (int i = 0; i < date.DataGroups.length; i++) {
            passData[i] = date.DataGroups[i].value;
        }
        return passData;
    }

    public static int[] getMaxMin(int[] data) {

        // get max / min value
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] >= max) {
                max = data[i];
            } else if (data[i] <= min) {
                min = data[i];
            }
        }
        int[] max_min = { max, min };
        return max_min;
    }

    public static String[][] addToChart(String[][] chart, int[] data) {

        // check data length
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
                    int dataPoint = (x_scale * chartHeight) + min; // value different between each y-point
                    if (dataPoint - data[i] <= 0) { // match y-coordinate
                        // loop column
                        for (int col = 1; col < chart[row].length - 1; col++) {
                            if (col - 1 == i * groupPoint) { // match x-coordinate
                                chart[row][col] = "*";
                            }
                        }
                        break;
                    }
                    chartHeight -= 1; // next line
                }
            }
        } else {
            System.out.println("\nData exceeded chart limit\n");
            System.exit(0);
        }
        return chart;
    }

    public static void displayChart(int[] data) {

        // ------------------------------------------------- Create empty chart
        // -----------------
        String[][] chart = new String[24][80];
        // fill up the chart with space
        for (int row = 0; row < chart.length; row++) {
            for (int col = 0; col < chart[row].length; col++) {
                chart[row][col] = " ";
                chart[chart.length - 1][col] = "_"; // add horizontal border
            }
            chart[row][0] = "|"; // add vertical border
        }

        // --------------------------------------------------- Plot the chart
        // --------------------
        addToChart(chart, data);

        // --------------------------------------------------- Display chart
        // ---------------------
        System.out.println();
        for (int row = 0; row < chart.length; row++) {
            for (int col = 0; col < chart[row].length; col++) {
                System.out.print(chart[row][col]);
            }
            System.out.println();
        }
    }

    // ---------------------------------------------------------------- OVERVIEW DISPLAY --------------

    public static void overviewDisplay(Data data) {
        // summarize the data entered by user
        System.out.println("Overview:\n");
        System.out.println("Country: " + data.country);
        System.out.println("Start date: " + data.startDate);
        System.out.println("End date: " + data.endDate);
        System.out.println("Method: " + data.method);
    }
}