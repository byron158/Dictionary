
/**
 * Write a description of class Runner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class Runner
{
	private static int badTries = 0; // so they dont try too much
	public static final String errorText = "try again, or enter 'h' for help or '--' to quit"; // way to quit
	private static Scanner keyIn; 
	private static File file; // file objects!
	private static Dictionary dict; // declare dictionary object
	private static FlashCards fc; // flash card object, to be full later
	
	// help text for easy edit
	public static final String helpText = 
			"Welcome, enter a number from the options to continue"+"\n"+
			"1. Single word, definition finder."+"\n"+
		    "2. Multiple words, definition finder."+"\n"+
			"3. File input.(Input words from file.)";
	
	// yes or no text for easy edit
	public static final String yesNoText = " Yes or No";
	
	// file saving text for easy edit
	public static final String fileSaveText = 
			"Enter the name of the file you want to save to.";
	
	// file reading text for easy edit
	public static final String fileReadText =
			"Enter the name of the file you want to read from.";
	
	// that double dash thing for easy edit
	public static final String cancelText = "--";

	/**
	 * I split your main method into multiple methods
	 * main only has initliazers and the lead into the
	 * method where everything happens
	 * @param args
	 */
    public static void main(String[]args)
    {
    	dict = new Dictionary();
    	keyIn = new Scanner(System.in);
    	begin();
    }
    
    /**
     * Basically dis method has the switch case and is the start of everything
     */
    private static void begin(){
        int input = chooseOption(); // this combines the help text and the option choosing
    	switch(input)
        {
            case 1: case1(); break;            
            case 2: case2(); break;
            case 3: case3(); break;
            default:
            System.out.println("Invalid menu choice");
            System.out.println("Goodbye");
            break;
        }
    }
    
    /**
     * This is case 1
     * you should probably rename to something more relevant
     */
    private static void case1(){
    	if(enterWord()){
    		if(enterYesNo("Want to make a flash card?"))
            {
                fc = new FlashCards(dict.words);
            }
            if(enterYesNo("Want to save this file?"))
            {
            	writeFilePre();
            }
    	}
    }
    
    /**
     * This is case 2
     * you should probably rename to something more relevant
     */
    private static void case2(){
    	System.out.println("Enter a word, when you are done hit '--' and enter");
    	boolean goodWord = false;
    	while(enterWord()){
    		goodWord = true;
    	}
    	if(goodWord){
    		if(enterYesNo("Want to make a flash card?")){
    			fc = new FlashCards(dict.words);
    		}
    		if(enterYesNo("Want to save this file?")){
    			writeFilePre();
    		}
    	}
    }
    
    /**
     * This is case 3
     * you should probably rename to something more elephant
     */
    private static void case3(){
    	if(enterFileName()){
    		if(enterYesNo("Want to make a flash card?")){
    			fc = new FlashCards(dict.words);
    		}
    		if(enterYesNo("Want to save this file?")){
    			writeFilePre();
    		}
    	}
    }
    
    /**
     * this is where you pick the options
     * h prints out the helptext
     * q (or more than 5 invalid choices) quits
     * otherwise, returns the choice
     * @return the int choice of input
     */
    private static int chooseOption(){
    	Scanner sc = new Scanner(System.in);
    	System.out.println(helpText);
    	String choice = sc.nextLine();
    	try{
    		if(choice.equals("h")){
    			return chooseOption();
    		}
    		else if(choice.equals("q") || badTries > 5){
    			return -1;
    		}
    		else{
    			int option = Integer.parseInt(choice);
    			return option;
    		}
    	}
    	catch(NumberFormatException e){
    		System.out.println("bad number; try again, or enter 'h' for help or '--' to quit");
    		badTries++;
    		return chooseOption();
    	}
    	finally{
    		sc.close();
    	}
    }
    
    /**
     * enter the filename and parse it with the utils
     * @return true if the file got parsed and we good, false if no or quit invoked
     */
    private static boolean enterFileName(){
    	String response = keyIn.nextLine();
    	if(!response.equals(cancelText) && !response.equalsIgnoreCase("q")){
    		File file = new File(response);
    		if(file.exists()){
    			try{
    				DictionaryUtilities.multipleWordsSearchText(dict.dictionary, dict.wordlist, dict.words, file);
    				return true;
    			}
    			catch(Exception e){
    				// we should not be going here
    				return false;
    			}
    		}
    		System.out.println("bad file, quitting");
    	}
    	return false;
    }
    
    /**
     * Enter a word
     * @return true if we enter a word, false if we quit or double dash
     */
    private static boolean enterWord(){
    	System.out.println("Enter a word");
        String response = keyIn.nextLine();
        String found = DictionaryUtilities.printWords(dict.dictionary,dict.wordlist,dict.words,response);
        if(response.equals(cancelText) || !found.equals(DictionaryUtilities.unFound) || response.equalsIgnoreCase("q"))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Enter yes and no
     * @param prompt - the text prompt for this question
     * @return true if yes, false if no
     */
    private static boolean enterYesNo(String prompt){
    	System.out.println(prompt+yesNoText);
    	String line = keyIn.nextLine();
    	if(line.equalsIgnoreCase("yes") || line.equalsIgnoreCase("y")){
    		return true;
    	}
    	return false;
    }
    
    
    // we arent using this, but we might
    /**
    private static ArrayList<String> readFile(File file) throws IOException, FileNotFoundException{
    	ArrayList<String> list = new ArrayList<String>();
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	String line;
    	try{
    		while( (line=br.readLine()) != null){
    			list.add(line);
    		}
    		return list;
    	}
    	catch(Exception e){
    		throw e;
    	}
    	finally{
    		br.close();
    	}
    	
    }//*/
    
    /**
     * Write a file name using a string filename
     * @param fileName - the name of the file
     */
    private static void writeFile(String fileName){
    	file = new File(fileName);
    	if(file.exists()){
    		if(!enterYesNo("File "+fileName+" already exists. Overwrite? (Y/n)")){
    			return;
    		}
    	}
    	try{
    		DictionaryUtilities.writeFile(dict.words,file);
    	}
    	catch(Exception e){
    		System.out.println("Error while saving. (err8492)");
    	}
    }
    
    /**
     * Write a file prelim actions
     * we get the filename here
     */
    private static void writeFilePre(){
    	System.out.println(fileSaveText);
    	String response =keyIn.nextLine();
    	writeFile(response);
    	System.out.println("File created at " + response);
    }
}

