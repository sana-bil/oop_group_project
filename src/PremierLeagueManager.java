import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class PremierLeagueManager implements LeagueManager {
    private int numberOfClubs;
    ArrayList<FootballClub> league= new ArrayList<>(2);
    private Scanner scanner;
    ArrayList<Match> matches= new ArrayList<>();

    public PremierLeagueManager() throws IOException {
    }

    public PremierLeagueManager(int numberOfClubs) throws IOException, ClassNotFoundException {
        this.numberOfClubs=numberOfClubs;
        scanner= new Scanner(System.in);
        displayMenu();
    }
    private void displayMenu() throws IOException, ClassNotFoundException {
        while (true){
            readAllData();
            System.out.println("                 ***Premier League Menu****    ");
            System.out.println("Create new team and add it to the league (Press 1)");
            System.out.println("Delete existing team from the league (Press 2");
            System.out.println("Display Statistics for the team (Press 3)");
            System.out.println("Display Premier League Table (Press 4)");
            System.out.println("Add a played match (Press 5)");

            String line = scanner.nextLine();
            int command=0;
            try {
                command= Integer.parseInt(line);
            }catch (Exception e){

            }
            switch (command){
                case 1: addTeam();
                    break;
                case 2: deleteTeam();
                    break;
                case 3: displayStatistics();
                    break;
                case 4: displayLeagueTable();
                    break;
                case 5:
                    addPlayedMatch();
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }
    //Adding club to  the league
    private void addTeam() throws IOException {
        FootballClub footballClub= new FootballClub();
        if (league.size()==numberOfClubs){
            System.out.println("Cant add more clubs to league");
            return;
        }
        System.out.println("Insert Club name");
        String line = scanner.nextLine();
        footballClub.setName(line);

        if (league.contains(footballClub)){
            System.out.println("This club is already in the League");
            return;
        }
        System.out.println("Insert Club Location");
        line=scanner.nextLine();
        footballClub.setLocation(line);
        league.add(footballClub);
        addArrayListToFile(league);
    }
    //deleting club from the league
    private void deleteTeam() throws IOException {
        System.out.println("Insert Club name");
        String line = scanner.nextLine();
        for (FootballClub footballClub:league){
            if (footballClub.getName().equals(line)){
                league.remove(footballClub);
                addArrayListToFile(league);
                System.out.println("Club "+ footballClub.getName()+ " removed");
                return;
            }
        }
        System.out.println("No such club exits");
        return;
    }
    private void displayStatistics() throws IOException, ClassNotFoundException {
        System.out.println("Insert Club name");
        String line = scanner.nextLine();
        for (FootballClub footballClub1:league) {
            readAllData();
            if (footballClub1.getName().equals(line)) {
                System.out.println( "Club Name: " + footballClub1.getName()+" Win Count: " + footballClub1.getWinCount());
                System.out.println( "Club Name: " + footballClub1.getName()+" Defeat Count: " + footballClub1.getDefeatCount());
                System.out.println("Club Name: " + footballClub1.getName()+" Draw Count: "+ footballClub1.getDrawCount());
                System.out.println("Club Name: " + footballClub1.getName()+ " Scored Goals Count: "+ footballClub1.getScoredGoalsCount());
                System.out.println("Club Name: " + footballClub1.getName()+ " Received Goals Count: " + footballClub1.getReceivedGoalCount());
                System.out.println("Club Name: " + footballClub1.getName()+ " Points: "+ footballClub1.getPoints());
                System.out.println("Club Name: " + footballClub1.getName()+" matches played: "+ footballClub1.getMatchesPlayed());
                return;
            }
        }
        System.out.println("No such club in the league");
    }
    private void displayLeagueTable() throws IOException, ClassNotFoundException {
        for (int i = 0; i < league.size(); i++) {
            readAllData();
            System.out.println("Club: " + league.get(i).getName() + " Points: "+ league.get(i).getPoints() + " Goal Difference: "
                    + (league.get(i).getScoredGoalsCount()-league.get(i).getReceivedGoalCount()));
        }
    }
    private void addPlayedMatch() throws IOException {
        System.out.println("Enter the date (mm-dd-yy)");
        String line = scanner.nextLine();
        Date date;
        try {
            date=new SimpleDateFormat("mm-dd-yy").parse(line);
        }
        catch (ParseException parseException){
            System.out.println("You have to enter in mm-dd-yy format");
            return;
        }
        System.out.println("Enter Home Team");
        String homeTeam  = scanner.nextLine();
        FootballClub home=null;
        for (FootballClub footballClub:league){

            if (footballClub.getName().equals(homeTeam)){
                home=footballClub;

            }
        }

        if (home==null){
            System.out.println("no such club exits");
            return;
        }
        System.out.println("Enter away Team");
        String awayTeam  = scanner.nextLine();
        FootballClub away = null;
        for (FootballClub footballClub:league){

            if (footballClub.getName().equals(awayTeam));
            away=footballClub;

        }
        if (away==null){
            System.out.println("No such club exits");
        }
        System.out.println("Enter Home team goals");
        String homeG =scanner.nextLine();
        int homeGoals = -1;
        try {
            homeGoals=Integer.parseInt(homeG);
        }
        catch (Exception e){

        }
        if (homeGoals<0){
            System.out.println("You have to enter the number of goals");
            return;
        }
        System.out.println("Enter away team goals");
        String awayG =scanner.nextLine();
        int awayGoals=-1;
        try {
            awayGoals=Integer.parseInt(awayG);
        }
        catch (Exception e){
        }
        if (awayGoals<0){
            System.out.println("You have to enter number of goals ");
            return;
        }
        Match match= new Match();
        match.setDate(date);
        match.setTeamA(home);
        match.setTeamB(away);
        match.setTeamAScore(homeGoals);
        match.setTeamBScore(awayGoals);


        matches.add(match);

        home.setScoredGoalsCount(home.getScoredGoalsCount()+homeGoals);
        assert away != null;
        away.setScoredGoalsCount(away.getScoredGoalsCount()+awayGoals);
        home.setReceivedGoalCount(home.getReceivedGoalCount()+awayGoals);
        away.setReceivedGoalCount(away.getReceivedGoalCount()+homeGoals);
        home.setMatchesPlayed(home.getMatchesPlayed()+1);
        away.setMatchesPlayed(away.getMatchesPlayed()+1);

        //3 points for win

        if (homeGoals>awayGoals){
            home.setPoints(home.getPoints()+3);
            home.setWinCount(home.getWinCount()+1);
            away.setDefeatCount(away.getDefeatCount()+1);
        }
        else if (awayGoals>homeGoals){
            away.setPoints(away.getPoints()+3);
            away.setWinCount(away.getWinCount()+1);
            home.setDefeatCount(home.getDefeatCount()+1);
        }
        else {
            home.setPoints(home.getPoints()+1);
            away.setPoints(away.getPoints()+1);
            home.setDrawCount(home.getDrawCount()+1);
            away.setDrawCount(away.getDrawCount()+1);
        }
        league.add(home);
        league.add(away);
        addArrayListToFile(league);
    }

    public void readAllData(){
        league= new ArrayList<>(2);
        // Input stream
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("football.ser"));

            boolean EOF = false;
            while (!EOF) {
                try {
                    FootballClub myObj = (FootballClub) inputStream.readObject();

                    league.add(myObj);
                } catch (ClassNotFoundException e) {
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }

    }


    public static void addArrayListToFile(ArrayList<FootballClub> FootballList) {
        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("football.ser"));

            for (int i = 0; i < FootballList.size(); i++) {
                outputStream.writeObject(FootballList.get(i));
            }
        } catch (IOException e) {
            System.out.println("IO Exception while opening file");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
    }


}