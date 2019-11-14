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
    public Game() {
        this.deck = new Deck();
        this.players.add(new Player(deck, "Player1"));
        this.players.add(new Player(deck, "Player2"));
        this.players.add(new Player(deck, "Player3"));
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
            for (Player player : players) {
                player.takeTurn(this);
                System.out.println(">>DISCARD pile size: "+deck.getDiscard().size() +" " +
                        "||  >>DRAW pile size: " + deck.getCards().size()+"\n");
                amountOfTurnsInGame++;
                player.handSize();
                if (player.handSize() == 0){
                    System.out.println("Total Number of Turns: "+amountOfTurnsInGame);
                    break;
                }
            }
        }
    }
    public boolean gameInProgress() {
        for (Player player : players) {
            if (player.handSize() == 0) {
                printGOverNWinner();
                return false;
            }
        }
        return true;
    }
    private void printGOverNWinner() {
        System.out.println(
                "=====Game Over=====\n" + " "+
                        winnerWinner() + " has won!");
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
    public String winnerWinner() {
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
}




