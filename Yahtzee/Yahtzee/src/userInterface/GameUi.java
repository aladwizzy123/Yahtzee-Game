/*
 * Alaeldein
 */
package userInterface;
//at 5:10 in video
import constants.Constants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;


public class GameUi extends JPanel
{
    private BoxLayout boxLayout;
    private ImageIcon logoImage;
    private JLabel maxTurn;
    private JLabel gameTurn;
    private JLabel logo;
    
    public GameUi()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        
        this.setLayout(boxLayout);
        this.setMinimumSize(new Dimension(250, 50));
        this.setPreferredSize(new Dimension(250, 50));
        this.setMaximumSize(new Dimension(250, 50));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        
        logo = new JLabel();
        logo.setMinimumSize(new Dimension(150, 150));
        logo.setPreferredSize(new Dimension(150, 150));
        logo.setMaximumSize(new Dimension(150, 150));
        //this code worked for me
        logoImage = new ImageIcon( getClass().getResource("/images/yahtzee.jpg"));
        logoImage = imageResize(logoImage);
        logo.setIcon(logoImage);
//        

        gameTurn = new JLabel();
        gameTurn.setText(String.valueOf("0"));
        gameTurn.setMinimumSize(new Dimension(30,30));
        gameTurn.setPreferredSize(new Dimension(30, 30));
        gameTurn.setMaximumSize(new Dimension(30, 30));
        gameTurn.setFont(new Font("Serif", Font.BOLD, 16));

        maxTurn = new JLabel();
        maxTurn.setMinimumSize(new Dimension(30, 30));
        maxTurn.setPreferredSize(new Dimension(30, 30));
        maxTurn.setMaximumSize(new Dimension(30, 30));
        maxTurn.setText(" / " + String.valueOf(Constants.NUM_CATERGORY));
        maxTurn.setFont(new Font("Serif", Font.BOLD, 16));

        this.add(logo);
        this.add(gameTurn);
        this.add(maxTurn);
    }
   
    public void setGameTurnValue(int value)
    {
        gameTurn.setText(String.valueOf(value));
    }
    
    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
}