/*
 * Alaeldein
 */
package core;

import constants.Constants;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kwhiting
 */
public class HumanPlayer extends Player
{
    public HumanPlayer()
    {
        setScore(new ScoreCard());
    }
    
    @Override
    public void rollDice(Roll roll) 
    {
        setRollDice(roll);
        
        for(Die die : roll.getDice())
        {
            die.rollDie();
        }
    }

    @Override
    public void selectCategory(Roll keep) 
    {
        boolean valid = false;
        System.out.println("Select the category for this roll by entering the index");
        Scanner scan = new Scanner(System.in);
        keep.displayDice();

        int selection = 0;
        
        while(!valid)
        {
            System.out.println("**********************");
            System.out.println(" UPPER SECTION");
            System.out.println("**********************");
            System.out.println("1 - ONES");
            System.out.println("2 - TWOS");
            System.out.println("3 - THREES");
            System.out.println("4 - FOURS");
            System.out.println("5 - FIVES");
            System.out.println("6 - SIXES");
            System.out.println("**********************");
            System.out.println("  LOWER SECTION");
            System.out.println("**********************");
            System.out.println("7 - THREE OF A KIND");
            System.out.println("8 - FOUR OF A KIND");
            System.out.println("9 - FULL HOUSE");
            System.out.println("10 - SMALL STRAIGHT");
            System.out.println("11 - LARGE STRAIGHT");
            System.out.println("12 - YAHTZEE");              
            System.out.println("13 - CHANCE");

            try
            {
                String select = scan.next();
                selection = Integer.parseInt(select);
                valid = true;
            }
            catch(Exception ex)
            {
                System.out.println("Invalid option, try again");
                valid = false;
            }

            if(selection < 1 || selection > 13)
            {
                System.out.println("Invalid option, try again");
                valid = false;
            }
            else
            {
                valid = true;
            }
        }
        
        calculateScore(keep, selection);
    }

    public void calculateScore(Roll keep, int category)
    {
        System.out.println("category " + category + " selected");
        
        switch(category)
        {
            case Constants.ONES:
            case Constants.TWOS:
            case Constants.THREES:
            case Constants.FOURS:
            case Constants.FIVES:
            case Constants.SIXES:
                getScore().getUpper().evaluateCategory(keep, category);
                break;
            case Constants.THREE_KIND:
            case Constants.FOUR_KIND:
            case Constants.FULL:
            case Constants.SMALL_STR:
            case Constants.LARGE_STR:
            case Constants.YATZEE_CAT:
            case Constants.CHANCE:
                getScore().getLower().evaluateCategory(keep, category);
                break;  
        }
        
        System.out.println("score calculated, score is " + getScore().getGrandTotal());
    }

    public void selectDice(Roll original, Roll keep, int rollNum)
    {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        boolean valid = false;

        System.out.println("Enter the index of each die you would like to keep, " + 
                           "enter D for Done when finished");
                        
        while(!done || !valid)
        {
            // display the die in the original array list
            original.displayDice();
            
            // get input from user
            String value = scan.next();

            // exit the while loop if the users selects to
            // or all dice have been selected
            if(value.equalsIgnoreCase("D"))
            {                
                if((!original.getDice().isEmpty()) && 
                    rollNum == (Constants.MAX_ROLLS - 1))
                {
                    System.out.println("Sorry " + getName() + 
                                       ", you have to keep the rest of the dice");
                     
                    for(Die die : original.getDice())
                    {
                        keep.getDice().add(die);
                    }
                    
                    original.getDice().removeAll(original.getDice());
                }
             
                done = true;
   
                break;
            }
            else
            {
                int idx = 0;                
                Die die = null;
                
                // parse the integer from the input
                try
                {
                    idx = Integer.parseInt(value);
                    
                    // get that die from the arraylist
                    die = original.getDice().get(idx);                

                    // add the die to the keep arraylist
                    keep.getDice().add(die);

                    // remove from the original dice
                    original.getDice().remove(idx);                
                }
                catch(Exception ex)
                {
                    System.out.println("Invalid option, try again");
                    valid = false;
                }
                
                System.out.println("***************");
                System.out.println("Keep dice " + keep.getDice().size());
                keep.displayDice();
                System.out.println("***************");
                
                if(keep.getDice().size() == Constants.NUM_DICE)
                {
                    break;
                }
            }
        }
    }
}
