/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import java.util.ArrayList;

/**
 *
 * @author Никита
 */
public class TrieNode {
    public ArrayList<TrieNode> values;
    public char value;
    public boolean isWord;

    public TrieNode(char value) {
        values = new ArrayList<>();
        this.value = value;
    }

    public TrieNode getNodeWithLetter(char letter) {
        for (TrieNode node: values) if (node.value == letter) return node;
        return null;
    }
}