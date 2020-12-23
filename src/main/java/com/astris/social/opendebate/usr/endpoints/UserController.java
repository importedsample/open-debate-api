package com.astris.social.opendebate.usr.endpoints;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.astris.social.opendebate.usr.UserService;
import com.astris.social.opendebate.usr.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Administration Endpoints.
 *
 * @author Christopher Bridges
 *     created: 12/20/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/user")
public class UserController {

  /**
   * User Administration Service Injected Bean.
   */
  private final UserService service;

  /**
   * Public Constructor.
   *
   * @param userService User Administration Service Bean.
   */
  public UserController(final UserService userService) {
    this.service = userService;
  }

  /**
   * Create a New User Account.
   *
   * @param user User Object
   * @return "Created" if successful
   */
  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createUser(@RequestBody final User user) {
    return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);
  }

  /**
   * Update User Information.
   *
   * @param user Inbound User Details Object.
   * @return "OK" if successful
   */
  @PatchMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> patchUser(@RequestBody final User user) {
    return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
  }

  // TODO : Fetch User Details

  @GetMapping(
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE
  )
  public ResponseEntity<User> fetchUser(@RequestBody final String email) {
    return new ResponseEntity<>(service.fetchUser(email), HttpStatus.OK);
  }

  // TODO : Delete User
}
