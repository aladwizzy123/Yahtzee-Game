/*
 * Alaeldein
 */

package core;

import java.util.ArrayList;

import constants.Constants;
import java.util.Collections;

/*
    This class will replicate the state and behavior of the five dice in the 
    game and rolling the dice
*/

public class Roll 
{
    // container for the five dice
    private ArrayList<Die> dice;
    /**
     * @return the dice
     */
    
    public Roll()
    {
        createDice();
    }
    
    private void createDice()
    {
        dice = new ArrayList();
        
        for(int d = 0; d < Constants.NUM_DICE; d++)
        {
            Die die = new Die();
            dice.add(die);
        }
    }
    
    public void displayDice()
    {
        int counter = 0;
        
        for(Die die : dice)
        {
            System.out.println("Die " + counter + " - " + die.getFaceValue());
            counter++;
        }
    }
    
    public void sortDice()
    {
        ArrayList<Integer> diceValues = new ArrayList();
        
        for(Die die : dice)
            diceValues.add(new Integer(die.getFaceValue()));
        
        Collections.sort(diceValues);
        
        removeDice(this);
        
        for(Integer intt : diceValues)
        {
            Die die = new Die();
            die.setFaceValue(intt);
            dice.add(die);
        }
    }
    

    public ArrayList<Die> getDice() 
    {
        return dice;
    }

    /**
     * @param dice the dice to set
     */
    public void setDice(ArrayList<Die> dice) 
    {
        this.dice = dice;
    }
    
    public void removeDice(Roll roll)
    {
        roll.getDice().removeAll(roll.getDice());
    }
}
