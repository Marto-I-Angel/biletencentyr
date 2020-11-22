import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import services.DistributionService;
import util.HibernateUtil;

import java.util.List;

public class test1 {
    private Session session;

    private Transaction transaction;

    @Before
    public void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void testRole(){
        System.out.println("test1 started");
        UserRole userRole = createRole("testRole");
        session.save(userRole);

        User user = createUser("testuser");
        session.save(user);

        Host host = createHost(user);
        session.save(host);

    }

    @Test
    public void testEvent() {
        DistributionService distributionService = new DistributionService();
        List<Distribution> list = distributionService.findAll();

        User user = createUser("testuser2");
        session.save(user);

        Host host = createHost(user);
        session.save(host);

        Event event = createEvent(list,host);
        session.save(event);

    }

    @After
    public void transactionCommit(){
        transaction.commit();
    }

    private UserRole createRole(String role){
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        return userRole;
    }

    private User createUser(String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("12345");
        return user;
    }

    private Host createHost(User user){
        Host host = new Host();
        host.setUser(user);
        return host;
    }

    private Event createEvent(List<Distribution> dist,Host host){
        Event event1 = new Event();
        event1.setName("Championship");
        event1.setBeginDate("Yesterday");
        event1.setEndDate("Tomorrow");
        event1.setEventType("Football");
        event1.setStatus("Cancelled");
        event1.setHost(host);
        event1.setListDist(dist);
        return event1;
    }


}
