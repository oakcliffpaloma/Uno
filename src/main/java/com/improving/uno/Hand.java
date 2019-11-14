package com.improving.uno;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> handCards;

    public Hand(Deck deck, int startingHand) {
        handCards = new ArrayList<>();
        for (int i = 0; i < startingHand; i++) {
            handCards.add(deck.draw());
        }

    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public Card getTopHandCard(){
        return handCards.get(0);
    }
    }
