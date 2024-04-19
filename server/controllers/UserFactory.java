package server.controllers;

import server.models.Store;
import server.models.User;

public interface UserFactory {
    void createUser(String username, String password, boolean isAdmin, Store store);
}
