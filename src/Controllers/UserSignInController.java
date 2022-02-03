package Controllers;
import java.sql.*;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignInController {
    @FXML
    private Label LOGINPLEASE;
    @FXML
    private Button Log_Button;
    @FXML
    private Button RegFinish_Button;
    @FXML
    private TextField Login_Text;
    @FXML
    private PasswordField Password_Text;
    @FXML
    private TextField Pasport_Text;
    @FXML
    private TextField FIO_Text;
    @FXML
    private TextField Phone_Text;
    @FXML
    private ImageView Success_Reg_Image;
    @FXML
    private ImageView Failed_Reg_Image;

    public void RegFinish(ActionEvent actionEvent){
        Boolean check;
        String []NewUserData = new String[6];//0-login 1-password 2-pasport_ser 3-pasport_num 4-FIO
        if(CheckNullText(Login_Text)&&CheckLoginText(Login_Text)) {
            NewUserData[0] = Login_Text.getText();
            check = true;
        }
        else check = false;
        if(CheckNullText(Password_Text)&&CheckPasswordText(Password_Text)){
        NewUserData[1] = Password_Text.getText();
            check = true;
        }
        else check = false;
        if(CheckNullText(Pasport_Text)&&CheckPasportText(Pasport_Text)) {
            if(Pasport_Text.getText().length()<11)Pasport_Text.insertText(4," ");
            NewUserData[2] = Pasport_Text.getText().substring(0,4).replaceAll(" ","");
            NewUserData[3] = Pasport_Text.getText().substring(5,11).replaceAll(" ","");
            check = true;
        }
        else check = false;
        if(CheckNullText(FIO_Text)){
        NewUserData[4] = FIO_Text.getText();
            check = true;
        }
        else check = false;
        /*  if(CheckNullText(Phone_Text)&&CheckPhoneText(Phone_Text)){
        NewUserData[5] = Phone_Text.getText();
            check = true;
        }
        else check = false;

         */
        for (String el:NewUserData) {
            System.out.println(el);
        }

        //Добавление в БД Нового пользователя
        if(check) {
            Login_Text.setDisable(true);
            Password_Text.setDisable(true);
            Pasport_Text.setDisable(true);
            FIO_Text.setDisable(true);
            // Phone_Text.setDisable(true);
            Failed_Reg_Image.setVisible(false);

            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully2");
                //Проверка на уникальность
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("Select * From pass where login = '"+NewUserData[0]+"' and password = '"+NewUserData[1]+"' and users = 'Passenger';");
                System.out.println(rs.toString());
                if(!rs.next()) {
                    //Добавление
                    // System.out.println('1');
                    String[]FIO = NewUserData[4].split(" ");
                    System.out.println(FIO[0]+"/"+FIO[1]+"/"+FIO[2]);
                    Success_Reg_Image.setVisible(true);
                    String sql = "\n" +
                            "Insert into pass (login,users,password)" +
                            "Values( '" + NewUserData[0] + "' , 'Passenger ','" + NewUserData[1] + "');";
                    stmt.executeUpdate(sql);
                    //0-login 1-password 2-pasport_ser 3-pasport_num 4-FIO 5-Phone
                    String sql2 = "\n" +
                            "Insert into passenger (first_name,last_name,middle_name,phone_number,pass_num,pass_ser)" +
                            "Values( '" + FIO[0] + "' , '"+FIO[1]+"','"+FIO[2]+"','"+NewUserData[0]+"','"+NewUserData[3]+"','"+NewUserData[2]+"');";
                    stmt.executeUpdate(sql2);

                }

                else{
                    Failed_Reg_Image.setVisible(true);
                    LOGINPLEASE.setStyle("-fx-text-fill: #FF0000;");

                }
                rs.close();
                c.close();
                RegFinish_Button.setDisable(true);
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
        //Main.c.createStatement();
        //System.out.println(NewUserData.toString());
    }
    public boolean CheckNullText(TextField ch){
        boolean result = true;
        System.out.println(ch.getText());
        if(ch.getText()==""){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            Failed_Reg_Image.setVisible(true);

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
            Failed_Reg_Image.setVisible(true);
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
            Failed_Reg_Image.setVisible(true);
            result = false;
        }
        return result;
    }
    public boolean CheckPasportText(TextField ch){
        boolean result = true;

        String regex = "\\d{4}\\s?\\d{6}";
       // String str = ch.getText();

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(ch.getText());
        if(!matcher.matches()){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            ch.clear();
            Failed_Reg_Image.setVisible(true);
            result = false;
        }
        return result;
    }
    public boolean CheckFIOText(TextField ch){
        boolean result = true;
        String regex = "^[А-Яа-я]*";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(ch.getText());
        Matcher matcher = pattern.matcher(ch.getText());
        if(!matcher.matches()){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            ch.clear();
            Failed_Reg_Image.setVisible(true);
            result = false;
        }
        return result;
    }
    public boolean CheckPhoneText(TextField ch){
        boolean result = true;
        String regex = "\\+?\\d+([\\(\\s\\-]?\\d+[\\)\\s\\-]?[\\d\\s\\-]+)?";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(ch.getText());
        Matcher matcher = pattern.matcher(ch.getText());
        if(!matcher.matches()){
            ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            ch.clear();
            Failed_Reg_Image.setVisible(true);
            result = false;
        }
        return result;
    }
    public void LoginChoose(ActionEvent actionEvent) throws IOException {
        LOGINPLEASE.setStyle("-fx-text-fill: BLACK;");
        Failed_Reg_Image.setVisible(false);
        Success_Reg_Image.setVisible(false);
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        Scene Login = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Login);
        appStage.show();
        //primaryStage.
    }

}
