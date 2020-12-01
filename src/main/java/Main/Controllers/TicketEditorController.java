package Main.Controllers;

import entities.Event;
import entities.Seats;
import entities.SoldTickets;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import services.SeatsService;
import services.SessionService;
import services.TicketService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class TicketEditorController implements Initializable {

    public Text lbl_total_sum;
    public Text lbl_total_sum2;
    public TextField txt_number_of_tickets;
    public ListView<Seats> SeatsList;
    public Label lbl_event_name;
    public Label lbl_event_type;
    public Label lbl_date_start;
    public Label lbl_date_end;
    public Label lbl_ticket_limit;
    public TabPane tabPane;
    public Tab payment_tab;
    public Label lbl_current_date;
    public ComboBox<String> CB_payment_method;
    public TextField txt_last_name;
    public TextField txt_middle_name;
    public TextField txt_first_name;

    private Event event;
    private boolean limit;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setEvent(Event event) {
        this.event = event;
        this.lbl_event_name.setText(event.getName());
        this.lbl_event_type.setText(event.getEventType());
        this.lbl_date_start.setText(event.getBeginDate());
        this.lbl_date_end.setText(event.getEndDate());

        SeatsService seatsService = new SeatsService();
        List<Seats> list = seatsService.getAllByEvent(this.event.getEventId());
        this.SeatsList.getItems().setAll(list);

        limit = this.event.getTicketLimit() != -1;
        if(limit) lbl_total_sum.setText(this.event.getTicketLimit()+"");
    }
    public void proceed() {
        //if everything is filled and the number of tickets is under the limit, proceed to payment
        if(Integer.parseInt(txt_number_of_tickets.getText()) <= this.event.getTicketLimit() || !limit ) {
            payment_tab.setDisable(false);
            payment_tab.getContent().setDisable(false);
            lbl_current_date.setText(LocalDate.now().toString());
            tabPane.getSelectionModel().select(payment_tab);
        }
        //else
    }
    public void set() {
        Seats seats = SeatsList.getSelectionModel().getSelectedItem();
        int freeSeats = seats.getNumberOfSeats() - seats.getNumberOfReservedSeats();
        if(Integer.parseInt(txt_number_of_tickets.getText()) <= this.event.getTicketLimit() || !limit) {
            if (Integer.parseInt(txt_number_of_tickets.getText()) <= freeSeats) {
                float totalPrice = SeatsList.getSelectionModel().getSelectedItem().getPriceAsFloat() * Integer.parseInt(txt_number_of_tickets.getText());
                lbl_total_sum2.setText(totalPrice + "");
                lbl_total_sum.setText(totalPrice + "");
            }
        }
    }

    public void createTicket() throws Exception {
        SoldTickets ticket = new SoldTickets(SeatsList.getSelectionModel().getSelectedItem(),SessionService.getDistributor(),lbl_current_date.getText(),Integer.parseInt(txt_number_of_tickets.getText()));
        TicketService ticketService = new TicketService();
        SeatsService seatsService = new SeatsService();
        seatsService.reserveSeats(seatsService.findById(ticket.getSeats().getSeatsId()),ticket.getNumberOfTickets());
        ticketService.persist(ticket);
        //we have to save the three names
    }
}