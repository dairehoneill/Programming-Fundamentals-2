package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.*;

import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI implements ISerializer {

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)

    private List<App> apps = new ArrayList<>();

    public boolean addApp(App app) {
        return apps.add(app);
    }

    public String listAllApps() {
        String listAllApps = "";
        for (App app : apps) {
            listAllApps += apps.indexOf(app) + ": " + app + "\n";
        }
        if (listAllApps.equals("")) {
            return "No apps";
        } else {
            return listAllApps;
        }
    }

    public boolean isValidApp(String appName) {
        for (App app : apps) {
            if (app.getAppName().equalsIgnoreCase(appName)) {
                return true;
            }
        }
        return false;
    }

    public boolean updateAppSize(String appName, double appSize) {
        if (isValidApp(appName)) {
            App appToUpdate = getAppByName(appName);
            appToUpdate.setAppSize(appSize);
            return true;
        }
        return false;
    }

    public boolean updateAppVersion(String appName, double appVersion) {
        if (isValidApp(appName)) {
            App appToUpdate = getAppByName(appName);
            appToUpdate.setAppVersion(appVersion);
            return true;
        }
        return false;
    }

    public boolean updateAppCost(String appName, double appCost) {
        if (isValidApp(appName)) {
            App appToUpdate = getAppByName(appName);
            appToUpdate.setAppCost(appCost);
            return true;
        }
        return false;
    }

    public String listSummaryOfAllApps() {
        String listSummaryOfAllApps = "";
        for (App appSummary : apps) {
            listSummaryOfAllApps += apps.indexOf(appSummary) + ": " + appSummary + "\n";
        }
        if (listSummaryOfAllApps.equals("")) {
            return "No summary of apps";
        } else {
            return listSummaryOfAllApps;
        }
    }

    public String searchByAppName(String appName) {
        String matchingApps = "";
        for(App app : apps) {
            if (app.getAppName().toUpperCase().contains(appName.toUpperCase())) {
                matchingApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }

        if (matchingApps.equals("")){
            return "No products match your search";
        }
        else{
            return matchingApps;
        }
    }

    public String listAllGameApps() {
        String listAllGameApps = "";

        for (App app : apps) {
            if (app instanceof EducationApp) {
                listAllGameApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        if (listAllGameApps.isEmpty()) {
            return "No Game Apps";
        } else {
            return listAllGameApps;
        }
    }

    public String listAllEducationApps() {
        String listAllEducationApps = "";

        for (App app : apps) {
            if (app instanceof EducationApp) {
                listAllEducationApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        if (listAllEducationApps.isEmpty()) {
            return "No Education Apps";
        } else {
            return listAllEducationApps;
        }
    }

    public String listAllProductivityApps() {
        String listAllProductivityApps = "";

        for (App app : apps) {
            if (app instanceof ProductivityApp) {
                listAllProductivityApps += apps.indexOf(app) + ": " + app + "\n";
            }
        }
        if (listAllProductivityApps.isEmpty()) {
            return "No Productivity Apps";
        } else {
            return listAllProductivityApps;
        }
    }

    //should have srting in ()
    public String listAllAppsByName() {
        String listAllAppsByName = "";
        for (App appName : apps) {
            listAllAppsByName += apps.indexOf(appName) + ": " + appName + "\n";
        }
        if (listAllAppsByName.equals("")) {
            return "No app name";
        } else {
            return listAllAppsByName;
        }
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating) {
        String str = "";
        for (App app : apps) {
            if (app.calculateRating() >= rating) {
                str += app + " ";
            }
        }
        if (str.isBlank()) {
            return "No apps have a rating of " + rating + " or above";
        }
        return str;
    }
    





    public String listAllRecommendedApps() {
        String listAllRecommendedApps = "";
        for (App recommendedApp : apps) {
            listAllRecommendedApps += apps.indexOf(recommendedApp) + ": " + recommendedApp + "\n";
        }
        if (listAllRecommendedApps.equals("")) {
            return "No recommended app";
        } else {
            return listAllRecommendedApps;
        }

    }


    public String listAllAppsByChosenDevelopers(Developer developer) {
        String listAllAppsByChosenDevelopers = "";
        for (App chosenApp : apps) {
            listAllAppsByChosenDevelopers += apps.indexOf(chosenApp) + ": " + chosenApp + "\n";
        }
        if (listAllAppsByChosenDevelopers.equals("")) {
            return "No chosen app";
        } else {
            return listAllAppsByChosenDevelopers;
        }

    }

    public int numberOfAppsByChosenDeveloper(Developer developer) {
        int numberOfAppsByChosenDeveloper = 0;
        for (App chosenApp : apps) {
            numberOfAppsByChosenDeveloper += apps.indexOf(chosenApp);
        }
        if (numberOfAppsByChosenDeveloper == (0)) {
            return 0;
        } else {
            return numberOfAppsByChosenDeveloper;
        }
    }


    public App deleteAppByIndex(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return apps.remove(indexToDelete);
        }
        return null;
    }

    //https://www.google.com/search?q=java+how+to+pick+a+random+string+from+an+arraylist+math+ramdom&rlz=1C1CHBF_enIE924IE924&biw=1150&bih=853&sxsrf=ALiCzsaVtTh0-S0UJCEMnb-26YNkkiPFVQ%3A1661805318779&ei=BiMNY4SSL4KW8gK3tr3YAQ&ved=0ahUKEwiErdid8-z5AhUCi1wKHTdbDxsQ4dUDCA4&uact=5&oq=java+how+to+pick+a+random+string+from+an+arraylist+math+ramdom&gs_lcp=Cgdnd3Mtd2l6EAMyBAghEAo6BwgAEEcQsAM6BQghEKABOgcIIRCgARAKSgQIQRgASgQIRhgAUMoGWKQRYJQSaAFwAHgAgAFriAGiBpIBBDExLjGYAQCgAQHIAQjAAQE&sclient=gws-wiz
    public App randomApp() {
        Random r = new Random();
        int randomApp = r.nextInt(apps.size());
        App randomElement = apps.get(randomApp);
        return randomElement;
    }


    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }


    public boolean isValidAppName(String appName) {
        for (App app : apps) {
            if (app.getAppName().equalsIgnoreCase(appName)) {
                return true;
            }
        }
        return false;
    }

    public App getAppByName (String appName){
        int index = apps.indexOf(appName);
        if (index != -1){
            return apps.get(index);
        }
        return null;
    }


    public App getAppByIndex(int index) {
        if (Utilities.isValidIndex(apps, index)) {
            return apps.get(index);
        } else {
            return null;
        }
    }


    public boolean isValidIndex(int index) {
        return (index > +0) && (index < apps.size());
    }

    public int numberOfApps() {
        return apps.size();
    }

    public void sortAppsByNameAscending() {
        for (int i = apps.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApps(apps, i, highestIndex);
        }
    }

    private void swapApps(List<App> sa, int i, int j) {
        App smaller = apps.get(i);
        App bigger = apps.get(j);

        apps.set(i, bigger);
        apps.set(j, smaller);
    }


    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, models.Rating.class};


        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);


        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("apps.xml"));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("apps.xml"));
        out.writeObject(apps);
        out.close();
    }

    public String fileName() {
        return "apps.xml";
    }
}



