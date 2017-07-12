/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;

/**
 *
 * @author fatihgulmez
 */
public class Shuffler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Memoization Method
        boolean test;
        if(args.length == 3)
            test = isShuffle(args[0], args[1], args[2]);
        else{
	    System.out.println("Insufficient or wrong command line arguements, moving on default values...");
            test = isShuffle("chocolate", "chips", "cchocohilaptes");
        }
        System.out.println(test);
    }
    
    public static HashMap <Character, Integer> analyzeChars  = new HashMap();
    
    public static boolean isShuffle(String base1, String base2, String aim){
        String bases = base1 + base2;
        analyzeChars  = characterSet(bases);
        char check;
        if(aim.length() != bases.length()){
            System.out.println(aim + " is not a shuffle of " + base1 + " and " + base2);
            return false;
        }else{
            for(int i = 0; i< aim.length(); i++){
                check = aim.charAt(i);
                if(analyzeChars.containsKey(check)){
                    int num = analyzeChars.get(check);
                    if(num == 1) 
                        analyzeChars.remove(check);
                    else
                        analyzeChars.put(check, num-1);
                }else{
                    System.out.println(aim + " is not a shuffle of " + base1 + " and " + base2);
                    return false;
                }
            }
            System.out.println(aim + " is a shuffle of " + base1 + " and " + base2);
            return true;
        }
    }
    public static HashMap characterSet(String input){
        HashMap <Character, Integer> characterSet = new HashMap();
        char processing;
        
        for(int i = 0; i<input.length(); i++){
            processing = input.charAt(i);
            if(!characterSet.containsKey(processing)){
                characterSet.put(processing, 1);
            }
            else if(characterSet.containsKey(processing)){
                characterSet.put(processing, characterSet.get(processing)+1);
            }
        }
        return characterSet;
    }
}
