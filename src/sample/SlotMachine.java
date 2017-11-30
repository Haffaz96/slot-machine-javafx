package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;


public class SlotMachine {
    private static int credits = 10;
    private static int bet = 00;
    private static int creditsWon;
    private static int creditsLost;
    private static boolean hasPlayed;

    private static myReel reel1;
    private static myReel reel2;
    private static myReel reel3;

    private static Symbol slot1;
    private static Symbol slot2;
    private static Symbol slot3;

    private static int wins;
    private static int losses;

    private static ArrayList<Symbol> slots;

    public static void spin() throws NullPointerException  {
        if (!reel1.isAlive()&& !reel2.isAlive() && !reel3.isAlive()) {
            if (bet > 0) {

                hasPlayed = true;
                slots = new ArrayList<>();
                reel1 = new myReel(Dashboard.image1);
                reel2 = new myReel(Dashboard.image2);
                reel3 = new myReel(Dashboard.image3);

                reel1.start();
                reel2.start();
                reel3.start();
                //System.out.println("38");
            }
            else {
                Alert betAlert = setAlerts("Error", "You have to bet first",
                        Alert.AlertType.WARNING);
                betAlert.showAndWait();
            }
        }
    }

    protected static void addCoin() {
        credits += 1;

        //System.out.println(credits);
        updateScoreBoard();
    }

    protected static void betOne() throws NullPointerException {

        if (!reel1.isAlive()&& !reel2.isAlive() && !reel3.isAlive()) {
            if (credits > 0) {
                bet += 1;
                credits -= 1;
                updateScoreBoard();
                //System.out.println(credits);
            } else {
                Alert betAlert = setAlerts("Error !", "Insufficient Credit",
                        Alert.AlertType.WARNING);
                betAlert.showAndWait();
            }
        }
    }

    protected static void betMax() throws NullPointerException  {
        if (!reel1.isAlive()&& !reel2.isAlive() && !reel3.isAlive()) {
            if (credits >= 3) {
                bet += 3;
                credits -= 3;
                updateScoreBoard();
            } else {
                Alert betAlert = setAlerts("Error !", "Insufficient Credit",
                        Alert.AlertType.WARNING);
                betAlert.showAndWait();
            }
        }
    }
    protected static void reset() {
        if (!reel1.isAlive()&& !reel2.isAlive() && !reel3.isAlive()) {

            if (bet>0) {
                credits += bet;
                bet = 0;
                updateScoreBoard();
            }
            else {
                Alert resetAlert = setAlerts("Error !", "You Haven't bet yet",
                        Alert.AlertType.WARNING);
                resetAlert.showAndWait();
            }
        }
    }

    public static boolean stopReel(boolean reelClicked) throws NullPointerException {

        if ((reel1.isAlive()&& reel2.isAlive() && reel3.isAlive())) {

            if (reelClicked==false) {
                reel1.stop();
                reel2.stop();
                reel3.stop();
                slot1 = reel1.getReelObj();
                slot2 = reel2.getReelObj();
                slot3 = reel3.getReelObj();
                reelClicked = true;
                function();
                //System.out.println("Slot 1 - "+ slot1.getValue());
                //System.out.println("Slot 2 - "+ slot2.getValue());
                //System.out.println("Slot 3 - "+ slot3.getValue());
            }
        }
        return reelClicked;
    }

    public static boolean gameOver(Button spin, Button addCoin, Button bet1, Button betM, Button reset) {
        if (Dashboard.reel1Clicked==true && Dashboard.reel2Clicked==true
                && Dashboard.reel3Clicked==true) {

            spin.setDisable(true);
            addCoin.setDisable(true);
            bet1.setDisable(true);
            betM.setDisable(true);
            reset.setDisable(true);
            Alert gameOver = setAlerts("Game Over", "Your chances are over",
                    Alert.AlertType.INFORMATION);
            gameOver.show();
            return true;
        }
        else {
            return false;
        }
    }

    private static void function() {
        System.out.println("slot1 - " + slot1.getValue());
        System.out.println("slot2 - " + slot2.getValue());
        System.out.println("slot3 - " + slot3.getValue());

        if (slot1.equals(slot2)) {
            System.out.println("slot1==slot2");
            //System.out.println("slot1 "+slot1.getValue());
            wonBet(slot1);
        }
        else if (slot1.equals(slot3)) {
            System.out.println("slot1==slot3");
            //System.out.println("slot1 "+slot1.getValue());
            wonBet(slot1);
        }
        else if (slot2.equals(slot3)) {
            System.out.println("slot2==slot3");
            //System.out.println("slot1 "+slot1.getValue());
            wonBet(slot2);
        }
        else {
            lostBet();
        }
        bet = 0;
        updateScoreBoard();
    }
    private static void wonBet(Symbol symbol) {
        //System.out.println("Won");
        creditsWon += bet * symbol.getValue();
        credits += (bet * symbol.getValue());
        wins++;
        System.out.println("Credits - "+credits);
        System.out.println("Symbol Value - "+symbol.getValue());
        //updateScoreBoard();
        //System.out.println(credits);
    }

    private static void lostBet() {
        creditsLost += bet;
        losses++;
    }

    private static Alert setAlerts(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        return alert;
    }


    protected static int generateRandomInt(int length) {
        Random generator = new Random();
        int x = generator.nextInt(length);
        //System.out.println("Random num - "+x);
        return x;
    }

    private static void updateScoreBoard() {
        //System.out.println("171");
        Dashboard.lbl_bet.setText("Betting Amount - " + String.format("%02d", bet));
        Dashboard.lbl_credits.setText("Total Credits  - " + String.format("%02d", credits));
    }

    public static int getWins() {
        return wins;
    }

    public static int getLosses() {
        return losses;
    }
/*
    private static <Symbol> boolean areAllSame(Symbol[] slots) {
        Set<Symbol> equals = new HashSet<>(Arrays.asList(slots));
        return equals.size() == 1;
    }*/
}

