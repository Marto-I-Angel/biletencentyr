package Main.Controllers;

import Main.Controllers.TableRowClasses.DistributorRow;
import dao.EventDao;
import entities.Distribution;
import entities.Distributor;
import entities.Event;
import entities.Seats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import services.*;
import util.HibernateUtil;

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

    public TextField fee;
    //Distributor Table
    public TableView<DistributorRow> DistributorTable;

    public TableColumn<DistributorRow,Integer> ColDistributorId;
    public TableColumn<DistributorRow,String> ColDistributorName;
    public TableColumn<DistributorRow,Float> ColDistributorFee;
    public TableColumn<DistributorRow,Integer> ColDistributorRating;


    public ComboBox<String> DistributorCB;
    public ComboBox<String> statusCB;



    ObservableList<entities.Seats> seats = FXCollections.observableArrayList();
    ObservableList<DistributorRow> tempDistributors =  FXCollections.observableArrayList();
    List<Distributor> allDistributors;

    public Event event= new Event();

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
        EventService service = new EventService();
        if(!eventName_id.getText().isEmpty() && !EventType.getText().isEmpty() ) {
            //save the data

                event.setEventType(EventType.getText());
                event.setEventName(eventName_id.getText());
                event.setHost(SessionService.getHost());
                System.out.println(SessionService.getHost());

                ObservableList<Distributor> assignedDistributors = FXCollections.observableArrayList();
                List<Seats> reservations = new ArrayList<>(seats);

                SeatsService seatsService = new SeatsService();
                for (Seats x : reservations)
                    seatsService.persist(x);

                event.setSeats(reservations);
                event.setStatus(statusCB.getValue());

                event.setBeginDate(StartDate_Id.getEditor().getText());
                event.setEndDate(EndDate_Id.getEditor().getText());

            service.setDistribution(tempDistributors, event);

            if(service.findById(event.getEventId())==null) {
                service.persist(event);
            }
            else{
                service.update(event);
            }
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
        //isLimitedPerPerson.setSelected(this.event.get);
        refreshTable();
    }
}
