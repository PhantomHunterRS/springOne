package server.auth;

import server.User;

public interface AuthService {

    boolean authUser(User user);
}
