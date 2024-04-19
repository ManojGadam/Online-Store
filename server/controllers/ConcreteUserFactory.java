package server.controllers;

import server.models.Adminstrator;
import server.models.Customer;
import server.models.Store;
import server.models.User;


public class ConcreteUserFactory implements UserFactory {
    @Override
    public void createUser(String username, String password, boolean isAdmin, Store store) {
        if (isAdmin) {
            store.addAdmin(new Adminstrator(username, password, true));
        } else {
            store.addCustomer(new Customer(username, password, false));
        }
    }
}
