/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

/**
 *
 * @author Никита
 */
public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode(Character.MIN_VALUE);
    }

    public void insertWord(String word){
        TrieNode currNode = root;
        TrieNode nextNode;
        for (int i = 0; i < word.length(); i++) {
            nextNode = currNode.getNodeWithLetter(word.charAt(i));
            if (nextNode == null) {
                nextNode = new TrieNode(word.charAt(i));
                currNode.values.add(nextNode);
                currNode = nextNode;
            }
            else currNode = nextNode;
        }
        currNode.isWord = true;
    }

    public TrieNode getWordNode(String word) {
        if (word.isEmpty()) return null;
        TrieNode currNode = root;
        TrieNode nextNode;
        for (int i = 0; i < word.length(); i++) {
            nextNode = currNode.getNodeWithLetter(word.charAt(i));
            if (nextNode != null)
                currNode = nextNode;
            else return null;
        }
        if (!currNode.isWord) return null;
        return currNode;
    }
}