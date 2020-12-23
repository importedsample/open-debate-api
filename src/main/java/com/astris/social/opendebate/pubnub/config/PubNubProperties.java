package com.astris.social.opendebate.pubnub.config;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * PubNub Configuration Properties.
 *
 * @author Christopher Bridges
 *     created: 12/22/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
@Data
public class PubNubProperties {

  /**
   * PubNub Publish Key.
   */
  @Value("${pubnub.publish}")
  private String publishKey;

  /**
   * PubNub Subscribe Key.
   */
  @Value("${pubnub.subscribe}")
  private String subscribeKey;

  /**
   * PubNub Secret Key.
   */
  @Value("${pubnub.secret}")
  private String secret;
}
