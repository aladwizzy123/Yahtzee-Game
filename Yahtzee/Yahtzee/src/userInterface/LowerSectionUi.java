/*
 * Alaeldein
 */
package userInterface;

import constants.Constants;
import core.Die;
import core.LowerSection;
import core.Player;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import javax.swing.*;
        
public class LowerSectionUi extends JPanel
{
    private ArrayList<JButton> categories;
    private ArrayList<JLabel> scores;
    private GridBagLayout gridLayout;
    private GridBagConstraints gridConstraints;
    private JLabel totalLower;
    private JLabel totalUpper;
    private LowerSection lowerSection;
    private SelectionListener selectionListener;
    private Player player;
    private static int CATS = 7;
    private static int ROWS = 10;
    private static int COLS = 2;

    public LowerSectionUi()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        // layout manager
        gridLayout = new GridBagLayout();
        gridConstraints = new GridBagConstraints();
        // setting up JPanel
        this.setLayout(gridLayout);        
        this.setMinimumSize(new Dimension(500, 500));
        this.setPreferredSize(new Dimension(500, 500));
        this.setMaximumSize(new Dimension(500, 500));
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        categories = new ArrayList<JButton>();
        scores = new ArrayList<JLabel>();
        
        selectionListener = new SelectionListener();
        
        for(int i = 0; i < CATS; i++)
        {
            // JButton for the category
            JButton category = new JButton();
            category.setMinimumSize(new Dimension(200, 35));
            category.setPreferredSize(new Dimension(200,35));
            category.setMaximumSize(new Dimension(200, 35));
            category.addActionListener(selectionListener);
            switch(i)
            {
                //may need to add "category.setText(Constants.TRIPLE); QUAD FULL_HSE if needed
                case 0:
                    category.setText("THREE OF A KIND");
                    category.putClientProperty("category", Constants.THREE_KIND);
                    break;
                case 1:
                    category.setText("FOUR OF A KIND");
                    category.putClientProperty("category", Constants.FOUR_KIND);
                    break;
                case 2:
                    category.setText("FULL HOUSE");
                    category.putClientProperty("category", Constants.FULL_HOUSE);
                    break;
                case 3:
                    category.setText("SMALL STRAIGHT");
                    category.putClientProperty("category", Constants.SM_STRAIGHT);
                    break;
                case 4:
                    category.setText("LARGE STRAIGHT");
                    category.putClientProperty("category", Constants.LG_STRAIGHT);
                    break;
                case 5:
                    category.setText("YAHTZEE");
                    category.putClientProperty("category", Constants.YAHTZEE);
                    break;
                case 6:
                    category.setText("CHANCE");
                    category.putClientProperty("category", Constants.CHANCE);
                    break;
                case 7:
                    category.setText("YAHTZEE BONUS");
                    category.putClientProperty("category", Constants.YAHTZEE_BONUS);
                    break;
                default:
                    category.setText("No valid value");
                    break;
            }
            
            categories.add(category);
            
            JLabel score = new JLabel(String.valueOf(Constants.ZERO));
            score.setMinimumSize(new Dimension(50, 35));
            score.setMaximumSize(new Dimension(50, 35));
            score.setPreferredSize(new Dimension(50, 35));
            score.setHorizontalTextPosition(JLabel.CENTER);
            scores.add(score);
        }
              
        totalLower = new JLabel("TOTAL of Lower Section");
        totalLower.setMinimumSize(new Dimension(500, 35));
        totalLower.setPreferredSize(new Dimension(500, 35));
        totalLower.setMaximumSize(new Dimension(500, 35));



        totalUpper = new JLabel("TOTAL of Upper Section");
        totalUpper.setMinimumSize(new Dimension(500, 35));
        totalUpper.setPreferredSize(new Dimension(500, 35));
        totalUpper.setMaximumSize(new Dimension(500, 35));



//        for(int row = 0; row < categories.size(); row++)
//        {
//            this.add(categories.get(row));
//            this.add(scores.get(row));
//        }
    int row = 0;
    for(JButton category : categories)
    {
        addComponent(0, row, 1, 1, this, category);
        row++;
    }
    row = 0;
    for(JLabel score: scores)
    {
        addComponent(1, row, 1, 1, this, score);
        row++;
    }

   }
    private void addComponent(int x, int y, int w, int h, Container aContainer, Component aComponent)
    {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.gridwidth = w;
        gridConstraints.gridwidth = h;
        
        gridLayout.setConstraints( aComponent, gridConstraints);
        
        aContainer.add( aComponent);
    }

    public LowerSection getLowerSection() {
        return lowerSection;
    }

    public void setLowerSection(LowerSection lowerSection) {
        this.lowerSection = lowerSection;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void updateUi()
    {
        int counter = 0;
        for(JLabel category : scores)
        {
            switch(counter)
            {
                case 0:
                    //this may need to be score.set
                    category.setText(String.valueOf(player.getScore().getLower().getThreeKind()));
                    break;
                case 1:
                    category.setText(String.valueOf(player.getScore().getLower().getFourKind()));
                    break;
                case 2:
                    category.setText(String.valueOf(player.getScore().getLower().getFullHouse()));
                    break;
                case 3:
                    category.setText(String.valueOf(player.getScore().getLower().getSmallStraight()));
                    break;
                case 4:
                    category.setText(String.valueOf(player.getScore().getLower().getLargeStraight()));
                    break;    
                case 5:
                    category.setText(String.valueOf(player.getScore().getLower().getYahtzee()));
                    break;
                case 6:
                    category.setText(String.valueOf(player.getScore().getLower().getYahtzeeBonus()));
                    break;    
            }
            counter++;
        }
        
        
        totalLower.setText(String.valueOf(player.getScore().getLower().getTotalScore()));
        totalUpper.setText(String.valueOf(player.getScore().getUpper().getTotal()));
    }
    private class SelectionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            int category = 0;
            
            if(ae.getSource() instanceof JButton)
            {
                JButton button = (JButton)ae.getSource();
                category = (int)button.getClientProperty("category");                
                player.setCatSelected(true);
                player.setFinished(true);
                
                lowerSection.evaluateCategory(player.getRollDice(), category);
                updateUi();
            }
        }
    }
}
