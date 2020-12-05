package notifications;

import Main.Controllers.HostController;
import javafx.application.Platform;
import models.TicketView;
import services.SessionService;
import services.TicketService;

import java.util.List;

public class CheckForSoldTickets implements Runnable {
    private HostController hostController;
    private TicketService ticketService = new TicketService();
    public CheckForSoldTickets(int soldTicketsNum, HostController hostController)
    {
        SessionService.setNotifNumber(soldTicketsNum);
        this.hostController = hostController;
    }

    @Override
    public void run() {
        checkForSoldTickets();
    }

    private void checkForSoldTickets()
    {
        int soldTicketsNum = SessionService.getNotifNumber();
        List<TicketView> soldTickets = ticketService.findAllByEvent(SessionService.getHost().getHostId());
        if(soldTicketsNum != soldTickets.size())
        {
            Platform.runLater(() -> // back to the FX thread
                    {
                        hostController.updateNotification((soldTickets.size() - soldTicketsNum), soldTickets );
                    });
            //TODO: SHOW THE NUMBER OF TICKETS THAT HAVE BEEN SOLD SINCE LAST CHECK!
        }
        else {
            Platform.runLater(() -> // back to the FX thread
            {
                hostController.updateNotification(0, null);
            });
        }
    }
}
