import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static List<Movies> filmes = Movies.getAvailableMovies();
    public static List<Ticket> ticketsPurchased = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Ticket ingresso;
        int option;

        do {
            showMenu();
            exibirDivisor();
            option = getValidOption(sc, 0, 4);

            switch (option) {
                case 1 -> {
                    exibirDivisor();
                    listarFilmes(filmes);
                    exibirDivisor();
                }
                case 2 -> {
                    ingresso = buyTicket(sc);
                    if (ingresso != null) {
                        System.out.println(ingresso);
                        ticketsPurchased.add(ingresso);
                    }
                    exibirDivisor();
                }
                case 3 -> {
                    if (ticketsPurchased.isEmpty()) {
                        exibirDivisor();
                        System.out.println("Nenhum ingresso foi comprado");
                        exibirDivisor();
                    }
                    else {
                        exibirDivisor();
                        System.out.println("===========INGRESSOS COMPRADOS===========");
                        exibirDivisor();
                        for (Ticket ticket : ticketsPurchased) {
                            System.out.println(ticket);
                            exibirDivisor();
                        }
                    }
                }
                case 4 -> {
                    if (ticketsPurchased.isEmpty()) {
                        exibirDivisor();
                        System.out.println("Nenhum ingresso foi comprado");
                        exibirDivisor();
                        break;
                    }

                    exibirDivisor();
                    System.out.println("===========INGRESSOS COMPRADOS===========");
                    exibirDivisor();
                    for (Ticket ticket : ticketsPurchased) {
                        System.out.println(ticket);
                        exibirDivisor();
                    }

                    System.out.println("Para reembolsar um ingresso, informe o ID dele.");
                    System.out.println("Caso queira cancelar a ação digite: '0'.");

                    Ticket ticketToRemove = null;

                    while (true) {
                        System.out.print("Digite o ID do ingresso: ");
                        String asnwer = sc.nextLine().trim();

                        if (asnwer.equals("0")) {
                            System.out.println("Reembolso cancelado.");
                            break;
                        }

                        try {
                            int id = Integer.parseInt(asnwer);
                            boolean found = false;

                            for (Ticket ticket : ticketsPurchased) {
                                if (ticket.getId() == id) {
                                    ticketToRemove = ticket;
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                ticketsPurchased.remove(ticketToRemove);
                                exibirDivisor();
                                System.out.println("Ingresso reembolsado com sucesso!");
                                exibirDivisor();
                                break;
                            }
                            else {
                                System.out.println("ID não encontrado. Tente novamente ou digite 0 para cancelar.");
                            }
                        }catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, digite um ID válido.");
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Encerrando...Até a próxima!! xD");
                    exibirDivisor();
                    return;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente");
                    exibirDivisor();
                }
            }
        } while (true);
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

    public static void exibirDivisor() {
        System.out.println("------------------------------------------------");
    }

    public static void listarFilmes(List<Movies> filmes) {
        System.out.println("=======Filmes Disponíveis=======");
        for (int i = 0; i < filmes.size(); i++) {
            System.out.printf("%d. %s%n", i+1, filmes.get(i).getTitulo());
        }
    }

    public static void showTicketMenu() {
        System.out.println("Escolha o tipo de ingresso:");
        System.out.println("1 - Inteira");
        System.out.println("2 - Meia Entrada");
        System.out.println("3 - Ingresso Familia");
    }

    public static Ticket buyTicket(Scanner sc) {

        exibirDivisor();
        listarFilmes(filmes);
        exibirDivisor();
        int movieOption = getValidOption(sc, 1, filmes.size());
        Movies selectedMovies = filmes.get(movieOption - 1);

        exibirDivisor();
        List<String> idiomas = getLanguageAvailable(selectedMovies);
        exibirDivisor();
        int pickIdioma = getValidOption(sc, 1, idiomas.size());
        String selectedIdioma = idiomas.get(pickIdioma - 1);

        exibirDivisor();
        showTicketMenu();
        exibirDivisor();
        int whichTicket = getValidOption(sc, 1,3);

        switch (whichTicket) {
            case 1 -> {
                double priceFull = selectedMovies.getPrice();

                System.out.println("Inteira selecionada.");
                exibirDivisor();
                System.out.println("====DETALHES DA COMPRA====");
                System.out.println("\nFilme: " + selectedMovies.getTitulo());
                System.out.println("Idioma: " + selectedIdioma);
                System.out.printf("Preço: R$ %.2f%n%n", priceFull);

                if (confirmPurchase(sc)) {
                    System.out.println("Compra efetuada com sucesso!!");
                    exibirDivisor();
                    return new Ticket(selectedMovies, selectedMovies.getPrice(), selectedIdioma);
                }
                else {
                    System.out.println("Compra cancelada.");
                    return null;
                }
            }
            case 2 -> {
                Ticket ingresso = new Ticket(selectedMovies, selectedMovies.getPrice(), selectedIdioma);

                System.out.println("Meia-Entrada selecionada.");
                exibirDivisor();
                System.out.println("====DETALHES DA COMPRA====");
                System.out.println("\nFilme: " + selectedMovies.getTitulo());
                System.out.println("Idioma: " + selectedIdioma);
                System.out.printf("Preço: R$ %.2f%n%n", ingresso.getPrice());

                if (confirmPurchase(sc)) {
                    System.out.println("Compra efetuada com sucesso!!");
                    exibirDivisor();
                    return new TicketHalfPrice(selectedMovies, selectedMovies.getPrice(), selectedIdioma);
                }
                else {
                    System.out.println("Compra cancelada.");
                    return null;
                }
            }
            case 3 -> {
                exibirDivisor();
                System.out.println("Promocional selecionada.");
                System.out.println("OBS: Valor promocional disponível apenas para 3 ou mais ingressos.");
                exibirDivisor();
                int quantidade;
                Ticket ingresso;
                while (true) {
                    try {
                        System.out.print("Digite a quantidade de ingressos: ");
                        quantidade = Integer.parseInt(sc.nextLine());
                        exibirDivisor();

                        if (quantidade < 1) {
                            System.out.println("Valor inválido. O número de ingressos precisa ser de pelo menos 1.");
                            exibirDivisor();
                        }
                        else {
                            ingresso = new TicketFamily(selectedMovies, selectedMovies.getPrice(), selectedIdioma, quantidade);
                            break;
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Opção inválida. Apenas números são permitidos.");
                        exibirDivisor();
                    }
                }

                System.out.println("\n====DETALHES DA COMPRA====");
                System.out.println("\nFilme: " + selectedMovies.getTitulo());
                System.out.println("Idioma: " + selectedIdioma);
                System.out.println("Quantidade de ingressos: " + quantidade);
                if (quantidade >= 3) {
                    System.out.printf("Preço: R$ %.2f (Desconto aplicado.)%n%n", ingresso.getPrice());
                }
                else {
                System.out.printf("Preço: R$ %.2f (Desconto não aplicado.)%n%n", ingresso.getPrice());
                }

                if (confirmPurchase(sc)) {
                    System.out.println("Compra efetuada com sucesso!!");
                    exibirDivisor();
                    return new TicketFamily(selectedMovies, selectedMovies.getPrice(), selectedIdioma, quantidade);
                }
                else {
                    System.out.println("Compra cancelada.");
                    return null;
                }
            }
            default -> {
                exibirDivisor();
                System.out.println("Opção inválida. Tente novamente.");
                exibirDivisor();
                return null;
            }
        }
    }

    private static boolean confirmPurchase(Scanner sc) {
        String answer;
        do {
            System.out.print("Deseja confirmar a compra? (S/N): ");
            answer = sc.nextLine().trim().toUpperCase();
            exibirDivisor();

            if (answer.equals("S") || answer.equals("SIM")) return true;
            if (answer.equals("N") || answer.equals("NÃO")) return false;

            System.out.println("Entrada inválida. Digite 'S' para sim ou 'N' para não");
            exibirDivisor();
        }while (true);
    }

    private static List<String> getLanguageAvailable(Movies selectedMovies) {
        List<String> idiomas = selectedMovies.getIdiomasDisponiveis();
        System.out.println("====IDIOMAS DISPONÍVEIS====");
        for (int i = 0; i < idiomas.size(); i++) {
            System.out.printf("(%d) %s%n", i + 1, idiomas.get(i));
        }
        return idiomas;
    }

    private static int getValidOption(Scanner sc, int min, int max) {
        int option;
        while (true) {
            try {
                System.out.print("Selecione uma das opções: ");
                option = Integer.parseInt(sc.nextLine());
                if (option >= min && option <= max) {
                    break;
                }
                else {
                    System.out.println("Opção inválida. Digite um número entre " + min + " e " + max + ".");
                    exibirDivisor();
                }
            }catch (NumberFormatException e) {
                System.out.println("Opção inválida. Apenas números são permitidos.");
                exibirDivisor();
            }
        }
        return option;
    }
}