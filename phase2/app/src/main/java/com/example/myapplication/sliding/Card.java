package com.example.myapplication.sliding;

import com.example.myapplication.R;

public class Card {

    /**
     * The background id to find the pair image.
     */
    private int background;
    /**
     * The unique point of each type of card.
     */
    private int point;


    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the Pair id.
     *
     * @return the tile id
     */
    public int getPoint() {
        return point;
    }

    /**
     * A Card with background and its associated point. The background may not have a corresponding image.
     *
     * @param point         the point
     * @param background the background
     */
    public Card(int point, int background) {
        this.point = point;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the backgroundId
     */
    public Card(int backgroundId) {
        point = backgroundId + 1;
        // "This looks so ugly" Part 2!
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.rare1;
                break;
            case 2:
                background = R.drawable.rare2;
                break;
            case 3:
                background = R.drawable.rare3;
                break;
            case 4:
                background = R.drawable.rare4;
                break;
            case 5:
                background = R.drawable.rare5;
                break;
            case 6:
                background = R.drawable.rare6;
                break;
            case 7:
                background = R.drawable.rare7;
                break;
            case 8:
                background = R.drawable.rare8;
                break;
            case 9:
                background = R.drawable.rare9;
                break;
            case 10:
                background = R.drawable.rare10;
                break;
            case 11:
                background = R.drawable.rare11;
                break;
            case 12:
                background = R.drawable.rare12;
                break;
            case 13:
                background = R.drawable.rare13;
                break;
            case 14:
                background = R.drawable.rare14;
                break;
            default:
                background = R.drawable.rare0;
        }
    }
}

