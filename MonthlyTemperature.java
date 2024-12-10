import java.lang.Math;

public class MonthlyTemperature {
    public static void main(String[] args) {
        // Initializer
        int i;

        // Random temperature generator from 10° C - 40° Celsius
        int minTemp = 10;
        int maxTemp = 40;
        int rangeTemp = maxTemp - minTemp + 1;

        // Days of the month
        int[] daysOfTheMonth = new int[30];


        // Track the highest and lowest temperatures
        int highestTemp = Integer.MIN_VALUE;
        int lowestTemp = Integer.MAX_VALUE;
        int highestDayTemp = 0;
        int lowestDayTemp = 0;

        for (i = 0; i < daysOfTheMonth.length; i++) {
            daysOfTheMonth[i] = (int)(Math.random() * rangeTemp) + minTemp;
            System.out.println("Day " + (i+1) + ": " + daysOfTheMonth[i] + "°C");

            // Check for the highest recorded temperature
            if (daysOfTheMonth[i] > highestTemp) {
                highestTemp = daysOfTheMonth[i];
                highestDayTemp = i + 1;
            }
        
            // Check for the lowest recorder temperature
            if (daysOfTheMonth[i] < lowestTemp) {
                lowestTemp = daysOfTheMonth[i];
                lowestDayTemp = i + 1;
            }
        }
        
        // Obtain the average temperature
        int totalTemp = 0;

        for (i = 0; i < daysOfTheMonth.length; i++) {
            totalTemp += daysOfTheMonth[i];
        }

        // Formula to obtain the average temperature
        int avgTemp = totalTemp / daysOfTheMonth.length;

        // Display the average, highest, and lowest temperatures
        System.out.println("\nAverage Temperature for the month is: " + avgTemp + "°C");
        System.out.println("Highest Temperature is: " + highestTemp + "°C on Day " + highestDayTemp);
        System.out.println("Lowest Temperature is: " + lowestTemp + "°C on Day " + lowestDayTemp);
    }
}
