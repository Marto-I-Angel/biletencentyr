package services;

import entities.Distributor;
import entities.Host;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

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
