package services;

import models.DistributorView;
import dao.EventDao;
import entities.Distribution;
import entities.Distributor;
import entities.Event;
import entities.Seats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.EventView;

import java.util.ArrayList;
import java.util.List;

public class EventService {
    private static EventDao eventDao;

    public EventService() {
        eventDao = new EventDao();
    }

    public void persist(Event entity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.persist(entity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public void update(Event entity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.update(entity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public Event findById(int id) {
        eventDao.openCurrentSession();
        Event event = eventDao.findById(id);
        eventDao.closeCurrentSession();
        return event;
    }

    public void delete(int id) {
        eventDao.openCurrentSessionwithTransaction();
        Event event = eventDao.findById(id);
        eventDao.delete(event);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public List<Event> findAll() {
        eventDao.openCurrentSession();
        List<Event> events = eventDao.findAll();
        eventDao.closeCurrentSession();
        return events;
    }

    public void setDistribution(ObservableList<DistributorView> input, Event event)
    {
        List<Distribution> listDist = new ArrayList<>();
        DistributorService distributorService = new DistributorService();
        DistributionService distributionService = new DistributionService();
        for(DistributorView x : input)
        {
            Distributor distributor =  distributorService.loadDistributor(x.getDistributorId());
            Distribution distribution = new Distribution(event,distributor,x.getFee());
            if(!listDist.contains(distribution)) {
                distributionService.saveOrUpdate(distribution);
                listDist.add(distribution);
            }
        }
        event.setListDist(listDist);
    }
    public void deleteAll() {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.deleteAll();
        eventDao.closeCurrentSessionwithTransaction();
    }

    public EventDao eventDao() {
        return eventDao;
    }

    public Event loadEvent(int eventId) {
        eventDao.openCurrentSession();
        Event event = eventDao.openCurrentSession().get(Event.class,eventId);
        eventDao.closeCurrentSession();
        return event;
    }
    public ObservableList<Seats> loadSeats(int eventId) {
        ObservableList<Seats> seats = FXCollections.observableArrayList();
        eventDao.openCurrentSession();
        seats.addAll(eventDao.openCurrentSession().get(Event.class, eventId).getSeats());
        eventDao.closeCurrentSession();
        return seats;
    }
    public ObservableList<DistributorView> loadDistributorRow(int eventId) {

        ObservableList<DistributorView> tempDistributors = FXCollections.observableArrayList();
        eventDao.openCurrentSession();
        List<Distribution> list = eventDao.openCurrentSession().get(Event.class,eventId).getListDist();
        for(Distribution x : list) {
            Distributor dist = x.getDistributor();
            tempDistributors.add(new DistributorView(dist.getDistributorId(),dist.getUser().getUsername(),x.getFee(),dist.getRating()));
        }
        eventDao.closeCurrentSession();
        return tempDistributors;
    }
    public List<Distributor> loadDistributors(int eventId) {

        List<Distributor> tempDistributors = new ArrayList<>();
        eventDao.openCurrentSession();
        List<Distribution> list = eventDao.openCurrentSession().get(Event.class,eventId).getListDist();
        for(Distribution x : list) {
            tempDistributors.add(x.getDistributor());
        }
        eventDao.closeCurrentSession();
        return tempDistributors;
    }

    public ObservableList<EventView> toEventView(List<Event> all,int distributorId) {
        ObservableList<EventView> list = FXCollections.observableArrayList();
        DistributionService distributionService = new DistributionService();
        SeatsService seatsService = new SeatsService();
        for(Event x : all)
        {
            Distribution distribution = distributionService.findDistribution(distributorId,x.getEventId());
            list.add(new EventView(x.getEventId(),x.getName(),x.getEventType(),x.getBeginDate(),
                    x.getEndDate(),x.getStatus(),distribution.getFee(),seatsService.getTotalTickets(x.getEventId()),seatsService.getSoldTickets(x.getEventId()),x.getLocation()));
        }
        return list;
    }

    public List<Event> findByDistributorId(int id, boolean type) {
        List<Event> events = new ArrayList<>();
        DistributionService distributionService = new DistributionService();
        List<Distribution> distributions=  distributionService.findAll();
        for(Distribution x : distributions)
        {
            if(x.getDistributor().getDistributorId() == id && x.isAccepted() == type)
            {
                events.add(x.getEvent());
            }
        }
        return events;
    }
}