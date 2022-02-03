package Controllers;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private Label WRONG;
    @FXML
    private Label SIGNINPLEASE;
    @FXML
    private Button Registration_Button;
    @FXML
    private Button In_Button;
    @FXML
    private CheckBox Moderator_Quetion;
    @FXML
    private  TextField Login_Text;
    @FXML
    private PasswordField Password_Text;
    public static int passenger_id;
    public static String sys_login;
    public void LogFinish(ActionEvent actionEvent) throws IOException {



        String[]LogUserData = new String[2];//0-login 1-password
        Boolean check = false;

        if(CheckNullText(Login_Text))//&&CheckLoginText(Login_Text))
        {
            LogUserData[0] = Login_Text.getText();
            sys_login = Login_Text.getText();
            check = true;
        }
        else check = false;
        if(CheckNullText(Password_Text))//&&CheckPasswordText(Password_Text))
        {
            LogUserData[1] = Password_Text.getText();
            check = true;
        }
        System.out.println(Moderator_Quetion.isSelected()+check.toString());
        if(check) {
            if ((!Moderator_Quetion.isSelected())) {

                // Login_Text.setDisable(true);
                //  Password_Text.setDisable(true);
                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");
                    //Проверка на наличие
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("Select * From pass where login = '" + LogUserData[0] + "' and password = '" + LogUserData[1] + "' and users = 'Passenger'");

                    if (rs.wasNull()) {

                        SIGNINPLEASE.setStyle("-fx-text-fill: #FF0000;");
                        WRONG.setVisible(true);


                    } else {
                        rs = stmt.executeQuery("Select id from passenger where phone_number = '"+LoginController.sys_login+"' group by id; ");
                        while (rs.next()) {
                            passenger_id = rs.getInt("id");
                        }

                        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserBuy.fxml"));
                        Scene Buy = new Scene(root);
                        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        appStage.setScene(Buy);
                        appStage.show();


                        //   Failed_Reg_Image.setVisible(true);
                        //    LOGINPLEASE.setStyle("-fx-text-fill: #FF0000;");

                    }
                    rs.close();
                    stmt.close();
                    c.close();
                    //  RegFinish_Button.setDisable(true);
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }

            }
            if ((Moderator_Quetion.isSelected()) ) {
                Connection c = null;
                Statement stmt = null;

                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");
                    //Проверка на наличие


                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("Select * From pass where login = '" + LogUserData[0] + "' and password = '" + LogUserData[1] + "' and users = 'Helper'");

                    if (rs.wasNull()) {
                        SIGNINPLEASE.setStyle("-fx-text-fill: #FF0000;");
                        WRONG.setVisible(true);

                    } else {
                        Parent root;
                        System.out.println(LogUserData[0]+" "+LogUserData[1]);

                        if((LogUserData[0].equals("admin"))&&(LogUserData[1].equals("admin"))) {
                            System.out.println("FFFFFFFFFFFFffff....");
                            root = FXMLLoader.load(getClass().getResource("FXML/AdminWindow.fxml"));
                        }
                        else {
                            root = FXMLLoader.load(getClass().getResource("FXML/ModeratorController.fxml"));
                        }
                        Scene Contoll = new Scene(root);
                        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        appStage.setScene(Contoll);
                        appStage.show();
                        //   Failed_Reg_Image.setVisible(true);
                        //    LOGINPLEASE.setStyle("-fx-text-fill: #FF0000;");

                    }
                    rs.close();
                    stmt.close();
                    c.close();
                    //  RegFinish_Button.setDisable(true);
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }

    }
    public boolean CheckNullText(TextField ch){
        boolean result = true;
        System.out.println(ch.getText());
        if(ch.getText()==""){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            result = false;
        }
        return result;
    }
    public boolean CheckLoginText(TextField ch){
        boolean result = true;
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(ch.getText());
        Matcher matcher = pattern.matcher(ch.getText());
        if(!matcher.matches()){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            ch.clear();
            result = false;
        }
        return result;
    }
    public boolean CheckPasswordText(PasswordField ch){
        boolean result = true;
        System.out.println(ch.getText());
        if(ch.getText().length()<6){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            ch.clear();
            result = false;
        }
        return result;
    }
    public void RegistationChoose(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserSign.fxml"));
        Scene Registration = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Registration);
        appStage.show();
        //primaryStage.
    }

}
