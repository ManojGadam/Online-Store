package server.controllers;

import common.AuthenicationInterface;
import server.models.Adminstrator;
import server.models.Customer;
import server.models.Store;
import server.models.User;

import java.rmi.RemoteException;
import java.util.Objects;

public class AuthenicateContoller implements AuthenicationInterface {
    private final Store store;
    private Adminstrator currentAdministrator;
    private Customer currentUser;
    private boolean currentAdmin;
    private final UserFactory userFactory;

    public AuthenicateContoller(Store store){
        this.userFactory = new ConcreteUserFactory();
        this.store = store;
    }

    @Override
    public boolean authenicate(String userName, String password,boolean isAdmin) throws RemoteException {
      this.currentAdministrator =  this.store.getAdministrators().stream().filter(user -> Objects.equals(user.getName(), userName) && Objects.equals(user.getPassword(),password)).findFirst().orElse(null);
      this.currentUser = this.store.getCustomers().stream().filter(user -> Objects.equals(user.getName(), userName) && Objects.equals(user.getPassword(), password)).findFirst().orElse(null);
      if(this.currentAdministrator!=null){
          this.currentAdmin = true;
          this.currentAdministrator.setStore(this.store);
      }
      else if(this.currentUser != null) {
          this.currentAdmin = false;
      }
      return this.currentAdministrator!=null || this.currentUser!=null;
    }

    @Override
    public void register(String userName, String password,boolean isAdmin) throws RemoteException {
        this.userFactory.createUser(userName,password,isAdmin,this.store);
    }

    public Adminstrator getCurrentAdmin(){
        return this.currentAdministrator;
    }
    public Customer getCurrentCustomer() {
        return this.currentUser;
    }
//    @Override
//    public void logOut() throws RemoteException {
//        if(this.currentAdmin)this.currentAdministrator = null;
//        else {
//            this.currentUser.resetShoppingCart();
//            this.currentUser = null;
//        }
//    }

}


