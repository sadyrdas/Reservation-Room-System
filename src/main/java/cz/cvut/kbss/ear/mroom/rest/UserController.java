package cz.cvut.kbss.ear.mroom.rest;

import cz.cvut.kbss.ear.mroom.dao.UserDao;
import cz.cvut.kbss.ear.mroom.dao.UserRoleDao;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.rest.util.RestUtil;
import cz.cvut.kbss.ear.mroom.security.model.AuthenticationToken;
import cz.cvut.kbss.ear.mroom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserRoleDao userRoleDao;

    private final UserService userService;
    private final UserDao userDao;

    @Autowired
    public UserController(UserRoleDao userRoleDao, UserService userService, UserDao userDao) {
        this.userRoleDao = userRoleDao;
        this.userService = userService;
        this.userDao = userDao;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword(),  userRoleDao.getAllRoles(1));

        LOG.info("User with email {} successfully registered.", user.getEmail());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_CLIENT')")
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getCurrent(Principal principal) {
        final AuthenticationToken auth = (AuthenticationToken) principal;
        return auth.getPrincipal().getUser();
    }

    @PostMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerAdmin(@RequestBody User user) {
        userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword(),  userRoleDao.getAllRoles(3));

        LOG.info("Admin wit email {} successfully registered.", user.getEmail());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/deleteUserByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@RequestBody User user){
        userService.deleteUserByEmail(user.getEmail());
        LOG.info("User with email {} was removed", user.getEmail());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateUserByEmail/{oldEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable String oldEmail){
        userService.updateUserEmailByEmail(oldEmail, user.getEmail());
        LOG.info("Old userEmail was updated to new {}", user.getEmail());
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getUserByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody User user) {
        LOG.info("Found user with email {}", user.getEmail());
        return userService.findUserByEmail(user.getEmail());
    }



    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_CLIENT')")
    @GetMapping(value = "/putUpMoney/{id}/{money}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> putUpMoney(@PathVariable Integer id, @PathVariable Double money) {
        userService.putUpMoney(userDao.find(id), money);
        LOG.info("User with id {} put up {} money", id, money);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
