import java.util.Scanner;

public class Temperature {
    public static void main(String[] args) {
        
        /* 
         * 1. Fahrenheit to Celsius OR Kelvin
         * 2. Celsius to Fahrenheit OR Kelvin
         * 3. Kelvin to Fahrenheit OR Celsius
        */

        // Create our input scanner first
        Scanner scan = new Scanner(System.in);

        // Start our program 
        System.out.println("[+] ----- Temperature Converter ----- [+]\n");

        System.out.println("1. Fahrenheit to Celsius/Kelvin");
        System.out.println("2. Celsius to Fahrenheit/Kelvin");
        System.out.println("3. Kelvin to Fahrenheit/Celsius");
        System.out.print("\nEnter your choice (1-3): ");
        int choice = scan.nextInt();

        // Switch case because why not :D
        switch (choice) {
            case 1: {
               
                System.out.println("\n[+] ----- Fahrenheit to Celsius/Kelvin ----- [+]\n");
                
                System.out.print("Enter a Fahrenheit temperature: ");
                double ftemp = scan.nextDouble();

                // To Celsius: °C = (°F - 32) × 5/9
                double ctemp = (ftemp - 32) * 5.0/9.0;

                // To Kelvin: °K = (°F - 32) × 5/9 + 273.15
                double ktemp = (ftemp - 32) * 5.0/9.0 + 273.15;

                System.out.println("\nCelsius: °" + ctemp);
                System.out.println("Kelvin °" + ktemp);         
                
                break;
            }

            case 2: {

                System.out.println("\n[+] ----- Celsius to Fahrenheit/Kelvin ----- [+]\n");
                    
                System.out.print("Enter a Celsius temperature: ");
                double ctemp = scan.nextDouble();

                // To Farehnheit: °F = °C × (9/5) + 32
                double ftemp = ctemp * (9.0/5.0) + 32;

                // To Kelvin: °K = (°F - 32) × 5/9 + 273.15
                double ktemp = ctemp + 273.15;

                System.out.println("\nFahrenheit: °" + ftemp);
                System.out.println("Kelvin °" + ktemp);

                break;
            }

            case 3: {

                System.out.println("\n[+] ----- Kelvin to Fahrenheit/Celsius ----- [+]\n");
                    
                System.out.print("Enter a Kelvin temperature: ");
                double ktemp = scan.nextDouble();

                // To Farehnheit: °F = (°K − 273.15) × 1.8 + 32
                double ftemp = (ktemp - 273.15) * 1.8 + 32;

                // To Celsius: °C = °K - 273.15
                double ctemp = ktemp - 273.15;

                System.out.println("\nFahrenheit: °" + ftemp);
                System.out.println("Celsius °" + ctemp);

                break;
            }

            default:
                System.out.println("Invalid choice!");
                break;
        }
        scan.close();
    }
}
