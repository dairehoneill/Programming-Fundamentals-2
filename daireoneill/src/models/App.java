package models;

import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class App {

    private Developer developer;
    private String appName ="No app name";
    private double appSize =0;
    private double appVersion =1.0;
    private double appCost =0;


    public App(Developer developer, String appName, double appSize, double appVersion, double appCost){

        this.developer = developer;
        setAppName(appName);
        setAppSize(appSize);
        setAppVersion(appVersion);
        setAppCost(appCost);
    }

    public abstract boolean isRecommendedApp();

    public String appSummary() {

        String summary = (appName + appVersion + developer + appCost + ratings);
        return summary;}



    public boolean addRating(Rating rating) {return ratings.add(rating);}


    public String listRatings() {
        if (ratings.isEmpty()) {
            return "No ratings to be listed";
        } else {
            String listOfRatings = "";
            for (int i = 0; i < ratings.size(); i++) {
                listOfRatings += i + ": " + ratings.get(i) + "\n";
            }
            return listOfRatings;
        }
    }

    public double calculateRating() {
        int number = 0;
        for (Rating rating : ratings) {
            if (rating instanceof Rating) {
                number++;
            }
        }
        return number;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public String getAppName() { return appName; }

    public void setAppName(String appName) {
        if (Utilities.validateStringLength(appName, 20)) {
            this.appName = appName;
        }
    }

    public void setDeveloper(Developer developer){
        this.developer = developer;}

    public double getAppSize(){ return appSize;}

    public void setAppSize(double appSize) {this.appSize = appSize;}

    public double getAppVersion(){ return appVersion;}

    public void setAppVersion(double appVersion) {this.appVersion = appVersion;}

    public double getAppCost(){return  appCost;}

    public void setAppCost(double appCost) {this.appCost = appCost;}

    public List<Rating> getRatings(){return ratings;}



    private List<Rating> ratings = new ArrayList<>();

    public String toString()
    {
        return "Developer: " + developer
                + ", app name: " + appName
                + ", app size: " + appSize
                + ", app version: " + appVersion
                + ", app cost: " + appCost;
    }


}


