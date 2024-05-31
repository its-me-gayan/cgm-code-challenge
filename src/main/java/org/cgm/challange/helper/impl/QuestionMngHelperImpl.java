package org.cgm.challange.helper.impl;

import org.cgm.challange.helper.QuestionMngHelper;
import org.cgm.challange.util.OutputMessages;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/20/24
 * Time: 5:52 PM
 */

/**
 * Helper class for managing questions and answers.
 */
public class QuestionMngHelperImpl implements QuestionMngHelper {


    /**
     * Reads the user's input option from the menu.
     *
     * @param scanner The Scanner object to read user input.
     * @return The user's selected option.
     */
    public int readOption(Scanner scanner) {
        System.out.print(OutputMessages.TYPE_INPUT);
        int option = scanner.hasNextInt() ? scanner.nextInt() : 0;
        scanner.nextLine(); // Consume newline
        return option;
    }

    /**
     * Prints the initial menu to the console.
     */
    public void printInitialMenu() {
        System.out.print(OutputMessages.BANNER);
        System.out.print(OutputMessages.TITLE);

    }

    /**
     * Removes white spaces and converts a raw question to a unique format.
     *
     * @param rawQuestion The raw question string.
     * @return The unique question string without white spaces.
     */
    public String getUniqueQuestionWithoutWhiteSpaces(String rawQuestion) {
        return Arrays.stream(rawQuestion.trim().split("\\s+"))
                .map(String::toUpperCase)
                .collect(Collectors.joining());
    }

    /**
     * Filters and extracts valid answers from a raw string of answers.
     *
     * @param rawAnswers The raw string of answers.
     * @return An array of valid answers.
     */
    public String[] filterValidAnswers(String rawAnswers) {
        return Pattern.compile("\"(.*?)\"")
                .matcher(rawAnswers.trim())
                .results()
                .map(match -> match.group(1))
                .toArray(String[]::new);
    }
}
