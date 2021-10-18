/*
 * 
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author Alaeldein
 */
public class AiPlayer extends Player
{

    @Override
    public void rollDice(Roll roll) 
    {
        // loop through the collection of class Die
        // call method rollDie for each
        for(Die die : roll.getDice())
        {
            die.rollDie();
        }
    }

    @Override
    public void selectCategory(Roll keep) 
    {
    }
    
    public void selectDice(Roll original, Roll keep, int rollNum)
    {      
        
    }
    
    public void calculateScore(Roll keep, int category)
    {
       
    }

    private void evaluateDice(Roll original, Roll keep)
    {
        
    }
}
