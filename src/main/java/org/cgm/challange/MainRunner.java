package org.cgm.challange;


import org.cgm.challange.helper.QuestionMngHelper;
import org.cgm.challange.helper.impl.QuestionMngHelperImpl;
import org.cgm.challange.service.QuestionMngService;
import org.cgm.challange.service.impl.QuestionMngServiceImpl;
import org.cgm.challange.util.Constant;
import org.cgm.challange.util.OutputMessages;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;


/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/20/24
 * Time: 10:02 AM
 */
public class MainRunner {

    private QuestionMngService questionMngService;
    private QuestionMngHelper questionMngHelper;

    public MainRunner() {
        this.questionMngService = new QuestionMngServiceImpl();
        this.questionMngHelper = new QuestionMngHelperImpl();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //starting the process
        new MainRunner().startProcess(scanner);
    }

    /**
     * Starts the process of handling user input.
     */
    public void startProcess(Scanner scanner) {

        // Continuous loop to keep the program running until the user chooses to exit
        questionMngHelper.printInitialMenu();
        while (true) {
            System.out.println(OutputMessages.INITAL_MENU_OPTION_STRING);

            int option = questionMngHelper.readOption(scanner);
            switch (option) {
                case 1:
                    Arrays.stream(askQuestion(scanner)).forEach(s -> out.println(Constant.bulletSymbol+s));
                    break;
                case 2:
                    out.println(addQuestion(scanner));
                    break;
                case 3:
                    System.out.println(OutputMessages.EXIT);
                    return;
                default:
                    System.out.println(OutputMessages.INVALID_OPTION_SELECTED);
            }
        }
    }

    public String[] askQuestion(Scanner scanner){
        out.println(OutputMessages.ENTER_QUESTION);
        String inputQuestion = scanner.nextLine();
        out.println(OutputMessages.ANSWER);
        return questionMngService.askQuestion(inputQuestion);
    }

    public String addQuestion(Scanner scanner){
        out.println(OutputMessages.ENTER_QUESTION_AND_ANSWER);
        String input = scanner.nextLine();
        return questionMngService.addQuestion(input, scanner);
    }

}
