package org.cgm.challange.service;

import org.cgm.challange.MainRunner;
import org.cgm.challange.util.Constant;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/20/24
 * Time: 6:13 PM
 */
@DisplayName("Add Question functionality testing")
class AddQuestionTest {

    private static MainRunner mainRunner;

    @BeforeAll
    public static void init(){
        mainRunner = new MainRunner();
    }

    /**
     * Test case for successful addition of a question and its answers.
     */
    @Test
    void addQuestion_Success() {
        String inputQuestion = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
        String exit = "3"; //exit input
        String expectedOutput = "Question and answers added successfully.";

        String userInput = String.format(inputQuestion+"%s"+exit+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));
        Assertions.assertEquals(outputString,expectedOutput);
    }
    /**
     * Test case for addition of a question with invalid format.
     */
    @Test
    void addQuestion_Failed_Invalid_Format() {
        //no question mark
        String inputQuestion = "What is Peters favorite food \"Pizza\" \"Spaghetti\" \"Ice cream\"";
        String exit = "3"; //exit input
        String expectedOutput = Constant.bulletSymbol+"Invalid input format.";

        String userInput = String.format(inputQuestion+"%s"+exit+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));
        Assertions.assertEquals(outputString,expectedOutput);
    }

    /**
     * Test case for addition of a question where the question length exceeds the maximum allowed.
     */
    @Test
    void addQuestion_Failed_Question_Length_Exceeded() {
        //no question mark
        String inputQuestion = "%s?\"Pizza\" \"Spaghetti\" \"Ice cream\"";
         inputQuestion = String.format(inputQuestion, getQuestionWith256Characters());
        String exit = "3"; //exit input
        String expectedOutput = Constant.bulletSymbol+"Question exceeds maximum length.";

        String userInput = String.format(inputQuestion+"%s"+exit+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));
        Assertions.assertEquals(outputString,expectedOutput);
    }

    /**
     * Test case for addition of a question where the length of one or more answers exceeds the maximum allowed.
     */
    @Test
    void addQuestion_Failed_Answer_Length_Exceeded() {
        //no question mark
        String inputQuestion = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"%s\"";
        inputQuestion = String.format(inputQuestion, getAnswerWith256Characters());
        String exit = "3"; //exit input
        String expectedOutput = Constant.bulletSymbol+"One or more answers exceeded maximum length.";
        String userInput = String.format(inputQuestion+"%s"+exit+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));
        Assertions.assertEquals(outputString,expectedOutput);
    }

    /**
     * Test case for addition of a question without any answers.
     */
    @Test
    void addQuestion_Failed_Should_Have_AtLeast_OneQuestion() {
        //no question mark
        String inputQuestion = "What is Peters favorite food? ";
        String exit = "3"; //exit input
        String expectedOutput = Constant.bulletSymbol+"Question must have at least one answer. Please try again.";
        String userInput = String.format(inputQuestion+"%s"+exit+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));
        Assertions.assertEquals(outputString,expectedOutput);
    }

    /**
     * Test case for successfully adding a question and replacing existing answers when the question already exists.
     */
    @Test
    void addQuestion_Success_Question_Already_Exists_And_Replaced() {
        String expectedOutput = "Question and answers added (replaced) successfully.";

        String inputQuestion = "How are your? \"im fine\" \"im doing well\"";
        String merge_input = "1"; //merge input

        String userInput = String.format(inputQuestion+"%s",
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        mainRunner.addQuestion(scanner);

        String inputQuestion2 = "How are your? \"not doing well\" \"going bad\"";

        String userInput2 = String.format(inputQuestion2+"%s"+merge_input+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput2.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));

        System.setIn(new ByteArrayInputStream("How are your?".getBytes()));
        String[] answerList = mainRunner.askQuestion(new Scanner(System.in));

        Assertions.assertEquals(outputString,expectedOutput);
        Assertions.assertArrayEquals(new String[]{"not doing well", "going bad"} , answerList);
    }

    /**
     * Test case for successfully adding a question and merging new answers with existing ones when the question already exists.
     */
    @Test
    void addQuestion_Success_Question_Already_Exists_And_Merged() {
        String expectedOutput = "Question and answers added (merged) successfully.";

        String inputQuestion = "What is your name? \"Gayan\" \"Sanjeewa\"";
        String merge_input = "2"; //merge input

        String userInput = String.format(inputQuestion+"%s",
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        mainRunner.addQuestion(scanner);

        String inputQuestion2 = "What is your name? \"testname1\" \"testname2\"";

        String userInput2 = String.format(inputQuestion2+"%s"+merge_input+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput2.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));

        System.setIn(new ByteArrayInputStream("What is your name?".getBytes()));

        String[] answerList = mainRunner.askQuestion(new Scanner(System.in));
        String[] finalAnswer = {"Gayan", "Sanjeewa", "testname1", "testname2"};
        String[] array = Arrays.stream(finalAnswer).map(String::toLowerCase).toArray(String[]::new);

        Assertions.assertEquals(outputString,expectedOutput);
        Assertions.assertArrayEquals(array,answerList);
    }

    /**
     * Test case for successfully discarding new answers when adding a question that already exists.
     */
    @Test
    void addQuestion_Success_Question_Already_Exists_And_Discard() {
        String expectedOutput = "Exiting...";

        String inputQuestion = "Tell me Something? \"Hi\" \"How are you\"";
        String discard_input = "3"; //merge input

        String userInput = String.format(inputQuestion+"%s",
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        mainRunner.addQuestion(scanner);

        String inputQuestion2 = "Tell me Something? \"watch some movies\" \"go on a trip\"";


        String userInput2 = String.format(inputQuestion2+"%s"+discard_input+"%s",
                System.lineSeparator(),
                System.lineSeparator()
        );
        System.setIn(new ByteArrayInputStream(userInput2.getBytes()));
        String outputString = mainRunner.addQuestion(new Scanner(System.in));

        System.setIn(new ByteArrayInputStream("Tell me Something?".getBytes()));

        String[] answerList = mainRunner.askQuestion(new Scanner(System.in));
        String[] finalAnswer = {"Hi", "How are you"};
        String[] array = Arrays.stream(finalAnswer).toArray(String[]::new);

        Assertions.assertEquals(outputString,expectedOutput);
        Assertions.assertArrayEquals(array,answerList);
    }

    private String getAnswerWith256Characters() {
        return getRandomString();
    }
    private  String getQuestionWith256Characters() {
        return getRandomString();
    }

    private String getRandomString(){
        String saltString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 256) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltString.length());
            salt.append(saltString.charAt(index));
        }
        return salt.toString();
    }

}