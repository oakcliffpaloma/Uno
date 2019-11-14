package com.improving.uno;

public class Card {

    private final Faces face;
    private  Colors color;


    public Card(Faces face, Colors color) {
        this.face = face;
        this.color= color;

    }
    @Override
    public String toString(){
        if(color == null){
            return "" + face.toString();
        }
        return "" + face.toString() + "-" + color.toString() ;
    }

    public Faces getFace() {
        return face;
    }

    public Colors getColor() {
        return color;
    }
    public void setColor(Colors color) {
        this.color = color;
    }
}

