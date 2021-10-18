/*
 * Alaeldein
 */

package core;

import constants.Constants;
import java.util.ArrayList;
import java.util.Collections;

public class LowerSection 
{
    private int threeKind;
    private int fourKind;
    private int fullHouse;
    private int smStraight;
    private int lgStraight;
    private int yahtzee;
    private int chance;
    private int yahtzeeBonus;
    private int totalScore;
    private ArrayList<Roll> smallStraights;
    private ArrayList<Roll> largeStraights;
    
    private void createValidStraights()
    {
        // small straight has to be 1, 2, 3, 4 ||
        //                          2, 3, 4, 5 ||
        //                          3, 4, 5, 6  
        smallStraights = new ArrayList();
        
        // large straight has to be 1, 2, 3, 4, 5 ||
        //                          2, 3, 4, 5, 6
        largeStraights = new ArrayList();
        
//        for()
    }
    
    private boolean checkThreeKind(Roll roll)
    {
        boolean threeKind = false;

        // after the dice are sorted, a valid three of a kind would exist
        // at indexes 0, 1, 2 ||
        //            1, 2, 3 ||
        //            2, 3, 4

        if((roll.getDice().get(Constants.ZERO).getFaceValue() == 
            roll.getDice().get(Constants.TWOS).getFaceValue()) ||
           (roll.getDice().get(Constants.ONES).getFaceValue() == 
            roll.getDice().get(Constants.THREES).getFaceValue()) ||
           (roll.getDice().get(Constants.TWOS).getFaceValue() == 
            roll.getDice().get(Constants.FOURS).getFaceValue()))
        {
            threeKind = true;
        }
        else
        {
            threeKind = false;
        }
        
        return threeKind;
    }
    
    
    public void evaluateCategory(Roll roll, int category)
    {
        switch(category)
        {
            case Constants.THREE_KIND:
                System.out.println("Checking Three of a Kind");
                // counter for die
                roll.sortDice();
                
                // after the dice are sorted, a valid three of a kind would exist
                // at indexes 0, 1, 2 ||
                //            1, 2, 3 ||
                //            2, 3, 4

                if(checkThreeKind(roll))
                {
                    for(Die die : roll.getDice())
                        setThreeKind(die.getFaceValue());
                }
                else
                {
                    setThreeKind(Constants.ZERO);
                }
                
                break;
            case Constants.FOUR_KIND:
                System.out.println("Checking Four of a Kind");

                roll.sortDice();

                // after the dice are sorted, a valid three of a kind would exist
                // at indexes 0, 1, 2, 3 ||
                //            1, 2, 3, 4 
                              
                // did they really get three of a kind
                if((roll.getDice().get(Constants.ZERO).getFaceValue() == 
                    roll.getDice().get(Constants.THREES).getFaceValue()) ||
                   (roll.getDice().get(Constants.ONES).getFaceValue() == 
                    roll.getDice().get(Constants.FOURS).getFaceValue()))
                {
                    for(Die die : roll.getDice())
                        setFourKind(die.getFaceValue());
                }
                else
                    setFourKind(Constants.ZERO);
                
                break;
            case Constants.FULL:
                // full house would be:
                // 1. the pair as the first two dice or last two dice
                // 2. three of a kind at beginning or end
                System.out.println("Checking Full House");

                roll.sortDice();
                
                // after the dice are sorted, a valid three of a kind would exist
                // at indexes 0, 1, 2 ||
                //            1, 2, 3 ||
                //                   
                // after the dice are sorted, a valid pair would exist
                // at indexes 0, 1 ||
                //            3, 4
                
                boolean threeKind = false;
                boolean pair = false;
                
                if(checkThreeKind(roll))
                    threeKind = true;
                else
                    threeKind = false;
                
                if(roll.getDice().get(Constants.ZERO).getFaceValue() == 
                   roll.getDice().get(Constants.ONES).getFaceValue() || 
                   roll.getDice().get(Constants.THREES).getFaceValue() == 
                   roll.getDice().get(Constants.FOURS).getFaceValue())
                    pair = true;
                else
                    pair = false;
                    
                    
                if(pair && threeKind)
                    setFullHouse(Constants.FULL_HOUSE);
                else
                    setFullHouse(Constants.ZERO);
                
                break;
            // small straight has to be 1, 2, 3, 4 ||
            //                          2, 3, 4, 5 ||
            //                          3, 4, 5, 6    
            case Constants.SMALL_STR:                
                System.out.println("Checking Small Straight");
                roll.sortDice();

                boolean isStrght = false;
                int falseCnt = 0;

                // get the first die value
                int cmprDieVal = roll.getDice().get(Constants.ZERO).getFaceValue();

                for(int d = 1; d < roll.getDice().size(); d++)
                {
                    // they are the same value, skip it
                    if(cmprDieVal == roll.getDice().get(d).getFaceValue())
                    {
                        falseCnt++;
                        continue;
                    }
                    else                        
                        if((cmprDieVal + 1) == roll.getDice().get(d).getFaceValue())
                            isStrght = true;
                        else
                        {
                            isStrght = false;
                            falseCnt++;
                        }
                                        
                    cmprDieVal = roll.getDice().get(d).getFaceValue();
                }

                if(!isStrght || falseCnt > Constants.ONES)
                    setSmallStraight(Constants.ZERO);
                else
                    setSmallStraight(Constants.SM_STRAIGHT);  
                
                break;
            // large straight has to be 1, 2, 3, 4, 5 ||
            //                          2, 3, 4, 5, 6
            case Constants.LARGE_STR:
                System.out.println("Checking Large Straight");
                boolean isStraight = false;
                // sort the dice
                roll.sortDice();
                
                // get the first die value
                int compareDieVal = roll.getDice().get(Constants.ZERO).getFaceValue();

                for(int d = 1; d < roll.getDice().size(); d++)
                {
                    if((compareDieVal + 1) == roll.getDice().get(d).getFaceValue())
                        isStraight = true;
                    else
                    {
                        isStraight = false;
                        break;
                    }
                    
                    compareDieVal = roll.getDice().get(d).getFaceValue();
                }
                
                if(isStraight)
                    setLargeStraight(Constants.LG_STRAIGHT);
                else
                    setLargeStraight(Constants.ZERO);
                
                break;
            case Constants.YATZEE_CAT:
                System.out.println("Checking Yahtzee");
                // counter for die
                boolean yahtzee = false;
                
                // if the first and the last die match, it is a Yahtzee
                // if yes, then a Yatzee
                // if no, then zero points
                if(roll.getDice().get(Constants.ZERO).getFaceValue() == 
                   roll.getDice().get(Constants.FOURS).getFaceValue())
                        yahtzee = true;
                
                // did they really get a Yahtzee?
                // did they have one before?
                if(yahtzee && getYahtzee() == Constants.ZERO)
                    setYahtzee(Constants.YAHTZEE);
                else if (yahtzee && getYahtzee() >= Constants.YAHTZEE)
                    setYahtzeeBonus(Constants.YAHTZEE_BONUS);
               
                break;     
            case Constants.CHANCE:
                System.out.println("Checking Chance");

                for(Die die : roll.getDice())
                    setChance(die.getFaceValue());
                break; 
        }
    }
    
