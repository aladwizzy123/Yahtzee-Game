/*
 * Alaeldein

 */

package core;

import java.util.Random;
import constants.Constants;

public class Die 
{
    private int faceValue;

    public void rollDie()
    {
        Random random = new Random();
        faceValue = (random.nextInt(Constants.MAX_DIE_VALUE) + 1);
    }
    
    /**
     * @return the faceValue
     */
    public int getFaceValue() 
    {
        return faceValue;
    }

    /**
     * @param faceValue the faceValue to set
     */
    public void setFaceValue(int faceValue) 
    {
        this.faceValue = faceValue;
    }
    
    public String toString()
    {
        return Integer.toString(getFaceValue());
    }
}