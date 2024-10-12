import java.util.Scanner;

public class Circle {
    public static void main(String[] args) {

        /* Find the area and the circumference of a circle
         * Radius (input)
         * Area (output)
         * Circumference (output)
        */

        // Input scanner
        Scanner scan = new Scanner(System.in);

        // Radius
        System.out.print("Enter radius: ");
        double r = scan.nextDouble();

        // Area: A = pi * r * r
        double a = Math.PI * r * r;

        // Circumference: C = 2 * pi * r
        double c = 2 * Math.PI * r;

        // Result
        System.out.println("\nArea: " + a);
        System.out.println("Circumference: " + c);

        scan.close();

    }
}