    /**
     * @return the threeKind
     */
    public int getThreeKind() 
    {
        return threeKind;
    }

    /**
     * @param threeKind the threeKind to set
     */
    public void setThreeKind(int threeKind) 
    {
        this.threeKind += threeKind;
    }

    /**
     * @return the fourKind
     */
    public int getFourKind() 
    {
        return fourKind;
    }

    /**
     * @param fourKind the fourKind to set
     */
    public void setFourKind(int fourKind) 
    {
        this.fourKind += fourKind;
    }

    /**
     * @return the fullHouse
     */
    public int getFullHouse() 
    {
        return fullHouse;
    }

    /**
     * @param fullHouse the fullHouse to set
     */
    public void setFullHouse(int fullHouse) 
    {
        this.fullHouse = fullHouse;
    }

    /**
     * @return the smallStraight
     */
    public int getSmallStraight() 
    {
        return smStraight;
    }

    /**
     * @param smallStraight the smallStraight to set
     */
    public void setSmallStraight(int smallStraight) 
    {
        this.smStraight = smallStraight;
    }

    /**
     * @return the largeStraight
     */
    public int getLargeStraight() 
    {
        return lgStraight;
    }

    /**
     * @param largeStraight the largeStraight to set
     */
    public void setLargeStraight(int largeStraight) 
    {
        this.lgStraight = largeStraight;
    }

    /**
     * @return the yahtzee
     */
    public int getYahtzee() 
    {
        return yahtzee;
    }

    /**
     * @param yahtzee the yahtzee to set
     */
    public void setYahtzee(int yahtzee) 
    {
        this.yahtzee = yahtzee;
    }

    /**
     * @return the chance
     */
    public int getChance() 
    {
        return chance;
    }

    /**
     * @param chance the chance to set
     */
    public void setChance(int chance) 
    {
        this.chance += chance;
    }

    /**
     * @return the yahtzeeBonus
     */
    public int getYahtzeeBonus() 
    {
        return yahtzeeBonus;
    }

    /**
     * @param yahtzeeBonus the yahtzeeBonus to set
     */
    public void setYahtzeeBonus(int yahtzeeBonus) 
    {
        this.yahtzeeBonus = yahtzeeBonus;
    }

    /**
     * @return the totalScore
     */
    public int getTotalScore() 
    {
        totalScore = getChance() + getFourKind() + getFullHouse() + 
                      getLargeStraight() + getSmallStraight() + getThreeKind() +
                      getYahtzee() + getYahtzeeBonus();
        
        return totalScore;
    }

    /**
     * @param totalScore the totalScore to set
     */
    public void setTotalScore(int totalScore) 
    {
        this.totalScore = totalScore;
    }
}
