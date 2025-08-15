package model;

public class TicketHalfPrice extends Ticket{

    public TicketHalfPrice(Movies filme, double price, String idioma) {
        super(filme, price, idioma);
    }

    @Override
    public double getPrice() {
        return price / 2;
    }
}