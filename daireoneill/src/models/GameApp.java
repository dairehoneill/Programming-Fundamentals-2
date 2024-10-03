package models;

import utils.Utilities;

public  class GameApp extends App{

    private boolean isMultiplayer = false;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {
        super(developer, appName, appSize, appVersion, appCost);
        setMultiplayer(isMultiplayer);
    }

    @Override
    public boolean isRecommendedApp() {
        if (isMultiplayer = true);
        return true;
    }

    public boolean isMultiplayer() {return isMultiplayer;}

    public void setMultiplayer(boolean isMultiplayer){
        this.isMultiplayer = isMultiplayer;
    }


    public String appSummary() {
        return super.appSummary();
    }


    public String toString() {
        return super.toString();
    }
}

