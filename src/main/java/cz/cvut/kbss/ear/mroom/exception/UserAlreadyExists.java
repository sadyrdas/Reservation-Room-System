package cz.cvut.kbss.ear.mroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExists extends BaseException {
    public UserAlreadyExists(String message) {
        super(message);
    }
    public static UserAlreadyExists create(String resourceName, Object identifier) {
        return new UserAlreadyExists(resourceName + " identified by " + identifier + " not found.");
    }
}
