package server;
import common.StoreInterface;
import server.controllers.FrontController;

import java.rmi.Naming;
public class RemoteStore {
    public RemoteStore(){

    }
    public static void main(String[] args) {
        try{
            StoreInterface store = new FrontController();
            Naming.rebind("store",store);
            System.out.println("Store registered");
        }
        catch (Exception ex){
            System.err.println("Error registering remote account object: " + ex.getMessage());
        }
    }
}
