package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ticket {

    private static final Set<Integer> usedIds = new HashSet<>();
    private static final Random random = new Random();

    private final int id;
    protected Movies filme;
    protected double price;
    protected String idioma;

    public Ticket(Movies filme, double price, String idioma) {
        this.filme = filme;
        this.price = price;
        this.idioma = idioma;

        int newId;
        do {
            newId = 1000 + random.nextInt(9000);
        }while (usedIds.contains(newId));

        this.id = newId;
        usedIds.add(newId);
    }

    public int getId() {
        return id;
    }

    public Movies getFilme() {
        return filme;
    }

    public String getIdioma() {
        return idioma;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID do Ingresso: "
                + id
                + "\nFilme: "
                + filme.getTitulo()
                + "\nIdioma: "
                + idioma
                + String.format("\nPreço: R$ %.2f", getPrice());
    }
}