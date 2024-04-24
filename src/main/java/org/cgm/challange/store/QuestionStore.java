package org.cgm.challange.store;

import java.util.*;
import java.util.stream.Stream;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 4/20/24
 * Time: 1:58 PM
 */
public class QuestionStore {
    // Static map to store questions and their corresponding answers
    private static final Map<String, String[]> questionStoreMap = new HashMap<>();
    // Singleton instance of QuestionStore
    private static QuestionStore questionStore;

    // Private constructor to enforce Singleton pattern
    private QuestionStore() {
    }

    // Singleton getInstance method to get an instance of QuestionStore
    public static QuestionStore getInstance(){
        return Objects.isNull(questionStore) ? new QuestionStore() : questionStore;
    }

    // Merge method to merge new answers with existing ones for a given question
    public void merge(String key, String[] value){
        // Concatenate new answers with existing ones, remove duplicates, and sort
        String[] finalAnswerList = Stream.concat(
                        Arrays.stream(value),
                        Arrays.stream(questionStoreMap.getOrDefault(key, new String[]{}))
                )
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .toArray(String[]::new);

        // Print each answer in the merged list
        Arrays.stream(finalAnswerList).forEach(System.out::println);
        // Update the map with the merged answer list
        questionStoreMap.put(key , finalAnswerList);
    }

    // Method to replace existing answers or save new ones for a given question
    public void replaceOrSave(String key, String[] value){
        questionStoreMap.put(key , value);
    }

    // Method to find all answers for a given question
    public String[] findAllAnswerByQuestion(String key){
        return questionStoreMap.getOrDefault(key, new String[]{});
    }

    // Method to print all questions and their corresponding answers
    public void printAll(){
        questionStoreMap.forEach((s, strings) -> System.out.println(s +" - " + Arrays.toString(strings)));
    }
}
