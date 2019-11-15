package com.improving.uno.players;

import com.improving.uno.Card;
import com.improving.uno.Game;

public interface PlayerInterface {

    int handSize();

    Card draw(Game game);

    void takeTurn(Game game);
}
