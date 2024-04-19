package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenicationInterface extends Remote {
    boolean authenicate(String userName,String password,boolean isAdmin) throws RemoteException;
    void register(String userName, String password, boolean isAdmin) throws RemoteException;
}
