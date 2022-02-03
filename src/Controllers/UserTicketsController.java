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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class UserTicketsController implements Initializable {

    ArrayList<TicketAsk> Answer = new ArrayList<TicketAsk>();
    @FXML
    private Button BackToBuy_Button;
    @FXML
    private TableView UserTickets_Table;
    @FXML
    private Button BackToLogin;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        TableColumn<TicketAsk, String> From //
                = new TableColumn<TicketAsk, String>("Откуда");

        TableColumn<TicketAsk, String> To//
                = new TableColumn<TicketAsk, String>("Куда");
        TableColumn<TicketAsk, String> klass//
                = new TableColumn<TicketAsk, String>("Класс");

        TableColumn<TicketAsk, Integer> Price//
                = new TableColumn<TicketAsk, Integer>("Цена");

        From.setCellValueFactory(new PropertyValueFactory<>("from"));
        To.setCellValueFactory(new PropertyValueFactory<>("to"));
        klass.setCellValueFactory(new PropertyValueFactory<>("klass"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
            System.out.println("Open DB Successfully");

            stmt = c.createStatement();

            System.out.println("Passenger"+LoginController.passenger_id);




                ResultSet rs = stmt.executeQuery("select  * from ticket_brone tb, ticket t, brone b where tb.flight_id = t.flight_id and tb.price = t.price and b.ticket_id = t.id and b.passenger_id = "+LoginController.passenger_id);
                while (rs.next()) {
                    String departure = rs.getString("start");
                    String arrival = rs.getString("end");
                    String klasstype = rs.getString("class");
                    System.out.println(klasstype);
                    int price = rs.getInt("price");
                    System.out.println(departure);
                    Answer.add(new TicketAsk(departure, arrival, "0", "0", klasstype, price));
                }

            System.out.println(Answer.size());
            Set<TicketAsk> set = new HashSet<>(Answer);
            Answer.clear();
            Answer.addAll(set);
            //System.out.println("opopop");
            ObservableList<TicketAsk> list = FXCollections.observableArrayList(Answer);
            UserTickets_Table.setItems(list);
            UserTickets_Table.getColumns().addAll(From, To,klass,Price);
            UserTickets_Table.setVisible(true);
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
    public void BackChoose(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/UserBuy.fxml"));
        Scene Buy = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Buy);
        appStage.show();

        //primaryStage.
    }
    public void LoginBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        Scene Buy = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Buy);
        appStage.show();

        //primaryStage.
    }
    public void Delete_Brone(ActionEvent actionEvent){
        if (UserTickets_Table.getSelectionModel().getSelectedItem() != null) {
            TableView.TableViewSelectionModel selectionModel = UserTickets_Table.getSelectionModel();
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
                rs = stmt.executeQuery("Select id from ticket where price = " + brone.getPrice() + " and flight_id = " + flight_id + "group by id;");
                while (rs.next()) {
                    ticket_id = rs.getInt("id");
                }

                stmt.executeUpdate("delete from brone where passenger_id =  " + LoginController.passenger_id + " and ticket_id = " + ticket_id);
                Answer.remove(brone);
                ObservableList<TicketAsk> list = FXCollections.observableArrayList(Answer);
                UserTickets_Table.setItems(list);
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
}
