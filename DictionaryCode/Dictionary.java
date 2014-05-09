import java.util.ArrayList;
import java.util.Collections;

/**
 * A Dictionary object contains the dictionary, words, and wordlist, as well as filename
 * @author Potato
 *
 */
public class Dictionary {

	public ArrayList<String>dictionary; // dictionary
    public ArrayList<String>words; // words
    public ArrayList<String>wordlist; // list of words
    private static String fileName = "Dictionary1.txt"; // file name
    
    /**
     * Creates a dictionary, runs the init method
     */
    public Dictionary(){
    	init();
    }
    
    /**
     * Creates a dictionary using a filename to read from, runs the init method
     * @param name
     */
    public Dictionary(String name){
    	fileName = name;
    	init();
    }
    
    /**
     * Where all the methods to initialize the Dictionary are
     */
    public void init(){
    	dictionary = new ArrayList<String>();
        Collections.sort(dictionary);
        words = new ArrayList<String>();
        wordlist = new ArrayList<String>();
        try{
        	DictionaryUtilities.readInData(dictionary, fileName);
        	DictionaryUtilities.extractMultipleWords(dictionary, wordlist);
        }
        catch(Exception e){
        	System.out.println("got some bad file data here");
        }
    }
    
    /**
     * set the filename
     * @param name - the name of the file to set 
     */
    public void setFileName(String name){
    	fileName = name;
    }
}
