package org.jacoco.examples.java.gradle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public final class Solver {
    /**
     * @param args the command line arguments
     */
    private Trie trie;
    private ArrayList<String> sourceText;
    private ArrayList<String> fullyConcWords;
    private ArrayList<String> notFullyConcWords;
    private ArrayList<String> nonConcWords;
    private String path;
    
    public ArrayList<String> getFullyConcWords() {
        return fullyConcWords;
    }
    
    public int getTotalCountConcWords(){
        return getFullyConcWords().size();
    }

    public ArrayList<String> getNotFullyConcWords() {
        return notFullyConcWords;
    }

    public ArrayList<String> getNonConcWords() {
        return nonConcWords;
    }

    public void setPath(String _filePath){
        if (new File(_filePath).isFile()) path = _filePath;
    }

    public Solver(String path){
        this();
        setPath(path);
    }
    
    public Solver() {
        trie = new Trie();
        sourceText = new ArrayList<>();
        fullyConcWords = new ArrayList<>();
        notFullyConcWords = new ArrayList<>();
        nonConcWords = new ArrayList<>();
    }
    
    private void getAllWordsFromFile() throws IOException{
        if(!path.isEmpty()){
        Stream<String> stream = Files.lines(Paths.get(path));
            stream.forEach(word -> {sourceText.add(word);
                trie.insertWord(word);
            });
        }
        else System.err.println("Empty path");
    }

    public void wordsSort() throws IOException {
        getAllWordsFromFile();
        for (String word : sourceText) {
            if (isFullyConcatenated(word, true)) {
                fullyConcWords.add(word);
                continue;
            }
            if (isPartlyConcatenated(word)) {
                notFullyConcWords.add(word);
                continue;
            }
            nonConcWords.add(word);
        }
    }

    private boolean isFullyConcatenated(String word, boolean isFullWord) {
        String pref;
        String suff;
        for (int i = 0; i < word.length(); i++) {
            pref = word.substring(0, i + 1);
            suff = word.substring(i + 1, word.length());
            if (trie.getWordNode(pref) != null && (
                        (!isFullWord && suff.isEmpty()) 
                        || trie.getWordNode(suff) != null 
                        || (!suff.isEmpty() && isFullyConcatenated(suff, false))))
                    return true;
        }
        return false;
    }

    private boolean isPartlyConcatenated(String word) {
        String pref;
        String suff;
        for (int i = 0; i < word.length(); i++) {
            pref = word.substring(0, i + 1);
            suff = word.substring(i + 1, word.length());
            if (pref.isEmpty() || suff.isEmpty()) break;
            if (trie.getWordNode(pref) != null || trie.getWordNode(suff) != null) return true;
        }
        return false;
    }

    private ArrayList<String> sortByLength(ArrayList<String> targetList){
       ArrayList<String> wordList = targetList;
       wordList.sort(Comparator.comparing((String) -> String.length()));
       return wordList;
    }
    
    public ArrayList<String> getLongestConcWords(int longestWordsNumber) throws IOException{
        if (!fullyConcWords.isEmpty()) sortByLength(fullyConcWords);
        else return null;
        ArrayList<String> result = new ArrayList<>();
        for(int i = fullyConcWords.size() - 1; i > fullyConcWords.size()- 1 - longestWordsNumber & i >= 0; i--){
            result.add(fullyConcWords.get(i));
        }
        return result;
    }
}
