/**
 * Gene Pressinger
 * CSC340-01
 * Project #2
 * API Application
 * https://www.tronalddump.io/
 *
 * Using open source JSON parser: https://github.com/stleary/JSON-java
 * All JSON parsing credit goes to the contributors of JSON-java.
 */
package apiapplication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class APIApplication {

    // Main API Link
    private static final String URI = "https://www.tronalddump.io/";

    // API Tags
    private static final String RETRIEVE_TAG = "tag/";
    private static final String RETRIEVE_RANDOM_QUOTE = "random/quote";
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        // Welcome user
        System.out.println("Welcome to TronaldDump, where you can search for funny quotes the 45th president has said.");

        Scanner scanner = new Scanner(System.in);

        int input;

        while (true) {
            printMenu();
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    String[] topics = getQuoteTopics();

                    System.out.println();
                    System.out.println("Quote Topics:");

                    for (String topic : topics) {
                        System.out.println(topic);
                    }

                    System.out.println();
                    break;
                case 2:
                    String quoteLink = getQuoteURLByTopic();

                    if (quoteLink == null) {
                        System.out.println("There are no quotes based on the topic you have entered.");
                    } else {
                        System.out.println();
                        System.out.println("Go to this webpage to view the quotes: " + quoteLink);
                        System.out.println();
                    }
                    break;
                case 3:
                    String randomQuote = getRandomQuote();

                    System.out.println();
                    System.out.println("Donald Trump: \"" + randomQuote + "\"");
                    System.out.println();
                    break;
                case 4:
                    // Exit
                    System.out.println("Thank you for using tronaldDump! Please come back again.");
                    return;
                default:
                    System.out.println("Invalid input please enter a number listed on the menu.");
                    break;
            }

        }
    }

    /**
     * Name: getRandomQuote(): String
     * Description: Retrieves a random quote from the API, and parses it as text
     *              into the output console.
     */
    private static String getRandomQuote() throws MalformedURLException, IOException {
        URL url = new URL(URI + RETRIEVE_RANDOM_QUOTE);
        Scanner scanner = new Scanner(url.openStream());
        String quoteJSON = scanner.nextLine();

        JSONObject json = new JSONObject(quoteJSON);
        String quote = json.getString("value");

        return quote;
    }

    /**
     * Name:        getQuoteTopics(): String[]
     * Description: Retrieves the entire list of quote topics. Parses through each
     *              and are all saved into a String array.
     */
    private static String[] getQuoteTopics() throws MalformedURLException, IOException {
        URL url = new URL(URI + RETRIEVE_TAG);
        Scanner scanner = new Scanner(url.openStream());
        String quoteJSON = scanner.nextLine();

        JSONObject json = new JSONObject(quoteJSON);
        json = json.getJSONObject("_embedded");

        JSONArray arr = json.getJSONArray("tag");

        String[] quoteTopics = new String[arr.length()];

        for (int i = 0; i < arr.length(); i++) {
            quoteTopics[i] = arr.getJSONObject(i).getString("value");
        }
        return quoteTopics;
    }

    /**
     * Name:        getQuoteByTopic(): String
     * Description: Prompts the user to enter a quote topic they wish to
     *              search for. The user's topic validity is then checked
     *              using getValidTopic(). Returns a valid URL containing
     *              quotes based on the topic.
     */
    private static String getQuoteURLByTopic() throws MalformedURLException, IOException {
        System.out.println("Enter a topic that you would like to search for.");
        Scanner scanner = new Scanner(System.in);
        String topic = scanner.nextLine();

        // Make sure topic is valid
        topic = getValidTopic(topic);

        if (topic != null) {
            topic = topic.replace(" ", "%20");
            return URI + RETRIEVE_TAG + topic;
        }

        return null;
    }

    /**
     * Name:        getValidTopic(): String
     * Description: Filters through topics and matches the user's input
     *              with an "official topics." Returns a valid topic,
     *              if one does not exist, null is returned.
     */
    private static String getValidTopic(String topic) throws IOException {
        String[] topics = getQuoteTopics();

        for (String sTopic : topics) {
            if (sTopic.equalsIgnoreCase(topic) || sTopic.toLowerCase().contains(topic.toLowerCase())) {
                return sTopic;
            }
        }
        return null;
    }

    /**
     * Name:        printMenu(): void
     * Description: Prints the menu containing user options.
     */
    private static void printMenu() {
        System.out.println("###############################");
        System.out.println("[1] List quote topics.");
        System.out.println("[2] Search for quotes by topic.");
        System.out.println("[3] Get random quote.");
        System.out.println("[4] Exit application.");
        System.out.println("###############################");
    }
}
