package service;

import model.Movies;
import model.Ticket;
import util.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaService {

    private final List<Movies> filmes;
    private final List<Ticket> ticketsPurchased;

    public CinemaService() {
        this.filmes = Movies.getAvailableMovies();
        this.ticketsPurchased = new ArrayList<>();
    }

    public List<Movies> getFilmes() {
        return filmes;
    }

    public List<Ticket> getTicketsPurchased() {
        return ticketsPurchased;
    }

    public void listarFilmes() {
        System.out.println("=======Filmes Disponíveis=======");
        for (int i = 0; i < filmes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, filmes.get(i).getTitulo());
        }
        InputHelper.exibirDivisor();
    }

    public void listarPurchasedTickets() {
        if (ticketsPurchased.isEmpty()) {
            System.out.println("Nenhum ingresso foi comprado");
            InputHelper.exibirDivisor();
            return;
        }
        System.out.println("=========== INGRESSOS COMPRADOS ===========");
        for (Ticket ticket : ticketsPurchased) {
            System.out.println(ticket);
            InputHelper.exibirDivisor();
        }
    }

    public void buyTickets(Scanner sc) {
        Ticket ingresso = TicketService.buyTicket(sc, filmes);
        if (ingresso != null) {
            ticketsPurchased.add(ingresso);
            System.out.println("Compra efetuada com sucesso!!");
            InputHelper.exibirDivisor();
        }
        else {
            System.out.println("Compra cancelada.");
            InputHelper.exibirDivisor();
        }
    }

    public void ticketRefund(Scanner sc) {
        TicketService.refundTicket(sc, ticketsPurchased);
    }
}