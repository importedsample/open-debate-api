package com.astris.social.opendebate.usr.repositories;

import com.astris.social.opendebate.usr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Administration JPA Repository Interface.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
public interface UserRepository extends JpaRepository<User, String> {

  /**
   * Find User Details using the provided User Email Address.
   *
   * @param email User Email Address
   * @return User Entity
   */
  User findByEmail(final String email);

}
