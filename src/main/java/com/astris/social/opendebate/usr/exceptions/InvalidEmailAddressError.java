package com.astris.social.opendebate.usr.exceptions;

import lombok.Getter;

/**
 * Exception Thrown when Email fails manual validation.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
public class InvalidEmailAddressError extends Exception {

  /**
   * Custom Exception Message.
   */
  @Getter
  private final String message;

  /**
   * Public Constructor.
   *
   * @param message Custom Exception Message
   */
  public InvalidEmailAddressError(final String message) {
    super(message);
    this.message = message;
  }
}
