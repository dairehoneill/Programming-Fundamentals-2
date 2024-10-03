package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                case 2 -> runAppStoreMenu();
                case 3 -> runReportsMenu();
                case 4 -> searchAppsBySpecificCriteria();
                case 5 ->  sortAppsByName();
                case 6 ->  recommendedApps();
                case 7 ->  randomAppOfTheDay();
                case 8 ->  simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private int appStoreMenu() {
            System.out.println("""
                     ------App Store Menu-------
                   |   1) Add an app            |
                   |   2) Update app            |
                   |   3) Delete app            |
                   |   0) RETURN to main menu   |
                     ----------------------------""");
            return ScannerInput.validNextInt("==>> ");
        }

        private void runAppStoreMenu() {
            int option = appStoreMenu();
            while (option != 0) {
               switch (option) {
                    case 1 -> addApp();
                    case 2 -> updateApp();
                    case 3 -> deleteApp();
                    default -> System.out.println("Invalid option entered" + option);
               }
                ScannerInput.validNextLine("\n Press the enter key to continue");
                option = appStoreMenu();
           }
       }
    private void addApp(){

        boolean isAdded = false;

        int option = ScannerInput.validNextInt("""
                    -------------------------------
                    |   1) Add an Education App   |
                    |   2) Add a Productivity App |
                    |   3) Add a Game App         |
                    -------------------------------
                    ==>> """);

        switch (option) {
            case 1 -> {
                Developer developer = readValidDeveloperByName();
                String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                double appSize = ScannerInput.validNextDouble("Enter the Size:  ");
                double appVersion = ScannerInput.validNextDouble("Enter the Version:  ");
                double appCost = ScannerInput.validNextDouble("Enter the Cost:  ");
                int appLevel = ScannerInput.validNextInt("Enter the Level:  ");
                isAdded = appStoreAPI.addApp(new EducationApp(developer, appName, appSize, appVersion, appCost, appLevel));
            }


            case 2 -> {
                Developer developer = readValidDeveloperByName();
                String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                double appSize = ScannerInput.validNextDouble("Enter the Size:  ");
                double appVersion = ScannerInput.validNextDouble("Enter the Version:  ");
                double appCost = ScannerInput.validNextDouble("Enter the Cost:  ");
                isAdded = appStoreAPI.addApp(new ProductivityApp(developer, appName, appSize, appVersion, appCost));
            }
            case 3 -> {
                Developer developer = readValidDeveloperByName();
                String appName = ScannerInput.validNextLine("Enter the App Name:  ");
                double appSize = ScannerInput.validNextDouble("Enter the Size:  ");
                double appVersion = ScannerInput.validNextDouble("Enter the Version:  ");
                double appCost = ScannerInput.validNextDouble("Enter the Cost:  ");
                char setMultiplayer = ScannerInput.validNextChar("Is this App Multiplayer (y/n): ");
                boolean isMultiplayer = Utilities.YNtoBoolean(setMultiplayer);
                isAdded = appStoreAPI.addApp(new GameApp(developer, appName, appSize, appVersion, appCost, isMultiplayer));
            }
            default -> System.out.println("Invalid option entered: " + option);
        }
            if (isAdded) {
                System.out.println("App Added Successfully");
            } else {
                System.out.println("No App Added");
            }

    }

    private void updateApp() {
        System.out.println(appStoreAPI.listAllApps());
        App app = readValidAppByName();
        if (app != null) {
            double appSize = ScannerInput.validNextDouble("Enter the Size:  ");
            if (appStoreAPI.updateAppSize(app.getAppName(), appSize))
                System.out.println("App size Updated");
            else
                System.out.println("App size NOT Updated");

            double appVersion = ScannerInput.validNextDouble("Enter the Version:  ");
            if (appStoreAPI.updateAppVersion(app.getAppName(), appVersion))
                System.out.println("App Version Updated");
            else
                System.out.println("App Version NOT Updated");

            double appCost = ScannerInput.validNextDouble("Enter the Cost:  ");
            if (appStoreAPI.updateAppCost(app.getAppName(), appCost))
                System.out.println("App Cost Updated");
            else
                System.out.println("App Cost NOT Updated");

        } else
            System.out.println("App name is NOT valid");
    }

    private App readValidAppByName() {
        String appName = ScannerInput.validNextLine("Please enter the apps name: ");
        if (appStoreAPI.isValidApp(appName)) {
            return appStoreAPI.getAppByName(appName);
        } else {
            return null;
        }
    }



    private void deleteApp() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        if (developerAPI.removeDeveloper(appName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private int appReportsMenu() {
        System.out.println("""
                     ------Reports Menu---------
                   |   1) Adds Overview         |
                   |   2) Developers Overview   |
                   |   0) RETURN to main menu   |
                     ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runReportsMenu() {
        int option = appStoreMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> appsOverview();
                case 2 -> developerOverview();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appStoreMenu();
        }
    }

    private void appsOverview(){
        System.out.println(appStoreAPI.listAllApps());
    }

    private void developerOverview(){
        System.out.println(developerAPI.listDevelopers());
    }


    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }


    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }




    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps of that rating or above)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            // TODO Search methods below
             case 1 -> searchAppsByName();
             case 2 -> searchAppsByDeveloper();
             case 3 -> searchAppsEqualOrAboveAStarRating();
             default -> System.out.println("Invalid option");
        }
    }

    private void searchAppsByName() {
        String appName = ScannerInput.validNextLine("Please search for an app name:");
        System.out.println(appStoreAPI.searchByAppName(appName));
    }

    private Developer searchAppsByDeveloper() {
      return readValidDeveloperByName();
    }

    private void searchAppsEqualOrAboveAStarRating() {
        int rating = ScannerInput.validNextInt("View the apps with a higher rating than:  ");
        System.out.println(appStoreAPI.listAllAppsAboveOrEqualAGivenStarRating(rating));
    }

    private void sortAppsByName(){
        appStoreAPI.sortAppsByNameAscending();
        System.out.println(appStoreAPI.listAllAppsByName());
    }

    private void recommendedApps() {
    System.out.println("These are all the recommended apps! " + appStoreAPI.listAllRecommendedApps());
    }


    private void randomAppOfTheDay(){
        System.out.println("this is the random app of the day!" + appStoreAPI.randomApp());
    }

    //--------------------------------------------------
    // TODO UNCOMMENT THIS COMPLETED CODE as you start working through this class
    //--------------------------------------------------
    private void simulateRatings() {
        if (appStoreAPI.numberOfApps() > 0) {
            System.out.println("Simulating ratings...");
            appStoreAPI.simulateRatings();
            System.out.println(appStoreAPI.listSummaryOfAllApps());
        } else {
            System.out.println("No apps");
        }
    }


    private void saveAllData() {
        try {
            System.out.println("Saving to file: " + appStoreAPI.fileName());
            appStoreAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
            try {
                System.out.println("Saving to file: " + developerAPI.fileName());
                developerAPI.save();
            } catch (Exception e1) {
                System.err.println("Error writing to file: " + e1);
            }
        }
    }

    private void loadAllData() {
        try {
            System.out.println("Loading from file: " + appStoreAPI.fileName());
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    try {
        System.out.println("Loading from file: " + developerAPI.fileName());
        developerAPI.load();
    } catch (Exception e1) {
        System.err.println("Error reading from file: " + e1);
    }
}

}