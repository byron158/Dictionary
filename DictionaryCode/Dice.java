
/**
 * Write a description of class Dice here.
 * 
 * @author Byron Aguilar 
 * @version 9/11/13
 */
import java.util.Random;
public class Dice implements Comparable<Dice>
{
    private int mySides;
    private Random roller;
    private int previousRoll;
    // private int rollCount;

    //two constructors (initializes private instance variables)
    Dice()
    {
        mySides = 6;
        roller = new Random();
        previousRoll = -1;
    }
    
    Dice(int sides )
    {
        setSides(sides);
        roller = new Random();
       
    }
    
    Dice(int sides, long seed)
    {
        setSides(sides);
        roller = new Random(seed);
    }
    public void  resetRoller()
    {
        roller = new Random();
    }
    //mutator/modifier
    public void setSides(int sides)
    {
         if (sides >= 1)
            mySides = sides;
        else
            mySides = 6;
    }
    //acessor
    public int getSides()
    {
        return mySides;
    }
    public int getPreviousRoll()
    {
        return previousRoll;
    }
    //act or action method
    public int getNextRoll()
    {
        previousRoll = roller.nextInt(mySides) + 1;
        return previousRoll;
        
    }
    
    public int compareTo(Dice ob)//object is when you define a class
    {
        Dice otherDice = (Dice)ob;
        return mySides-otherDice.getSides();
        //if the datatype is a string
        //return myName.compareTo(otherPerson.getName());
    }
}
