package core;

import core.TASkChusmeando;
import java.io.File;
import java.rmi.registry.*;
import remote.RemoteObserver;
import remote.RemoteTaskController;

public class Main {

    public static void main(String[] args) {
        System.out.println("Viva Perón");

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5050);

            RemoteTaskController remoteController = (RemoteTaskController) registry.lookup("TASkOcupado");
            var fileName = System.getProperty("user.home") + File.separator + ".TASkChusmeando.txt";
            RemoteObserver remoteObserver = new TASkChusmeando(fileName);

            remoteController.addObserver(remoteObserver);

            System.out.println("Success: RemoteObserver listening");
        } catch (Exception e) {
            if (e.hashCode() == 248609774) {
                System.out.println("Nothing to listen: TASkOcupadoApp not running");
                return;
            }
            System.out.println("?error registering RemoteObserver: " + e.getMessage());
        }
    }

}
