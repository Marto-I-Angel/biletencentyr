package Main.Controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import models.DistributorView;
import entities.Distributor;
import entities.Event;
import entities.Seats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.*;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventViewController implements Initializable {
    public TextField eventName_id;
    public DatePicker StartDate_Id;
    public DatePicker EndDate_Id;
    public TextField EventType;
    //Seat Table
    public TableView<Seats> SeatTypesTable;
    public TableColumn<Seats,String> ColTypeSeats;
    public TableColumn<Seats,String> ColPrice;
    public TableColumn<Seats,String> ColNumSeats;
    public TextField type_txt;
    public TextField count_txt;
    public TextField price_txt;

    public TextField fee;
    //Distributor Table
    public TableView<DistributorView> DistributorTable;

    public TableColumn<DistributorView,Integer> ColDistributorId;
    public TableColumn<DistributorView,String> ColDistributorName;
    public TableColumn<DistributorView,Float> ColDistributorFee;
    public TableColumn<DistributorView,Integer> ColDistributorRating;

    public ComboBox<String> DistributorCB;
    public ComboBox<String> statusCB;
    public TextField ticketLimitTxt;
    public CheckBox isLimitedPerPerson;
    public Label saveErrorLabel;


    ObservableList<entities.Seats> seats = FXCollections.observableArrayList();
    ObservableList<DistributorView> tempDistributors =  FXCollections.observableArrayList();
    List<Distributor> allDistributors;

    private Event event= new Event();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        EventService eventService = new EventService();
        SeatsService seatsService = new SeatsService();
        if(!eventName_id.getText().isEmpty() && !EventType.getText().isEmpty() ) {
            //save the data

                event.setEventType(EventType.getText());
                event.setEventName(eventName_id.getText());
                event.setHost(SessionService.getHost());
                List<Seats> reservations = new ArrayList<>(seats);
                event.setStatus(statusCB.getValue());
                event.setBeginDate(StartDate_Id.getEditor().getText());
                event.setEndDate(EndDate_Id.getEditor().getText());

                if(isLimitedPerPerson.isSelected())
                event.setTicketLimit(Integer.parseInt(ticketLimitTxt.getText()));
                else event.setTicketLimit(-1);

            eventService.setDistribution(tempDistributors, event);
            if(eventService.findById(event.getEventId())==null) {
                eventService.persist(event);
            }
            for (Seats x : reservations) {
                x.setEvent(eventService.findById(this.event.getEventId()));
                if (seatsService.findById(x.getSeatsId()) == null) {
                    seatsService.persist(x);
                }
            }
                //close the window
            Stage stage = (Stage) eventName_id.getScene().getWindow();
            stage.close();
        }
        else {
            saveErrorLabel.setText("All fields must be filled before saving!");
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
                DistributorView data = new DistributorView(x.getDistributorId(),x.getUser().getUsername(),Float.parseFloat(fee.getText()),x.getRating());
                tempDistributors.add(data);
                System.out.println(data);
            }
        }
        refreshTable();
    }

    public void RemoveDistributor() {
        tempDistributors.remove(DistributorTable.getSelectionModel().getSelectedIndex());

    }

    public void setEvent(Event event) {
        this.event = event;
        eventName_id.setText(this.event.getName());
        StartDate_Id.getEditor().setText(this.event.getBeginDate());
        EndDate_Id.getEditor().setText(this.event.getEndDate());
        EventType.setText(this.event.getEventType());
        statusCB.getSelectionModel().select(this.event.getStatus());
        EventService eventService = new EventService();
        this.seats = eventService.loadSeats(this.event.getEventId());
        this.tempDistributors = eventService.loadDistributorRow(this.event.getEventId());
        if(this.event.getTicketLimit() > 0) {
            isLimitedPerPerson.setSelected(true);
            ticketLimitTxt.setText(this.event.getTicketLimit()+"");
        }else isLimitedPerPerson.setSelected(false);
        refreshTable();
        refreshToggle();
    }

    public void toggleLimit(ActionEvent actionEvent) {
        refreshToggle();
    }

    private void refreshToggle() {
        ticketLimitTxt.setDisable(!isLimitedPerPerson.isSelected());
    }
}
