package org.cgm.challange.store;

/**
 * Author: Gayan Sanjeewa
 * User: gayan
 * Date: 5/31/24
 * Time: 8:24 PM
 */
public interface QuestionStore {
    public void merge(String key, String[] value);
    public void replaceOrSave(String key, String[] value);
    public String[] findAllAnswerByQuestion(String key);
}
