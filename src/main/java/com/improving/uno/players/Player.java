package com.improving.uno.players;

import com.improving.uno.Card;
import com.improving.uno.Colors;
import com.improving.uno.Deck;
import com.improving.uno.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.improving.uno.Game.isPlayable;

public class Player implements PlayerInterface {
    public static int takeTurnCount = 1;
    private final List<Card> handCards;
    private final String name;
    private static int gameOverUno = 0;


    public Player(Deck deck, String name) {
        handCards = new ArrayList<>();
        initializeSevenCardsToHand(deck, 7);
        this.name = name;
    }


    @Override
    public int handSize() {
        return handCards.size();
    }

    private void initializeSevenCardsToHand(Deck deck, int startingHand) {
        for (int i = 0; i < startingHand; i++) {
            handCards.add(deck.draw());
        }
    }


    public static int getTakeTurnCount() {
        return takeTurnCount;
    }

    @Override
    public void takeTurn(Game game) {
        var ttc = takeTurnCount++;
        for (Card card : handCards) {
            if (isPlayable(game.getDeck(), card)) {
                playCard(game.getDeck(), card);
                if (game.getDeck().getTopDiscardCard().getColor() == null){
                    game.getDeck().getTopDiscardCard().setColor(Colors.values()[new Random().nextInt(4)]);
                    System.out.print("<--> " +card.getColor().toString()+ " " );
                }
                System.out.print("and has FINISHED turn (" + ttc + ")\n");
                if (handCards.size() == 1) {
                    printUNO();
                }
                return;

            }
        }
        //TODO: game over if player won else draw
        var pDrewCard = draw(game);
        handCards.add(pDrewCard);
        printCardDrawnNTurnAmount(ttc, pDrewCard);
        if (isPlayable(game.getDeck(), pDrewCard)){
            playCard(game.getDeck(), pDrewCard);
        }

    }

    @Override
    public Card draw(Game game) {
        Card card = game.getDeck().draw();
        handCards.add(card);
        return card;
    }

    private void printUNO() {
        System.out.println(
                "     -------------\n" +
                        "   -------Uno!------" +
                        "\n     -------------\n");
    }

    private void printCardDrawnNTurnAmount(int ttc, Card pDrewCard) {
        System.out.println(getName() + " drew a card and has FINISHED turn (" + ttc + "). ");
    }

    private void playCard(Deck deck, Card card) {
        deck.getDiscard().add(card);
        System.out.print("" + getName() + " played " + card+" ");
        handCards.remove(card);
    }

    public String getName() {
        return name;
    }


    public List<Card> getHandCards() {
        return handCards;
    }
}