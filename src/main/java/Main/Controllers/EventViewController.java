package Main.Controllers;

import Main.Controllers.VisualClasses.SeatTypeRow;
import entities.Distributor;
import entities.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public ComboBox<String> DistributorCB;

    ObservableList<SeatTypeRow> seats = FXCollections.observableArrayList();
    List<Distributor> distributors =  new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Add type events to the combo box

/*        EventTypeService service = new EventTypeService();
        List<EventType> eventTypes = service.findAll();
        List<String> eventTypesNames = new List<String>();
        for(EventType x : eventTypes)
        {
            eventTypesNames.add(x.getName());
        }
        EventTypeCB.setItems(eventTypesNames);
*/

//        Set up seats type table
        ColTypeSeats.setCellValueFactory(new PropertyValueFactory<>("seatType"));
        ColNumSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    public void SaveEvent() {
        //if everything is filled
        if(true) {
            //save the data
            Event event = new Event();
//            event.setEventType(new EventType(EventTypeCB.getValue()));
//            event.setEventName(eventName_id);
//            event.setHost(host);
            System.out.println(SessionService.getHost());
//            event.setListDist(distributors);
            event.setRating(-1);    //not set yet. Will be set by the distributors.
//            event.setStatus();???????????????
        }
        //else show error

    }
    public void createNewSeatsType() {
        SeatTypeRow row1 = new SeatTypeRow(type_txt.getText(),count_txt.getText(),price_txt.getText());
        seats.add(row1);
        refreshTable();
    }

    private void refreshTable() {
        SeatTypesTable.setItems(seats);
    }
    public void removeSelected() {
        seats.remove(SeatTypesTable.getSelectionModel().getSelectedIndex());
        refreshTable();
    }

    public void AddDistributor() {
//        DistributorService service = new DistributorService();
//        Distributor tmp = service.findByUsername(DistributorCB.getValue());
//        distributors.add(tmp);
    }

    public void RemoveDistributor() {
        distributors.remove(DistributorTable.getSelectionModel().getSelectedIndex());
    }
}
