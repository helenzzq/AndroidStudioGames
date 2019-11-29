package com.example.gamecenter.games.math24game.activity;


public class ButtonBuilder {

    private ButtonFacade buttonFacade;

    void createNewButton(String buttonID){
        buttonFacade = new ButtonFacade(buttonID);
    }



}
