/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Никита
 */
public class TrieTest {
    
    /**
     * Test of insertWord method, of class Trie.
     */
    @Test
    public void testInsertWord() {
        System.out.println("insertWord");
        String word = "test";
        Trie instance = new Trie();
        instance.insertWord(word);
        assertEquals(instance.getWordNode("test").value, new TrieNode('t').value);
    }

    /**
     * Test of getWordNode method, of class Trie.
     */
    @Test
    public void testGetWordNode() {
        System.out.println("getWordNode");
        String word = "test";
        Trie instance = new Trie();
        instance.insertWord(word);
        TrieNode expResult = new TrieNode('t');
        TrieNode result = instance.getWordNode(word);
        assertEquals(expResult.value, result.value);
    }   
}
