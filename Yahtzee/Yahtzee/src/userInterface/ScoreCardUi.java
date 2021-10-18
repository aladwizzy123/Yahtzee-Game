/*
 * Alaeldein
 */

package userInterface;

import core.Player;
import java.awt.Dimension;

import javax.swing.*;

public class ScoreCardUi extends JPanel
{
    private BoxLayout boxLayout;
    private JLabel grandTotal;    
    private LowerSectionUi lowerUi;
    private UpperSectionUi upperUi;
    private Player player;
    
    public ScoreCardUi()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        this.setLayout(boxLayout);
        this.setMinimumSize(new Dimension(300, 500));
        this.setPreferredSize(new Dimension(300, 500));
        this.setMaximumSize(new Dimension(300, 500));
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        upperUi = new UpperSectionUi();
        lowerUi = new LowerSectionUi();

        grandTotal = new JLabel("GRAND TOTAL                                        0");
        grandTotal.setMinimumSize(new Dimension(300, 25));
        grandTotal.setPreferredSize(new Dimension(300, 25));
        grandTotal.setMaximumSize(new Dimension(300, 25));
       
        this.add(upperUi);
        this.add(lowerUi);
        this.add(grandTotal);
    }

    public BoxLayout getBoxLayout() {
        return boxLayout;
    }

    public void setBoxLayout(BoxLayout boxLayout) {
        this.boxLayout = boxLayout;
    }

    public JLabel getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(JLabel grandTotal) {
        this.grandTotal = grandTotal;
    }

    public LowerSectionUi getLowerUi() {
        return lowerUi;
    }

    public void setLowerUi(LowerSectionUi lowerUi) {
        this.lowerUi = lowerUi;
    }

    public UpperSectionUi getUpperUi() {
        return upperUi;
    }

    public void setUpperUi(UpperSectionUi upperUi) {
        this.upperUi = upperUi;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        
        lowerUi.setPlayer(this.player);
        lowerUi.setLowerSection(this.player.getScore().getLower());
        
        upperUi.setPlayer(this.player);
        upperUi.setUpperSection(this.player.getScore().getUpper());
        
        updateUi();
    }
    private void updateUi()
    {
        lowerUi.updateUi();
        upperUi.updateUi();
    }
}
