/*
 * Alaeldein
 */

package userInterface;

import constants.Constants;
import core.Game;
import core.Player;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.MenuListener;

public class YahtzeeUi
{
    private GameUi gameUi;
    private PlayerUi playerUi;
    private RollUi rollUi;
    private ScoreCardUi scoreCardUi;
    private Game theGame;
    
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu game;
    private JMenuItem exit;
    private JMenuItem newGame;
    private JPanel rightPanel;
    private MenuListener menuListener;
    
    public YahtzeeUi()
    {
        initComponents();
        theGame = new Game(this);
        startGame();
    }
    
    private void initComponents()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Yahtzee!");
        frame.setMinimumSize(new Dimension(700, 725));
        frame.setPreferredSize(new Dimension(700, 725));
        frame.setMaximumSize(new Dimension(700, 725));
        
        // Initialize the JMenuBar and add to the JFrame
        createMenu();
        
        // Add everything to the JFrame
        frame.setJMenuBar(menuBar);
        
        // setting up the game
        setGameUi(new GameUi());
        setPlayerUi( new PlayerUi());      
        setRollUi( new RollUi());
        
        rightPanel = new JPanel();
        rightPanel.setMinimumSize(new Dimension(250, 600));
        rightPanel.setPreferredSize(new Dimension(250, 600));
        rightPanel.setMaximumSize(new Dimension(250, 600));
        rightPanel.add(getGameUi());
        rightPanel.add(getPlayerUi());
        rightPanel.add(getRollUi());
        
        setScoreCardUi( new ScoreCardUi());
        
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(scoreCardUi, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private void createMenu()
    {        
        menuListener = new MenuListener();
        
        menuBar = new JMenuBar();
        game = new JMenu("Yahtzee");
        game.setMnemonic('Y');
        
        newGame = new JMenuItem("New Game");
        newGame.setMnemonic('N');
        newGame.addActionListener(menuListener);
        
        exit = new JMenuItem("Exit");
        exit.setMnemonic('E');
        exit.addActionListener(menuListener);

        game.add(newGame);    
        game.add(exit);    
        
        menuBar.add(game);
    }
    private void resetUi()
    {
        for(Player player : theGame.getPlayers())
        {
            player.getScore().getUpper().setAces(Constants.ZERO);
             player.getScore().getUpper().setTwos(Constants.ZERO);
              player.getScore().getUpper().setThrees(Constants.ZERO);
               player.getScore().getUpper().setFours(Constants.ZERO);
                player.getScore().getUpper().setFives(Constants.ZERO);
                 player.getScore().getUpper().setSixes(Constants.ZERO);
 player.getScore().getLower().setFourKind(Constants.ZERO);
  player.getScore().getLower().setChance(Constants.ZERO);
   player.getScore().getLower().setFullHouse(Constants.ZERO);
    player.getScore().getLower().setLargeStraight(Constants.ZERO);
     player.getScore().getLower().setSmallStraight(Constants.ZERO);
      player.getScore().getLower().setThreeKind(Constants.ZERO);
       player.getScore().getLower().setTotalScore(Constants.ZERO);
        player.getScore().getLower().setYahtzee(Constants.ZERO);
         player.getScore().getLower().setYahtzeeBonus(Constants.ZERO);
         
     
           
        }
    }
        private void startGame()
    {
        theGame.playGame();
    }

    public GameUi getGameUi() {
        return gameUi;
    }

    public void setGameUi(GameUi gameUi) {
        this.gameUi = gameUi;
    }

    public PlayerUi getPlayerUi() {
        return playerUi;
    }

    public void setPlayerUi(PlayerUi playerUi) {
        this.playerUi = playerUi;
    }

    public RollUi getRollUi() {
        return rollUi;
    }

    public void setRollUi(RollUi rollUi) {
        this.rollUi = rollUi;
    }

    public ScoreCardUi getScoreCardUi() {
        return scoreCardUi;
    }

    public void setScoreCardUi(ScoreCardUi scoreCardUi) {
        this.scoreCardUi = scoreCardUi;
    }

    public Game getTheGame() {
        return theGame;
    }

    public void setTheGame(Game theGame) {
        this.theGame = theGame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getGame() {
        return game;
    }

    public void setGame(JMenu game) {
        this.game = game;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public void setExit(JMenuItem exit) {
        this.exit = exit;
    }

    public JMenuItem getNewGame() {
        return newGame;
    }

    public void setNewGame(JMenuItem newGame) {
        this.newGame = newGame;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public MenuListener getMenuListener() {
        return menuListener;
    }

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

  
    private class MenuListener implements ActionListener
            {
        @Override
                public void actionPerformed(ActionEvent ae)
                {
                    int result = 0;
                            if(ae.getSource() instanceof JMenuItem)
                            {
                                if(ae.getActionCommand().equals("Exit"))
                                    System.exit(0);
                                else if(ae.getActionCommand().equals("New Game"))
                                {
        result = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new gmae");
        if(result == JOptionPane.YES_OPTION)
        {
            resetUi();
            startGame();
        }
                                }
                            }
                }
            }
    }
