/*
 * Alaeldein
 */
package userInterface;

import constants.Constants;
import core.Player;
import core.UpperSection;
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

public class UpperSectionUi extends JPanel
{
    private ArrayList<JButton> categories;
    private ArrayList<JLabel> scores;
    private JLabel total;
    private JLabel bonus;
    private JLabel totalScore;
    private SelectionListener selectionListener;
    private Player player;
    private static int CATS = 6;
    private static int ROWS = 9;
    private static int COLS = 2;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints constraints;
    private UpperSection upperSection;
    
    public UpperSectionUi()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        // layout manager
        gridBagLayout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        // setting up JPanel
        this.setLayout(gridBagLayout);        
        this.setMinimumSize(new Dimension(500, 300));
        this.setPreferredSize(new Dimension(500, 300));
        this.setMaximumSize(new Dimension(500, 300));
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        categories = new ArrayList();
        scores = new ArrayList();
        
        selectionListener = new SelectionListener();
               
        for(int i = 0; i < CATS; i++)
        {
            JButton category = new JButton();
            category.setMinimumSize(new Dimension(300, 35));
            category.setPreferredSize(new Dimension(300, 35));
            category.setMaximumSize(new Dimension(300, 35));
            category.addActionListener(selectionListener);
            switch(i)
            {
                case 0:
                    category.setText("ACES");
                    category.putClientProperty("category", Constants.ONES);
                    break;
                case 1:
                    category.setText("TWOS");
                    category.putClientProperty("category", Constants.TWOS);
                    break;
                case 2:
                    category.setText("THREES");
                    category.putClientProperty("category", Constants.THREES);
                    break;
                case 3:
                    category.setText("FOURS");
                    category.putClientProperty("category", Constants.FOURS);
                    break;
                case 4:
                    category.setText("FIVES");
                    category.putClientProperty("category", Constants.FIVES);
                    break;
                case 5:
                    category.setText("SIXES");
                    category.putClientProperty("category", Constants.SIXES);
                    break;
                default:
                    break;
            }
            categories.add(category);

            JLabel score = new JLabel(String.valueOf(Constants.ZERO));
            score.setMinimumSize(new Dimension(50, 35));
            score.setMaximumSize(new Dimension(50, 35));
            score.setPreferredSize(new Dimension(50, 35));
            score.setVerticalTextPosition(JLabel.CENTER);

            scores.add(score);
        }
           //may need total lower and total upper here 6:28   
        totalScore = new JLabel("TOTAL SCORE");
        totalScore.setMinimumSize(new Dimension(300, 35));
        totalScore.setPreferredSize(new Dimension(300, 35));
        totalScore.setMaximumSize(new Dimension(300, 35));

        bonus = new JLabel();
        bonus.setText("BONUS");
        bonus.setMinimumSize(new Dimension(300, 35));
        bonus.setPreferredSize(new Dimension(300, 35));
        bonus.setMaximumSize(new Dimension(300, 35));
        
        total = new JLabel("TOTAL of Upper Section");
        total.setMinimumSize(new Dimension(300, 35));
        total.setPreferredSize(new Dimension(300, 35));
        total.setMaximumSize(new Dimension(300, 35));
        int row = 0;
        for(JButton category : categories)
        {
            addComponent(0, row, 1, 1, this, category);
            row++;
        }
        row = 0;
        for(JLabel score : scores)
        {
            addComponent(1, row, 1, 1, this, score);
            row++;
        }
        addComponent(1, row++, 1, 1, this, totalScore);
        addComponent(1, row++, 1, 1, this, bonus);
        addComponent(1, row++, 1, 1, this, total);
    }
     private void addComponent(int x, int y, int w, int h, Container aContainer, Component aComponent)
     {
         constraints.gridx = x;
         constraints.gridy = y;
         constraints.gridwidth = w;
         constraints.gridheight = h;
         
         gridBagLayout.setConstraints( aComponent, constraints);
         
         aContainer.add( aComponent);
         
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
         
         for(JLabel score:scores)
         {
             switch(counter)
             {
                 case 0:
                     score.setText(String.valueOf(player.getScore().getUpper().getAces()));
                     break;
                  case 1:
                     score.setText(String.valueOf(player.getScore().getUpper().getTwos()));
                     break;    
                   case 2:
                     score.setText(String.valueOf(player.getScore().getUpper().getThrees()));
                     break;   
                   case 3:
                     score.setText(String.valueOf(player.getScore().getUpper().getFours()));
                     break;   
                    case 4:
                     score.setText(String.valueOf(player.getScore().getUpper().getFives()));
                     break;  
                     case 5:
                     score.setText(String.valueOf(player.getScore().getUpper().getSixes()));
                     break; 
                      case 6:
                     score.setText(String.valueOf(player.getScore().getUpper().getTotalScore()));
                     break;
             }
             counter++;
         }
     }

    public UpperSection getUpperSection() {
        return upperSection;
    }

    public void setUpperSection(UpperSection upperSection) {
        this.upperSection = upperSection;
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
                 //check em
                 upperSection.evaluateCategory(player.getRollDice(), category);
                 
                 player.setCatSelected(true);
                 player.setFinished(true);
                 
                 updateUi();
             }
         }
     }
     
    }

