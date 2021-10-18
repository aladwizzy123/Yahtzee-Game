/*
 * Alaeldein
 */

package userInterface;

import constants.Constants;

import java.awt.Dimension;
import javax.swing.*;

public class PlayerUi extends JPanel
{
    private BoxLayout boxLayout;
    private JLabel playerName;
    private JLabel playerScore;
    
    public PlayerUi()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        
        this.setLayout(boxLayout);
        this.setMinimumSize(new Dimension(200, 100));
        this.setPreferredSize(new Dimension(200, 100));
        this.setMaximumSize(new Dimension(200, 100));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        
        playerName = new JLabel();
        playerName.setText("Name");
        playerName.setMinimumSize(new Dimension(100, 100));
        playerName.setPreferredSize(new Dimension(100, 100));
        playerName.setMaximumSize(new Dimension(100, 100));
        
        playerScore = new JLabel();
        playerScore.setText(String.valueOf(Constants.ZERO));
        playerScore.setMinimumSize(new Dimension(100, 100));
        playerScore.setPreferredSize(new Dimension(100, 100));
        playerScore.setMaximumSize(new Dimension(100, 100));
        
        this.add(playerName);
        this.add(playerScore);
    }
    public void setPlayerName(String name)
    {
        playerName.setText(name);
    }

    public BoxLayout getBoxLayout() {
        return boxLayout;
    }

    public void setBoxLayout(BoxLayout boxLayout) {
        this.boxLayout = boxLayout;
    }

    public JLabel getPlayerName() {
        return playerName;
    }

    public void setPlayerName(JLabel playerName) {
        this.playerName = playerName;
    }

    public JLabel getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(JLabel playerScore) {
        this.playerScore = playerScore;
    }

 

    public void setPlayerScore(String score) {
        this.playerScore.setText(score);
    }
//    yahtzeeUi.getPlayerUi().setPlayerName(currentPlayer.getName());
//yahtzeeUi.getPlayerScore().setPlayerScore(String.valueof(currentPlayer.getScore().getGrandTotal));
}
