public class ConsoleHelper {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void printInline(String message) {
        System.out.print(message);
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printHeader(String title) {
        System.out.println("\n=== " + title.toUpperCase() + " ===");
    }
}