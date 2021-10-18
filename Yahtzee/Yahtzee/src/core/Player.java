/*
 * Alaeldein
 */

package core;

public abstract class Player implements IPlayer
{
    private String name;
    private ScoreCard score;
    private boolean finished = false;
    private boolean catSelected = false;
    private Roll rollDice;
    private int roll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScoreCard getScore() {
        return score;
    }

    public void setScore(ScoreCard score) {
        this.score = score;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isCatSelected() {
        return catSelected;
    }

    public void setCatSelected(boolean catSelected) {
        this.catSelected = catSelected;
    }

    public Roll getRollDice() {
        return rollDice;
    }

    public void setRollDice(Roll rollDice) {
        this.rollDice = rollDice;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
            public void incrementRoll()
            {
                if(roll < 3)
                {
                    finished = false;
                    roll++;
                }
                else
                {
                    finished = true;
                }
            }
    public abstract void selectDice(Roll original, Roll keep, int rollNum);
    public abstract void calculateScore(Roll keep, int category);
}
