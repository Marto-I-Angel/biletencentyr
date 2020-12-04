package notifications;

import Main.Controllers.DistributorController;
import javafx.application.Platform;
import javafx.scene.Parent;
import services.EventService;
import services.SessionService;

public class CheckForNewEvent implements Runnable {
    DistributorController distributorController;
    public CheckForNewEvent(DistributorController controller)
    {
        distributorController = controller;
    }

    @Override
    public void run() {
        checkForNewEvent();
    }

    private void checkForNewEvent()
    {
        EventService eventService = new EventService();
        int num = eventService.findByDistributorId(SessionService.getDistributor().getDistributorId(),false).size();
            Platform.runLater(() -> // back to the FX thread
                    {
                        distributorController.notificationUpdate(num);
                    });
        //TODO: connect to the UI with some kind of notification!
    }

}
