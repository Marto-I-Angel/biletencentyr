package services;

import dao.SoldTicketsDao;
import entities.Event;
import entities.Seats;
import entities.SoldTickets;
import models.TicketView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TicketService {
        private static SoldTicketsDao ticketDao;

        public TicketService() {
            ticketDao = new SoldTicketsDao();
        }

        public void persist(SoldTickets entity) {
            ticketDao.openCurrentSessionwithTransaction();
            ticketDao.persist(entity);
            ticketDao.closeCurrentSessionwithTransaction();
        }

        public void update(SoldTickets entity) {
            ticketDao.openCurrentSessionwithTransaction();
            ticketDao.update(entity);
            ticketDao.closeCurrentSessionwithTransaction();
        }

        public void saveOrUpdate(SoldTickets entity) {
            ticketDao.openCurrentSessionwithTransaction();
            ticketDao.saveOrUpdate(entity);
            ticketDao.closeCurrentSessionwithTransaction();
        }

        public SoldTickets findById(int id) {
            ticketDao.openCurrentSession();
            SoldTickets ticket = ticketDao.findById(id);
            ticketDao.closeCurrentSession();
            return ticket;
        }

        public void delete(int id) {
            ticketDao.openCurrentSessionwithTransaction();
            SoldTickets ticket = ticketDao.findById(id);
            ticketDao.delete(ticket);
            ticketDao.closeCurrentSessionwithTransaction();
        }

        public List<SoldTickets> findAll() {
            ticketDao.openCurrentSession();
            List<SoldTickets> tickets = ticketDao.findAll();
            ticketDao.closeCurrentSession();
            return tickets;
        }

        public List<TicketView> getTicketViews(int distributorId) {
            EventService eventService = new EventService();

            List<SoldTickets> all = findAll();
            List<TicketView> ticketView = new ArrayList<>();
            for(SoldTickets x : all){
                if(x.getDistributor().getDistributorId() == distributorId)
                {
                    Seats seats = x.getSeats();
                    Event event = eventService.findById(seats.getEvent().getEventId());

                    ticketView.add(new TicketView(event.getName(),seats.getSeatsType(),x.getNumberOfTickets(),x.getDateBought(),seats.getPriceAsFloat(),x.getTypePayment(),x.getFirstName(),x.getMiddleName(),x.getLastName()));
                }
            }
            return ticketView;
        }

        public SoldTicketsDao ticketDao() {
            return ticketDao;
        }

    public List<SoldTickets> findAllByEvent(int hostId) {
        EventService eventService = new EventService();

        List<SoldTickets> all = findAll();
        List<SoldTickets> list = new ArrayList<>();
        for(SoldTickets x : all)
        {
            Seats seats = x.getSeats();
            Event event = eventService.findById(seats.getEvent().getEventId());
            if(event.getHost().getHostId() == hostId)
            {
                list.add(x);
            }
        }
        return list;

    }
}
