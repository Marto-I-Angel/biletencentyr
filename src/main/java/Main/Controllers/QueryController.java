package Main.Controllers;

import entities.Distributor;
import entities.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.DistributorView;
import services.DistributionService;
import services.EventService;
import services.SessionService;
import services.TicketService;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QueryController implements Initializable {
    public ListView<String> LV_Distributor;
    public Tab distributorTab;
    public Tab eventTab;
    public ListView<String> LV_Event;
    public DatePicker datepicker_from;
    public DatePicker datepicker_to;
    public TabPane tabPane;
    public Button bt_showEvent;
    public TextField TF_eventName;
    public TextField TF_distributorName;
    public Button bt_showDistributor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(SessionService.getDistributor() != null){
            tabPane.getSelectionModel().select(eventTab);
            eventTab.setDisable(false);
            distributorTab.setDisable(true);
        }
        else {
            eventTab.setDisable(true);
            distributorTab.setDisable(false);
        }
    }

    public void showDistributor() throws ParseException {
        EventService eventService = new EventService();

        List<Event> listEvents = new ArrayList<>();
        List<Event> allEvents = eventService.findAll();
        for (Event eventIter: allEvents) {
            //Check if event start date is between the dates given
            if(SessionService.inPeriod(SessionService.toDateFromDatePicker(datepicker_from.getEditor().getText()),SessionService.toDateFromDatePicker(datepicker_to.getEditor().getText()),SessionService.toDateFromDatePicker(eventIter.getBeginDate()))){
                if(eventIter.getHost().equals(SessionService.getHost())) {
                    listEvents.add(eventIter);
                }
            }
        }
        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (Event event: listEvents) {
            List<Distributor> list = eventService.loadDistributors(event.getEventId());
            DistributionService distributionService = new DistributionService();
            TicketService ticketService = new TicketService();

            if (!TF_distributorName.getText().equals("")) {
                for (Distributor iter : list) {
                    if (iter.getUser().getUsername().contains(TF_distributorName.getText())) {
                        DistributorView distributorView = new DistributorView(iter.getDistributorId(), iter.getUser().getUsername(), distributionService.findDistribution(iter.getDistributorId(), event.getEventId()).getFee(), iter.getRating());
                        observableList.add("Event Name:" + event.getName() + "\n" + distributorView.toString() + " Sold tickets: " + ticketService.getSoldTicketsForDistribution(event.getEventId(), iter.getDistributorId()));
                    }
                }
            }else {
                for (Distributor iter : list) {
                    DistributorView distributorView = new DistributorView(iter.getDistributorId(), iter.getUser().getUsername(), distributionService.findDistribution(iter.getDistributorId(), event.getEventId()).getFee(), iter.getRating());
                    observableList.add("Event Name:" + event.getName() + "\n" + distributorView.toString() + " Sold tickets: " + ticketService.getSoldTicketsForDistribution(event.getEventId(), iter.getDistributorId()));
                }
            }

        }
        LV_Distributor.setItems(observableList);
    }

    public void showEvent() throws ParseException {
        if(TF_eventName.getText() != null){
            EventService eventService = new EventService();
            List<Event> list = eventService.findAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (Event iter: list) {
                //Check if date is between selected dates
                if(SessionService.inPeriod(SessionService.toDateFromDatePicker(datepicker_from.getEditor().getText()),SessionService.toDateFromDatePicker(datepicker_to.getEditor().getText()),SessionService.toDateFromDatePicker(iter.getBeginDate()))){
                    if(iter.getName().contains(TF_eventName.getText()))
                            observableList.add(iter.toString());
                }
            }
            LV_Event.setItems(observableList);
        }
    }
}
