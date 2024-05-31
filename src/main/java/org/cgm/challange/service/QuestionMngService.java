package org.cgm.challange.service;

import java.util.Scanner;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 5/31/24
 * Time: 8:17 PM
 */
public interface QuestionMngService {
    public String[] askQuestion(String question);

    public String addQuestion(String input, Scanner scanner);
}
