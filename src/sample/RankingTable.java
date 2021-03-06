package sample;

import gameSystem.GameScene;
import gameSystem.PauseClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.util.*;
import static sample.Main.primaryStage;
import static gameSystem.GameScene.*;
import static sample.Main.IMAGE_PATH;

public class RankingTable {

    private static final Image backImage =new Image( new File(IMAGE_PATH + "Ranking.png").toURI().toString(), 500,550,false,true);
    static String fileName = IMAGE_PATH+"RankingTable.csv";
    static String charset = "UTF-8";
    static PlayerData playerData=new PlayerData();
    static int numScores=0;
    static int numClick=0;
    public static boolean enableAddButton; //serve per impedire che si aggiungano risultati nei momenti sbagliati

    static List<Integer> scoreRecords= new ArrayList<>();


    private static TextField nameInput;
    private static TableView<Player> table;


    public static void scoreRecord() throws IOException {

        Stage scoreStage = new Stage();
        scoreStage.setTitle("Ranking");
        scoreStage.initModality(Modality.APPLICATION_MODAL);
        scoreStage.setResizable(false);

        nameInput = new TextField();
        nameInput.setPromptText("Player NAME");


        //caricamento ranking
        if(numClick<1) {
            LinkedList<String[]> lstRows = FileManagment.read(fileName, charset);
            for (String[] sArr : lstRows) {
                playerData.add(new Player(sArr[0], Integer.parseInt(sArr[1])));
                scoreRecords.add(Integer.parseInt(sArr[1]));
            }
        }
        numScores = scoreRecords.size();
        //sorting the scores in decreasing values
        scoreRecords.sort(Collections.reverseOrder());



        Button addButton = new Button("Add");
        addButton.setDisable(!enableAddButton);

        if (numScores >9 && scoreRecords.get(9) > GameScene.points)
            addButton.setDisable(true);



        addButton.setOnAction(e -> {
            try {
                addButtonClicked(addButton);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button resumeButton = new Button("Resume");


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, addButton, resumeButton);



        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        //Column name
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Column score
        TableColumn<Player, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));


        //Sfondo
        BackgroundImage backgroundImage = new BackgroundImage(backImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        gridPane.setBackground(new Background(backgroundImage));

        if (numClick < 1) {
            table = new TableView<>();

            table.setItems(getPlayer(playerData));

            //noinspection unchecked
            table.getColumns().addAll(nameColumn, scoreColumn);
        }

        gridPane.add(hBox, 0, 2);
        gridPane.add(table, 0, 1);


        Scene scene = new Scene(gridPane, 500, 530);
        scoreStage.setScene(scene);
        scoreStage.show();

        resumeButton.setOnAction(e -> {
            numClick++;
            //Main.autoPlay = true;
            PauseClass.gameSceneAutoPlay=true;
            if(FROGGER_LIVES==0 || burrowCounter==5) {
                Main.mediaPlayer.pause();
                primaryStage.setScene(Main.scene);

            }else {
                Main.mediaPlayer.play();
            }
            scoreStage.close();
        });


        scoreStage.setOnCloseRequest(we -> numClick++);

    }


    public static void addButtonClicked(Button button) throws IOException {

        Player player = new Player();
        String name=nameInput.getText();
        if(!name.equals("") && !name.contains(";")) {
            player.setName(name);
            player.setScore(GameScene.points);
            scoreRecords.add(GameScene.points);
            playerData.add(player);
            Collections.sort(scoreRecords, Collections.reverseOrder());
            playerData = sortPlayers(playerData);
            if (scoreRecords.size() > 10) {
                playerData.remove(playerData.size()-1);
                scoreRecords.remove(scoreRecords.size()-1);
            }
            table.getItems().clear();
            table.setItems(getPlayer(playerData));
            FileManagment.write(fileName, charset, playerData.asListOfStringArray());
            nameInput.clear();

            enableAddButton = false;
            button.setDisable(true);
        }else
            System.out.println("non valido");


    }


    public static ObservableList<Player> getPlayer(PlayerData playerLst){

        PlayerData sortedPlayers=sortPlayers(playerLst);
        ObservableList<Player> players = FXCollections.observableArrayList();
        LinkedList<Player>  allPlayers= sortedPlayers.getListOfPlayers();
        players.addAll(allPlayers);





        return players;

    }
    public static PlayerData sortPlayers(PlayerData allplayers){
        Player player;
        for(int i=0;i<scoreRecords.size();i++)
            for(int j=0;j<scoreRecords.size();j++)
                if(scoreRecords.get(i)==allplayers.get(j).getScore()) {
                    player = allplayers.get(j);
                    allplayers.set(j, allplayers.get(i));
                    allplayers.set(i, player);
                }
        return allplayers;
    }



}