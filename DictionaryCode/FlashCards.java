import java.util.ArrayList;
import java.util.Scanner;
// import java.util.StringTokenizer;

/**
 * I made a seperate FlashCard object for the flashcrads
 * @author stemhp16
 *
 */
public class FlashCards {
	
	private int sides;
	private ArrayList<String> words2;
	private static Dice random;
	// private StringTokenizer strTok;
	// private String line = "";
	private Scanner sc;
	private String answer = "";
	public static final long waitTime = 1000L;
	
	public static final String helpText = 
			"Welcome to the Flash Card Maker, try to guess the definition of the word you see, then hit enter to see the definition."+"\n"+
			"When you are done, type 'q' to quit";
	
	/**
	 * Creates a flashcard using words
	 * @param words - the list of words
	 */
	public FlashCards(ArrayList<String> words){
		sides = words.size();
		words2 = new ArrayList<String>();
		random = new Dice();
		random.setSides(sides);
		sc = new Scanner(System.in);
		for(int i =0; i<words.size();i++)
	    {
	         words2.add(words.get(i));
	    }
	}
	
	/**
	 * we start here
	 */
	public void begin(){
		printOutHelp();
		for(int i = 0; i < sides; i++){
			int r = random.getNextRoll()-1;
			String word = words2.get(r);
			String word1 = DictionaryUtilities.extractWord(word);
			String def = DictionaryUtilities.extractDefinition(word);
			System.out.println(word1);
			answer = sc.nextLine();
			answer.toLowerCase();
			if(answer.equals(""))
			{
	             System.out.println(def);
	             System.out.println("");
	             System.out.println("");
	             try{
	            	 dotWait(waitTime);
	             }
	             catch(Exception e){
	            	 // we shouldn't be going here
	             }
	             words2.remove(r);
	             random.setSides(words2.size());
	        }
			else{
				i = sides+1;
				System.out.println("quitting");
			}
		}
	}
	
	/**
	 * Waits for five iterations of a certain time, so 
	 * the definition doesnt disappera
	 * @param time - the amount of time to wait per iteration
	 * @throws InterruptedException
	 */
	private void dotWait(long time) throws InterruptedException{
		System.out.print(" ");
		for(int i = 0; i < 5; i++){
			Thread.sleep(time);
			System.out.print(". ");
		}
		System.out.println("");
	}
	
	/**
	 * Prints out the help
	 */
	private void printOutHelp(){
		System.out.println(helpText);
	}
}
