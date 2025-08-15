package util;

import java.util.Scanner;

public class InputHelper {

    public static int getValidOption(Scanner sc, int min, int max) {
        int option;
        while (true) {
            exibirDivisor();
            System.out.print("Selecione uma das opções: ");
            String input = sc.nextLine();
            exibirDivisor();

            try {
                option = Integer.parseInt(input);
                if (option >= min && option <= max) break;
                System.out.println("Opção inválida. Digite um número entre " + min + " e " + max + ".");
            }catch (NumberFormatException e) {
                System.out.println("Opção inválida. Apenas números são permitidos.");
            }
        }
        return option;
    }

    public static boolean confirmPurchase(Scanner sc) {
        while(true) {
            System.out.print("Deseja confirmar a compra? (S/N): ");
            String answer = sc.nextLine().trim().toUpperCase();

            if (answer.equals("S") || answer.equals("SIM")) return true;
            if (answer.equals("N") || answer.equals("NÃO") || answer.equals("NAO")) return false;

            System.out.println("Entrada inválida. Digite 'S' para sim ou 'N' para não");
        }
    }

    public static void exibirDivisor() {
        System.out.println("------------------------------------------------");
    }

    public static void showMenu() {
        System.out.print("""
                ==== Bem-vindo(a) ao Mini Cinema ====
                1 - Ver lista de filmes disponíveis
                2 - Comprar ingressos
                3 - Ver ingressos comprados
                4 - Reembolsar ingressos
                0 - Sair
                """);
    }
}