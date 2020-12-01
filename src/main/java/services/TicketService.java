package services;

import dao.SoldTicketsDao;
import entities.SoldTickets;
import models.TicketView;

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
            ticketDao.openCurrentSession();
            List<TicketView> ticketView = ticketDao.getTicketViews(distributorId);
            ticketDao.closeCurrentSession();
            return ticketView;
        }

        public SoldTicketsDao ticketDao() {
            return ticketDao;
        }

}
