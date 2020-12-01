package services;

import dao.SeatsDao;
import entities.Seats;

import java.util.List;

public class SeatsService {
    private static SeatsDao seatsDao;

    public SeatsService() {
        seatsDao = new SeatsDao();
    }

    public void persist(Seats entity) {
        seatsDao.openCurrentSessionwithTransaction();
        seatsDao.persist(entity);
        seatsDao.closeCurrentSessionwithTransaction();
    }

    public void update(Seats entity) {
        seatsDao.openCurrentSessionwithTransaction();
        seatsDao.update(entity);
        seatsDao.closeCurrentSessionwithTransaction();
    }

    public Seats findById(int id) {
        seatsDao.openCurrentSession();
        Seats seats = seatsDao.findById(id);
        seatsDao.closeCurrentSession();
        return seats;
    }

    public void delete(int id) {
        seatsDao.openCurrentSessionwithTransaction();
        Seats seats = seatsDao.findById(id);
        seatsDao.delete(seats);
        seatsDao.closeCurrentSessionwithTransaction();
    }

    public List<Seats> findAll() {
        seatsDao.openCurrentSession();
        List<Seats> seatss = seatsDao.findAll();
        seatsDao.closeCurrentSession();
        return seatss;
    }

    public void deleteAll() {
        seatsDao.openCurrentSessionwithTransaction();
        seatsDao.deleteAll();
        seatsDao.closeCurrentSessionwithTransaction();
    }

    public SeatsDao seatsDao() {
        return seatsDao;
    }

    public int getTotalTickets(int eventId) {
        seatsDao.openCurrentSessionwithTransaction();
        int n = seatsDao.getTotalTickets(eventId);
        seatsDao.closeCurrentSessionwithTransaction();
        return n;
    }

    public int getSoldTickets(int eventId) {
        seatsDao.openCurrentSessionwithTransaction();
        int n = seatsDao.getSoldTickets(eventId);
        seatsDao.closeCurrentSessionwithTransaction();
        return n;
    }
    public void reserveSeats(Seats seats, int numberOfReservations) throws Exception {
        seatsDao.openCurrentSessionwithTransaction();
        seatsDao.reserveSeats(seats,numberOfReservations);
        seatsDao.closeCurrentSessionwithTransaction();
    }

    public List<Seats> getAllByEvent(int eventId) {
        seatsDao.openCurrentSession();
        List<Seats> seats = seatsDao.findSeatsByEventId(eventId);
        seatsDao.closeCurrentSession();
        return seats;
    }
}
