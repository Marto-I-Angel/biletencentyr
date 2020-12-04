package Main.Controllers;

import entities.Distributor;
import entities.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import services.DistributorService;
import services.EventService;
import services.SessionService;

import java.net.URL;
import java.text.ParseException;
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

    public void showDistributor(ActionEvent actionEvent) {
        if(TF_distributorName.getText() != null){
            DistributorService distributorService = new DistributorService();
            List<Distributor> list = distributorService.findAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (Distributor iter: list) {
                observableList.add(iter.getUser().getUsername());
            }
            LV_Distributor.setItems(observableList);
        }
    }

    public void showEvent(ActionEvent actionEvent) throws ParseException {
        if(TF_eventName.getText() != null){
            EventService eventService = new EventService();
            List<Event> list = eventService.findAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (Event iter: list) {
                if(SessionService.inPeriod(SessionService.toDateFromDatePicker(datepicker_from.getEditor().getText()),SessionService.toDateFromDatePicker(datepicker_to.getEditor().getText()),SessionService.toDateFromDatePicker(iter.getBeginDate()))){
                    observableList.add(iter.getName());
                }
            }
            LV_Event.setItems(observableList);
        }
    }
}
