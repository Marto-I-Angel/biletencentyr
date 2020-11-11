package services;

import entities.Distributor;
import entities.Host;

public final class SessionService {
    private static Host host;
    private static Distributor distributor;

    public static void setHost(Host input) {
        host = input;
    }
    public void setDistributor(Distributor input) {
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


}
