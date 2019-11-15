package com.improving.uno;

import com.improving.uno.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Deck deck;
    private List<Player> players = new ArrayList<>();
    private Card card;
    private static int amountOfTurnsInGame = 0;
    int numOfPlayers;
    int turnOrder = 0;
    int currentPlayer= 0;


    public Game() {
        this.deck = new Deck();
        this.players.add(new Player(deck, "Player1"));
        this.players.add(new Player(deck, "Player2"));
        this.players.add(new Player(deck, "Player3"));
        numOfPlayers = players.size();


    }
    public void play() {
        deck.getDiscard().add(deck.draw());
        if (deck.getTopDiscardCard().getColor() == null){
            deck.getTopDiscardCard().setColor(Colors.values()[new Random().nextInt(4)]);
        }
        System.out.println("        NUMBER OF PLAYERS: " + players.size());
        System.out.println("    STARTING CARD: " + deck.getDiscard().getLast() );
        System.out.println(">>DISCARD pile size: " + deck.getDiscard().size() +" " +
                "||  >>DRAW pile size: " + deck.getCards().size()+"\n");
        System.out.print("   ---------------------------\n");

        while (gameInProgress() == true) {
            if (turnOrder < 0) {
                turnOrder = turnOrder + numOfPlayers;
            }
            currentPlayer = turnOrder % numOfPlayers;
            players.get(turnOrder).takeTurn(this);
                System.out.println(">>DISCARD pile size: "+deck.getDiscard().size() +" " +
                        "||  >>DRAW pile size: " + deck.getCards().size()+"\n");
                amountOfTurnsInGame++;
                //currentPlayer.handSize();
                if (players.get(turnOrder).handSize() == 0){
                    System.out.println("Total Number of Turns: "+amountOfTurnsInGame);
                    break;
                }
            turnOrder++;
        }
        }
    public boolean gameInProgress() {
        for (Player player : players) {
            if (player.handSize() == 0) {
                printGameResults();
                return false;
            }
        }
        return true;
    }
    private void printGameResults() {
        System.out.println(
                "=====Game Over=====\n" + " "+
                        gameWinner() + " has won!");
    }
    public static boolean isPlayable(Deck deck, Card card) {
        if (deck.getTopDiscardCard().getColor() == card.getColor() ||
                deck.getTopDiscardCard().getFace() == card.getFace() ||
                card.getFace() == Faces.Wild ||
                card.getFace() == Faces.WildDrawFour) {
            return true;
        }
        return false;
    }
    public String gameWinner() {
        for (Player player : players) {
            if (player.handSize() == 0) {
                return player.getName();
            }
        }
        return "NO WINNER!";
    }
    public Deck getDeck() {
        return deck;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public List<Player> getPlayer() {
        return players;
    }

    //add playcard method
    //<optional>- .isPresent- only when playing a whild card, naming a color
}




