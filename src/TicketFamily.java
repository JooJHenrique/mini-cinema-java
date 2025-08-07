public class TicketFamily extends Ticket{

    private int quantity;
    private static final double DISCOUNT = 0.05;

    public TicketFamily(Movies filme, double price, String idioma, int quantity) {
        super(filme, price, idioma);
        this.quantity = quantity;
    }

    @Override
    public double getPrice() {
        double total = price * quantity;
        if (quantity >= 3) {
            return total * (1 - DISCOUNT);
        }
        return total;
    }

    @Override
    public String toString() {
        String discountMsg = (quantity >= 3) ? "(Desconto aplicado)" : "";
        return "ID do ingresso: "
                + getId()
                + "\nFilme: "
                + filme.getTitulo()
                + "\nIdioma: "
                + idioma
                + "\nQuantidade de Ingressos: "
                + quantity
                + String.format("\nPreço: R$ %.2f %s", getPrice(), discountMsg);
    }
}