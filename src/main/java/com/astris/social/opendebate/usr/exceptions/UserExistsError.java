package com.astris.social.opendebate.usr.exceptions;

import lombok.Getter;

/**
 * User Already Exists Exception.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
public class UserExistsError extends Exception {

  /**
   * Custom Exception Message.
   */
  @Getter
  public final String message;

  /**
   * Public Constructor.
   */
  public UserExistsError(final String message) {
    super(message);
    this.message = message;
  }

}
