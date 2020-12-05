package services;

import entities.Distributor;
import entities.Host;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class SessionService {
    private static Host host;
    private static Distributor distributor;
    private static int notifNumber;

    public static void setHost(Host input) {
        host = input;
    }
    public static void setDistributor(Distributor input) {
        distributor = input;
    }
    public static Host getHost()
    {
        return host;
    }
    public static Distributor getDistributor()
    {
        return distributor;
    }

    public static void logout(){
        host = null;
        distributor = null;
    }
    public static Date toDateFromDatePicker(String sDate) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
    }
    public static Date toDateFromLocalDate(String sDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    }
    public static boolean inPeriod(Date from, Date to, Date period){
        //if user gives "from" date after "to" date
        if(from.after(to)){
            return period.after(to) && period.before(from);
        }
        else if(from.equals(to)) return from.equals(period);
        else return period.after(from) && period.before(to);
    }

    public static int getNotifNumber() {
        return notifNumber;
    }

    public static void setNotifNumber(int notifNumber) {
        SessionService.notifNumber = notifNumber;
    }
}
