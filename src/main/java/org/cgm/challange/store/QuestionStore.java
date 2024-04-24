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
    private static final Map<String, String[]> questionStoreMap = new HashMap<>();
   private static QuestionStore questionStore;
    private QuestionStore() {
    }

    public static QuestionStore getInstance(){
        return Objects.isNull(questionStore) ? new QuestionStore():questionStore;
    }

    public void merge(String key, String[] value){

        String[] finalAnswerList = Stream
                .concat(
                        Arrays.stream(value),
                        Arrays.stream(questionStoreMap.getOrDefault(key, new String[]{}))
                )
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .sorted()
                .toArray(String[]::new);
        Arrays.stream(finalAnswerList).forEach(s -> System.out.println(s));
        questionStoreMap.put(key , finalAnswerList);

    }
    public  void replaceOrSave(String key, String[] value){
        questionStoreMap.put(key , value);
    }

    public  String[] findAllAnswerByQuestion(String key){
       return questionStoreMap.getOrDefault(key,new String[]{});
    }
    public void printAll(){
        questionStoreMap.forEach((s, strings) -> System.out.println(s +" - " + Arrays.toString(strings)));
    }

}
