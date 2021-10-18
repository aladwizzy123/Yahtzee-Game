/*
 * Alaeldein
 */

package userInterface;

import constants.Constants;
import core.Die;
import core.Player;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import javax.swing.*;

public class RollUi extends JPanel
{
    private ArrayList<JToggleButton> dice;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints constraints;
    private JButton rollButton;
    private Player player;
    private ImageIcon dieImage;
    private DieListener dieListener;
    
    public RollUi()
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
        this.setMinimumSize(new Dimension(200, 480));
        this.setPreferredSize(new Dimension(200, 480));
        this.setMaximumSize(new Dimension(200, 480));
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        dice = new ArrayList();
              
        dieListener = new DieListener();

        // creating the dice buttons
        for(int i = 0; i < Constants.NUM_DICE; i++)
        {
            JToggleButton die = new JToggleButton();
            die.setMinimumSize(new Dimension(65, 65));
            die.setPreferredSize(new Dimension(65, 65));
            die.setMaximumSize(new Dimension(65, 65));
            die.setAlignmentX(Component.CENTER_ALIGNMENT);            
            die.putClientProperty("die", (i + 1));
            die.putClientProperty("value", (i + 1));
            die.putClientProperty("selected", false);
            die.addActionListener(dieListener);
            addImage(die);
            dice.add(die);
           // dice.add(die);
            
           // this.add(die);
           addComponent(0, i, 1, 1, this, die);
        }
        
        // creating the roll button
        rollButton = new JButton("Roll Dice");
        rollButton.setMinimumSize(new Dimension(100, 100));
        rollButton.setPreferredSize(new Dimension(100, 100));
        rollButton.setMaximumSize(new Dimension(100, 100));
        rollButton.setFont(new Font("Serif", Font.BOLD, 16));
        rollButton.addActionListener(new RollListener());
        //this.add(rollButton);
        addComponent(0, 7, 1, 1, this, rollButton);
    }
    
        // method for adding the images to the dice buttons
    private void addImage(JToggleButton die)
    {
        int value = (int)die.getClientProperty("value");
      //  ImageIcon dieImage = new ImageIcon();

        switch(value)
        {
            case 1:
                dieImage = new ImageIcon( getClass().getResource("../images/one.png"));
                break;
            case 2:
                dieImage = new ImageIcon( getClass().getResource("../images/two.png"));
                break;
            case 3:
                dieImage = new ImageIcon( getClass().getResource("../images/three.png"));
                break;
            case 4:
                dieImage = new ImageIcon( getClass().getResource("../images/four.png"));
                break;
            case 5:
                dieImage = new ImageIcon( getClass().getResource("../images/five.png"));
                break;
            case 6:
                dieImage = new ImageIcon( getClass().getResource("../images/six.png"));
                break;
        }
        
        dieImage = imageResize(dieImage);
        die.setIcon(dieImage);
    }
    // method to resize the image to fit on the jbutton
    private void addComponent( int x, int y, int w, int h, Container aContainer, Component aComponent)
    {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        if(y == 7)
        {
            constraints.insets = new Insets(8, 8, 8, 8);
        }
        else
        {
            constraints.insets = new Insets(3, 3, 3, 3);
        }
        gridBagLayout.setConstraints( aComponent, constraints);
        aContainer.add( aComponent);
    }
    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
    public void setDice(ArrayList<Die> diceData)
    {
        int index = 0;
        for(JToggleButton die: dice)
        {
            die.putClientProperty("value", diceData.get(index).getFaceValue());
            addImage(die);
            index++;
        }
    }
    /**
     * 
     * @return the player
     */
//    public Player getPlayer(){
//        return player;
//    }
    /**
     * 
     * @param player the player to set 
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }
        public void resetSelectedDice()
            {
                System.out.println("reseting your mistakes");
                for(JToggleButton button : dice)
                {
                    if(button.isSelected())
                    {
                        button.setBorder(BorderFactory.createLineBorder(null));
                        button.putClientProperty("selected", false);
                    }
                }
                rollButton.setEnabled(true);
            }
    private class DieListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            int die = 0;
            int value = 0;
            boolean selected = false;

            if(ae.getSource() instanceof JToggleButton)
            {
                JToggleButton button = (JToggleButton)ae.getSource();
                die = (int)button.getClientProperty("die");
                value = (int)button.getClientProperty("value"); 
                                
                selected = button.isSelected();
                
                if(selected)
                {
                    button.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                    button.putClientProperty("selected", true);
                }
                else
                {
                    button.setBorder(BorderFactory.createLineBorder(null));                
                    button.putClientProperty("selected", false);
                }
            }            
        }        
    }

    private class RollListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            // this rolls all dice regardless of which are selected
            //error here
            ArrayList<Die> diceData = player.getRollDice().getDice();

            int counter = 0;

            player.incrementRoll();
            
            if(player.getRoll() < 3)
            {
            for(JToggleButton die: dice)
            {
                boolean selected = (boolean)die.getClientProperty("selected");
                if(!selected)
                {
                    diceData.get(counter).rollDie();
                }
                counter++;
            }
            }
        else
        {
            rollButton.setEnabled(false);
        }
            
            setDice(player.getRollDice().getDice());
        }    
    }

}
