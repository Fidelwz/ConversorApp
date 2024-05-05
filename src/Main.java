import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Scanner scanner = new Scanner(System.in);


    // Método para solicitar entrada al usuario
    private static String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    // Método para validar entrada de tipo double
    public static double promptDouble(String message) {
        while (true) {
            String input = prompt(message);
            // Convertimos y verificamos si el valor es un double
            try {
                double result = Double.parseDouble(input);
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido");
            }
        }
    }

    // Método para validar entrada de tipo int
    public static int promptInt(String message) {
        while (true) {
            String input = prompt(message);
            // Convertimos y verificamos si el valor es un int
            try {
                int result = Integer.parseInt(input);
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido");
            }
        }
    }

    // Método para cerrar el scanner
    public static void closeScanner() {
        scanner.close();
    }


    private static void showMenu() {
        System.out.println("************************************************************");
        System.out.println("Bienvenido/a al conversor de moneda");
        System.out.println("Conversiones básicas:");
        System.out.println("1) Dólar a Peso Mexicano");
        System.out.println("2) Peso Mexicano a Dólar");
        System.out.println("3) Dólar a Peso Colombiano");
        System.out.println("4) Dólar a Euro");
        System.out.println("5) Conversión personalizada");
        System.out.println("6) Salir");
        System.out.println("************************************************************");
        System.out.println("\nElija una opción:");
    }




    public static void main(String[] args) throws IOException, InterruptedException {

        Conversor conversor = new Conversor();
        int selection;
        while (true) {
            showMenu();

            selection = promptInt("Ingrese un número entero:");

            switch (selection) {
                case 1:
                    convertCurrency("USD", "MXN", scanner);
                    break;
                case 2:
                    convertCurrency("MXN", "USD", scanner);
                    break;
                case 3:
                    convertCurrency("USD", "COP", scanner);
                    break;
                case 4:
                    convertCurrency("USD", "EUR", scanner);
                    break;
                case 5:
                    customConversion(scanner);
                    break;
                case 6:
                    System.out.println("Gracias por usar el conversor de moneda. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

        }

    }



    private static void customConversion(Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Ingrese el código ISO de la moneda base:");
        String baseCurrency = scanner.next();

        System.out.println("Ingrese el código ISO de la moneda objetivo:");
        String targetCurrency = scanner.next();

        System.out.println("Ingrese el monto en " + baseCurrency + ":");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consumir el carácter de nueva línea pendiente

        Conversor.getData(baseCurrency, targetCurrency, amount);
    }


    // Método para manejar las conversiones de moneda básicas
    private static void convertCurrency(String baseCurrency, String targetCurrency, Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Ingrese el monto en " + baseCurrency + ":");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consumir el carácter de nueva línea pendiente

        Conversor.getData(baseCurrency, targetCurrency, amount);
    }



}