package com.astris.social.opendebate.utils;

import lombok.Data;

/**
 * Error Response Object.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Data
public class ErrorResponse {

  private String message;

  private Class<?> exception;
}
