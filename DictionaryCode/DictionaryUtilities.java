
/**
 * Write a description of class DictionaryUtilities here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

/**
 * I made all the methods static, so you use it like:
 * DictionaryUtilities.exampleMethod(exampleParam,...);
 * @author Potato
 *
 */
public class DictionaryUtilities
{
	/**
	 * Basic string telling us that the word was not found (for easy edit)
	 */
    public static final String unFound = "Word Not Found";

    /**
     * extracts multiple words?
     * @param dictionary - the dictionary
     * @param wordlist - list of words to extract
     */
    public static void extractMultipleWords(ArrayList<String> dictionary, ArrayList<String> wordlist)
    {
        for(int i =0; i<dictionary.size();i++)
        {
            wordlist.add((extractWord(dictionary.get(i))).trim());
        }
    }

    /**
     * reads in data, passes to readInData with file
     * @param dictionary - the dictionary
     * @param fileName - name of the file
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void readInData(ArrayList<String> dictionary, String fileName)throws IOException, FileNotFoundException
    {
        try{
        	File file = new File(fileName);
        	readInData(dictionary, file);
        }
        catch(Exception e){
        	throw e;
        }
    }
    
    /**
     * reads in data, using File
     * @param dictionary - the dictionary
     * @param file - File to read from
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void readInData(ArrayList<String> dictionary, File file)throws IOException, FileNotFoundException
    {
        BufferedReader br = new BufferedReader( new FileReader(file));
        String line;
        try{
        	while( (line=br.readLine()) != null )
        	{
        		dictionary.add(line);
        	}
        }
        catch(Exception e){
        	throw e;
        }
        finally{
        	br.close();
        }
    }

    /**
     * searching through text for multiple words?
     * @param dictionary - the dictionary
     * @param wordlist - list of words to search for
     * @param words - words?
     * @param path - the name of the file
     * @return a string if we good?
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String multipleWordsSearchText(ArrayList<String> dictionary, ArrayList<String> wordlist, ArrayList<String> words, String path)throws IOException, FileNotFoundException
    {
        File file = new File(path);
        try{
        	return multipleWordsSearchText(dictionary,wordlist,words,file);
        }
        catch(Exception e){
        	throw e;
        }
    }
    
    /**
     * searching through text using File
     * @param dictionary - the dictionary
     * @param wordlist - list of words
     * @param words - words?
     * @param file - the File to look trhough
     * @return a string if we good?
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String multipleWordsSearchText(ArrayList<String> dictionary, ArrayList<String> wordlist, ArrayList<String> words, File file)throws IOException, FileNotFoundException
    {
        ArrayList<String>list=new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        try{
        	while( (line=br.readLine()) != null ){
        		list.add(line);
        	}
        	for(int i =0; i<list.size();i++)
        	{
        		printWords(dictionary, wordlist, words, list.get(i));
        	}
        	return "";
        }
        catch(FileNotFoundException e){
        	System.out.println("Error Invalid Path");
        	throw e;
        }
        catch(Exception e){
        	throw e;
        }
        finally{
        	br.close();
        }
    }

    /**
     * searches using binary
     * @param dictionary - the dictionary
     * @param wordlist - list of words to search
     * @param word - the word
     * @return the index of where the word is
     */
    private static int binarySearch(ArrayList<String> dictionary, ArrayList<String> wordlist, String word)
    {

        int left = 0;
        int right = dictionary.size()-1; 
        int mid;

        while(left <= right)
        {
            mid = (left + right)/2;
            if(word.equals(wordlist.get(mid)))
            {
                return mid;
            } 
            else if((word.compareTo(wordlist.get(mid))) > 0)
            {
                // tab comes after mid so update left
                left = mid + 1;
            }
            else 
                right = mid - 1;
        }
        return -1;
    }

    /**
     * Saves the words to the words list
     * @param words - list of words
     * @param word - word to save to list of words
     * @return a strign if we good?
     */
    public static String saveWords(ArrayList<String> words, String word)
    {
        words.add(word);
        return "";
    }

    /**
     * write a file using String filename
     * (passes to writeFile with File
     * @param words - list of words
     * @param path - string filename
     * @throws IOException
     */
    public static void writeFile(ArrayList<String> words, String path)throws IOException
    {
        try{
        	writeFile(words, new File(path));
        }
        catch(Exception e){
        	throw e;
        }
    }
    
    /**
     * write file using File
     * @param words - list of words
     * @param file - the File to write to
     * @throws IOException
     */
    public static void writeFile(ArrayList<String> words, File file)throws IOException
    {
        BufferedWriter writer = new BufferedWriter( new FileWriter(file));
        try{
        	for(int i =0; i<words.size();i++)
        	{
        		writer.write((System.getProperty("line.separator"))+words.get(i));
        	}
        }
        catch(Exception e){
        	throw e;
        }
        finally{
        	writer.close();
        }
    }

    /**
     * prints the words and searches too
     * @param dictionary - the dictionary
     * @param wordlist - the list of words
     * @param words - words
     * @param word - a word
     * @return unFound if not found, nothing if found
     */
    public static String printWords(ArrayList<String> dictionary, ArrayList<String> wordlist, ArrayList<String> words, String word)
    {
        word=word.toLowerCase();
        int loc =binarySearch(dictionary, wordlist, word);
        if(loc==-1)
            return unFound;
        
        String word1 = dictionary.get(loc);
        String def = word1;
        System.out.println(def);
        saveWords(words, def);
        return "";
    }

    /**
     * extracts a word
     * @param word - a word
     * @return the String of extracting?
     */
    public static String extractWord(String word)
    {
        String tok = "";
        int i =0;
        while(word.charAt(i)!='-')
        {
            tok += word.charAt(i);
            i++;
        }
        return tok;
    }

    /**
     * extracts a definition
     * @param word - the word to find
     * @return the definition
     */
    public static String extractDefinition(String word)
    {
        String tok = "";
        int i =0;
        while(word.charAt(i)!='-')
        {
            i++;
        }
        tok = word.substring(i,word.length());
        return tok;
    }
}
