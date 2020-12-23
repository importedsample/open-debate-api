package com.astris.social.opendebate.pubnub.config;

import com.astris.social.opendebate.pubnub.sockets.SocketHandler;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Web Socket Configuration.
 *
 * @author Christopher Bridges
 *     created: 12/16/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @SneakyThrows
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    registry.addHandler(new SocketHandler(), "/socket")
            .setAllowedOrigins("*");
  }

}
