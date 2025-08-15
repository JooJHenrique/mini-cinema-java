package main;

import service.CinemaService;
import util.InputHelper;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        CinemaService cine = new CinemaService();

        int option;
        do {
            InputHelper.showMenu();
            option = InputHelper.getValidOption(sc, 0, 4);

            switch (option) {
                case 1 -> cine.listarFilmes();
                case 2 -> cine.buyTickets(sc);
                case 3 -> cine.listarPurchasedTickets();
                case 4 -> cine.ticketRefund(sc);
                case 0 -> System.out.println("Encerrando... Até a próxima!! xD");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }
}