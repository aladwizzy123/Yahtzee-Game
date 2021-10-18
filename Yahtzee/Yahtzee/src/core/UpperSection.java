/*
 * Alaeldein
 */

package core;

import constants.Constants;

public class UpperSection 
{
    private int aces;
    private int twos;
    private int threes;
    private int fours;
    private int fives;
    private int sixes;
    private int totalScore;
    private int bonus;
    private int total;

    public void evaluateCategory(Roll roll, int category)
    {
        switch(category)
        {
            case Constants.ONES:
                for(Die die : roll.getDice())
                    if(die.getFaceValue() == Constants.ONES)
                        setAces(Constants.ONES);
                break;
            case Constants.TWOS:
                for(Die die : roll.getDice())
                    if(die.getFaceValue() == Constants.TWOS)
                        setTwos(Constants.TWOS);
                break;
            case Constants.THREES:
                for(Die die : roll.getDice())
                    if(die.getFaceValue() == Constants.THREES)
                        setThrees(Constants.THREES);
                break;
            case Constants.FOURS:
                for(Die die : roll.getDice())
                    if(die.getFaceValue() == Constants.FOURS)
                        setFours(Constants.FOURS);
                break;
            case Constants.FIVES:
                for(Die die : roll.getDice())
                    if(die.getFaceValue() == Constants.FIVES)
                        setFives(Constants.FIVES);
                break;
            case Constants.SIXES:
                for(Die die : roll.getDice())
                    if(die.getFaceValue() == Constants.SIXES)
                        setSixes(Constants.SIXES);
                break;      
        }
    }
    
    /**
     * @return the aces
     */
    public int getAces() 
    {
        return aces;
    }

    /**
     * @param aces the aces to set
     */
    public void setAces(int aces) 
    {
        this.aces += aces;
    }

    /**
     * @return the twos
     */
    public int getTwos() 
    {
        return twos;
    }

    /**
     * @param twos the twos to set
     */
    public void setTwos(int twos) 
    {
        this.twos += twos;
    }

    /**
     * @return the threes
     */
    public int getThrees() 
    {
        return threes;
    }

    /**
     * @param threes the threes to set
     */
    public void setThrees(int threes) 
    {
        this.threes += threes;
    }

    /**
     * @return the fours
     */
    public int getFours() 
    {
        return fours;
    }

    /**
     * @param fours the fours to set
     */
    public void setFours(int fours) 
    {
        this.fours += fours;
    }

    /**
     * @return the fives
     */
    public int getFives() 
    {
        return fives;
    }

    /**
     * @param fives the fives to set
     */
    public void setFives(int fives) 
    {
        this.fives += fives;
    }

    /**
     * @return the sixes
     */
    public int getSixes() 
    {
        return sixes;
    }

    /**
     * @param sixes the sixes to set
     */
    public void setSixes(int sixes) 
    {
        this.sixes += sixes;
    }

    /**
     * @return the totalScore
     */
    public int getTotalScore() 
    {
        totalScore = getAces() + getTwos() + getThrees() + getFours() + 
                      getFives() + getSixes();
        
        if(totalScore >= Constants.BONUS_SCORE)
            setBonus(Constants.UPPER_BONUS);
        
        return totalScore;
    }

    /**
     * @param totalScore the totalScore to set
     */
    public void setTotalScore(int totalScore) 
    {
        this.totalScore = totalScore;
    }

    /**
     * @return the bonus
     */
    public int getBonus() 
    {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(int bonus) 
    {
        this.bonus = bonus;
    }

    /**
     * @return the total
     */
    public int getTotal() 
    {
        total = getTotalScore() + getBonus();
        
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) 
    {
        this.total = total;
    }
}
