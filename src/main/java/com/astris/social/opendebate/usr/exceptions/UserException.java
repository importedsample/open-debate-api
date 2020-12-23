package com.astris.social.opendebate.usr.exceptions;

import lombok.Getter;

/**
 * Exception Wrapper for User Administration Errors.
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
public class UserException extends RuntimeException {

  /**
   * Nested Exception.
   */
  @Getter
  private final Exception exception;

  /**
   * Public Consructor.
   *
   * @param ex Root Exception
   */
  public UserException(final Exception ex) {
    super(ex);
    this.exception = ex;
  }

}