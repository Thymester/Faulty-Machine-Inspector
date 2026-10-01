import UI.ConsoleUI;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FaultyMachine {
    private static final String CURRENT_VERSION = "0.3.0";
    private static final String RELEASES_URL =
            "https://api.github.com/repos/Thymester/Faulty-Machine-Inspector/releases/latest";
    private static final String RELEASE_PAGE =
            "https://github.com/Thymester/Faulty-Machine-Inspector/releases/latest";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        checkForNewRelease(scanner);

        ConsoleUI.mainMenu(scanner, CURRENT_VERSION);
    }

    private static void checkForNewRelease(Scanner scanner) {
        try {
                HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(RELEASES_URL))
                    .timeout(Duration.ofSeconds(5))
                    .header("Accept", "application/vnd.github+json")
                    .header("User-Agent", "Faulty-Machine-Inspector")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                return;
            }

            String latestVersion = extractTagName(response.body());

            if (latestVersion == null) {
                return;
            }

            latestVersion = latestVersion.replaceFirst("^v", "");

            if (isNewerVersion(latestVersion, CURRENT_VERSION)) {
                System.out.println("A new release is available: v" + latestVersion);
                System.out.println("1. Go to New Release");
                System.out.println("2. Skip Release");

                while (true) {
                    System.out.print("Select an option: ");
                    String choice = scanner.nextLine().trim();

                    if (choice.equals("1")) {
                        openReleasePage();
                        break;
                    }

                    if (choice.equals("2")) {
                        break;
                    }

                    System.out.println("Please enter 1 or 2.");
                }
            }
        } catch (IOException exception) {
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private static void openReleasePage() {
        try {
            if (Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(URI.create(RELEASE_PAGE));
                return;
            }
        } catch (IOException exception) {
            // Fall through and print the URL when the browser cannot be opened.
        }

        System.out.println("Open this page in your browser:");
        System.out.println(RELEASE_PAGE);
    }

    private static String extractTagName(String json) {
        Matcher matcher = Pattern.compile("\"tag_name\"\\s*:\\s*\"([^\"]+)\"")
                .matcher(json);

        return matcher.find() ? matcher.group(1) : null;
    }

    private static boolean isNewerVersion(String latest, String current) {
        String[] latestParts = latest.split("\\.");
        String[] currentParts = current.split("\\.");

        if (latestParts.length != 3 || currentParts.length != 3) {
            return false;
        }

        try {
            for (int i = 0; i < 3; i++) {
                int latestNumber = Integer.parseInt(latestParts[i]);
                int currentNumber = Integer.parseInt(currentParts[i]);

                if (latestNumber > currentNumber) {
                    return true;
                }

                if (latestNumber < currentNumber) {
                    return false;
                }
            }
        } catch (NumberFormatException exception) {
            return false;
        }

        return false;
    }
}