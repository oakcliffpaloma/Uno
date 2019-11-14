package com.improving.uno.players;

import com.improving.uno.Card;
import com.improving.uno.Game;

public interface PlayerInterface {

    int handSize();

    public Card draw(Game game);

    public void takeTurn(Game game);
}
