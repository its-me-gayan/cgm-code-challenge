package org.cgm.challange.service;

import org.cgm.challange.MainRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/24/24
 * Time: 1:02 PM
 */
@DisplayName("Ask Question functionality testing")
class AskQuestionTest {

    /**
     * Test case for successfully asking a question and receiving answers.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void askQuestion_Success() throws IOException {

        String inputQuestion = "You wanna go somewhere? \"yes\" \"exactly!\"";
        String[] answerArray = Pattern.compile("\"(.*?)\"")
                .matcher(inputQuestion.split("\\?")[1].trim())
                .results()
                .map(match -> match.group(1))
                .toArray(String[]::new);
        String expectedOutput = "Question and answers added successfully.";

        String userInput = String.format(inputQuestion+"%s",
                System.lineSeparator()
        );
        setInput(userInput);
        String outputString = MainRunner.addQuestion(new Scanner(System.in));
        String questionString = "You wanna go somewhere?"; //question

        String userInput2 = String.format(questionString+"%s",
                System.lineSeparator()
        );
        setInput(userInput2);
        String[] strings = MainRunner.askQuestion(new Scanner(System.in));

        Assertions.assertEquals(outputString , expectedOutput);
        Assertions.assertEquals(answerArray.length , strings.length);
        Assertions.assertArrayEquals(answerArray , strings);
    }

    /**
     * Test case for failing to match the question with any specific answer, returning a universal answer.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void askQuestion_Failed_No_Matching_Answer_Universal_Answer() throws IOException {
        String questionString = "What are you doing?"; //question

        String expectedAnswer = "the answer to life, universe and everything is 42";

        String userInput = String.format(questionString+"%s",
                System.lineSeparator()
        );
        setInput(userInput);
        String[] answerList = MainRunner.askQuestion(new Scanner(System.in));

        Assertions.assertEquals(1 , answerList.length);
        Assertions.assertArrayEquals(answerList , new String[]{expectedAnswer});
    }

    /**
     * Test case for failing due to an invalid question format.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void askQuestion_Failed_Invalid_Format() throws IOException {
        String questionString = "What are you doing "; //without question mark

        String expectedAnswer = "Invalid input format.";

        String userInput = String.format(questionString+"%s",
                System.lineSeparator()
        );
        setInput(userInput);
        String[] answerList = MainRunner.askQuestion(new Scanner(System.in));

        Assertions.assertEquals(1 , answerList.length);
        Assertions.assertArrayEquals(answerList , new String[]{expectedAnswer});
    }

    /**
     * Helper method to set user input for testing.
     * @param userInput the user input string to be simulated.
     */
    private void setInput(String userInput) {
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
    }
}
