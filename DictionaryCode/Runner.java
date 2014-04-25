
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
    public static void run ()throws IOException
    {
        DictionaryUtilities[]g=new DictionaryUtilities[1];
        DictionaryUtilities l = new DictionaryUtilities();
        g[0]=l;
        System.out.println("Welcome, enter a number from the options to continue");
        System.out.println("1. Single word, definition finder.");
        System.out.println("2. Multiple words, definiton finder.");
        System.out.println("3. File input.(Input words from file.)");
        Scanner keyIn = new Scanner(System.in);
        int input = Integer.parseInt(keyIn.nextLine());
        String sentence="";

        switch(input)
        {

            case 1: 
            System.out.println("Enter a word");

            sentence = keyIn.nextLine();
            System.out.println(g[0].printWords(sentence));
            
            if(sentence.equals("--"))
            {
                System.exit(-1);
            }

            System.out.println("Want to make a flash card? Yes or No");
            sentence = keyIn.nextLine();
            sentence = sentence.toLowerCase();

            if(sentence.equals("yes"))
            {
                g[0].FlashCards();
                sentence= "";
            }

            System.out.println("Want to save this file? Yes or No");
            sentence = keyIn.nextLine();
            sentence = sentence.toLowerCase();

            if(sentence.equals("s"))
            {
                System.out.println("Enter the path of the directory where you want to save this file.");
                System.out.println("Ex: F:/Comp Sci/First Program/NameOfFile.txt");
                sentence =keyIn.nextLine();
                System.out.println(g[0].writeFile(sentence));
                System.out.println("File created at " + sentence);
                break;
            }

            break;

            case 2:
            System.out.println("Enter a word, when you are done hit '--' and enter");
            sentence = "";
            while(!sentence.equals("--"))
            {
                sentence = keyIn.nextLine();
                System.out.println(g[0].printWords(sentence));
            }
            System.out.println("Want to make flash cards? Yes or No");
            sentence = keyIn.nextLine();
            sentence = sentence.toLowerCase();

            if(sentence.equals("yes"))
            {
                g[0].FlashCards();
                sentence= "";
            }

            System.out.println("Want to save the the definitions file? Yes or No");
            sentence = keyIn.nextLine();
            sentence = sentence.toLowerCase();

            if(sentence.equals("s"))
            {
                System.out.println("Enter the path of the directory where you want to save this file.");
                System.out.println("Ex: F:/Comp Sci/First Program/NameOfFile.txt");
                sentence =keyIn.nextLine();
                System.out.println(g[0].writeFile(sentence));
                System.out.println("File created at " + sentence);
                break;
            }

            break;

            case 3:
            System.out.println("Enter the path of the text file, use /");
            System.out.println("Ex: F:/Comp Sci/First Program/NameOfFile.txt");
            System.out.println("Make sure that the txt file has only one word per line");
            System.out.println("Type '--' to exit");
            sentence = keyIn.nextLine();
            System.out.println(g[0].multipleWordsSearchText(sentence)); 
            System.out.println("Want to make flash cards? Yes or No");
            sentence = keyIn.nextLine();
            sentence= sentence.toLowerCase();

            if(sentence.equals("yes"))
            {
                System.out.flush();
                g[0].FlashCards();
                sentence= "";
            }

            System.out.println("Want to save the definitions file? S or No");
            sentence = keyIn.nextLine();
            sentence=sentence.toLowerCase();
            
            if(sentence.equals("s"))
            {
                System.out.println("Enter the path of the directory where you want to save this file.");
                System.out.println("Ex: F:/Comp Sci/First Program/NameOfFile.txt");
                sentence =keyIn.nextLine();
                System.out.println(g[0].writeFile(sentence));
                System.out.println("File created at " + sentence);
                break;
            }

            break;

            default:
            System.out.println("Invalid menu choice");
            System.out.println("Goodbye");
            System.exit(-1);

        }
    }
}

