package notifications;

import Main.Controllers.HostController;
import javafx.application.Platform;
import services.SessionService;
import services.TicketService;

public class CheckForSoldTickets implements Runnable {
    private int soldTicketsNum;
    private HostController hostController;
    private TicketService ticketService = new TicketService();
    public CheckForSoldTickets(int soldTicketsNum, HostController hostController)
    {
        this.soldTicketsNum = soldTicketsNum;
        this.hostController = hostController;
    }

    @Override
    public void run() {
        checkForSoldTickets();
    }

    private void checkForSoldTickets()
    {
        int soldTickets = ticketService.findAllByEvent(SessionService.getHost().getHostId()).size();
        if(soldTicketsNum != soldTickets)
        {
            Platform.runLater(() -> // back to the FX thread
                    {
                        hostController.updateNotification((soldTickets - soldTicketsNum) + " TICKETS HAVE BEEN SOLD!!");
                    });
            //TODO: SHOW THE NUMBER OF TICKETS THAT HAVE BEEN SOLD SINCE LAST CHECK!
        }
        else {
            Platform.runLater(() -> // back to the FX thread
            {
                hostController.updateNotification("No new tickets have been sold.");
            });
        }
    }

    public void setSoldTicketsNum(int soldTicketsNum) {
        this.soldTicketsNum = soldTicketsNum;
    }
}
