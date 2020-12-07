package notifications;

import Main.Controllers.DistributorController;
import Main.Controllers.HostController;
import entities.Event;
import entities.Seats;
import javafx.application.Platform;
import services.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckForUpcomingEvent implements Runnable {
    DistributorController distributorController;
    HostController hostController;
    public CheckForUpcomingEvent(DistributorController controller)
    {
        distributorController = controller;
    }
    public CheckForUpcomingEvent(HostController controller)
    {
        hostController = controller;
    }

    @Override
    public void run() {
        checkForUpcomingEvent();
    }

    private void checkForUpcomingEvent()
    {
        EventService eventService = new EventService();
        try {
            Date now = SessionService.toDateFromLocalDate(LocalDate.now().toString());
            SeatsService seatsService= new SeatsService();
            List<Event> events = new ArrayList<>();
            List<Event> upcommingEvents = new ArrayList<>();
            if(SessionService.getHost()!=null)
            {
                events = eventService.findAll();
                for(Event x : events)
                {
                    if(x.getHost().equals(SessionService.getHost()))
                    {
                        TimeUnit timeUnit = TimeUnit.DAYS;
                        long daysDiff =  timeUnit.convert(SessionService.toDateFromDatePicker(x.getBeginDate()).getTime() - now.getTime(),TimeUnit.MILLISECONDS);
                        if(daysDiff < 7)
                        {
                                if (seatsService.getTotalTickets(x.getEventId()) != seatsService.getSoldTickets(x.getEventId())) {
                                    upcommingEvents.add(x);
                                }
                        }
                    }
                }
                if(!upcommingEvents.isEmpty())
                {
                    Platform.runLater(() -> // back to the FX thread
                    {
                        hostController.upcomingEventNotif(upcommingEvents);
                    });
                }
            }
            else {
                events = eventService.findByDistributorId(SessionService.getDistributor().getDistributorId(),true);
                for(Event x : events)
                {
                        TimeUnit timeUnit = TimeUnit.DAYS;
                        long daysDiff =  timeUnit.convert(SessionService.toDateFromDatePicker(x.getBeginDate()).getTime() - now.getTime(),TimeUnit.MILLISECONDS);
                        if(daysDiff < 7)
                        {
                            if (seatsService.getTotalTickets(x.getEventId()) != seatsService.getSoldTickets(x.getEventId())) {
                                upcommingEvents.add(x);
                            }
                        }
                }
                if(!upcommingEvents.isEmpty())
                {
                    Platform.runLater(() -> // back to the FX thread
                    {
                        distributorController.upcomingEventNotif(upcommingEvents);
                    });
                }


            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
