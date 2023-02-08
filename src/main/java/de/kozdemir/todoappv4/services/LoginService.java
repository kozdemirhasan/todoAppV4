package de.kozdemir.todoappv4.services;

import de.kozdemir.todoappv4.model.User;
import de.kozdemir.todoappv4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;


@Service
public class LoginService implements Serializable {

    private User user;


    public User getUser() {
        return user;
    }

    public void doLogIn(User user) {
        this.user = user;
    }

    public void doLogOut() {
        this.user = null;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }
}
