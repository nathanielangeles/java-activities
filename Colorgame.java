import java.util.Scanner;
import java.lang.Math;

public class Colorgame {
    public static void main(String[] args) {

        // Input
        Scanner input = new Scanner(System.in);

        // Variables
        String userColor1;
        String userColor2;
        String userColor3;

        int userBet;
        int userWinnings = 0;

        // All 6 colors
        String[] colors = {"red", "green", "blue", "yellow", "white", "pink"};

        // Start of color game
        System.out.println("----- Color Game -----");

        // Player's input
        // Color 1
        System.out.print("Color 1: ");
        userColor1 = input.nextLine().trim().toLowerCase();

        // Color 2
        System.out.print("Color 2: ");
        userColor2 = input.nextLine().trim().toLowerCase();

        // Color 3
        System.out.print("Color 3: ");
        userColor3 = input.nextLine().trim().toLowerCase();

        // Bet (loop until the user input is correct)
        do {
            System.out.print("Bet (0-20): ");
            userBet = input.nextInt();
        } while (userBet < 0 || userBet > 20);

        // Random color calls
        int color1 = (int)(Math.random() * colors.length);
        int color2 = (int)(Math.random() * colors.length);
        int color3 = (int)(Math.random() * colors.length);

        // Colors to match
        System.out.println("\n----- Colors -----");
        System.out.println("[*] Color 1: " + colors[color1]);
        System.out.println("[*] Color 2: " + colors[color2]);
        System.out.println("[*] Color 3: " + colors[color3]);

        System.out.println("\n----- Result -----");

        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    if (userColor1.equals(colors[color1]) || 
                        userColor1.equals(colors[color2]) || 
                        userColor1.equals(colors[color3])) {
                        userWinnings += userBet * 2;
                        System.out.println("Color 1 Match! You won: " + (userBet * 2));
                    } else {
                        System.out.println("Color 1 did not match!");
                    }
                    break;
                case 1:
                    if (userColor2.equals(colors[color1]) || 
                        userColor2.equals(colors[color2]) || 
                        userColor2.equals(colors[color3])) {
                        userWinnings += userBet * 2;
                        System.out.println("Color 2 Match! You won: " + (userBet * 2));
                    } else {
                        System.out.println("Color 2 did not match!");
                    }
                    break;
                case 2:
                    if (userColor3.equals(colors[color1]) || 
                        userColor3.equals(colors[color2]) || 
                        userColor3.equals(colors[color3])) {
                        userWinnings += userBet * 2;
                        System.out.println("Color 3 Match! You won: " + (userBet * 2));
                    } else {
                        System.out.println("Color 3 did not match!");
                    }
                    break;
                default:
                    System.out.println("None of the above.");
            }
        }
    }
}
