/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Никита
 */
public class SolverTest {
    
    private static Solver instance;
    @BeforeClass
    public static void setUp(){
        instance = new Solver("test.txt");
        try {
            instance.wordsSort();
        }
        catch (IOException ex) {ex.printStackTrace();}
    }
    
    /**
     * Test of getFullyConcWords method, of class Solver.
     * @throws java.io.IOException
     */
    @Test
    public void testGetFullyConcWords() throws IOException {
        System.out.println("getFullyConcWords");
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("catsdogcats");
        expResult.add("dogcatsdog");
        expResult.add("ratcatdogcat");
        System.out.println(expResult.toString());
        ArrayList<String> result = instance.getFullyConcWords();
        System.out.println(result.toString());
        assertEquals(expResult, result);
        assertEquals(expResult.size(), instance.getTotalCountConcWords());
    }

    /**
     * Test of getNotFullyConcWords method, of class Solver.
     */
    @Test
    public void testGetNotFullyConcWords() {
        System.out.println("getNotFullyConcWords");
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("cats");
        ArrayList<String> result = instance.getNotFullyConcWords();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNonConcWords method, of class Solver.
     */
    @Test
    public void testGetNonConcWords() {
        System.out.println("getNonConcWords");
        ArrayList<String> result = instance.getNonConcWords();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of setPath method, of class Solver.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String _filePath = "test.txt";
        instance.setPath(_filePath);
        assertTrue(new File(_filePath).isFile());
    }

    /**
     * Test of wordsSort method, of class Solver.
     * @throws java.lang.Exception
     */
    @Test
    public void testWordsSort() throws Exception {
        System.out.println("wordsSort");
        assertTrue(!instance.getFullyConcWords().isEmpty());
        assertTrue(!instance.getNotFullyConcWords().isEmpty());
        assertTrue(!instance.getNonConcWords().isEmpty());
    }

    /**
     * Test of getLongestConcWords method, of class Solver.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetLongestConcWords() throws Exception {
        System.out.println("getLongestConcWords");
        int longestWordsNumber = 2;
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("ratcatdogcat");
        expResult.add("catsdogcats");
        ArrayList<String> result = instance.getLongestConcWords(longestWordsNumber);
        System.out.println(result.toString());
        assertEquals(expResult, result);
        assertEquals("ratcatdogcat", result.get(0));
        assertEquals("catsdogcats", result.get(1));
        expResult = new ArrayList<>();
        expResult.add("ethylenediaminetetraacetates");
        expResult.add("ethylenediaminetetraacetate");
        Metrics.start();
        Solver fullTextSolver = new Solver("words.txt");
        fullTextSolver.wordsSort();
        result = fullTextSolver.getLongestConcWords(2);
        Metrics.stop();
        System.out.println(result.toString());
        Metrics.getAllMetrics();
        assertEquals(expResult, result);
        assertEquals(97107, fullTextSolver.getTotalCountConcWords());
        
    }
    
}
