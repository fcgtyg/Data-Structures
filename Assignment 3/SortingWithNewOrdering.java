/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author fatihgulmez
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class SortingWithNewOrdering {
   
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static HashMap<String, Integer> newAlphabet;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        newAlphabet = newOrder(readNewOrder("NewOrdering.txt"));
        String[] task = readInput("InputNewOrdering.txt");
        sortThem(task);
        writeFile("OutputNewOrdering.txt", task);
        //reOrder(readInput("InputNewOrdering.txt"), newAlphabet);
    }
    
    public static String[] readNewOrder(String path) throws FileNotFoundException, IOException{
        FileReader f = new FileReader(path);
        BufferedReader file = new BufferedReader(f);
        String line;
        String[] lines = null;
        int i = 0;
        while ((line = file.readLine()) != null){
            lines = line.split(" ");
        }
        
        return lines;
    }
    
    public static String[] readInput(String path) throws FileNotFoundException, IOException{
        FileReader f = new FileReader(path);
        BufferedReader file = new BufferedReader(f);
        String line;
        String line_="";
        String[] lines = null;
        while ((line = file.readLine()) != null){
            line_ += line + " ";
        }
        lines = line_.split(" ");
        
        return lines;
    }
    
    public static void writeFile(String path, String[] input) throws FileNotFoundException {
        PrintWriter output = new PrintWriter(path);
        for(String word:input){
            output.println(word);
        }
        output.close();
        
    }
    
    public static HashMap newOrder(String[] ordering){
        HashMap newAbc = new HashMap(26);
        int index = 0;
        for(String i:ordering){
            newAbc.put(i, index);
            index++;
        }
        return newAbc;
    }
    
    public static String compareThem(String one, String two){
        int index = 0;
        int diff = 0;
        while (diff == 0 && index<one.length() && index<two.length()){
            diff = charVal(one.charAt(index)) - charVal(two.charAt(index));
            index++;
        }
        if (diff<0){
            return one;
        }else if(diff == 0){
            if (one.length()<two.length())
                return one;
            else
                return two;
        }else{
            return two;
        }
    }
    
    public static int charVal(char a){
        String b = "";
        b += a;
        return newAlphabet.get(b);
    }
    
    //PROBLEM
    public static void sortThem(String[] input){
        int i;
        int maxIn = 0;
        String max;
        String temp;
        for(int j = 0; j<input.length; j++){
            max = input[j];
            i = j+1;
            while(i<input.length){
                String compare = input[i];
                String bigger = compareThem(max, compare);
                if (bigger.equals(compare)){
                    max = compare;
                    maxIn = i;
                }
                i++;
            }
            temp = input[j];
            input[j] = max;
            input[maxIn] = temp;
        }
    }
}