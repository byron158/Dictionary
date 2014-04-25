
/**
 * Write a description of class DictionaryUtilities here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class DictionaryUtilities
{
    private static ArrayList<String>dictionary;
    private static ArrayList<String>words;
    private static ArrayList<String>wordlist;
    private static Dice[]random;
    private static Dice l;
    DictionaryUtilities()throws IOException
    {
        dictionary = new ArrayList<String>();
        Collections.sort(dictionary);
        words = new ArrayList<String>();
        wordlist = new ArrayList<String>();
        random = new Dice[1];
        l = new Dice();
        random[0] = l;
        readInData();
        extractMultipleWords();
    }

    private static void extractMultipleWords()
    {
        for(int i =0; i<dictionary.size();i++)
        {
            wordlist.add((extractWord(dictionary.get(i))).trim());
        }
    }

    private static void readInData()throws IOException
    {
        File file = new File("Dictionary1.txt");
        Scanner fileIn = new Scanner(file);
        while(fileIn.hasNextLine())
        {
            dictionary.add(fileIn.nextLine());
        }
    }

    public static String multipleWordsSearchText(String path)throws IOException
    {
        ArrayList<String>list=new ArrayList<String>();
        File file = new File(path);
        if(!file.isFile())
            return "Error Invalid Path";
        Scanner sc = new Scanner(file);
         while(sc.hasNextLine())
        {
            list.add(sc.nextLine());
        }
        for(int i =0; i<list.size();i++)
        {
            printWords(list.get(i));
        }
        return "";
    }

    private static int binarySearch(String word)
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

    public static String saveWords(String word)
    {
        words.add(word);
        return "";
    }

    public static String writeFile(String path)throws IOException
    {
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        for(int i =0; i<words.size();i++)
        {
            writer.write((System.getProperty("line.separator"))+words.get(i));
        }
        writer.flush();
        writer.close();
        
        return "";
    }

    public static String printWords(String word)
    {
        word=word.toLowerCase();
        if(binarySearch(word)==-1)
            return "Word Not Found";
        
        String word1 = dictionary.get(binarySearch(word));
        

        String def = word1;
        System.out.println(def);
        
        saveWords(def);
        return "";
    }

    private static String extractWord(String word)
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

    private static String extractDefinition(String word)
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

    public static void FlashCards()
    {
        int sides = words.size();  
        ArrayList<String>words2= new ArrayList<String>();
        random[0].setSides(sides);
        StringTokenizer strTok;
        String line = new String();
        Scanner sc = new Scanner(System.in);
        String answer = "";
        for(int i =0; i<words.size();i++)
        {
            words2.add(words.get(i));
        }
        System.out.println("Welcome to Flash Card Maker, try to guess the definition of the word you see, then hit enter to see the definition");
        System.out.println("When you are done, type 'exit'");
        for(int i=0;i<words.size();i++)
        {

            int r = random[0].getNextRoll()-1;
            String word = words2.get(r);
            String word1 = extractWord(word);
            String def = extractDefinition(word);  
            System.out.println(word1);
            answer=sc.nextLine();
            answer.toLowerCase();
            if(answer.equals(""))
            {
                System.out.println(def);
                System.out.println("");
                System.out.println("");
            }
            else
                break;
            words2.remove(r);
            random[0].setSides(words2.size());
        }

    }

}
