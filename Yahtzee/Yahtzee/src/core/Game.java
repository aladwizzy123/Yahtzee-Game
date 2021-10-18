/*
 * Alaeldein
 */

package core;

import constants.Constants;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import userInterface.YahtzeeUi;

public class Game 
{
    private int gameTurn;
    private ArrayList<Player> players;
    private YahtzeeUi yahtzeeUi;
    private Roll dice;
    private Player currentPlayer;

    public Game(YahtzeeUi ui)
    {
        yahtzeeUi = ui;
        createPlayers();
//        displayPlayers();
setGameTurn(Constants.ZERO);
JOptionPane.showMessageDialog(null,"Let's play Yahtzee");

//playGame();
    }
    
    private void createPlayers()
    {
        // instantiate the players ArrayList
        players = new ArrayList();
        
        for(int p = 0; p<Constants.TWOS; p++)
        {
            String name = JOptionPane.showInputDialog("Enter human player name");
            
       
        // get the human player name
       

        HumanPlayer hp = new HumanPlayer();
        hp.setName(name);   
              
        // add players to array 
        players.add(hp);
       
        }
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public void setGameTurn(int gameTurn) {
        this.gameTurn = gameTurn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public YahtzeeUi getYahtzeeUi() {
        return yahtzeeUi;
    }

    public void setYahtzeeUi(YahtzeeUi yahtzeeUi) {
        this.yahtzeeUi = yahtzeeUi;
    }

    public Roll getDice() {
        return dice;
    }

    public void setDice(Roll dice) {
        this.dice = dice;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }

    private void displayPlayers()
    {
//        System.out.println("***************************");
        System.out.println("Players for this game are: ");
//        System.out.println("***************************");
//
        // loop through players and display information
        for(Player player : players)
        {
            System.out.println(player.getName());
        }
    }
        private void switchPlayers()
        {
            currentPlayer.setFinished(false);
            currentPlayer.setCatSelected(false);
            currentPlayer.setRoll(0);
            yahtzeeUi.getRollUi().resetSelectedDice();
            
            if(currentPlayer == players.get(Constants.ZERO))
            {
                currentPlayer = players.get(Constants.ZERO);
             
            }
            else if(currentPlayer == players.get(Constants.ONES))
                    {
                        currentPlayer = players.get(Constants.ZERO);
                    }
        }
    public void playGame()
    {
        dice = new Roll();
        currentPlayer = players.get(0);
//        yahtzeeUi.getGameUi().setGameTurnValue(turn + 1);
        for(int turn = 0; turn < Constants.NUM_CATERGORY; turn++)
        {
            yahtzeeUi.getGameUi().setGameTurnValue(turn + 1);
            for(int p = 0;p < 2; p++)
            {currentPlayer.setRollDice(dice);
            currentPlayer.rollDice(dice);
            yahtzeeUi.getRollUi().setDice(dice.getDice());
            JOptionPane.showMessageDialog(yahtzeeUi.getRollUi(), currentPlayer.getName() + "please roll the dice");
            while(!currentPlayer.isFinished())
            {
                yahtzeeUi.getPlayerUi().setPlayerName(currentPlayer.getName());
                yahtzeeUi.getScoreCardUi().setPlayer(currentPlayer);
                yahtzeeUi.getRollUi().setPlayer(currentPlayer);
                yahtzeeUi.getPlayerUi().setPlayerName(currentPlayer.getName());
            }
            while(!currentPlayer.isCatSelected())
            {
                
            }
            switchPlayers();
            }
        }
    }
}



        
     