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

public class StockMarket {
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        data = readFile("StockDealsInput.txt");
        int i = 0;
        while(i != 1){
            i = analyzeCase(data);
        }

        String[] out = output.split(".-.");
        writeFile("TransitionOutput.txt", out);
        
    }
    
    public static String output="";
    public static String[] data;
    
    public static String[] readFile(String path) throws FileNotFoundException, IOException{
        FileReader f = new FileReader(path);
        BufferedReader file = new BufferedReader(f);
        String line;
        
        int i = 0;
        while ((line = file.readLine()) != null){
            i++;
        }
        String[] lines = new String[i];
        
        file.close();
        
        f = new FileReader(path);
        file = new BufferedReader(f);
        i = 0;
        while ((line = file.readLine()) != null){
            lines[i] = line;
            i++;
        }
        
        return lines;
    }
    
    public static void writeFile(String path, String[] in) throws FileNotFoundException{
        PrintWriter outFile = new PrintWriter(path);
        for(String k: in){
            outFile.println(k);
        }
        outFile.close();
    }
    
    public static int analyzeCase(String[] input) { //Setting best prices for buyers and sellers.
        String[] activeSeller = getActiveSeller(input);
        String[] activeBuyer = getActiveBuyer(input);
        int countSeller = Integer.parseInt(activeSeller[2]);
        int countBuyer = Integer.parseInt(activeBuyer[2]);
        if(countSeller == 0 || countBuyer == 0)
            return 1;
        if (countSeller != 0 && countBuyer != 0) {
            if (countSeller < countBuyer){
                countBuyer -= countSeller;
                output += activeSeller[0] + activeSeller[1] + " sells to " + activeBuyer[0] + activeBuyer[1] + " in size of " + countSeller + ".-.";
                countSeller = 0;
            }else{
                countSeller -= countBuyer;
                output += activeSeller[0] + activeSeller[1] + " sells to " + activeBuyer[0] + activeBuyer[1] + " in size of " + countBuyer + ".-.";
                countBuyer = 0;
            }
            
            //countBuyer = 0;
            activeSeller[2] = String.valueOf(countSeller);
            activeBuyer[2] = String.valueOf(countBuyer);
            updateCounts(activeSeller, activeBuyer);
        }
        return 0;
    }
    
    public static String[] getActiveSeller(String[] input){
        String[] activeSeller = null;
        for(String i : input){
           String[] s = i.split(" ");
            if ("S".equals(s[0])){
                if (activeSeller != null){
                    int priceSeller = Integer.parseInt(s[3]);
                    int priceActive = Integer.parseInt(activeSeller[3]);
                    int countActive = Integer.parseInt(activeSeller[2]);
                    if(priceSeller < priceActive || countActive == 0){
                        activeSeller = s;
                    }else if(priceActive == priceSeller){
                        int countSeller = Integer.parseInt(s[2]);
                        
                        if (countSeller>countActive)
                            activeSeller = s;
                    }
                }
                else{
                    activeSeller = s;
                }
            }
        }
        return activeSeller;
    }
    
    public static String[] getActiveBuyer(String[] input){
        String[] activeBuyer = null;
        for(String i : input){
           String[] s = i.split(" ");
            if ("B".equals(s[0])){
                if (activeBuyer != null){
                    int priceBuyer = Integer.parseInt(s[3]);
                    int priceActive = Integer.parseInt(activeBuyer[3]);
                    int countActive = Integer.parseInt(activeBuyer[2]);
                    if(priceBuyer > priceActive ||countActive == 0){
                        activeBuyer = s;
                    }else if(priceActive == priceBuyer){
                        int countSeller = Integer.parseInt(s[2]);
                        
                        if (countSeller>countActive)
                            activeBuyer = s;
                    }
                }
                else{
                    activeBuyer = s;
                }
            }
        }
        return activeBuyer;
        
    }
    
    public static void updateCounts(String[] seller, String[] buyer){  //Updating counts after each simulation.
        for(int i = 0; i<data.length; i++){
            String id = data[i].charAt(0)+String.valueOf(data[i].charAt(2));
            String sellerID = seller[0] + seller[1];
            String buyerID = buyer[0] + buyer[1];
            if(id.equals(sellerID)){
                data[i] = seller[0] + " " + seller[1] + " " + seller[2] + " " + seller[3];
            }else if(id.equals(buyerID)){
                data[i] = buyer[0] + " " + buyer[1] + " " + buyer[2] + " " + buyer[3];
            }
        }     
    }
}
