package org.cgm.challange.util;

import static org.cgm.challange.util.Constant.bulletSymbol;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/20/24
 * Time: 5:11 PM
 */
public record OutputMessages() {
    public static final String BANNER = "\n\n,------.                      ,--------.,--.                         ,--.       ,--.   \n" +
            "|  .-.  \\  ,---.  ,---.  ,---.'--.  .--'|  ,---.  ,---.,--.,--.,---. |  ,---. ,-'  '-. \n" +
            "|  |  \\  :| .-. :| .-. :| .-. |  |  |   |  .-.  || .-. |  ||  | .-. ||  .-.  |'-.  .-' \n" +
            "|  '--'  /\\   --.\\   --.| '-' '  |  |   |  | |  |' '-' '  ''  ' '-' '|  | |  |  |  |   \n" +
            "`-------'  `----' `----'|  |-'   `--'   `--' `--' `---' `----'.`-  / `--' `--'  `--'   \n" +
            "                        `--'                                  `---'                 \n";

    public static final String TITLE = "A Command line interface to simulate the DeepThought supercomputer :) :) \n\n";
    public static final String INVALID_OPTION_SELECTED = "\nInvalid option. Please choose again.";
    public static final String EXIT = "Exiting...";
    public static final String DISCARD = "Exiting...";
    public static final String TYPE_INPUT = "Type your input here : ";
    public static final String INITAL_MENU_OPTION_STRING = "\nChoose an option:\n1. Ask a question\n2. Add a question and its answers\n3. Exit";
    public static final String SECOND_MENU_OPTION_STRING = "\nPlease choose an option\n1. Replace\n2. Merge\n3. Discard";
    public static final String ENTER_QUESTION = "\nEnter your question : ";
    public static final String ANSWER = "Answers will be : ";
    public static final String DEFAULT_ANSWER_TO_LIFE = "the answer to life, universe and everything is 42";
    public static final String ENTER_QUESTION_AND_ANSWER = "\nEnter the question and its answers (separated by '?' and enclosed in double quotes \"\") : ";
    public static final String INVALID_INPUT = bulletSymbol+"Invalid input format.";
    public static final String INVALID_INPUT_NO_BULL = "Invalid input format.";
    public static final String QUESTION_MAX_SIZE_EXCEEDED = bulletSymbol+"Question exceeds maximum length.";
    public static final String ANSWER_MAX_SIZE_EXCEEDED = bulletSymbol+"One or more answers exceeded maximum length.";
    public static final String AT_LEAST_ONE_ANSWER = bulletSymbol+"Question must have at least one answer. Please try again.";
    public static final String QUESTION_ALREADY_EXISTS = "\nSame question already exists with following answers";
    public static final String ADDED_SUCCESSFULLY = "Question and answers added successfully.";
    public static final String MERGED_SUCCESSFULLY = "Question and answers added (merged) successfully.";
    public static final String REPLACED_SUCCESSFULLY = "Question and answers added (replaced) successfully.";

}
