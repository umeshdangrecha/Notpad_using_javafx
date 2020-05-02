package me.umeshpatidar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import me.umeshpatidar.file.WriteDocument;

import java.io.File;
import java.util.Optional;


public class Controller {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea textArea;

    @FXML
    private void keyEventHandle(KeyEvent event){
        if(event.isControlDown() && event.getCode()== KeyCode.S){
            saveContent();
        }else if(event.isControlDown() && event.getCode() == KeyCode.O){
            openFile();
        }else if(event.isControlDown() && event.getCode() == KeyCode.N){
            newFile();
        }
    }

    @FXML
    private boolean saveContent(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("D:\\"));
        chooser.setTitle("Save File Here...");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text","*.txt"));
        chooser.setInitialFileName("New File");
        File file = chooser.showSaveDialog(borderPane.getScene().getWindow());
        if(file != null){
            return WriteDocument.write(textArea.getText(),file);
        }
        return false;
    }

    @FXML
    private void openFile(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text","*.txt"));
        File file = chooser.showOpenDialog(borderPane.getScene().getWindow());

        String content = "";
        if(file != null){
            content = WriteDocument.read(file);
        }
        textArea.setText(content);
    }

    @FXML
    private void newFile(){
        if(textArea.getText().trim().equals("") || textArea.getText() == null){

        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save Old Content");
            alert.setContentText("Do you want to save changes to Untitled?");
            alert.setHeaderText(null);
            alert.getButtonTypes().add(ButtonType.CLOSE);
            Optional<ButtonType> result = alert.showAndWait();
            System.out.println(result.get());
            if(result.isPresent() && result.get() == ButtonType.OK){
                System.out.println("Ok Pressed..");
                if(saveContent())
                    textArea.setText("");
            }
            if(result.isPresent() && result.get()== ButtonType.CANCEL){
                System.out.println("Cancel Pressed..");
            }

            if(result.isPresent() && result.get() == ButtonType.CLOSE){
                System.out.println("Close Pressed..");
                textArea.setText("");
            }

        }
    }

    @FXML
    private void newWindow(){

    }
    @FXML
    private void exit(){
        Platform.exit();
    }


}
