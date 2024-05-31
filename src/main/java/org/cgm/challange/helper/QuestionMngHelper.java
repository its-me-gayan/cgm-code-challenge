package org.cgm.challange.helper;

import org.cgm.challange.util.OutputMessages;

import java.util.Scanner;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 5/31/24
 * Time: 8:22 PM
 */
public interface QuestionMngHelper {
    public int readOption(Scanner scanner);
    public void printInitialMenu();
    public String getUniqueQuestionWithoutWhiteSpaces(String rawQuestion);
    public String[] filterValidAnswers(String rawAnswers);

}
