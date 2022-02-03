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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminWindowController implements Initializable {
    @FXML
    private TextField PaswLogAdd;
    @FXML
    private TextField PaswRolAdd;
    @FXML
    private TextField PaswPaswAdd;
    @FXML
    private Button BtnPaswAdd;
    @FXML
    private AnchorPane PaswAdd;
    @FXML
    private TextField PassenFNAdd;
    @FXML
    private TextField PassenLNAdd;
    @FXML
    private  TextField PassenMNAdd;
    @FXML
    private TextField PassenPNAdd;
    @FXML
    private TextField PassenPSAdd;
    @FXML
    private TextField PassenPhNAdd;
    @FXML
    private Button BtnPassenAdd;
    @FXML
    private AnchorPane PassenAdd;
    @FXML
    private TextField CountryAddName;
    @FXML
    private TextField CountryAddCode;
    @FXML
    private Button BtnAddCountry;
    @FXML
    private AnchorPane CountryAdd;
    @FXML
    private TextField CityAddName;
    @FXML
    private TextField CityAddCountryCode;
    @FXML
    private Button BtnAddCity;
    @FXML
    private AnchorPane CityAdd;
    @FXML
    private TextField AiroportNameAdd;
    @FXML
    private TextField AiroportSiteAdd;
    @FXML
    private TextField AiroportCityCodeAdd;
    @FXML
    private TextField AiroportCodeAdd;
    @FXML
    private TextField SeatClassAdd;
    @FXML
    private TextField SeatCCountAdd;
    @FXML
    private TextField SeatNAdd;
    @FXML
    private TextField SeatBusyAdd;
    @FXML
    private TextField SeatPidAdd;
    @FXML
    private Button BtnSeatAdd;
    @FXML
    private AnchorPane SeatAdd;
    @FXML
    private Button BtnAiroportAdd;
    @FXML
    private AnchorPane AiroportAdd;
    @FXML
    private TextField HelperFNAdd;
    @FXML
    private TextField HelperLNAdd;
    @FXML
    private TextField HelperMNAdd;
    @FXML
    private TextField HelperAidAdd;
    @FXML
    private TextField HelperPNAdd;
    @FXML
    private Button BtnHelperAdd;
    @FXML
    private AnchorPane HelperAdd;
    @FXML
    private TextField PlaneNameAdd;
    @FXML
    private TextField PlaneTypeAdd;
    @FXML
    private Button BtnPlaneAdd;
    @FXML
    private AnchorPane PlaneAdd;
    @FXML
    private AnchorPane FlightAdd;
    @FXML
    private TextField FBasePrice;
    @FXML
    private TextField FDT;
    @FXML
    private TextField FAT;
    @FXML
    private TextField FDAId;
    @FXML
    private TextField FAAId;
    @FXML
    private TextField FPI;
    @FXML
    private Button BtnFlightAdd;
    @FXML
    private Label FUCK;
    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button Return;
    @FXML
    private TableView TableShow;
    @FXML
    private Button Dump;
    @FXML
    private Button Recover;
    @FXML
    private ComboBox<String> Table_Choice_Admin;

    ArrayList<CountryAsk> AnswerCo = new ArrayList<CountryAsk>();
    ArrayList<CityAsk> AnswerC = new ArrayList<CityAsk>();
    ArrayList<AiroprtsAsk> AnswerA = new ArrayList<AiroprtsAsk>();
    ArrayList<HelperAsk> AnswerH = new ArrayList<HelperAsk>();
    ArrayList<PlaneAsk> AnswerP = new ArrayList<PlaneAsk>();
    ArrayList<FlightAsk> AnswerF = new ArrayList<FlightAsk>();
    ArrayList<SeatAsk> AnswerS = new ArrayList<>();
    ArrayList<TicketAdAsk> AnswerT = new ArrayList<TicketAdAsk>();
    ArrayList<BroneAsk> AnswerB = new ArrayList<BroneAsk>();
    ArrayList<PassengerAsk> AnswerPa = new ArrayList<PassengerAsk>();
    ArrayList<PassAsk> AnswerPass = new ArrayList<PassAsk>();
    ArrayList<StoryAsk> AnswerStory = new ArrayList<StoryAsk>();

    public void ChooseTable(ActionEvent actionEvent){
        FUCK.setVisible(false);
        String choice = Table_Choice_Admin.getValue();
        TableShow.getColumns().clear();
        System.out.println(choice);
        if(choice == "Country") showTableCountry();
        if(choice == "City") showTableCity();
        if(choice == "Airport") showTableAiroport();
        if(choice == "Helper") showTableHelper();
        if(choice == "Plane") showTablePlane();
        if(choice == "Seat") showTableSeat();
        if(choice == "Flight") showTableFlight();
        if(choice == "Ticket") showTableTicket();
        if(choice == "Brone") showTableBrone();
        if(choice == "Passenger") showTablePassenger();
        if (choice=="Pass") showTablePass();
        if(choice == "Story") showTableStory();
    }

    public void showTableStory() {
        AnswerStory.clear();
        TableColumn<StoryAsk, Integer> id//
                = new TableColumn<StoryAsk, Integer>("id");

        TableColumn<StoryAsk, String> tableName//
                = new TableColumn<StoryAsk, String>("Название таблицы");

        TableColumn<StoryAsk, String> tableId//
                = new TableColumn<StoryAsk, String>("Код в табл.");
        TableColumn<StoryAsk, String> operation//
                = new TableColumn<StoryAsk, String>("Операция");
        TableColumn<StoryAsk, String> operationTime//
                = new TableColumn<StoryAsk, String>("Время выполнения");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        tableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        operation.setCellValueFactory(new PropertyValueFactory<>("operation"));
        operationTime.setCellValueFactory(new PropertyValueFactory<>("operationTime"));

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
            System.out.println("Open DB Successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from story");

            while (rs.next()) {
                int storyid = rs.getInt("id");
                String storyname = rs.getString("name_table");
                String storytableid = rs.getString("table_id");
                String storyoperation = rs.getString("operation");
                String storyoperationtime = rs.getString("operation_time");
                //System.out.println(departure);
                AnswerStory.add(new StoryAsk(storyid, storyname, storytableid, storyoperation, storyoperationtime));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) { /* ignored */}
            }
        }


        Set<StoryAsk> set = new HashSet<>(AnswerStory);
        AnswerStory.clear();
        AnswerStory.addAll(set);
        //System.out.println("opopop");
        ObservableList<StoryAsk> list = FXCollections.observableArrayList(AnswerStory);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,tableName,tableId,operation,operationTime);
        TableShow.setVisible(true);
    }
    public void showTableCountry(){
        AnswerCo.clear();
        TableColumn<CountryAsk, String> id//
                = new TableColumn<CountryAsk, String>("id");

        TableColumn<CountryAsk, String> name//
                = new TableColumn<CountryAsk, String>("Название");

        TableColumn<CountryAsk, String> code//
                = new TableColumn<CountryAsk, String>("Код");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));



        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CountryAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CountryAsk,String> event) {
                CountryAsk ask = event.getRowValue();
                ask.setName((event.getNewValue()));
            }
        });
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        code.setCellFactory(TextFieldTableCell.forTableColumn());
        code.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CountryAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CountryAsk,String> event) {
                CountryAsk ask = event.getRowValue();
                ask.setCode((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from country");

            while(rs.next()){
                int countyid = rs.getInt("id");
                String countryname = rs.getString("name");
                String countrycode = rs.getString("code");
                //System.out.println(departure);
                AnswerCo.add(new CountryAsk(countyid+"",countryname,countrycode));
            }
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

        Set<CountryAsk> set = new HashSet<>(AnswerCo);
        AnswerCo.clear();
        AnswerCo.addAll(set);
        //System.out.println("opopop");
        ObservableList<CountryAsk> list = FXCollections.observableArrayList(AnswerCo);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,name,code);
        TableShow.setVisible(true);
    }
    public void showTableCity(){
        AnswerC.clear();
        TableColumn<CityAsk, String> id//
                = new TableColumn<CityAsk, String>("id");

        TableColumn<CityAsk, String> name//
                = new TableColumn<CityAsk, String>("Название");

        TableColumn<CityAsk, String> cityId//
                = new TableColumn<CityAsk, String>("Код Страны");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CityAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CityAsk,String> event) {
                CityAsk ask = event.getRowValue();
                ask.setName((event.getNewValue()));
            }
        });
        cityId.setCellValueFactory(new PropertyValueFactory<>("cityId"));
        cityId.setCellFactory(TextFieldTableCell.forTableColumn());
        cityId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CityAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CityAsk,String> event) {
                CityAsk ask = event.getRowValue();
                ask.setCityId(((event.getNewValue())));
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
            ResultSet rs = stmt.executeQuery("select * from city");

            while(rs.next()){
                int countyid = rs.getInt("id");
                String countryname = rs.getString("name");
                int citycode = rs.getInt("country_id");
                //System.out.println(departure);
                AnswerC.add(new CityAsk(countyid+"",countryname,citycode+""));
            }
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

        Set<CityAsk> set = new HashSet<>(AnswerC);
        AnswerC.clear();
        AnswerC.addAll(set);
        //System.out.println("opopop");
        ObservableList<CityAsk> list = FXCollections.observableArrayList(AnswerC);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,name,cityId);
        TableShow.setVisible(true);
    }
    public void showTableAiroport(){
        AnswerA.clear();
        TableShow.getItems().clear();
        TableColumn<AiroprtsAsk, String> id//
                = new TableColumn<AiroprtsAsk, String>("id");

        TableColumn<AiroprtsAsk, String> name//
                = new TableColumn<AiroprtsAsk, String>("Название");

        TableColumn<AiroprtsAsk, String> cityId//
                = new TableColumn<AiroprtsAsk, String>("Код Города");
        TableColumn<AiroprtsAsk, String> site//
                = new TableColumn<AiroprtsAsk, String>("Сайт");
        TableColumn<AiroprtsAsk, String> code//
                = new TableColumn<AiroprtsAsk, String>("Код");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AiroprtsAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AiroprtsAsk,String> event) {
                AiroprtsAsk ask = event.getRowValue();
                ask.setName(event.getNewValue());
            }
        });

        cityId.setCellValueFactory(new PropertyValueFactory<>("cityId"));
        cityId.setCellFactory(TextFieldTableCell.forTableColumn());
        cityId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AiroprtsAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AiroprtsAsk,String> event) {
                AiroprtsAsk ask = event.getRowValue();
                ask.setCityId((event.getNewValue()));
            }
        });
        site.setCellValueFactory(new PropertyValueFactory<>("site"));
        site.setCellFactory(TextFieldTableCell.forTableColumn());
        site.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AiroprtsAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AiroprtsAsk,String> event) {
                AiroprtsAsk ask = event.getRowValue();
                ask.setSite((event.getNewValue()));
            }
        });
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        code.setCellFactory(TextFieldTableCell.forTableColumn());
        code.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AiroprtsAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AiroprtsAsk,String> event) {
                AiroprtsAsk ask = event.getRowValue();
                ask.setCode((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from Airoport");

            while(rs.next()){
                int airid = rs.getInt("id");
                String airname = rs.getString("name");
                int aircodecountry = rs.getInt("city_id");
                String airsite = rs.getString("site");
                String aircode = rs.getString("code");
                //System.out.println(departure);
                AnswerA.add(new AiroprtsAsk(airid+"",airname,airsite,aircodecountry+"",aircode));
            }
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

        Set<AiroprtsAsk> set = new HashSet<>(AnswerA);
        AnswerA.clear();
        AnswerA.addAll(set);
        //System.out.println("opopop");
        ObservableList<AiroprtsAsk> list = FXCollections.observableArrayList(AnswerA);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,name,cityId);
        TableShow.setVisible(true);
    }
    public void showTableHelper(){
        AnswerH.clear();

        TableColumn<HelperAsk, String> id//
                = new TableColumn<HelperAsk, String>("id");

        TableColumn<HelperAsk, String> phoneNumber//
                = new TableColumn<HelperAsk, String>("Номер телефон");
        TableColumn<HelperAsk, String> firstName//
                = new TableColumn<HelperAsk, String>("Имя");
        TableColumn<HelperAsk, String> lastName//
                = new TableColumn<HelperAsk, String>("Фамилия");
        TableColumn<HelperAsk, String> middleName//
                = new TableColumn<HelperAsk, String>("Отчество");
        TableColumn<HelperAsk, String> airportId//
                = new TableColumn<HelperAsk, String>("КодАйропорта");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HelperAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HelperAsk,String> event) {
                HelperAsk ask = event.getRowValue();
                ask.setPhoneNumber(((event.getNewValue())));
            }
        });
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        firstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HelperAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HelperAsk,String> event) {
                HelperAsk ask = event.getRowValue();
                ask.setFirstName((event.getNewValue()));
            }
        });
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        lastName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HelperAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HelperAsk,String> event) {
                HelperAsk ask = event.getRowValue();
                ask.setLastName((event.getNewValue()));
            }
        });
        middleName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        middleName.setCellFactory(TextFieldTableCell.forTableColumn());
        middleName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HelperAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HelperAsk,String> event) {
                HelperAsk ask = event.getRowValue();
                ask.setMiddleName((event.getNewValue()));
            }
        });
        airportId.setCellValueFactory(new PropertyValueFactory<>("airportId"));
        airportId.setCellFactory(TextFieldTableCell.forTableColumn());
        airportId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HelperAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<HelperAsk,String> event) {
                HelperAsk ask = event.getRowValue();
                ask.setAirportId((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from Helper");

            while(rs.next()){
                int Hid = rs.getInt("id");

                long HPnumber = rs.getLong("phone_number");
                String HFname = rs.getString("first_name");
                String HLname = rs.getString("last_name");
                String HMname = rs.getString("middle_name");
                int HAid = rs.getInt("airoport_id");
                //System.out.println(departure);
                AnswerH.add(new HelperAsk(Hid+"",HPnumber+"",HFname,HLname,HMname,HAid+""));
            }
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

        Set<HelperAsk> set = new HashSet<>(AnswerH);
        AnswerH.clear();
        AnswerH.addAll(set);
        //System.out.println("opopop");
        ObservableList<HelperAsk> list = FXCollections.observableArrayList(AnswerH);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,phoneNumber,firstName,lastName,middleName,airportId);
        TableShow.setVisible(true);
    }
    public void showTablePlane(){
        AnswerP.clear();

        TableColumn<PlaneAsk, String> id//
                = new TableColumn<PlaneAsk, String>("id");
        TableColumn<PlaneAsk, String> name//
                = new TableColumn<PlaneAsk, String>("Название");
        TableColumn<PlaneAsk, String> type//
                = new TableColumn<PlaneAsk, String>("Тип");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PlaneAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PlaneAsk,String> event) {
                PlaneAsk ask = event.getRowValue();
                ask.setName((event.getNewValue()));
            }
        });
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PlaneAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PlaneAsk,String> event) {
                PlaneAsk ask = event.getRowValue();
                ask.setType((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from plane");

            while(rs.next()){
                int Pid = rs.getInt("id");
                String Pname = rs.getString("name");
                String Ptype = rs.getString("type");
                //System.out.println(departure);
                AnswerP.add(new PlaneAsk(Pid+"",Pname,Ptype));
            }
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

        Set<PlaneAsk> set = new HashSet<>(AnswerP);
        AnswerP.clear();
        AnswerP.addAll(set);
        //System.out.println("opopop");
        ObservableList<PlaneAsk> list = FXCollections.observableArrayList(AnswerP);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,name,type);
        TableShow.setVisible(true);
    }
    public void showTableSeat(){
        AnswerS.clear();

        TableColumn<SeatAsk, String> id//
                = new TableColumn<SeatAsk, String>("id");
        TableColumn<SeatAsk, String> klass//
                = new TableColumn<SeatAsk, String>("Класс");
        TableColumn<SeatAsk, String> ccount//
                = new TableColumn<SeatAsk, String>("Коэ.расчет");
        TableColumn<SeatAsk, String> seatNumber//
                = new TableColumn<SeatAsk, String>("#Места");
        TableColumn<SeatAsk, String> Busyiness//
                = new TableColumn<SeatAsk, String>("Занятость");
        TableColumn<SeatAsk, String> planeId//
                = new TableColumn<SeatAsk, String>("Код самолета");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        klass.setCellValueFactory(new PropertyValueFactory<>("klass"));
        klass.setCellFactory(TextFieldTableCell.forTableColumn());
        klass.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeatAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SeatAsk,String> event) {
                SeatAsk ask = event.getRowValue();
                ask.setKlass(event.getNewValue());
            }
        });
        ccount.setCellValueFactory(new PropertyValueFactory<>("ccount"));
        ccount.setCellFactory(TextFieldTableCell.forTableColumn());
        ccount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeatAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SeatAsk,String> event) {
                SeatAsk ask = event.getRowValue();
                ask.setCcount((event.getNewValue()));
            }
        });
        seatNumber.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        seatNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        seatNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeatAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SeatAsk,String> event) {
                SeatAsk ask = event.getRowValue();
                ask.setSeatNumber(event.getNewValue());
            }
        });
        Busyiness.setCellValueFactory(new PropertyValueFactory<>("Busyiness"));
        Busyiness.setCellFactory(TextFieldTableCell.forTableColumn());
        Busyiness.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeatAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SeatAsk,String> event) {
                SeatAsk ask = event.getRowValue();
                ask.setBusyiness(event.getNewValue());
            }
        });
        planeId.setCellValueFactory(new PropertyValueFactory<>("planeId"));
        planeId.setCellFactory(TextFieldTableCell.forTableColumn());
        planeId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeatAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SeatAsk,String> event) {
                SeatAsk ask = event.getRowValue();
                ask.setPlaneId((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from seat");

            while(rs.next()){
                int Sid = rs.getInt("id");
                String Sclass = rs.getString("class");
                Float Scount = rs.getFloat("ccount");
                String SSnumber = rs.getString("seat_number");
                Boolean SBusyiness = rs.getBoolean("busyiness");
                int SPid = rs.getInt("plane_id");
                //System.out.println(departure);
                AnswerS.add(new SeatAsk(Sid+"",Sclass,Scount+"",SSnumber,SBusyiness+"",SPid+""));
            }
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

        Set<SeatAsk> set = new HashSet<>(AnswerS);
        AnswerS.clear();
        AnswerS.addAll(set);
        //System.out.println("opopop");
        ObservableList<SeatAsk> list = FXCollections.observableArrayList(AnswerS);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,klass,ccount,seatNumber,Busyiness,planeId);
        TableShow.setVisible(true);
    }
    public void showTableFlight(){
        AnswerF.clear();

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
            public void handle(TableColumn.CellEditEvent<FlightAsk,String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setBasePrice((event.getNewValue()));
            }
        });
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        departureTime.setCellFactory(TextFieldTableCell.forTableColumn());
        departureTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk,String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setDepartureTime((event.getNewValue()));
            }
        });
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime.setCellFactory(TextFieldTableCell.forTableColumn());
        arrivalTime.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk,String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setArrivalTime((event.getNewValue()));
            }
        });
        airoportDepatureId.setCellValueFactory(new PropertyValueFactory<>("airportDepartureId"));
        airoportDepatureId.setCellFactory(TextFieldTableCell.forTableColumn());
        airoportDepatureId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk,String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setAirportDepartureId(((event.getNewValue())));
            }
        });
        airoportArrivalId.setCellValueFactory(new PropertyValueFactory<>("airportArrivalId"));
        airoportArrivalId.setCellFactory(TextFieldTableCell.forTableColumn());
        airoportArrivalId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk,String> event) {
                FlightAsk ask = event.getRowValue();
                ask.setAirportArrivalId(((event.getNewValue())));
            }
        });
        planeId.setCellValueFactory(new PropertyValueFactory<>("planeId"));
        planeId.setCellFactory(TextFieldTableCell.forTableColumn());
        planeId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FlightAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FlightAsk,String> event) {
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

            while(rs.next()){
                int Fid = rs.getInt("id");

                int FbasePrice = rs.getInt("base_price");
                String FDT = rs.getTimestamp("departure_time").toString();
                String FAT = rs.getTimestamp("arrival_time").toString();
                int FADid = rs.getInt("airoport_departure_id");
                int FAAid = rs.getInt("airoport_arrival_id");
                int FPid = rs.getInt("plane_id");
                //System.out.println(departure);
                AnswerF.add(new FlightAsk(Fid+"",FbasePrice+"",FDT,FAT,FADid+"",FAAid+"",FPid+""));
            }
        }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally {

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) { /* ignored */}
            }
        }

        Set<FlightAsk> set = new HashSet<>(AnswerF);
        AnswerF.clear();
        AnswerF.addAll(set);
        //System.out.println("opopop");
        ObservableList<FlightAsk> list = FXCollections.observableArrayList(AnswerF);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,basePrice,departureTime,arrivalTime,airoportDepatureId,airoportArrivalId,planeId);
        TableShow.setVisible(true);
    }
    public void showTableTicket(){
        AnswerT.clear();

        TableColumn<TicketAdAsk, String> id//
                = new TableColumn<TicketAdAsk, String>("id");
        TableColumn<TicketAdAsk, String> price//
                = new TableColumn<TicketAdAsk, String>("Цена");
        TableColumn<TicketAdAsk, String> flightId//
                = new TableColumn<TicketAdAsk, String>("Код перелета");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TicketAdAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TicketAdAsk,String> event) {
                TicketAdAsk ask = event.getRowValue();
                ask.setPrice(((event.getNewValue())));
            }
        });
        flightId.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        flightId.setCellFactory(TextFieldTableCell.forTableColumn());
        flightId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TicketAdAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TicketAdAsk,String> event) {
                TicketAdAsk ask = event.getRowValue();
                ask.setFlightId(((event.getNewValue())));
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
            ResultSet rs = stmt.executeQuery("select * from ticket");

            while(rs.next()){
                int Tid = rs.getInt("id");
                int Tplrice = rs.getInt("price");
                int TflightId = rs.getInt("flight_id");

                //System.out.println(departure);
                AnswerT.add(new TicketAdAsk(Tid+"",Tplrice+"",TflightId+""));
            }
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
        Set<TicketAdAsk> set = new HashSet<>(AnswerT);
        AnswerT.clear();
        AnswerT.addAll(set);
        //System.out.println("opopop");
        ObservableList<TicketAdAsk> list = FXCollections.observableArrayList(AnswerT);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,price,flightId);
        TableShow.setVisible(true);
    }
    public void showTableBrone(){
        AnswerB.clear();

        TableColumn<BroneAsk, String> passengerId//
                = new TableColumn<BroneAsk, String>("passengerId");
        TableColumn<BroneAsk, String> ticketId//
                = new TableColumn<BroneAsk, String>("ticketId");

        passengerId.setCellValueFactory(new PropertyValueFactory<>("passengerId"));

        ticketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager

                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
            System.out.println("Open DB Successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from brone");

            while(rs.next()){
                int BPid = rs.getInt("passenger_id");
                int BTid = rs.getInt("ticket_id");
                //System.out.println(departure);
                AnswerB.add(new BroneAsk(BPid+"",BTid+""));
            }
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

        Set<BroneAsk> set = new HashSet<>(AnswerB);
        AnswerB.clear();
        AnswerB.addAll(set);
        //System.out.println("opopop");
        ObservableList<BroneAsk> list = FXCollections.observableArrayList(AnswerB);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(passengerId,ticketId);

    }
    public void showTablePassenger(){
        AnswerPa.clear();

        TableColumn<PassengerAsk, String> id//
                = new TableColumn<PassengerAsk, String>("id");

        TableColumn<PassengerAsk, String> pas_ser//
                = new TableColumn<PassengerAsk, String>("Паспорт серия");

        TableColumn<PassengerAsk, String> number//
                = new TableColumn<PassengerAsk, String>("Номер телефон");
        TableColumn<PassengerAsk, String> firstName//
                = new TableColumn<PassengerAsk, String>("Имя");
        TableColumn<PassengerAsk, String> lastName//
                = new TableColumn<PassengerAsk, String>("Фамилия");
        TableColumn<PassengerAsk, String> middleName//
                = new TableColumn<PassengerAsk, String>("Отчество");
        TableColumn<PassengerAsk, String> pas_num//
                = new TableColumn<PassengerAsk, String>("Паспорт номер");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setId((event.getNewValue()));
            }
        });
        pas_num.setCellValueFactory(new PropertyValueFactory<>("pas_num"));
        pas_num.setCellFactory(TextFieldTableCell.forTableColumn());
        pas_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setPas_num((event.getNewValue()));
            }
        });
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        number.setCellFactory(TextFieldTableCell.forTableColumn());
        number.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setNumber((event.getNewValue()));
            }
        });
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        firstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setFirstName(event.getNewValue());
            }
        });
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        lastName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setLastName(event.getNewValue());
            }
        });
        middleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        middleName.setCellFactory(TextFieldTableCell.forTableColumn());
        middleName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setMiddleName(event.getNewValue());
            }
        });
        pas_ser.setCellValueFactory(new PropertyValueFactory<>("pas_ser"));
        pas_ser.setCellFactory(TextFieldTableCell.forTableColumn());
        pas_ser.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassengerAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassengerAsk,String> event) {
                PassengerAsk ask = event.getRowValue();
                ask.setPas_ser((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from Passenger");

            while(rs.next()){
                int Pid = rs.getInt("id");
                int PPN = rs.getInt("pass_num");
                long Pnumber = rs.getLong("phone_number");
                String PFname = rs.getString("first_name");
                String PLname = rs.getString("last_name");
                String PMname = rs.getString("middle_name");
                int PSN = rs.getInt("pass_ser");
                //System.out.println(departure);
                AnswerPa.add(new PassengerAsk(Pid+"",PFname,PLname,PMname,Pnumber+"",PPN+"",PSN+""));
            }
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

        Set<PassengerAsk> set = new HashSet<>(AnswerPa);
        AnswerPa.clear();
        AnswerPa.addAll(set);
        //System.out.println("opopop");
        ObservableList<PassengerAsk> list = FXCollections.observableArrayList(AnswerPa);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(id,firstName,lastName,middleName,number,pas_ser,pas_num);
        TableShow.setVisible(true);
    }
    public void showTablePass(){
        AnswerPass.clear();
        TableShow.getItems().clear();
        TableColumn<PassAsk, String> login//
                = new TableColumn<PassAsk, String>("login");

        TableColumn<PassAsk, String> users//
                = new TableColumn<PassAsk, String>("users");

        TableColumn<PassAsk, String> password//
                = new TableColumn<PassAsk, String>("password");


        login.setCellValueFactory(new PropertyValueFactory<>("login"));

        users.setCellValueFactory(new PropertyValueFactory<>("users"));
        users.setCellFactory(TextFieldTableCell.forTableColumn());
        users.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassAsk,String> event) {
                PassAsk ask = event.getRowValue();
                ask.setUsers((event.getNewValue()));
            }
        });
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        password.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PassAsk, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PassAsk,String> event) {
                PassAsk ask = event.getRowValue();
                ask.setPassword((event.getNewValue()));
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
            ResultSet rs = stmt.executeQuery("select * from Pass");

            while(rs.next()){
                String Plogin = rs.getString("login");
                String Pusers = rs.getString("users");
                String Ppassword = rs.getString("password");
                //System.out.println(departure);
                AnswerPass.add(new PassAsk(Plogin,Pusers,Ppassword));
            }
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

        Set<PassAsk> set = new HashSet<>(AnswerPass);
        AnswerPass.clear();
        AnswerPass.addAll(set);
        //System.out.println("opopop");
        ObservableList<PassAsk> list = FXCollections.observableArrayList(AnswerPass);
        TableShow.setItems(list);
        //  System.out.println("opopop2");
        TableShow.getColumns().addAll(login,users,password);
        TableShow.setVisible(true);
    }

    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> langs = FXCollections.observableArrayList("Story","Passenger","Brone","Ticket","Flight","Plane","Seat","Helper","Airport","City","Country","Pass");

        Table_Choice_Admin.setItems(langs);

    }

    public void BackToLogin(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        Scene Buy = new Scene(root);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(Buy);
        appStage.show();

        //primaryStage.
    }

    public void DumpFile(ActionEvent actionEvent) throws IOException {

       Runtime.getRuntime().exec("cmd /c c:/DB/bin/dump.bat");
       System.out.println("Сделана резервная копия");
    }

    public void RecoverFile(ActionEvent actionEvent) throws IOException {
        TableShow.getColumns().clear();

        File backup = new File("c:/DB/bin/backup.tar");
        if(backup.exists()){
            Runtime.getRuntime().exec("cmd /c c:/DB/bin/restore.bat");
            System.out.println("Восстановлена резервная копия") ;
        }
        else
            System.out.println("Файл восстановления не был создан");

    }

    public void AddtoTable(ActionEvent actionEvent) throws IOException {
        String choice = Table_Choice_Admin.getValue();
        if(choice == "Country") {
            CountryAdd.setVisible(true);
            BtnAddCountry.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((CountryAddName.getText()!=""))&&((CountryAddCode.getText()!=""))){
                        CountryAsk ask = new CountryAsk(" ",CountryAddName.getText(),CountryAddCode.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into country (name,code) values ('"+ask.getName()+"','"+ ask.getCode()+"')");
                            ResultSet rs = stmt.executeQuery("select id from country where name = '"+ask.getName()+"'");
                            while (rs.next()) ask.setId(Integer.toString(rs.getInt("id")));
                            AnswerCo.add(ask);
                            ObservableList<CountryAsk> list = FXCollections.observableArrayList(AnswerCo);
                            TableShow.setItems(list);
                            CountryAdd.setVisible(false);
                        }catch (Exception e) {
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                    }
                }
            });
        }
        if(choice == "City") {
            CityAdd.setVisible(true);
            BtnAddCity.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((CityAddName.getText()!=""))&&((CityAddCountryCode.getText()!=""))){
                        CityAsk ask = new CityAsk(" ",CityAddName.getText(),CityAddCountryCode.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into city (name,country_id) values ('"+ask.getName()+"',"+ ask.getCityId()+")");
                            ResultSet rs = stmt.executeQuery("select id from city where name = '"+ask.getName()+"'");
                            while (rs.next()) ask.setId(Integer.toString(rs.getInt("id")));
                            AnswerC.add(ask);
                            ObservableList<CityAsk> list = FXCollections.observableArrayList(AnswerC);
                            TableShow.setItems(list);
                            CityAdd.setVisible(false);
                        }catch (Exception e) {
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                    }
                }
            });
        }
        if(choice == "Airport") {
            AiroportAdd.setVisible(true);
            BtnAiroportAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((AiroportNameAdd.getText()!=""))&&((AiroportSiteAdd.getText()!="")&&(AiroportCityCodeAdd.getText()!="")&&(AiroportCodeAdd.getText()!=""))){
                        AiroprtsAsk ask = new AiroprtsAsk(" ",AiroportNameAdd.getText(),AiroportSiteAdd.getText(),AiroportCityCodeAdd.getText(),AiroportCodeAdd.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into airoport (name,site,city_id,code) values ('"+ask.getName()+"','"+ask.getSite()+"',"+ ask.getCityId()+",'"+ask.getCode()+"')");
                            ResultSet rs = stmt.executeQuery("select id from airoport where name = '"+ask.getName()+"'");
                            while (rs.next()) ask.setId(Integer.toString(rs.getInt("id")));
                            AnswerA.add(ask);
                            ObservableList<AiroprtsAsk> list = FXCollections.observableArrayList(AnswerA);
                            TableShow.setItems(list);
                            AiroportAdd.setVisible(false);
                        }catch (Exception e) {
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                    }
                }
            });
        }
        if(choice == "Helper") {
            HelperAdd.setVisible(true);
            BtnHelperAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((HelperFNAdd.getText()!=""))&&((HelperLNAdd.getText()!="")&&(HelperMNAdd.getText()!="")&&(HelperAidAdd.getText()!="")&&(HelperPNAdd.getText()!=""))){
                        HelperAsk ask = new HelperAsk(" ",HelperPNAdd.getText(),HelperFNAdd.getText(),HelperLNAdd.getText(),HelperMNAdd.getText(),HelperAidAdd.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into helper (phone_number,first_name,last_name,middle_name,airoport_id) values ('"
                                    +ask.getPhoneNumber()+"','"+ask.getFirstName()+"','"+ask.getLastName()+"','"+ask.getMiddleName()+"',"+ask.getAirportId()+")");
                            ResultSet rs = stmt.executeQuery("select id from helper where name = '"+ask.getFirstName()+"'");
                            while (rs.next()){
                                ask.setId(Integer.toString(rs.getInt("id")));
                            }
                            AnswerH.add(ask);
                            ObservableList<HelperAsk> list = FXCollections.observableArrayList(AnswerH);
                            TableShow.setItems(list);

                        }catch (Exception e) {
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                        HelperAdd.setVisible(false);
                    }
                }
            });
        }
        if(choice == "Plane") {
            PlaneAdd.setVisible(true);
            BtnPlaneAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((PlaneNameAdd.getText()!=""))&&((PlaneTypeAdd.getText()!=""))){
                        PlaneAsk ask = new PlaneAsk(" ",PlaneNameAdd.getText(),PlaneTypeAdd.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into plane (name,type) values ('"
                                    +ask.getName()+"','"+ask.getType()+"')");
                            ResultSet rs = stmt.executeQuery("select id from plane where name = "+ask.getName()+"");
                            while (rs.next()){
                                ask.setId(Integer.toString(rs.getInt("id")));

                            }
                            AnswerP.add(ask);
                            ObservableList<PlaneAsk> list = FXCollections.observableArrayList(AnswerP);
                            TableShow.setItems(list);

                        }catch (Exception e) {
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                        PlaneAdd.setVisible(false);
                    }
                }
            });
        }
        if(choice == "Seat") {
            SeatAdd.setVisible(true);
            BtnSeatAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if (((SeatClassAdd.getText() != "")) && ((SeatCCountAdd.getText() != "") && (SeatNAdd.getText() != "") && (SeatBusyAdd.getText() != "") && (SeatPidAdd.getText() != ""))) {
                        SeatAsk ask = new SeatAsk(" ", SeatClassAdd.getText(), SeatCCountAdd.getText(), SeatNAdd.getText(), SeatBusyAdd.getText(), SeatPidAdd.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into seat (class,ccount,seat_number,busyiness,plane_id) values ('"
                                    + ask.getKlass() + "'," + ask.getCcount() + ",'" + ask.getSeatNumber() + "'," + ask.getBusyiness() + "," + ask.getPlaneId() + ")");
                            ResultSet rs = stmt.executeQuery("select id from helper where plane_id = " + ask.getPlaneId() + " and seat_number = '" + ask.getSeatNumber() + "';");
                            while (rs.next()) {
                                ask.setId(Integer.toString(rs.getInt("id")));
                            }
                            AnswerS.add(ask);
                            ObservableList<SeatAsk> list = FXCollections.observableArrayList(AnswerS);
                            TableShow.setItems(list);

                        } catch (Exception e) {
                            FUCK.setVisible(true);
                            SeatAdd.setVisible(false);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                    }
                }
            });

        }

        if(choice == "Flight") {
            FlightAdd.setVisible(true);
            BtnFlightAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((FBasePrice.getText()!=""))&&((FDT.getText()!="")&&(FAT.getText()!="")&&(FDAId.getText()!="")&&(FAAId.getText()!="")&&(FPI.getText() !=""))){
                        FlightAsk ask = new FlightAsk(" ",FBasePrice.getText(),FDT.getText(),FAT.getText(),FDAId.getText(),FAAId.getText(),FPI.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into flight (base_price,departure_time,arrival_time,airoport_departure_id,airoport_arrival_id,plane_id) values ('"
                                    +ask.getClass()+"',"+ask.getDepartureTime()+",'"+ask.getArrivalTime()+"',"+ask.getAirportDepartureId()+","+ask.getAirportArrivalId()+","+ask.getPlaneId()+")");
                            ResultSet rs = stmt.executeQuery("select max(id) from flight");
                            while (rs.next()){
                                ask.setId(Integer.toString(rs.getInt("id")));
                            }
                            AnswerF.add(ask);
                            ObservableList<FlightAsk> list = FXCollections.observableArrayList(AnswerF);
                            TableShow.setItems(list);
                            FlightAdd.setVisible(false);
                        }catch (Exception e) {
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                    }
                }
            });
        }
        



      //  if(choice == "Ticket") ;
    //    if(choice == "Brone") ;
        if(choice == "Passenger"){
            PassenAdd.setVisible(true);
            BtnPassenAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((PassenFNAdd.getText()!=""))&&((PassenLNAdd.getText()!="")&&(PassenMNAdd.getText()!="")&&(PassenPNAdd.getText()!="")&&(PassenPSAdd.getText()!="")&&(PassenPhNAdd.getText()!=""))){
                        PassengerAsk ask = new PassengerAsk(" ",PassenFNAdd.getText(),PassenLNAdd.getText(),PassenMNAdd.getText(),PassenPhNAdd.getText(),PassenPNAdd.getText(),PassenPSAdd.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into passenger (first_name,last_name,middle_name,phone_number,pass_num,pass_ser) values ('"
                                    +ask.getFirstName()+"','"+ask.getLastName()+"','"+ask.getMiddleName()+"','"+ask.getNumber()+"',"+ask.getPas_num()+","+ask.getPas_ser()+")");
                            ResultSet rs = stmt.executeQuery("select id from passenger where first_name = '"+ask.getFirstName()+"' and phone_number = '"+ask.getNumber()+"';");
                            while (rs.next()){
                                ask.setId(Integer.toString(rs.getInt("id")));
                            }
                            AnswerPa.add(ask);
                            ObservableList<PassengerAsk> list = FXCollections.observableArrayList(AnswerPa);
                            TableShow.setItems(list);

                        }catch (Exception e) {
                            System.err.println(e);
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                        PassenAdd.setVisible(false);
                    }
                }
            });
        }
        if (choice=="Pass") {
            PaswAdd.setVisible(true);
            BtnPaswAdd.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("click!!!");
                    if(((PaswLogAdd.getText()!=""))&&((PaswRolAdd.getText()!="")&&(PaswPaswAdd.getText()!=""))){
                        PassAsk ask = new PassAsk(PaswLogAdd.getText(),PaswRolAdd.getText(),PaswPaswAdd.getText());
                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                            System.out.println("Open DB Successfully");

                            stmt = c.createStatement();

                            stmt.executeUpdate("insert into pass (login,users,password) values ('"
                                    +ask.getLogin()+"','"+ask.getUsers()+"','"+ask.getPassword()+"')");

                            AnswerPass.add(ask);
                            ObservableList<PassAsk> list = FXCollections.observableArrayList(AnswerPass);
                            TableShow.setItems(list);

                        }catch (Exception e) {
                            System.err.println(e);
                            FUCK.setVisible(true);
                        }finally {

                            if (c != null) {
                                try {
                                    c.close();
                                } catch (SQLException e) { /* ignored */}
                            }
                        }
                        PaswAdd.setVisible(false);
                    }
                }
            });
        }
    }

    public void DeletefromTable(ActionEvent actionEvent){
        String choice = Table_Choice_Admin.getValue();
        if(choice == "Country") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                CountryAsk ask = (CountryAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from country where id =  "+ ask.getId());
                    AnswerCo.remove(ask);
                    ObservableList<CountryAsk> list = FXCollections.observableArrayList(AnswerCo);
                    TableShow.setItems(list);
                }catch (Exception e) {
                   FUCK.setVisible(true);
                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        };
        if(choice == "City") { if (TableShow.getSelectionModel().getSelectedItem() != null) {
            TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
            CityAsk ask = (CityAsk) selectionModel.getSelectedItem();

            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");

                stmt = c.createStatement();

                stmt.executeUpdate("delete from city where id =  "+ ask.getId());
                AnswerC.remove(ask);
                ObservableList<CityAsk> list = FXCollections.observableArrayList(AnswerC);
                TableShow.setItems(list);
            }catch (Exception e) {
                FUCK.setVisible(true);
            }finally {

                if (c != null) {
                    try {
                        c.close();
                    } catch (SQLException e) { /* ignored */}
                }
            }
        }};
        if(choice == "Airport") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                AiroprtsAsk ask = (AiroprtsAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from airoport where id =  "+ ask.getId());
                    AnswerA.remove(ask);
                    ObservableList<AiroprtsAsk> list = FXCollections.observableArrayList(AnswerA);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Helper") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                HelperAsk ask = (HelperAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from helper where id =  "+ ask.getId());
                    AnswerH.remove(ask);
                    ObservableList<HelperAsk> list = FXCollections.observableArrayList(AnswerH);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Plane") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                PlaneAsk ask = (PlaneAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from plane where id =  "+ ask.getId());
                    AnswerP.remove(ask);
                    ObservableList<PlaneAsk> list = FXCollections.observableArrayList(AnswerP);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Seat") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                SeatAsk ask = (SeatAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from seat where id =  "+ ask.getId());
                    AnswerS.remove(ask);
                    ObservableList<SeatAsk> list = FXCollections.observableArrayList(AnswerS);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Flight") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                FlightAsk ask = (FlightAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from flight where id =  "+ ask.getId());
                    AnswerF.remove(ask);
                    ObservableList<FlightAsk> list = FXCollections.observableArrayList(AnswerF);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Ticket") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                TicketAdAsk ask = (TicketAdAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from ticket where id =  "+ ask.getId());
                    AnswerA.remove(ask);
                    ObservableList<TicketAdAsk> list = FXCollections.observableArrayList(AnswerT);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Brone") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
               BroneAsk ask = (BroneAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from brone where passenger_id =  "+ ask.getPassengerId()+" and ticket_id = "+ask.getTicketId());
                    AnswerB.remove(ask);
                    ObservableList<BroneAsk> list = FXCollections.observableArrayList(AnswerB);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if(choice == "Passenger") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                PassengerAsk ask = (PassengerAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from passenger where id =  "+ ask.getId());
                    AnswerPa.remove(ask);
                    ObservableList<PassengerAsk> list = FXCollections.observableArrayList(AnswerPa);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

                }finally {

                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) { /* ignored */}
                    }
                }
            }
        }
        if (choice=="Pass") {
            if (TableShow.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = TableShow.getSelectionModel();
                PassAsk ask = (PassAsk) selectionModel.getSelectedItem();

                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                    System.out.println("Open DB Successfully");

                    stmt = c.createStatement();

                    stmt.executeUpdate("delete from airoport where login =  "+ ask.getLogin());
                    AnswerPass.remove(ask);
                    ObservableList<PassAsk> list = FXCollections.observableArrayList(AnswerPass);
                    TableShow.setItems(list);
                }catch (Exception e) {
                    FUCK.setVisible(true);
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());

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

    public void ChangeinTable(ActionEvent actionEvent){
        String choice = Table_Choice_Admin.getValue();
        if(choice == "Country"){
           CountryAsk A =(CountryAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                stmt.executeUpdate("Update country  set name ='"+A.getName()+"', code = '"+A.getCode() +"'  where id = "+A.getId()+";");

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
        if(choice == "City") {
           CityAsk A = (CityAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update city  set name ='"+A.getName()+"', country_id = "+A.getCityId() +"  where id = "+A.getId()+";");

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
        if(choice == "Airport") {
            AiroprtsAsk A = (AiroprtsAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update airoport  set name ='"+A.getName()+"', city_id = "+A.getCityId() +", site = '"+A.getSite()+"',code = '"+A.getCode()+"'  where id = "+A.getId()+";");

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
        if(choice == "Helper") {
            HelperAsk A = (HelperAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update helper set phone_number = '"+A.getPhoneNumber() +"', first_name = '"+
                            A.getFirstName()+"',last_name = '"+A.getLastName()+"',middle_name = '"+A.getMiddleName()+"', airoport_id = "+A.getAirportId()+"  where id = "+A.getId()+";");

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
        if(choice == "Plane") {
            PlaneAsk A =(PlaneAsk) TableShow.getSelectionModel().getSelectedItem();
            System.out.println(A.getType());
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();
                    stmt.executeUpdate("Update plane  set name ='"+A.getName()+"', type = '"+A.getType() +"'  where id = "+A.getId()+";");

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
        if(choice == "Seat") {
           SeatAsk A = (SeatAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update plane  set class ='"+A.getKlass()+"',ccount = "+A.getCcount() +",seat_number = "+
                            A.getSeatNumber()+", busyiness = "+A.getBusyiness()+",plane_id = "+A.getPlaneId()+"  where id = "+A.getId()+";");

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
        if(choice == "Flight") {
            FlightAsk A = (FlightAsk)TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update flight  set  base_price = "+A.getBasePrice() +","+
                            "departure_time = '"+A.getDepartureTime()+"',arrival_time = '"+
                            A.getArrivalTime()+"',airoport_departure_id = "+A.getAirportDepartureId()+","+
                            "airoport_arrival_id = "+A.getAirportArrivalId()+",plane_id = "+A.getPlaneId()+"  where id = "+A.getId()+";");

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
        if(choice == "Ticket") {
            TicketAdAsk A = (TicketAdAsk)TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update ticket  set price = "+A.getPrice()+", base_price = "+A.getFlightId() +" where id = "+A.getId()+";");

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
        if(choice == "Passenger") {
            PassengerAsk A = (PassengerAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update passenger set first_name = '"+A.getFirstName()+"', last_name = '"+A.getLastName() +"',"+
                            "middle_name = '"+A.getMiddleName()+"',phone_number = '"+
                            A.getNumber()+"',pass_num = "+A.getPas_num()+","+
                            "pass_ser = "+A.getPas_ser()+" where id = "+A.getId()+";");

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
        if (choice=="Pass") {
            PassAsk A = (PassAsk) TableShow.getSelectionModel().getSelectedItem();
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
                System.out.println("Open DB Successfully");
                stmt = c.createStatement();

                    stmt.executeUpdate("Update pass set users = '"+A.getUsers()+"', password = '"+A.getPassword() +"'  where id = "+A.getLogin()+";");

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

    public void RecoverAction(ActionEvent actionEvent){
        TableShow.getColumns().clear();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ASales", "postgres", "kjkz");
            System.out.println("Open DB Successfully");
            stmt = c.createStatement();
            //получение последнего
            ResultSet rs = stmt.executeQuery("select max(id) from story");
            //инициализация temp значений
            int storylastmove = 0;
            StoryAsk returnMove = null;

            while(rs.next())
            storylastmove = rs.getInt("max");
            //получение данных о последней операции
            rs = stmt.executeQuery("select * from story where id = "+storylastmove+";");
            while (rs.next()){
                int storyid = rs.getInt("id");
                String storyname = rs.getString("name_table");
                String storytableid = rs.getString("table_id");
                String storyoperation = rs.getString("operation");
                String storyoperationtime = rs.getString("operation_time");
                returnMove = new StoryAsk(storyid,storyname,storytableid,storyoperation,storyoperationtime);

            }
            int lasttableid = 0;
            rs = stmt.executeQuery("select max(temp) from tmp_"+returnMove.getTableName() );
            while(rs.next())
                lasttableid = rs.getInt("max");

            switch (returnMove.getOperation()){
                case "delete":{

                    switch (returnMove.getTableName()){
                        case "plane":{
                            PlaneAsk tempData = null;
                            System.out.println(lasttableid);
                            rs = stmt.executeQuery("select * from tmp_plane where temp = "+lasttableid+";");
                            while (rs.next()){
                                int planeid = rs.getInt("id");
                                String planename = rs.getString("name");
                                String planetype = rs.getString("type");
                                tempData = new PlaneAsk(Integer.toString(planeid),planename,planetype);
                            }
                            stmt.executeUpdate("insert into plane values ("+tempData.getId()+",'"+tempData.getName()+"','"+tempData.getType()+"')");
                            stmt.executeUpdate("delete from tmp_plane where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_plane where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTablePlane();
                            break;
                        }
                        case "passenger":{
                            PassengerAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_plane where temp = "+lasttableid+";");
                            while (rs.next()){
                                int Pid = rs.getInt("id");
                                int PPN = rs.getInt("pass_num");
                                long Pnumber = rs.getLong("phone_number");
                                String PFname = rs.getString("first_name");
                                String PLname = rs.getString("last_name");
                                String PMname = rs.getString("middle_name");
                                int PSN = rs.getInt("pass_ser");
                                //System.out.println(departure);
                                tempData = new PassengerAsk(Pid+"",PFname,PLname,PMname,Pnumber+"",PPN+"",PSN+"");;
                            }
                            stmt.executeUpdate("insert into passeneger values ("+tempData.getId()+",'"+tempData.getFirstName()+"','"+tempData.getLastName()+"','"+tempData.getMiddleName()+"','"+tempData.getNumber()+"','"+tempData.getPas_num()+"','"+tempData.getPas_ser()+"')");
                            stmt.executeUpdate("delete from tmp_passenger where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_passenger where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTablePassenger();
                            break;
                        }
                        case "ticket":{
                            TicketAdAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_ticket where temp = "+lasttableid+";");
                            while (rs.next()){
                                String Tid = rs.getString("id");
                                String Tprice = rs.getString("price");
                                String TflightId = rs.getString("flight_id");
                                //System.out.println(departure);
                                tempData = new TicketAdAsk(Tid,Tprice,TflightId);
                            }
                            stmt.executeUpdate("insert into ticket values ("+tempData.getId()+","+tempData.getPrice()+","+tempData.getFlightId()+")");
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableTicket();
                            break;
                        }
                        case "flight":{
                            FlightAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_flight where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Fid = rs.getInt("id");
                                int FbasePrice = rs.getInt("base_price");
                                String FDT = rs.getTimestamp("departure_time").toString();
                                String FAT = rs.getTimestamp("arrival_time").toString();
                                int FADid = rs.getInt("airoport_departure_id");
                                int FAAid = rs.getInt("airoport_arrival_id");
                                int FPid = rs.getInt("plane_id");
                                //System.out.println(departure);
                                tempData = new FlightAsk(Fid+"",FbasePrice+"",FDT,FAT,FADid+"",FAAid+"",FPid+"");
                            }
                            stmt.executeUpdate("insert into flight values ("+tempData.getId()+","+tempData.getBasePrice()+
                                    ",'"+tempData.getDepartureTime()+"' ,'"+tempData.getArrivalTime()+"' , "+tempData.getAirportDepartureId()+
                                    ","+tempData.getAirportArrivalId()+","+tempData.getPlaneId()+")");
                            stmt.executeUpdate("delete from tmp_flight where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_flight where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableFlight();
                            break;

                        }
                        case "seat":{
                            SeatAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_seat where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Sid = rs.getInt("id");
                                String Sclass = rs.getString("class");
                                Float Scount = rs.getFloat("ccount");
                                String SSnumber = rs.getString("seat_number");
                                Boolean SBusyiness = rs.getBoolean("busyiness");
                                int SPid = rs.getInt("plane_id");
                                //System.out.println(departure);
                                tempData = new SeatAsk(Sid+"",Sclass,Scount+"",SSnumber,SBusyiness+"",SPid+"");
                            }
                            stmt.executeUpdate("insert into seat values ("+tempData.getId()+",'"+tempData.getKlass()+
                                    "',"+tempData.getCcount()+",'"+tempData.getSeatNumber()+"',"+tempData.getBusyiness()+","+tempData.getPlaneId()+")");
                            stmt.executeUpdate("delete from tmp_seat where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_seat where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableSeat();
                            break;
                        }
                        case "helper":{
                            HelperAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_helper where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Hid = rs.getInt("id");
                                long HPnumber = rs.getLong("phone_number");
                                String HFname = rs.getString("first_name");
                                String HLname = rs.getString("last_name");
                                String HMname = rs.getString("middle_name");
                                int HAid = rs.getInt("airoport_id");
                                //System.out.println(departure);
                                tempData = new HelperAsk(Hid+"",HPnumber+"",HFname,HLname,HMname,HAid+"");
                            }
                            stmt.executeUpdate("insert into helper values ("+tempData.getId()+",'"+tempData.getPhoneNumber()+"','"+
                                    tempData.getFirstName()+"','"+tempData.getLastName()+"','"+tempData.getMiddleName()+"',"+tempData.getAirportId()+")");
                            stmt.executeUpdate("delete from tmp_helper where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_helper where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableHelper();
                            break;
                        }
                        case "city":{
                            CityAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_city where temp = "+lasttableid+";");
                            while(rs.next()){
                                int countyid = rs.getInt("id");
                                String countryname = rs.getString("name");
                                int citycode = rs.getInt("country_id");
                                //System.out.println(departure);
                                tempData = new CityAsk(countyid+"",countryname,citycode+"");
                            }
                            stmt.executeUpdate("insert into city values ("+tempData.getId()+",'"+tempData.getName()+"',"+tempData.getCityId()+")");
                            stmt.executeUpdate("delete from tmp_city where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_city where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableCity();
                            break;

                        }
                        case "country":{
                            CountryAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_country where temp = "+lasttableid+";");
                            while(rs.next()){
                                int countyid = rs.getInt("id");
                                String countryname = rs.getString("name");
                                String countrycode = rs.getString("code");
                                //System.out.println(departure);
                                tempData = new CountryAsk(countyid+"",countryname,countrycode);
                            }
                            stmt.executeUpdate("insert into country values ("+tempData.getId()+",'"+tempData.getName()+"','"+tempData.getCode()+"')");
                            stmt.executeUpdate("delete from tmp_country where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_country where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableCountry();
                            break;
                        }
                        case "airoport":{
                            AiroprtsAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_airoport where temp = "+lasttableid+";");
                            while(rs.next()){
                                int airid = rs.getInt("id");
                                String airname = rs.getString("name");
                                int aircodecountry = rs.getInt("city_id");
                                String airsite = rs.getString("site");
                                String aircode = rs.getString("code");
                                //System.out.println(departure);
                                tempData = new AiroprtsAsk(airid+"",airname,airsite,aircodecountry+"",aircode);
                            }
                            stmt.executeUpdate("insert into airoport values ("+tempData.getId()+",'"+tempData.getName()+"','"+
                                    tempData.getSite()+"',"+tempData.getCityId()+",'"+tempData.getCode()+"')");
                            stmt.executeUpdate("delete from tmp_airoport where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_airoport where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTableAiroport();
                            break;
                        }
                    }
                    break;
                }
                case "update":{
                    switch (returnMove.getTableName()){
                        case "plane":{
                            PlaneAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_plane where temp = "+lasttableid+";");
                            while (rs.next()){
                                int planeid = rs.getInt("id");
                                String planename = rs.getString("name");
                                String planetype = rs.getString("type");
                                tempData = new PlaneAsk(Integer.toString(planeid),planename,planetype);
                            }
                            stmt.executeUpdate("update plane set name = '"+tempData.getName()+"',type = '"+tempData.getType()+"' where id = "+tempData.getId()+";");
                            stmt.executeUpdate("delete from tmp_plane where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_plane where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            showTablePlane();
                            break;
                        }
                        case "passenger":{
                            PassengerAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_plane where temp = "+lasttableid+";");
                            while (rs.next()){
                                int Pid = rs.getInt("id");
                                int PPN = rs.getInt("pass_num");
                                long Pnumber = rs.getLong("phone_number");
                                String PFname = rs.getString("first_name");
                                String PLname = rs.getString("last_name");
                                String PMname = rs.getString("middle_name");
                                int PSN = rs.getInt("pass_ser");
                                //System.out.println(departure);
                                tempData = new PassengerAsk(Pid+"",PFname,PLname,PMname,Pnumber+"",PPN+"",PSN+"");;
                            }
                            stmt.executeUpdate("update passeneger set first_name = '"+tempData.getFirstName()+"', last_name = '"+tempData.getLastName()+"', middle_name = '"+tempData.getMiddleName()+"', phone_number = '"+tempData.getNumber()+"', pass_num = '"+tempData.getPas_num()+"', pass_ser = '"+tempData.getPas_ser()+"' where id ="+tempData.getFirstName()+";");
                            stmt.executeUpdate("delete from tmp_passenger where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_passenger where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "ticket":{
                            TicketAdAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_ticket where temp = "+lasttableid+";");
                            while (rs.next()){
                                String Tid = rs.getString("id");
                                String Tprice = rs.getString("price");
                                String TflightId = rs.getString("flight_id");
                                //System.out.println(departure);
                                tempData = new TicketAdAsk(Tid,Tprice,TflightId);
                            }
                            stmt.executeUpdate("update ticket set price = "+tempData.getPrice()+", flight_id = "+tempData.getFlightId()+" where id = "+tempData.getId()+";");
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "flight":{
                            FlightAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_flight where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Fid = rs.getInt("id");
                                int FbasePrice = rs.getInt("base_price");
                                String FDT = rs.getTimestamp("departure_time").toString();
                                String FAT = rs.getTimestamp("arrival_time").toString();
                                int FADid = rs.getInt("airoport_departure_id");
                                int FAAid = rs.getInt("airoport_arrival_id");
                                int FPid = rs.getInt("plane_id");
                                //System.out.println(departure);
                                tempData = new FlightAsk(Fid+"",FbasePrice+"",FDT,FAT,FADid+"",FAAid+"",FPid+"");
                            }
                            stmt.executeUpdate("update flight set price = "+tempData.getBasePrice()+
                                    ", departure_time = '"+tempData.getDepartureTime()+"', arrival_time = '"+tempData.getArrivalTime()+"' airoport_departure_id = "+tempData.getAirportDepartureId()+
                                    ", airoport_arrival_id = "+tempData.getAirportArrivalId()+", plane_id = "+tempData.getPlaneId()+" where id = "+tempData.getId()+";");
                            stmt.executeUpdate("delete from tmp_flight where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_flight where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;

                        }
                        case "seat":{
                            SeatAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_seat where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Sid = rs.getInt("id");
                                String Sclass = rs.getString("class");
                                Float Scount = rs.getFloat("ccount");
                                String SSnumber = rs.getString("seat_number");
                                Boolean SBusyiness = rs.getBoolean("busyiness");
                                int SPid = rs.getInt("plane_id");
                                //System.out.println(departure);
                                tempData = new SeatAsk(Sid+"",Sclass,Scount+"",SSnumber,SBusyiness+"",SPid+"");
                            }
                            stmt.executeUpdate("update seat set class = '"+tempData.getKlass()+
                                    "', ccount = "+tempData.getCcount()+", seat_number = '"+tempData.getSeatNumber()+"',busyiness = "+tempData.getBusyiness()+", plane_id = "+tempData.getPlaneId()+";");
                            stmt.executeUpdate("delete from tmp_seat where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_seat where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "helper":{
                            HelperAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_helper where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Hid = rs.getInt("id");
                                long HPnumber = rs.getLong("phone_number");
                                String HFname = rs.getString("first_name");
                                String HLname = rs.getString("last_name");
                                String HMname = rs.getString("middle_name");
                                int HAid = rs.getInt("airoport_id");
                                //System.out.println(departure);
                                tempData = new HelperAsk(Hid+"",HPnumber+"",HFname,HLname,HMname,HAid+"");
                            }
                            stmt.executeUpdate("update helper set phone_number = '"+tempData.getPhoneNumber()+"', first_name = '"+
                                    tempData.getFirstName()+"', last_name = '"+tempData.getLastName()+"', middle_name = '"+tempData.getMiddleName()+"', airoport_id = "+tempData.getAirportId()+";");
                            stmt.executeUpdate("delete from tmp_helper where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_helper where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "city":{
                            CityAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_city where temp = "+lasttableid+";");
                            while(rs.next()){
                                int countyid = rs.getInt("id");
                                String countryname = rs.getString("name");
                                int citycode = rs.getInt("country_id");
                                //System.out.println(departure);
                                tempData = new CityAsk(countyid+"",countryname,citycode+"");
                            }
                            stmt.executeUpdate("update city set name = '"+tempData.getName()+"', city_id = "+tempData.getCityId()+" where id = "+tempData.getId()+";");
                            stmt.executeUpdate("delete from tmp_city where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_city where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;

                        }
                        case "country":{
                            CountryAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_country where temp = "+lasttableid+";");
                            while(rs.next()){
                                int countyid = rs.getInt("id");
                                String countryname = rs.getString("name");
                                String countrycode = rs.getString("code");
                                //System.out.println(departure);
                                tempData = new CountryAsk(countyid+"",countryname,countrycode);
                            }
                            stmt.executeUpdate("update country set name = '"+tempData.getName()+"',code = '"+tempData.getCode()+"' where id = "+tempData.getId()+";");
                            stmt.executeUpdate("delete from tmp_country where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_country where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "airoport":{
                            AiroprtsAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_airoport where temp = "+lasttableid+";");
                            while(rs.next()){
                                int airid = rs.getInt("id");
                                String airname = rs.getString("name");
                                int aircodecountry = rs.getInt("city_id");
                                String airsite = rs.getString("site");
                                String aircode = rs.getString("code");
                                //System.out.println(departure);
                                tempData = new AiroprtsAsk(airid+"",airname,airsite,aircodecountry+"",aircode);
                            }
                            stmt.executeUpdate("update airoport set name = '"+tempData.getName()+"', site = '"+
                                    tempData.getSite()+"', city_id = "+tempData.getCityId()+", code = '"+tempData.getCode()+"' where id = "+tempData.getId()+";");
                            stmt.executeUpdate("delete from tmp_airoport where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_airoport where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                    }
                    break;
                }
                case "insert":{
                    switch (returnMove.getTableName()){
                        case "plane":{
                            PlaneAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_plane where temp = "+lasttableid+";");
                            while (rs.next()){
                                int planeid = rs.getInt("id");
                                String planename = rs.getString("name");
                                String planetype = rs.getString("type");
                                tempData = new PlaneAsk(Integer.toString(planeid),planename,planetype);
                            }
                            stmt.executeUpdate("delete from plane where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_plane where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_plane where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "passenger":{
                            PassengerAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_plane where temp = "+lasttableid+";");
                            while (rs.next()){
                                int Pid = rs.getInt("id");
                                int PPN = rs.getInt("pass_num");
                                long Pnumber = rs.getLong("phone_number");
                                String PFname = rs.getString("first_name");
                                String PLname = rs.getString("last_name");
                                String PMname = rs.getString("middle_name");
                                int PSN = rs.getInt("pass_ser");
                                //System.out.println(departure);
                                tempData = new PassengerAsk(Pid+"",PFname,PLname,PMname,Pnumber+"",PPN+"",PSN+"");;
                            }
                            stmt.executeUpdate("delete from passenger where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_passenger where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_passenger where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "ticket":{
                            TicketAdAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_ticket where temp = "+lasttableid+";");
                            while (rs.next()){
                                String Tid = rs.getString("id");
                                String Tprice = rs.getString("price");
                                String TflightId = rs.getString("flight_id");
                                //System.out.println(departure);
                                tempData = new TicketAdAsk(Tid,Tprice,TflightId);
                            }
                            stmt.executeUpdate("delete from ticket where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "flight":{
                            FlightAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_flight where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Fid = rs.getInt("id");
                                int FbasePrice = rs.getInt("base_price");
                                String FDT = rs.getTimestamp("departure_time").toString();
                                String FAT = rs.getTimestamp("arrival_time").toString();
                                int FADid = rs.getInt("airoport_departure_id");
                                int FAAid = rs.getInt("airoport_arrival_id");
                                int FPid = rs.getInt("plane_id");
                                //System.out.println(departure);
                                tempData = new FlightAsk(Fid+"",FbasePrice+"",FDT,FAT,FADid+"",FAAid+"",FPid+"");
                            }
                            stmt.executeUpdate("delete from flight where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_flight where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_flight where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;

                        }
                        case "seat":{
                            SeatAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_seat where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Sid = rs.getInt("id");
                                String Sclass = rs.getString("class");
                                Float Scount = rs.getFloat("ccount");
                                String SSnumber = rs.getString("seat_number");
                                Boolean SBusyiness = rs.getBoolean("busyiness");
                                int SPid = rs.getInt("plane_id");
                                //System.out.println(departure);
                                tempData = new SeatAsk(Sid+"",Sclass,Scount+"",SSnumber,SBusyiness+"",SPid+"");
                            }
                            stmt.executeUpdate("delete from seat where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_seat where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_seat where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "helper":{
                            HelperAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_helper where temp = "+lasttableid+";");
                            while(rs.next()){
                                int Hid = rs.getInt("id");
                                long HPnumber = rs.getLong("phone_number");
                                String HFname = rs.getString("first_name");
                                String HLname = rs.getString("last_name");
                                String HMname = rs.getString("middle_name");
                                int HAid = rs.getInt("airoport_id");
                                //System.out.println(departure);
                                tempData = new HelperAsk(Hid+"",HPnumber+"",HFname,HLname,HMname,HAid+"");
                            }
                            stmt.executeUpdate("delete from helper where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_helper where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_helper where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "city":{
                            CityAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_city where temp = "+lasttableid+";");
                            while(rs.next()){
                                int countyid = rs.getInt("id");
                                String countryname = rs.getString("name");
                                int citycode = rs.getInt("country_id");
                                //System.out.println(departure);
                                tempData = new CityAsk(countyid+"",countryname,citycode+"");
                            }
                            stmt.executeUpdate("delete from city where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_city where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_city where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;

                        }
                        case "country":{
                            CountryAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_country where temp = "+lasttableid+";");
                            while(rs.next()){
                                int countyid = rs.getInt("id");
                                String countryname = rs.getString("name");
                                String countrycode = rs.getString("code");
                                //System.out.println(departure);
                                tempData = new CountryAsk(countyid+"",countryname,countrycode);
                            }
                            stmt.executeUpdate("delete from country where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_country where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_country where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                        case "airoport":{
                            AiroprtsAsk tempData = null;
                            rs = stmt.executeQuery("select * from tmp_airoport where temp = "+lasttableid+";");
                            while(rs.next()){
                                int airid = rs.getInt("id");
                                String airname = rs.getString("name");
                                int aircodecountry = rs.getInt("city_id");
                                String airsite = rs.getString("site");
                                String aircode = rs.getString("code");
                                //System.out.println(departure);
                                tempData = new AiroprtsAsk(airid+"",airname,airsite,aircodecountry+"",aircode);
                            }
                            stmt.executeUpdate("delete from airoport where id = "+tempData.getId());
                            stmt.executeUpdate("delete from tmp_airoport where temp = "+lasttableid);
                            stmt.executeUpdate("delete from tmp_ticket where temp = "+(lasttableid+1));
                            stmt.executeUpdate("delete from story where id = "+storylastmove);
                            stmt.executeUpdate("delete from story where id = "+(storylastmove+1));
                            break;
                        }
                    }
                    break;
                }
            }
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
