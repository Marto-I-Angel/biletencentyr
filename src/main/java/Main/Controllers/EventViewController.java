package Main.Controllers;

import entities.Distributor;
import entities.Event;
import entities.Reservation;
import entities.Seats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DistributorService;
import services.EventService;
import services.SessionService;

import java.net.URL;

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
    public TableView<Distributor> DistributorTable;
    public TableColumn<Distributor,Integer> ColDistributorId;
    public TableColumn<Distributor,String> ColDistributorName;
    public TableColumn<Distributor,Float> ColDistributorFee;

    public ComboBox<String> DistributorCB;
    public ComboBox<String> statusCB;

    ObservableList<entities.Seats> seats = FXCollections.observableArrayList();
    ObservableList<Distributor> assignedDistributors =  FXCollections.observableArrayList();
    List<Distributor> allDistributors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        // Get the name from the user??
        //ColDistributorName.setCellValueFactory(new PropertyValueFactory<>(""));
        ColDistributorFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

    }

    public void SaveEvent() {
        //if everything is filled
        if(!eventName_id.getText().isEmpty() && !EventType.getText().isEmpty() ) {
            //save the data
            Event event = new Event();
            event.setEventType(EventType.getText());
            event.setEventName(eventName_id.getText());
            event.setHost(SessionService.getHost());
            System.out.println(SessionService.getHost());
            event.setListDist(assignedDistributors);
            List<Reservation> reservations = new ArrayList<>();
            event.setSeats(reservations);
            event.setStatus(statusCB.getValue());

            EventService service = new EventService();
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
        DistributorTable.setItems(assignedDistributors);
    }
    public void removeSelected() {
        seats.remove(SeatTypesTable.getSelectionModel().getSelectedIndex());
        refreshTable();
    }

    public void AddDistributor() {
        for(Distributor x : allDistributors)
        {
            if(x.getUser().getUsername().equals(DistributorCB.getValue())) {
                assignedDistributors.add(x);
                System.out.println(x);
            }
        }
        refreshTable();
    }

    public void RemoveDistributor() {
        assignedDistributors.remove(DistributorTable.getSelectionModel().getSelectedIndex());
    }
}
