import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        bot.start();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Hello! I am your chatbot. How can I help you today?");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                processCommand(input);
            }
        }
    }

    public void processCommand(String input) {
        if (input.startsWith("open ")) {
            String app = input.substring(5).toLowerCase();
            openApplication(app);
        } else if (input.startsWith("search ")) {
            String query = input.substring(7);
            searchWeb(query);
        } else {
            System.out.println("Sorry, I don't understand that command.");
        }
    }

    public void openApplication(String app) {
        try {
            switch (app) {
                case "notepad":
                    new ProcessBuilder("notepad").start();
                    break;
                case "calculator":
                    new ProcessBuilder("calc").start();
                    break;
                // Add more cases here for different applications
                default:
                    System.out.println("Application not supported.");
                    break;
            }
            // Add more cases here for different applications
                    } catch (IOException e) {
        }
    }

    public void searchWeb(String query) {
        try {
            String encodedQuery = query.replace(" ", "%20");
            URI uri = new URI("https://www.google.com/search?q=" + encodedQuery);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Web browsing is not supported on your system.");
            }
        } catch (IOException | URISyntaxException e) {
}
}
}
