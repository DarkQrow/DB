package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ModeratorControlController implements Initializable {
@FXML
private Button BackToLogin;
@FXML
private TableView Fly_Table;
@FXML
private Button MSave_btn;

    ArrayList<FlightAsk> Answer = new ArrayList<FlightAsk>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Answer.clear();
        Fly_Table.getColumns().clear();
        Fly_Table.setEditable(true);
        TableColumn<FlightAsk, String> id//
                = new TableColumn<FlightAsk, String>("id");

        TableColumn<FlightAsk, String> basePrice//
                = new TableColumn<FlightAsk, String>("Базовая цена");
        TableColumn<FlightAsk, String> departureTime//
                = new TableColumn<FlightAsk, String>("Время вылета");
        TableColumn<FlightAsk, String> arrivalTime//
                = new TableColumn<FlightAsk, String>("Время приземления");
        TableColumn<FlightAsk, String> airoportDepatureId//
                = new TableColumn<FlightAsk, String>("Код айропорта вылета");
        TableColumn<FlightAsk, String> airoportArrivalId//
                = new TableColumn<FlightAsk, String>("Код айропорта прилета");
        TableColumn<FlightAsk, String> planeId//
                = new TableColumn<FlightAsk, String>("Код самолета");


         id.setCellValueFactory(new PropertyValueFactory<>("id"));



        basePrice.setCellValueFactory(new PropertyValueFactory<>("basePrice"));
        basePrice.setCellFactory(TextFieldTableCell.forTableColumn());
        basePrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk, String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setBasePrice((event.getNewValue()));
            }
        });
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        departureTime.setCellFactory(TextFieldTableCell.forTableColumn());
        departureTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk, String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setDepartureTime((event.getNewValue()));
            }
        });
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime.setCellFactory(TextFieldTableCell.forTableColumn());
        arrivalTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk, String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setArrivalTime((event.getNewValue()));
            }
        });
        airoportDepatureId.setCellValueFactory(new PropertyValueFactory<>("airportDepartureId"));
        airoportDepatureId.setCellFactory(TextFieldTableCell.forTableColumn());
        airoportDepatureId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk, String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setAirportDepartureId(((event.getNewValue())));
            }
        });
        airoportArrivalId.setCellValueFactory(new PropertyValueFactory<>("airportArrivalId"));
        airoportArrivalId.setCellFactory(TextFieldTableCell.forTableColumn());
        airoportArrivalId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk, String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setAirportArrivalId(((event.getNewValue())));
            }
        });
        planeId.setCellValueFactory(new PropertyValueFactory<>("planeId"));
        planeId.setCellFactory(TextFieldTableCell.forTableColumn());
        planeId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk, String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setPlaneId(((event.getNewValue())));
            }
        });


        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
            System.out.println("Open DB Successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from flight");

            while (rs.next()) {
                int Fid = rs.getInt("id");

                int FbasePrice = rs.getInt("base_price");
                String FDT = rs.getTimestamp("departure_time").toString();
                String FAT = rs.getTimestamp("arrival_time").toString();
                int FADid = rs.getInt("airoport_departure_id");
                int FAAid = rs.getInt("airoport_arrival_id");
                int FPid = rs.getInt("plane_id");
                //System.out.println(departure);
                Answer.add(new FlightAsk(Fid + "", FbasePrice + "", FDT, FAT, FADid + "", FAAid + "", FPid + ""));
            }
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

        Set<FlightAsk> set = new HashSet<>(Answer);
        Answer.clear();
        Answer.addAll(set);
        //Syszem.out.println("opopop");
        ObservableList<FlightAsk> list = FXCollections.observableArrayList(Answer);
        Fly_Table.setItems(list);
        //  System.out.println("opopop2");
        for(FlightAsk el:Answer){
            System.out.println(el.getId());
        }
        Fly_Table.getColumns().addAll(id, basePrice, departureTime, arrivalTime, airoportDepatureId, airoportArrivalId, planeId);
        Fly_Table.setVisible(true);
    }
    public void SaveFlight(ActionEvent actionEvent){
        ObservableList<FlightAsk>  list =  Fly_Table.getItems();
        ArrayList<FlightAsk> ask = new ArrayList<>();
        for(FlightAsk el:list) {
            ask.add(el);
        }
        for(FlightAsk el:ask){
            System.out.println(el.getId());
        }
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
            System.out.println("Open DB Successfully");
            stmt = c.createStatement();
            for(FlightAsk el:ask)
                stmt.executeUpdate("Update flight  set base_price = "+el.getBasePrice() +","+
                        "departure_time = '"+el.getDepartureTime()+"',arrival_time = '"+
                        el.getArrivalTime()+"',airoport_departure_id = "+el.getAirportDepartureId()+","+
                        "airoport_arrival_id = "+el.getAirportArrivalId()+",plane_id = "+el.getPlaneId()+"  where id = "+el.getId()+";");

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
    public void BackToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        Scene Buy = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Buy);
        appStage.show();

        //primaryStage.
    }
}
