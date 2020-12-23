package com.astris.social.opendebate.usr.endpoints;

import com.astris.social.opendebate.usr.exceptions.UserException;
import com.astris.social.opendebate.utils.ErrorResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * User Administration Error Response Handler.
 *
 * @author Christopher Bridges
 *     created: 12/20/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@RestControllerAdvice
public class UserErrorHandler {

  /**
   * Handle Error During New User Creation.
   *
   * @param ex New User Exception
   * @return New User Exception Error Response
   */
  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorResponse> handleNewUserException(
      final UserException ex
  ) {
    val response = new ErrorResponse();
    response.setMessage(ex.getException().getMessage());
    response.setException(ex.getException().getClass());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
