package models;

import utils.Utilities;

public  class ProductivityApp extends App {

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        super(developer, appName, appSize, appVersion, appCost);

    }

    @Override

    public boolean isRecommendedApp() {
       return true;
}

    public String toString() {
        return super.toString();
    }
}
