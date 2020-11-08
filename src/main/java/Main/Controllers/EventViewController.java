package Main.Controllers;

import Main.Controllers.VisualClasses.SeatTypeRow;
import entities.Distributor;
import entities.Event;
import entities.EventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DistributorService;
import services.EventTypeService;
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
    public ComboBox<String> EventTypeCB;

    //Seat Table
    public TableView<SeatTypeRow> SeatTypesTable;
    public TableColumn<SeatTypeRow,String> ColTypeSeats;
    public TableColumn<SeatTypeRow,String> ColPrice;
    public TableColumn<SeatTypeRow,String> ColNumSeats;
    public TextField type_txt;
    public TextField count_txt;
    public TextField price_txt;

    //Distributor Table
    public TableView<Distributor> DistributorTable;
    public TableColumn<Distributor,Integer> ColDistributorId;
    public TableColumn<Distributor,String> ColDistributorName;
    public TableColumn<Distributor,Float> ColDistributorFee;

    public ComboBox<String> DistributorCB;

    ObservableList<SeatTypeRow> seats = FXCollections.observableArrayList();
    ObservableList<Distributor> assignedDistributors =  FXCollections.observableArrayList();
    ObservableList<String> eventTypesNames = FXCollections.observableArrayList();
    List<Distributor> allDistributors;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Add type events to the combo box
        EventTypeService service = new EventTypeService();
        List<EventType> eventTypes = service.findAll();
        for(EventType x : eventTypes)
        {
            eventTypesNames.add(x.getName());
        }
        EventTypeCB.setItems(eventTypesNames);

//        Add distributors to the combo box
        DistributorService distributorService = new DistributorService();
        allDistributors = distributorService.findAll();
        for (Distributor x : allDistributors)
        {
            DistributorCB.getItems().add(x.getUser().getUsername());
        }


//        Set up seats type table
        ColTypeSeats.setCellValueFactory(new PropertyValueFactory<>("seatType"));
        ColNumSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        Set up distributor table
        ColDistributorId.setCellValueFactory(new PropertyValueFactory<>("distributorId"));
        // Get the name from the user??
        //ColDistributorName.setCellValueFactory(new PropertyValueFactory<>("distributorId"));
        ColDistributorFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

    }

    public void SaveEvent() {
        //if everything is filled
        if(!eventName_id.getText().isEmpty() && !EventTypeCB.getValue().isEmpty() ) {
            //save the data
            Event event = new Event();
            event.setEventType(new EventType(EventTypeCB.getValue()));
//            event.setEventName(eventName_id);             trqbva da dobavim Name field v Event
            event.setHost(SessionService.getHost());
            System.out.println(SessionService.getHost());
            event.setListDist(assignedDistributors);
//            event.setRating(-1);   ?
//            event.setStatus();???????????????

            //Ako ne sushtestvuva takuv tip event, da go dobavi kum spisuka
            if(eventTypesNames.contains(EventTypeCB.getValue()))
            {
//                EventTypeService service = new EventTypeService();
//                 service.add(new EventType(EventTypeCB.getValue()));
                eventTypesNames.add(EventTypeCB.getValue());
            }
        }
    }
    public void createNewSeatsType() {
        SeatTypeRow row1 = new SeatTypeRow(type_txt.getText(),count_txt.getText(),price_txt.getText());
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
