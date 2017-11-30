package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dashboard extends Application {

    Stage gameStage, statStage;
    Scene scene1, scene2, scene3;
    private static final int IMAGE_SIZE = 150;

    static Text credits, bet;
    static Label lbl_credits, lbl_bet;
    static ImageView image1, image2, image3;

    protected static boolean reel1Clicked;
    protected static boolean reel2Clicked;
    protected static boolean reel3Clicked;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button btn_start = new Button("Start");
        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                gameWindow();
            }
        });

        // layout settings
        VBox layout = new VBox(20);
        layout.setId("layout");

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(btn_start);

        scene1 = new Scene(layout,575,325);
        scene1.getStylesheets().add("/StyleSheet/scene1_StyleSheet.css");
        //scene2 = gameWindow();

        primaryStage.setScene(scene1);
        primaryStage.setTitle("The Slot Machine");
        primaryStage.show();
    }

    private void gameWindow() {

        gameStage = new Stage();

        credits = new Text("10");
        bet     = new Text("XX");
        lbl_credits  = new Label("Total Credits  - 10");
        lbl_bet      = new Label("Betting Amount - 00");

        Button btn_addCoin = new Button("Add Coin");
        btn_addCoin.setOnAction(event -> SlotMachine.addCoin());
        Button btn_betOne  = new Button("Bet One");
        btn_betOne.setOnAction(event -> SlotMachine.betOne());
        Button btn_betMax  = new Button("Bet Max");
        btn_betMax.setOnAction(event -> SlotMachine.betMax());
        Button btn_reset   = new Button("Reset");
        btn_reset.setOnAction(event -> SlotMachine.reset());

        Button btn_spin    = new Button("Spin");
        btn_spin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(SlotMachine.gameOver(btn_spin, btn_addCoin,btn_betOne, btn_betMax, btn_reset) == false) {
                    SlotMachine.spin();
                }
            }
        });

        Button btn_stats   = new Button("Statistics");
        btn_stats.setOnAction(event -> statsWindow());

        image1 = new ImageView(Reel.img_empty);
        image1.setOnMouseClicked(event -> reel1Clicked = SlotMachine.stopReel(reel1Clicked));
        image2 = new ImageView(Reel.img_empty);
        image2.setOnMouseClicked(event -> reel2Clicked = SlotMachine.stopReel(reel2Clicked));
        image3 = new ImageView(Reel.img_empty);
        image3.setOnMouseClicked(event -> reel3Clicked = SlotMachine.stopReel(reel3Clicked));

        image1.setId("image1");
        image2.setId("image2");
        image3.setId("image3");

        image1.setFitWidth(IMAGE_SIZE);
        image1.setFitHeight(IMAGE_SIZE);
        image2.setFitWidth(IMAGE_SIZE);
        image2.setFitHeight(IMAGE_SIZE);
        image3.setFitWidth(IMAGE_SIZE);
        image3.setFitHeight(IMAGE_SIZE);


        VBox areaCrd = new VBox(20);
        areaCrd.setAlignment(Pos.CENTER);
       /* areaCrd.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" *//*+ "-fx-border-color: blue;"*//*);*/
        areaCrd.getChildren().addAll(lbl_credits, btn_addCoin);

        HBox areaBet_buttons = new HBox(5);
        areaBet_buttons.setAlignment(Pos.CENTER);
        areaBet_buttons.getChildren().addAll(btn_betOne,btn_betMax,btn_reset);
        VBox areaBet = new VBox(20);
        areaBet.setAlignment(Pos.CENTER);
        areaBet.getChildren().addAll(lbl_bet, areaBet_buttons);

        HBox areaTop = new HBox(50);
        areaTop.setAlignment(Pos.CENTER);
        areaTop.getChildren().addAll(areaCrd, areaBet);

        HBox areaCentre = new HBox();
        areaCentre.setAlignment(Pos.CENTER);
        areaCentre.getChildren().add(btn_spin);

        HBox areaReel = new HBox(50);
        areaReel.setId("areaReel");
        areaReel.setPrefSize(600, 250);
        areaReel.setAlignment(Pos.CENTER);
        //areaReel.setOnMouseClicked(event -> SlotMachine.stopReel());
        areaReel.getChildren().addAll(image1, image2, image3);

        HBox areaStats = new HBox();
        areaStats.setAlignment(Pos.CENTER_RIGHT);
        areaStats.getChildren().add(btn_stats);

        VBox layout = new VBox(40);
        layout.setId("layout");
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(areaTop,areaCentre,areaReel, areaStats);

        Pane pane = new Pane();
        pane.getChildren().add(layout);

        /*String image = Dashboard.class.getResource("/Images/bg.jpg").toExternalForm();
        layout.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-size: stretch;"+
                "-fx-background-position: center center;"+
                "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);"+
                "-fx-background-repeat: stretch;");*/

        scene2 = new Scene(pane);
        scene2.getStylesheets().add("/StyleSheet/scene2_StyleSheet.css");

        gameStage.setScene(scene2);
        gameStage.show();
    }

    private void statsWindow() {
        statStage = new Stage();
        statStage.setTitle("Imported Fruits");
        statStage.setWidth(500);
        statStage.setHeight(500);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Wins", SlotMachine.getWins()),
                        new PieChart.Data("Losses", SlotMachine.getLosses())
                        );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Your Game Stats");

        Label lbl_wins   = new Label("Total wins  - " + SlotMachine.getWins());
        Label lbl_losses = new Label("Total loses - " + SlotMachine.getLosses());


        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(chart, lbl_wins, lbl_losses);
        scene3 = new Scene(layout);
        statStage.setScene(scene3);
        statStage.show();
    }
}
