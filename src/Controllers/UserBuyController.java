package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.CharConversionException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class UserBuyController implements Initializable {
    @FXML
    private Label TIMEWRONG;
    @FXML
    private ComboBox<String> MonthChooser;
    @FXML
    private Button Brone_Button;
    @FXML
    private Label TRYAGAIN;
    @FXML
    private Button Bucket_Button;
    @FXML
    private Button Find_Button;
    @FXML
    private TextField From_Text;
   @FXML
   private DatePicker DataChoose;
    @FXML
    private TextField To_Text;
    @FXML
    private TextField Price_Text;
    @FXML
    private TableView<TicketAsk> Ticket_Table;
    @FXML
    private CheckBox Class_Choice_E;
    @FXML
    private CheckBox Class_Choice_C;
    @FXML
    private CheckBox Class_Choice_B;

    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> langs = FXCollections.observableArrayList("Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентября","Октябрь","Ноябрь","Декабрь");

        MonthChooser.setItems(langs);

    }


    public void BroneTicket(ActionEvent actionEvent) {
        if (Ticket_Table.getSelectionModel().getSelectedItem() != null) {
            TableView.TableViewSelectionModel selectionModel = Ticket_Table.getSelectionModel();
            TicketAsk brone = (TicketAsk) selectionModel.getSelectedItem();
            System.out.println("Selected Value " + brone.getFrom());


            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("Select flight_id from ticket_brone where start = '"+brone.getFrom()+
                        "' and \"end\" = '"+brone.getTo()+"' and class = '"+brone.getKlass()+"'group by flight_id;");
                int flight_id = 0;
                int ticket_id = 0;

                while (rs.next()) {
                    flight_id = rs.getInt("flight_id");
                }
                System.out.println(flight_id);
                //rs = stmt.executeQuery("Select flight_id from ticket where flight_id = "+flight_id+";");
                //if(rs.wasNull()) {
                    stmt.executeUpdate("Insert into ticket (price,flight_id) values (" + brone.getPrice() + " , " + flight_id + " )");
                    rs = stmt.executeQuery("Select id from ticket where price = " + brone.getPrice() + " and flight_id = " + flight_id + "group by id;");
                    while (rs.next()) {
                        ticket_id = rs.getInt("id");
                    }

                    stmt.executeUpdate("Insert into brone (passenger_id,ticket_id) values (" + LoginController.passenger_id + " , '" + ticket_id + "' )");
               // }
               // else System.out.println("Билет на этот рейс забронирован вами");
                    //"Insert into ticket (price,flight_id) values"+
            }catch (Exception e) {
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

    public void FindTickets(ActionEvent actionEvent){
        String[] TicketData = new String[5];
       // String[] klassData = new String[3];
        Boolean check;
        TIMEWRONG.setVisible(false);
        Ticket_Table.getColumns().clear();
        Boolean ec = Class_Choice_E.isSelected();
        Boolean com = Class_Choice_C.isSelected();
        Boolean bus = Class_Choice_B.isSelected();
        //Ticket_Table = new TableView<>();
        // Create column UserName (Data type of String).
        TableColumn<TicketAsk, String> From //
                = new TableColumn<TicketAsk, String>("Откуда");

        TableColumn<TicketAsk, String> To//
                = new TableColumn<TicketAsk, String>("Куда");

        TableColumn<TicketAsk, String> Start//
                = new TableColumn<TicketAsk, String>("Дата вылета");

        TableColumn<TicketAsk, String> End//
                = new TableColumn<TicketAsk, String>("Дата прилета");

        TableColumn<TicketAsk, String> klass//
                = new TableColumn<TicketAsk, String>("Класс");

        TableColumn<TicketAsk, Integer> Price//
                = new TableColumn<TicketAsk, Integer>("Цена");


        // Defines how to fill data for each cell.
        // Get value from property of UserAccount. .
        From.setCellValueFactory(new PropertyValueFactory<>("from"));
        To.setCellValueFactory(new PropertyValueFactory<>("to"));
        Start.setCellValueFactory(new PropertyValueFactory<>("start"));
        End.setCellValueFactory(new PropertyValueFactory<>("end"));
        klass.setCellValueFactory(new PropertyValueFactory<>("klass"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(CheckNullText(From_Text)){
            check=true;
            TicketData[0] = "start like '"+From_Text.getText()+"%' ";

            if(TicketData.length>1) {
                TicketData[0] = TicketData[0].substring(0,12)+Character.toUpperCase(TicketData[0].charAt(12))+TicketData[0].substring(13).toLowerCase(Locale.ROOT);
            }

            System.out.println(TicketData[0]);
        }
        else check = false;
        if(CheckNullText(To_Text)){
            check=true;
            TicketData[1] = "\"end\" like '"+To_Text.getText()+"%' ";
            TicketData[1] = TicketData[1].substring(0,12)+Character.toUpperCase(TicketData[1].charAt(12))+TicketData[1].substring(13).toLowerCase(Locale.ROOT);
        }
if(DataChoose.getValue() != null) {
MonthChooser.setValue(null);
    if ((DataChoose.getValue().isAfter(LocalDate.now()))||(DataChoose.getValue().toString().equals(LocalDate.now().toString()))) {
        String str = DataChoose.getValue().toString();
        System.out.println(str);
        String[] data = str.split("-");

        check = true;
        for (String el : data) {
            System.out.println(el);
        }
        // int [] data = new int[3];

        TicketData[2] = "departure_time::date = '" + str + "' ";
    }
}
        if((MonthChooser.getValue()!=null)){
            check = true;
            String choice = (String)MonthChooser.getValue();
            System.out.println(choice);
            if(choice == "Январь")
                TicketData[2] = "departure_time::text like \'2022-01-%\'";
            if(choice == "Февраль")
                TicketData[2] = "departure_time::text like \'2022-02-%\'";
            if(choice == "Март")
                TicketData[2] = "departure_time::text like \'2022-03-%\'";
            if(choice == "Апрель")
                TicketData[2] = "departure_time::text like \'2022-04-%\'";
            if(choice == "Май")
                TicketData[2] = "departure_time::text like \'2022-05-%\'";
            if(choice == "Июнь")
                TicketData[2] = "departure_time::text like \'2022-06-%\'";
            if(choice == "Июль")
                TicketData[2] = "departure_time::text like \'2022-07-%\'";
            if(choice == "Август")
                TicketData[2] = "departure_time::text like \'2022-08-%\'";
            if(choice == "Сентябрь")
                TicketData[2] = "departure_time::text like \'2022-09-%\'";
            if(choice == "Октябрь")
                TicketData[2] = "departure_time::text like \'2022-10-%\'";
            if(choice == "Ноябрь")
                TicketData[2] = "departure_time::text like \'2022-11-%\'";
            if(choice == "Декабрь")
                TicketData[2] = "departure_time::text like \'2022-12-%\'";
        }
/*
        if(CheckNullText(WhenStart_Text)){
            check=true;
            TicketData[2] = "departure_time ::date = '"+WhenStart_Text.getText()+"' ";
        }


 */

       /* if(CheckNullText(Price_Text)){
            check=true;
            TicketData[4] = "price "+Price_Text.getText();
        }

        */



        if(check) {
            TRYAGAIN.setVisible(false);
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                String sqlAsk ="";
                for(int i = 0;i<5;i++){
                    if(TicketData[i]!=null&&(i!=4)) {
                        if (sqlAsk!="") sqlAsk+=" AND ";
                        sqlAsk += TicketData[i];
                    }
                    if(TicketData[i]!=null&&(i==4)) sqlAsk+=TicketData[i];
                    System.out.println(sqlAsk);
                    //if (sqlAsk!=null&&(i!=4)) sqlAsk+=" AND ";

                }
                String classfinder=" and (";
                if(ec&&com&&bus){
                    classfinder += "class = 'Econom' or class = 'Сomfort' or class = 'Business'";
                }
                else {
                    if (ec) {
                        classfinder +="class = 'Econom'";
                        if(com||bus) classfinder+=" or ";
                    }
                    if (com) {
                        classfinder +="class = 'Сomfort'";//тут русская "С" потому что ты долбоебина
                        if(bus&&(!ec)) classfinder +=" or ";
                    }
                    if (bus) {
                        classfinder +="class = 'Business'";
                    }
                }
                classfinder+=")";
                sqlAsk+=classfinder;
                System.out.println(sqlAsk);
                //Поиск на наличие

                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("select * from ticket_brone  "+
                 "where "+sqlAsk+" ;");

                //сохранение резов
                ArrayList<TicketAsk> Answer = new ArrayList<TicketAsk>();

                while(rs.next()){
                    String departure = rs.getString("start");
                    String arrival = rs.getString("end");
                     String starttime = rs.getTimestamp("departure_time").toString();
                    String endtime = rs.getTimestamp("arrival_time").toString();
                    String klasstype = rs.getString("class");
                    System.out.println(klasstype);
                    int price = rs.getInt("price");
                    //System.out.println(departure);
                    Answer.add(new TicketAsk(departure,arrival,starttime,endtime,klasstype,price));
                }
                Set<TicketAsk> set = new HashSet<>(Answer);
                Answer.clear();
                Answer.addAll(set);
                //System.out.println("opopop");
                ObservableList<TicketAsk> list = FXCollections.observableArrayList(Answer);
               // System.out.println("opopop1");
                Ticket_Table.setItems(list);
              //  System.out.println("opopop2");
                Ticket_Table.getColumns().addAll(From, To, Start, End,klass,Price);
                Ticket_Table.setVisible(true);

            }  catch (Exception e) {
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
        else{
            Ticket_Table.setVisible(false);
            TRYAGAIN.setVisible(true);
        }


    }
    public boolean CheckNullText(TextField ch){
        boolean result = true;

        if(ch.getText()==""){
          //  ch.setStyle("-fx-prompt-text-fill: #FF0000;");
            result = false;
        }
        return result;
    }
    public void BucketChoose(ActionEvent actionEvent) throws IOException {



        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserTickets.fxml"));
        Scene Bucket = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Bucket);
        appStage.show();
        //primaryStage.
    }
}
