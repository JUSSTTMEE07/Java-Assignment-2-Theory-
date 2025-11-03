import java.util.InputMismatchException;
import java.util.Scanner;


class Calculator {

    // add two ints
    public int add(int a, int b) {
        return a + b;
    }

    // add two doubles
    public double add(double a, double b) {
        return a + b;
    }

    // add three ints
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // subtract two ints
    public int subtract(int a, int b) {
        return a - b;
    }

    // multiply two doubles
    public double multiply(double a, double b) {
        return a * b;
    }

    // divide two ints - throws ArithmeticException for divide by zero
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        // return double result to show fractional results when needed
        return (double) a / b;
    }
}

/**
 * UserInterface class: handles input, menu, and user interaction.
 * Uses Scanner for console input and validates entries.
 */
public class CalculatorApp {

    private final Scanner scanner;
    private final Calculator calc;

    public CalculatorApp() {
        scanner = new Scanner(System.in);
        calc = new Calculator();
    }

    // Entry point
    public static void main(String[] args) {
        CalculatorApp ui = new CalculatorApp();
        ui.mainMenu();
    }

    // Main menu loop
    public void mainMenu() {
        while (true) {
            System.out.println("\nWelcome to the Calculator Application!");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();
            switch (choice) {
                case 1 -> performAddition();
                case 2 -> performSubtraction();
                case 3 -> performMultiplication();
                case 4 -> performDivision();
                case 5 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    // Addition handler: chooses among overloaded add methods
    private void performAddition() {
        System.out.println("\nAddition options:");
        System.out.println("1) Add two integers");
        System.out.println("2) Add two doubles");
        System.out.println("3) Add three integers");
        System.out.print("Choose option: ");
        int opt = readIntInput();
        try {
            switch (opt) {
                case 1 -> {
                    System.out.print("Enter first integer: ");
                    int a = readIntInput();
                    System.out.print("Enter second integer: ");
                    int b = readIntInput();
                    System.out.println("Result: " + calc.add(a, b));
                }
                case 2 -> {
                    System.out.print("Enter first double: ");
                    double a = readDoubleInput();
                    System.out.print("Enter second double: ");
                    double b = readDoubleInput();
                    System.out.println("Result: " + calc.add(a, b));
                }
                case 3 -> {
                    System.out.print("Enter first integer: ");
                    int a = readIntInput();
                    System.out.print("Enter second integer: ");
                    int b = readIntInput();
                    System.out.print("Enter third integer: ");
                    int c = readIntInput();
                    System.out.println("Result: " + calc.add(a, b, c));
                }
                default -> System.out.println("Invalid addition option.");
            }
        } catch (InputMismatchException ime) {
            System.out.println("Invalid number entered. Returning to main menu.");
            scanner.nextLine(); // clear buffer
        }
    }

    // Subtraction handler
    private void performSubtraction() {
        try {
            System.out.print("\nEnter first integer: ");
            int a = readIntInput();
            System.out.print("Enter second integer: ");
            int b = readIntInput();
            System.out.println("Result: " + calc.subtract(a, b));
        } catch (InputMismatchException ime) {
            System.out.println("Invalid integer input. Returning to main menu.");
            scanner.nextLine();
        }
    }

    // Multiplication handler (doubles)
    private void performMultiplication() {
        try {
            System.out.print("\nEnter first double: ");
            double a = readDoubleInput();
            System.out.print("Enter second double: ");
            double b = readDoubleInput();
            System.out.println("Result: " + calc.multiply(a, b));
        } catch (InputMismatchException ime) {
            System.out.println("Invalid double input. Returning to main menu.");
            scanner.nextLine();
        }
    }

    // Division handler with exception handling
    private void performDivision() {
        try {
            System.out.print("\nEnter numerator (integer): ");
            int a = readIntInput();
            System.out.print("Enter denominator (integer): ");
            int b = readIntInput();
            double result = calc.divide(a, b); // may throw ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException ae) {
            System.out.println("Error: " + ae.getMessage());
        } catch (InputMismatchException ime) {
            System.out.println("Invalid integer input. Returning to main menu.");
            scanner.nextLine();
        }
    }

    // Utility: read integer with validation loop
    private int readIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("That's not a valid integer. Try again: ");
            scanner.next(); // discard invalid token
        }
        return scanner.nextInt();
    }

    // Utility: read double with validation loop
    private double readDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("That's not a valid number. Try again: ");
            scanner.next(); // discard invalid token
        }
        return scanner.nextDouble();
    }
}
