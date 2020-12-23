package com.astris.social.opendebate.pubnub.config;

import com.pubnub.api.PNConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Pub Nub Configuration Bean.
 *
 * @author Christopher Bridges
 *     created: 12/8/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
public class PubNubConfig {

  /**
   * Injected PubNub Properties Bean.
   */
  private final PubNubProperties props;

  /**
   * Public Constructor.
   *
   * @param properties Pub Nub Properties Bean.
   */
  public PubNubConfig(final PubNubProperties properties) {
    this.props = properties;
  }

  /**
   * PubNub Configuration Bean.
   *
   * @return PubNub Configuration Object
   */
  @Bean
  public PNConfiguration pnConfiguration() {
    PNConfiguration pnConfiguration = new PNConfiguration();
    pnConfiguration.setSubscribeKey(props.getSubscribeKey());
    pnConfiguration.setPublishKey(props.getPublishKey());
    pnConfiguration.setSecure(true);

    return pnConfiguration;
  }
}
