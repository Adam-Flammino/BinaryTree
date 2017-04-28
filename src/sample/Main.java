package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
         /* Define panes */
        BorderPane bPane = new BorderPane();
        VBox topBox = new VBox();
        GridPane leftGrid = new GridPane();
        GridPane rightGrid = new GridPane();
        HBox buttonBox = new HBox(15);

        /* Set VBox attributes */
        topBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        topBox.setAlignment(Pos.CENTER);

        /* Set HBox attributes */
        buttonBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        buttonBox.setAlignment(Pos.CENTER);

        /* Set GridPane Attributes */
        leftGrid.setAlignment(Pos.CENTER_LEFT);
        leftGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        leftGrid.setHgap(5.5);
        leftGrid.setVgap(5.5);
        rightGrid.setAlignment(Pos.CENTER_LEFT);
        rightGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        rightGrid.setHgap(5.5);
        rightGrid.setVgap(5.5);

        /* Add panes to BorderPanes, set borderpane attributes */
        bPane.setTop(topBox);
        bPane.setLeft(leftGrid);
        bPane.setRight(rightGrid);
        //bPane.setBottom(buttonBox);

        /* Declare top vBox components */
        Text banner = new Text("Binary Tree");
        banner.setFont(Font.font("Bodoni MT Black", FontWeight.BOLD,
                FontPosture.ITALIC, 28));
        Text instructions = new Text("Preform operations on a binary tree.  Click the Make Tree button to begin!");
        instructions.setTextAlignment(TextAlignment.CENTER);
        topBox.getChildren().add(banner);
        topBox.getChildren().add(instructions);

        /* Add buttonBox components */
        Button btnMakeTree = new Button("Make Tree");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        buttonBox.getChildren().add(btnMakeTree);
        buttonBox.getChildren().add(btnClear);
        buttonBox.getChildren().add(btnExit);

        /* Add left GridPane components */
        TextArea txaRandomArray = new TextArea();
        txaRandomArray.setEditable(false);
        txaRandomArray.setWrapText(true);
        txaRandomArray.setPrefSize(295,60);
        Image binaryCats = new Image("binaryCats.png");
        ImageView catTree = new ImageView(binaryCats);
        leftGrid.add(txaRandomArray, 0, 0);
        leftGrid.add(buttonBox,0,1);
        leftGrid.add(catTree, 0, 2);

        /* Add right GridPane components */
        TextArea txaResults = new TextArea();
        txaResults.setWrapText(true);
        txaResults.setPrefSize(295,432);
        rightGrid.add(txaResults, 0,0);


        /* Creates scene, sets stage */
        Scene scene = new Scene(bPane);
        primaryStage.setTitle("Binary Trees");
        primaryStage.setScene(scene);
        primaryStage.show();

        Validation v = new Validation();

        ArrayList<Integer> arr = new ArrayList<>();


        btnMakeTree.setOnAction((ActionEvent e) ->{
            if (!v.emptyArrayList(arr)){
                arrayExists();
            }
            else{
                Random rand = new Random();
                for (int i = 0; i < 20; i++){ // Populates array with 20 numbers between 1 and 99
                    int r = rand.nextInt(100);
                    arr.add(r);
                }
                txaRandomArray.setText("The array contains the following random numbers:\n");
                arrayToTextArea(arr, txaRandomArray);
                Set<Integer> removeDupes = new HashSet<>(arr); // Removes duplicate values
                arr.clear();
                arr.addAll(removeDupes);
                txaResults.setText("The tree contains the following nodes:\n");
                arrayToTextArea(arr, txaResults);
                BST<Integer> tree = new BST<>(arr);
                tree.breadthFirst();
                String printTree = tree.getOutput();
                txaResults.appendText(printTree);
                tree.clear();
            }
        });

        btnClear.setOnAction((ActionEvent e) ->{
          txaRandomArray.clear();
          txaResults.clear();
          arr.clear();
        });

        btnExit.setOnAction((ActionEvent e) ->{
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });
    }

    /**
     * Warns user if an array already exists
     */
    private void arrayExists(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Array already present!");
        alert.setContentText("Array already present. Please hit the Clear button"
                + " and try again");
        alert.showAndWait();
    }

    /**
     * Takes array, places into text area separated by a comma and a space
     * @param a
     * @param t
     */
    private void arrayToTextArea(ArrayList<Integer> a, TextArea t){
        int size = a.size();
        for (int i = 0; i < size; i++){
            t.appendText(Integer.toString(a.get(i)));
            if (i < size - 1){
                t.appendText(", ");
            }
        }
    }

}

