package com.astris.social.opendebate.usr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.astris.social.opendebate.usr.exceptions.InvalidEmailAddressError;
import com.astris.social.opendebate.usr.exceptions.UserException;
import com.astris.social.opendebate.usr.exceptions.UserExistsError;
import com.astris.social.opendebate.usr.models.User;
import com.astris.social.opendebate.usr.repositories.UserRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * User Administration Service Unit Tests.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
class UserServiceTest {

  /**
   * Class Under Test.
   */
  @InjectMocks
  private UserService service;

  /**
   * Mocked Dependency of Class Under Test
   */
  @Mock
  private UserRepository repo;

  /**
   * Initialize Units Under Test.
   */
  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Create a new User Happy Path.
   */
  @DisplayName("Create New User")
  @Test
  void createUserTest() {
    User user = new User();
    user.setEmail("pizza@TheHut.com");
    user.setUsername("pizza");

    assertEquals("Created", service.createUser(user));
    assertNotNull(user.getUid());
    verify(repo).save(user);
  }

  /**
   * Fail on Invalid Email
   */
  @DisplayName("Invalid Email Address")
  @Test
  void failEmailTest() {
    User user = new User();
    user.setEmail("hello");

    try {
      service.createUser(user);
      fail();
    } catch (UserException ex) {
      assertEquals("hello", ex.getException().getMessage());
      assertEquals(InvalidEmailAddressError.class, ex.getException().getClass());
      verify(repo, never()).save(user);
    }
  }

  /**
   * Fail on Duplicate User Creation Attempt.
   */
  @DisplayName("Duplicate User")
  @Test
  void duplicateUserTest() {
    User user = new User();
    user.setEmail("hello@world.com");

    when(repo.findByEmail(user.getEmail())).thenReturn(user);

    try {
      service.createUser(user);
      fail();
    } catch (UserException ex) {
      assertEquals("hello@world.com", ex.getException().getMessage());
      assertEquals(UserExistsError.class, ex.getException().getClass());
      verify(repo, never()).save(user);
    }
  }

  /**
   * Fetch User Happy Path.
   */
  @DisplayName("Fetch User by Email")
  @Test
  void fetchUserEmail() {
    User user = new User();
    user.setUsername("username");
    user.setUid(UUID.randomUUID());
    user.setEmail("hello@world.com");

    when(repo.findByEmail(user.getEmail())).thenReturn(user);

    User result = service.fetchUser(user.getEmail());

    assertEquals( user.getEmail(), result.getEmail());
    assertEquals(user.getUsername(), result.getUsername());
    assertEquals(user.getUid(), result.getUid());
    assertNull(user.getId());

  }

  /**
   * Fetch User fails with invalid email.
   */
  @DisplayName("Fetch User Fail Email")
  @Test
  void fetchUserFail() {
    try {
      service.fetchUser("someEmail");
      fail();
    } catch (UserException ex) {
      assertEquals(
          InvalidEmailAddressError.class,
          ex.getException().getClass()
      );
      assertEquals("someEmail", ex.getException().getMessage());
    }

  }

  /**
   * Verify that the repository is called when updating the user information.
   * TODO : Features will need added to this to verify the user data with
   *   the user making the call.
   */
  @DisplayName("Update User - Success")
  @Test
  void updateUserTest() {
    User user = new User();
    user.setEmail("someuser@someplace.com");
    user.setId(12345L);
    user.setUid(UUID.randomUUID());
    user.setUsername("TheDude");

    service.updateUser(user);

    verify(repo).save(user);
  }

  /**
   * Fail Updating User when email address is invalid.
   */
  @DisplayName("Update User - Fail on Email")
  @Test
  void updateUserFailTest() {
    User user = new User();
    user.setEmail("someuser@someplace");
    user.setId(12345L);
    user.setUid(UUID.randomUUID());
    user.setUsername("TheDude");

    try {
      service.updateUser(user);
      fail();
    } catch (UserException ex) {
      verify(repo, never()).save(user);

      assertEquals(
          "com.astris.social.opendebate.usr.exceptions."
              + "InvalidEmailAddressError: someuser@someplace",
          ex.getMessage()
      );
    }

  }



}
