package cz.cvut.kbss.ear.mroom.rest;

import cz.cvut.kbss.ear.mroom.dao.UserRoleDao;
import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.model.UserRole;
import cz.cvut.kbss.ear.mroom.rest.util.RestUtil;
import cz.cvut.kbss.ear.mroom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserRoleDao userRoleDao;

    private final UserService userService;

    @Autowired
    public UserController(UserRoleDao userRoleDao, UserService userService) {
        this.userRoleDao = userRoleDao;
        this.userService = userService;
    }

    @PreAuthorize("(#user.role_id != 3 )")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.createUser(user.getEmail(), user.getFirst_name(), user.getLast_name(),
                user.getPassword(), userRoleDao.getRoleIdByRoleName("student"), userRoleDao.getallRoles(1));

        LOG.info("User {} successfully registered.", user);
        final HttpHeaders headers = RestUtil.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


}
