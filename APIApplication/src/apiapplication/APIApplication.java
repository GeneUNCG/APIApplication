/**
 * Gene Pressinger
 * CSC340-01
 * Project #2
 * API Application
 * https://www.tronalddump.io/
 */
package apiapplication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APIApplication {

    private static final String URI = "https://www.tronalddump.io/";

    // API Tags
    private static final String RETRIEVE_TAGS = "tag";
    private static final String RETRIEVE_RANDOM_MEME = "random/meme";
    private static final String RETRIEVE_RANDOM_QUOTE = "random/meme";

    public static void main(String[] args) throws MalformedURLException, IOException {
        // Welcome user
        System.out.println("Welcome to TronaldDump, where you can search for funny things the 45th president has said.");

        Scanner scanner = new Scanner(System.in);

        int input;
        String sInput;

        while (true) {
            printMenu();
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    String topics = getQuoteTopics();
                    System.out.println(topics);
                    break;
                case 2:
                    sInput = scanner.nextLine();

                    sInput = getValidTopicBasedOnInput(sInput);

                    if (sInput == null) {
                        System.out.println("There are no quotes based on the topic you have entered.");
                    } else {
                        String quote = getQuoteByTopic(sInput);
                        System.out.println(quote);
                    }
                    break;
                case 3:
                    String randomQuote = getRandomQuote();
                    System.out.println("Random Meme Link: " + randomQuote);
                    break;
                case 4:
                    String randomMeme = getRandomMeme();
                    System.out.println("Random Meme Link: " + randomMeme);
                    break;
                case 5:
                    // Exit
                    System.out.println("Thank you for using tronaldDump! Please come back again.");
                    return;
                default:
                    System.out.println("Invalid input please enter a number listed on the menu.");
                    break;
            }

        }
    }

    private static String getRandomMeme() throws MalformedURLException, IOException {
        URL url = new URL(URI + RETRIEVE_RANDOM_MEME);
        Scanner scanner = new Scanner(url.openStream());
        return scanner.nextLine();
    }

    private static String getRandomQuote() throws MalformedURLException, IOException {
        URL url = new URL(URI + RETRIEVE_RANDOM_QUOTE);
        Scanner scanner = new Scanner(url.openStream());
        return scanner.nextLine();
    }

    private static String getQuoteTopics() {
        return null;
    }

    private static String getQuoteByTopic(String topic) {
        return null;
    }

    private static String getValidTopicBasedOnInput(String input) {
        return null;
    }

    private static void printMenu() {
        System.out.println("[1] List quote topics.");
        System.out.println("[2] Search for quote by topic.");
        System.out.println("[3] Get random quote.");
        System.out.println("[4] Get random meme.");
        System.out.println("[5] Exit application.");
    }
}
