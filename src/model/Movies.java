package model;

import java.util.ArrayList;
import java.util.List;

public class Movies {

    private String titulo;
    private double price;
    private List<String> idiomasDisponiveis;

    public Movies(String titulo, double price, List<String> idiomasDisponiveis) {
        this.titulo = titulo;
        this.price = price;
        this.idiomasDisponiveis = idiomasDisponiveis;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getIdiomasDisponiveis() {
        return idiomasDisponiveis;
    }

    public static List<Movies> getAvailableMovies() {
        List<Movies> filmes = new ArrayList<>();

        filmes.add(new Movies("Harakiri", 25.00, List.of("Legendado")));
        filmes.add(new Movies("Sinners", 15.00, List.of("Dublado", "Legendado")));
        filmes.add(new Movies("Mirror", 25.00, List.of("Legendado")));
        filmes.add(new Movies("Dune: Part Two", 25.00, List.of("Dublado", "Legendado")));
        filmes.add(new Movies("The Boy and The Heron", 25.00, List.of("Dublado", "Legendado")));
        filmes.add(new Movies("Haru", 25.00, List.of("Legendado")));
        filmes.add(new Movies("The Apartment", 25.00, List.of("Legendado")));
        filmes.add(new Movies("Solanin", 25.00, List.of("Legendado")));
        filmes.add(new Movies("A Minecraft Movie", 25.00, List.of("Dublado", "Legendado")));
        filmes.add(new Movies("Flow", 25.00, List.of("Dublado", "Legendado")));

        return filmes;
    }
}