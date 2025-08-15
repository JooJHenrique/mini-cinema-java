package service;

import model.Movies;
import model.Ticket;
import model.TicketFamily;
import model.TicketHalfPrice;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

public class TicketService {

    public static Ticket buyTicket(Scanner sc, List<Movies> filmes) {

        System.out.println("======= Escolha um filme =======");
        for (int i = 0; i < filmes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, filmes.get(i).getTitulo());
        }
        int movieOption = InputHelper.getValidOption(sc, 1, filmes.size());
        Movies selectedMovie = filmes.get(movieOption - 1);

        System.out.println("======= Idiomas disponíveis =======");
        List<String> idiomas = selectedMovie.getIdiomasDisponiveis();
        for (int i = 0; i < idiomas.size(); i++) {
            System.out.printf("(%d) %s%n", i + 1, idiomas.get(i));
        }
        int idiomaOption = InputHelper.getValidOption(sc, 1, idiomas.size());
        String selectedIdioma = idiomas.get(idiomaOption - 1);

        System.out.println("======= Tipo de ingresso =======");
        System.out.println("1 - Inteira");
        System.out.println("2 - Meia Entrada");
        System.out.println("3 - Ingresso Família");
        int whichTicket = InputHelper.getValidOption(sc, 1,3);

        Ticket ingresso;
        switch (whichTicket) {
            case 1 -> ingresso = new Ticket(selectedMovie, selectedMovie.getPrice(), selectedIdioma);
            case 2 -> ingresso = new TicketHalfPrice(selectedMovie, selectedMovie.getPrice(), selectedIdioma);
            case 3 -> {
                int quantidade;
                while (true) {
                    System.out.print("Digite a quantidade de ingressos: ");
                    try {
                        quantidade = Integer.parseInt(sc.nextLine());
                        InputHelper.exibirDivisor();
                        if (quantidade < 1) {
                            System.out.println("Quantidade mínima é 1.");
                        } else break;
                    } catch (NumberFormatException e) {
                        System.out.println("Apenas números são permitidos.");
                    }
                }
                ingresso = new TicketFamily(selectedMovie, selectedMovie.getPrice(), selectedIdioma, quantidade);
            }
            default -> {
                System.out.println("Opção inválida");
                return null;
            }
        }

        showTicketDetails(ingresso);
        InputHelper.exibirDivisor();

        if (InputHelper.confirmPurchase(sc)) {
            return ingresso;
        }
        else {
            return null;
        }
    }

    public static void refundTicket(Scanner sc, List<Ticket> ticketsPurchased) {
        if (ticketsPurchased.isEmpty()) {
            System.out.println("Nenhum ingresso para reembolsar.");
            InputHelper.exibirDivisor();
            return;
        }

        System.out.println("======= Ingressos Comprados =======");
        for (Ticket pt : ticketsPurchased) System.out.println(pt);

        Ticket ticketToRemove = null;
        while (true) {
            InputHelper.exibirDivisor();
            System.out.print("Digite o ID do ingresso para reembolso (0 para cancelar): ");
            String input = sc.nextLine();
            InputHelper.exibirDivisor();

            if (input.equals("0")) {
                System.out.println("Reembolso cancelado.");
                InputHelper.exibirDivisor();
                return;
            }
            try {
                int id = Integer.parseInt(input);
                boolean found = false;
                for (Ticket pt : ticketsPurchased) {
                    if (pt.getId() == id) {
                        ticketToRemove = pt;
                        found = true;
                        break;
                    }
                }
                if (found) {
                    ticketsPurchased.remove(ticketToRemove);
                    System.out.println("Ingresso reembolsado com sucesso!!");
                    InputHelper.exibirDivisor();
                    return;
                }
                else {
                    System.out.println("ID não encontrado. Tente novamente");
                }
            }catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Apenas números são permitidos.");
            }
        }
    }

    public static void showTicketDetails(Ticket ticket) {
        System.out.println("==== DETALHES DO INGRESSO ====");
        System.out.println("Filme: " + ticket.getFilme().getTitulo());
        System.out.println("Idioma: " + ticket.getIdioma());
        if (ticket instanceof TicketFamily familyTicket) {
            System.out.println("Quantidade: " + familyTicket.getQuantity());
        }
        if (ticket instanceof TicketFamily familyTicket) {
            String discountMsg = (familyTicket.getQuantity() >= 3) ? "(Desconto Aplicado)" : "(Sem Desconto)";
            System.out.printf("Preço: R$ %.2f %s%n", ticket.getPrice(), discountMsg);
        } else {
            System.out.printf("Preço: R$ %.2f%n", ticket.getPrice());
        }
    }
}