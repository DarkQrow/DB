import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main extends Application {
    private Scene Login ;
    private Scene Registration;
    Stage window;


    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Открытие окна входа
        String FXMLPackage = ("Controllers/FXML/");
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPackage+"Login.fxml"));
        Login = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(Login);
        (this.window = primaryStage).show();

        //Connection to DB
        //c.setAutoCommit(false);


    }


}
