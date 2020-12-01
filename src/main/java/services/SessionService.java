package services;

import entities.Distributor;
import entities.Host;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class SessionService {
    private static Host host;
    private static Distributor distributor;

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
        return new SimpleDateFormat("dd.MM.yyyy").parse(sDate);
    }
    public static Date toDateFromLocalDate(String sDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    }
    public static boolean inPeriod(Date begin, Date end, Date period){
        //if user gives start date after end date
        if(begin.after(end)){
            return period.after(end) && period.before(begin);
        }
        else if(begin.equals(end)) return begin.equals(period);
        else return period.after(begin) && period.before(end);
    }
}
