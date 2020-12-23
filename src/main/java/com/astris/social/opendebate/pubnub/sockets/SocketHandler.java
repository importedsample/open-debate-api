package com.astris.social.opendebate.pubnub.sockets;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Web Socket Handler.
 *
 * @author Christopher Bridges
 *     created: 12/16/20
 *
 * @since 0.0.1-SNAPSHOT
 */
@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {

  private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
  public void handleTextMessage(
      @NonNull final WebSocketSession session,
      @NonNull final TextMessage message
  ) throws InterruptedException, IOException {
    for (WebSocketSession socketSession : sessions) {
      if (socketSession.isOpen() && !session.getId().equals(socketSession.getId())) {
        socketSession.sendMessage(message);
      }
    }
  }

  @Override
  public void afterConnectionEstablished(
      @NonNull final WebSocketSession session
  ) {
    sessions.add(session);
  }
}
