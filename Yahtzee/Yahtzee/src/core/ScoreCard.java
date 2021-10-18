/*
 * Alaeldein
 */

package core;

public class ScoreCard 
{
    private UpperSection upper;
    private LowerSection lower;
    
    private int grandTotal;

    public ScoreCard()
    {
        upper = new UpperSection();
        lower = new LowerSection();
    }
    
    public UpperSection getUpper() 
    {
        return upper;
    }

    public void setUpper(UpperSection upper) 
    {
        this.upper = upper;
    }

    public LowerSection getLower() 
    {
        return lower;
    }

    public void setLower(LowerSection lower) 
    {
        this.lower = lower;
    }

    /**
     * @return the grandTotal
     */
    public int getGrandTotal() 
    {
        grandTotal += getUpper().getTotal() + getLower().getTotalScore();
        
        return grandTotal;
    }

    /**
     * @param grandTotal the grandTotal to set
     */
    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }
}
