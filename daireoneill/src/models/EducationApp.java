package models;

import utils.Utilities;

public  class EducationApp extends App {


    private int level = 0;

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
        super(developer, appName, appSize, appVersion, appCost);
        setLevel(level);

    }

    @Override
    public boolean isRecommendedApp() {
        if (level >= 3);
        return true;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (Utilities.validRange(level, 1, 10)) {
            this.level = level;
        }
    }

}

