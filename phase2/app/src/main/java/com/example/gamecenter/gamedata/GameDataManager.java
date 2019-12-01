package com.example.gamecenter.gamedata;

public class GameDataManager  {
    private GameData gameData;
    private GameData userGameData;



    public GameDataManager(GameData userGameData, GameData gameData){
        this.userGameData = userGameData;
        this.gameData = gameData;
        setGlobalScore();

    }

    private void setGlobalScore(){

     if (userGameData.getHighestScore() > gameData.getHighestScore()) {
         gameData.getGameDataBuilder().setHighestScore(userGameData.getHighestScore());
         gameData.getGameDataBuilder().setUserName(userGameData.getUserName());
        }
    }

    public int getGlobalScore(){
        return gameData.getHighestScore();
    }

    public GameData getGameData() {
        return gameData;
    }

    public GameData getUserGameData() {
        return userGameData;
    }
}
