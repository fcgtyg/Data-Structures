import java.io.*;
import java.util.ArrayList;

public class Calculator {
    
    public static ArrayList symbols = new ArrayList();
    public static char operation;
    public static ArrayList tempNumbers = new ArrayList();
    
    public static void main(String[] args) throws IOException {
        
        try{
            calculateElements(readFile("InputCalculator.txt"), "OutputCalculator.txt");
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public static ArrayList readFile(String fileName) throws FileNotFoundException, IOException{
        BufferedReader f = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = f.readLine()) != null){
            try{
                Double lineAsDouble = Double.valueOf(line);
                symbols.add(lineAsDouble);
            }catch(Exception e){
                //String to Char
                symbols.add(line);
            }
        }
        return symbols;
    }
    
    public static void calculateElements(ArrayList input, String fileName) throws FileNotFoundException{
        try (PrintWriter f = new PrintWriter(fileName)) {
            String readIt = "";
            double tempNum = 0.0;
            for(byte i = 0; i<input.size(); i++){
                readIt += input.get(i);
                if("=".equals(input.get(i))){
                    readIt = "";
                    f.print(tempNum);
                    tempNumbers.clear();
                }else if (input.get(i).getClass() == Double.class){
                    tempNumbers.add(input.get(i));
                }else if("+".equals(input.get(i))){
                    operation = '+';
                }else if("-".equals(input.get(i))){
                    operation = '-';
                }else if("*".equals(input.get(i))){
                    operation = '*';
                }else if("/".equals(input.get(i))){
                    operation = '/';
                }else{
                    operation = 'x';
                }
                if(tempNumbers.size() == 2){
                    tempNum = getResultFromTwoNumber(tempNumbers);
                }
                f.println(readIt);
            }
        }
    }
    
    public static double getResultFromTwoNumber(ArrayList tempNums){
        double result;
        double first = (double) tempNums.get(0);
        double second = (double) tempNums.get(1);
        switch (operation) {
            case '+':
                result = first + second;
                break;
            case '-':
                result = first - second;
                break;
            case '*':
                result = first * second;
                break;
            case '/':
                result = first / second;
                break;
            default:
                result = 0.0;
                break;
        }
        tempNumbers.clear(); 
        tempNumbers.add(result);
        return result;
    }
}
