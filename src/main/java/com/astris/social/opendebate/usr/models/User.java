package com.astris.social.opendebate.usr.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Data Model Representing a User.
 *
 * @author Christopher Bridges
 *     created: 12/20/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(schema = "usr", name = "usr_header")
@Validated
public class User {

  /**
   * Internal System Identification.
   */
  @Id
  private Long id;

  /**
   * User Identification Code.
   */
  @NotNull
  @NotBlank
  private UUID uid;

  /**
   * User Screen name.
   *     Value to the left of the @ in email address if not provided.
   */
  @NotNull
  @NotBlank
  private String username;

  /**
   * User Email Address.
   */
  @Email
  @NotNull
  @NotBlank
  private String email;

}
