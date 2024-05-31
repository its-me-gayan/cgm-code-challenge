package org.cgm.challange.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.cgm.challange.helper.QuestionMngHelper;
import org.cgm.challange.helper.impl.QuestionMngHelperImpl;
import org.cgm.challange.service.QuestionMngService;
import org.cgm.challange.store.QuestionStore;
import org.cgm.challange.store.impl.QuestionStoreImpl;
import org.cgm.challange.util.OutputMessages;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;
import static org.cgm.challange.util.Constant.MAX_ANSWER_LENGTH;
import static org.cgm.challange.util.Constant.MAX_QUESTION_LENGTH;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/20/24
 * Time: 5:50 PM
 */

/**
 * Service class for managing questions and answers.
 */
public class QuestionMngServiceImpl implements QuestionMngService {

    private final QuestionMngHelper questionMngHelper;
    private final QuestionStore questionStore;

    public QuestionMngServiceImpl() {
        this.questionMngHelper = new QuestionMngHelperImpl();
        this.questionStore = new QuestionStoreImpl();
    }

    /**
     * Method to ask a question and display its answers.
     *
     * @param question The user input question string.
     */
    public String[] askQuestion(String question) {
        String trimQuestion = question.trim();
        String lastCharacter = trimQuestion.substring(trimQuestion.length() - 1);
        if (!StringUtils.isEmpty(lastCharacter) && lastCharacter.equalsIgnoreCase("?")) {
            String inputQuestion = trimQuestion.replace("?", "");
            String uniqueQuestionWithoutWhiteSpaces = questionMngHelper.getUniqueQuestionWithoutWhiteSpaces(inputQuestion);
            String[] answerList = questionStore.findAllAnswerByQuestion(uniqueQuestionWithoutWhiteSpaces);
            return answerList.length < 1 ? new String[]{OutputMessages.DEFAULT_ANSWER_TO_LIFE} : answerList;
        } else {
            return new String[]{OutputMessages.INVALID_INPUT_NO_BULL};
        }

    }

    /**
     * Method to add a new question and its answers.
     *
     * @param input The Scanner object to read user input.
     */
    public String addQuestion(String input, Scanner scanner) {
        String[] parts = input.split("\\?");
        if (parts.length != 2) {
            return OutputMessages.INVALID_INPUT;
        }

        String question = parts[0].trim();
        if (question.length() > MAX_QUESTION_LENGTH) {
            return OutputMessages.QUESTION_MAX_SIZE_EXCEEDED;
        }

        String[] answers = questionMngHelper.filterValidAnswers(parts[1]);
        if (answers.length < 1 || Arrays.stream(answers).anyMatch(answer -> answer.length() > MAX_ANSWER_LENGTH)) {
            return answers.length < 1 ? OutputMessages.AT_LEAST_ONE_ANSWER : OutputMessages.ANSWER_MAX_SIZE_EXCEEDED;
        }

        String uniqueQuestion = questionMngHelper.getUniqueQuestionWithoutWhiteSpaces(question);
        String[] allAnswerByQuestion = questionStore.findAllAnswerByQuestion(uniqueQuestion);
        if (allAnswerByQuestion.length > 0) {
            out.println(OutputMessages.QUESTION_ALREADY_EXISTS);
            out.println(Arrays.asList(allAnswerByQuestion));
            return duplicateQuestionSection(uniqueQuestion, answers, scanner);

        } else {
            questionStore.replaceOrSave(uniqueQuestion, answers);
            return OutputMessages.ADDED_SUCCESSFULLY;
        }
    }

    /**
     * Method to handle duplicate question scenarios.
     *
     * @param uniqueQuestion The unique question string without white spaces.
     * @param answers        The answers to the question.
     */
    private String duplicateQuestionSection(String uniqueQuestion, String[] answers, Scanner scanner) {
        out.println(OutputMessages.SECOND_MENU_OPTION_STRING);
        int option = questionMngHelper.readOption(scanner);
        return switch (option) {
            case 1 -> {
                questionStore.replaceOrSave(uniqueQuestion, answers);
                yield OutputMessages.REPLACED_SUCCESSFULLY;
            }
            case 2 -> {
                questionStore.merge(uniqueQuestion, answers);
                yield OutputMessages.MERGED_SUCCESSFULLY;
            }
            case 3 -> OutputMessages.DISCARD;
            default -> duplicateQuestionSection(uniqueQuestion, answers, scanner);
        };
    }

}
