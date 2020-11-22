package Main.Controllers;

import Main.Controllers.TableRowClasses.DistributorRow;
import entities.Distributor;
import entities.Event;
import entities.Seats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DistributorService;
import services.EventService;
import services.SeatsService;
import services.SessionService;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventViewController implements Initializable {
    public TextField eventName_id;
    public DatePicker StartDate_Id;
    public DatePicker EndDate_Id;
    public CheckBox isLimitedPerPerson;
    public TextField EventType;
    //Seat Table
    public TableView<Seats> SeatTypesTable;
    public TableColumn<Seats,String> ColTypeSeats;
    public TableColumn<Seats,String> ColPrice;
    public TableColumn<Seats,String> ColNumSeats;
    public TextField type_txt;
    public TextField count_txt;
    public TextField price_txt;

    //Distributor Table
    public TableView<DistributorRow> DistributorTable;

    public TableColumn<DistributorRow,Integer> ColDistributorId;
    public TableColumn<DistributorRow,String> ColDistributorName;
    public TableColumn<DistributorRow,Float> ColDistributorFee;
    public TableColumn<DistributorRow,Integer> ColDistributorRating;
    public TextField fee;

    public ComboBox<String> DistributorCB;
    public ComboBox<String> statusCB;



    ObservableList<entities.Seats> seats = FXCollections.observableArrayList();
    ObservableList<DistributorRow> tempDistributors =  FXCollections.observableArrayList();
    List<Distributor> allDistributors;

    private Event event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        event = new Event();

//        Set up the status comboBox
        statusCB.getItems().add("Active");
        statusCB.getItems().add("Canceled");
        statusCB.getItems().add("Completed");
        statusCB.getItems().add("Pending");


//        Add distributors to the combo box
        DistributorService distributorService = new DistributorService();
        allDistributors = distributorService.findAll();
        for (Distributor x : allDistributors)
        {
            DistributorCB.getItems().add(x.getUser().getUsername());
        }

//        Set up seats type table
        ColTypeSeats.setCellValueFactory(new PropertyValueFactory<>("seatsType"));
        ColNumSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        Set up distributor table
        ColDistributorId.setCellValueFactory(new PropertyValueFactory<>("distributorId"));
        ColDistributorName.setCellValueFactory(new PropertyValueFactory<>("username"));
        ColDistributorFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        ColDistributorRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

    }

    public void SaveEvent() {
        //if everything is filled
        if(!eventName_id.getText().isEmpty() && !EventType.getText().isEmpty() ) {
            //save the data

            event.setEventType(EventType.getText());
            event.setEventName(eventName_id.getText());
            event.setHost(SessionService.getHost());
            System.out.println(SessionService.getHost());

            ObservableList<Distributor> assignedDistributors =  FXCollections.observableArrayList();
            List<Seats> reservations = new ArrayList<>(seats);

            SeatsService seatsService = new SeatsService();
            for(Seats x: reservations)
            seatsService.persist(x);

            event.setSeats(reservations);
            event.setStatus(statusCB.getValue());

            event.setBeginDate(StartDate_Id.getEditor().getText());
            event.setEndDate(EndDate_Id.getEditor().getText());

            EventService service = new EventService();
            service.setDistribution(tempDistributors,event);
            service.persist(event);
        }
    }
    public void createNewSeatsType() {
        entities.Seats row1 = new entities.Seats(type_txt.getText(),count_txt.getText(),price_txt.getText());
        seats.add(row1);
        refreshTable();
    }

    private void refreshTable() {
        SeatTypesTable.setItems(seats);
        DistributorTable.setItems(tempDistributors);
    }
    public void removeSelected() {
        seats.remove(SeatTypesTable.getSelectionModel().getSelectedIndex());
        refreshTable();
    }

    public void AddDistributor() {
        for(Distributor x : allDistributors)
        {
            if(x.getUser().getUsername().equals(DistributorCB.getValue())) {
                DistributorRow data = new DistributorRow(x.getDistributorId(),x.getUser().getUsername(),Float.parseFloat(fee.getText()),x.getRating());
                tempDistributors.add(data);
                System.out.println(data);
            }
        }
        refreshTable();
    }

    public void RemoveDistributor() {
        tempDistributors.remove(DistributorTable.getSelectionModel().getSelectedIndex());

    }
}
