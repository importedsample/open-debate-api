package com.astris.social.opendebate.usr;

import com.astris.social.opendebate.usr.exceptions.InvalidEmailAddressError;
import com.astris.social.opendebate.usr.exceptions.UserException;
import com.astris.social.opendebate.usr.exceptions.UserExistsError;
import com.astris.social.opendebate.usr.models.User;
import com.astris.social.opendebate.usr.repositories.UserRepository;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * User Administration Service.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Service
public class UserService {

  /**
   * User Administration Repository Bean.
   */
  private final UserRepository repo;

  /**
   * Public Constructor.
   *
   * @param repository User JPA Repository
   */
  public UserService(final UserRepository repository) {
    this.repo = repository;
  }

  /**
   * Create a new User.
   *
   * @param user User Object
   * @return "Created" if successful.
   */
  public String createUser(@Valid final User user) {

    try {

      validateEmail(user.getEmail());
      verifyUniqueUser(user.getEmail());

      user.setUid(UUID.randomUUID());

      repo.save(user);
    } catch (InvalidEmailAddressError | UserExistsError ex) {
      throw new UserException(ex);
    }

    return "Created";
  }

  /**
   * Fetch User Info using Email Address.
   *
   * @param email User Email Address
   * @return User Public Information
   */
  public User fetchUser(@Email final String email) {
    try {
      validateEmail(email);
      return repo.findByEmail(email);
    } catch (InvalidEmailAddressError ex) {
      throw new UserException(ex);
    }
  }

  /**
   * Update User Information.
   *
   * @param user Inbound User Details Object.
   * @return "OK" if successful
   */
  public String updateUser(@Validated final User user) {
    // TODO : Verify that the user who is making the update is the user
    //   being updated.
    repo.save(user);
    return "OK";
  }

  /**
   * Validate correct email address format.
   *
   * @param email Inbound Email Address
   */
  private void validateEmail(@Email final String email)
      throws InvalidEmailAddressError {
    val regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    // Verify that the email format if valid.
    if (!email.matches(regex)) {
      throw new InvalidEmailAddressError(email);
    }
  }

  /**
   * Verify that the user email address is unique.
   *
   * @param email User email address.
   */
  private void verifyUniqueUser(@Email final String email)
      throws UserExistsError {
    if (repo.findByEmail(email) != null) {
      throw new UserExistsError(email);
    }
  }

}
