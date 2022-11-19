package site.metacoding.second.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

// 한 서버에 여러 클라이언트가 접속할 수 있어서 
// 서버는 여러 클라이언트가 발송한 메시지를 받아서 처리해줄 handler 필요
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("payload : {}", payload);
    // client로부터 받은 메시지를 log로 출력

    TextMessage initialGreeting = new TextMessage("Welcome to Chat Server");
    session.sendMessage(initialGreeting);
    // client에게 환영 메시지를 보냄
  }
}
