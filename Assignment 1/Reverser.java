import java.io.*;

public class Reverser {
       public static void main(String[] args) throws IOException {
           try{
               String[] input = takeReverse("CsvToReverse.txt");
               writeFile(input, "ReverseCsv.txt");
           }catch(FileNotFoundException e){
               System.out.println("NOT FOUND: " + e.getMessage());
           }catch(Exception r){
               System.out.println("ERROR: " + r.getMessage());
           }
       }
       public static String[] takeReverse(String file) throws FileNotFoundException, IOException {
           
           String[] tempList;
           
           try (FileReader f = new FileReader(file)) {
               BufferedReader myFile = new BufferedReader(f);
               String line = null;
               String temp;
               
               temp = myFile.readLine();
               tempList = temp.split(",");
               return tempList;
               
           }catch(Exception e){
               System.out.println("ERROR: " + e.getMessage());
               return null;
           }
       }
       
       public static void writeFile(String[] in, String fileName) throws FileNotFoundException{
           
           try (PrintWriter f = new PrintWriter(fileName)) {
               for(int i = in.length-1; i>=0; i--){
                   if(i > 0){
                       f.print(in[i] + ",");
                   }else if(i==0){
                       f.print(in[i]);
                   }               
               }
           }catch(Exception e){
               System.out.println("ERROR: " + e.getMessage());
           }
       }
}