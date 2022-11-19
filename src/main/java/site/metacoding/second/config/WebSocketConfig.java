package site.metacoding.second.config;

import javax.websocket.WebSocketContainer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.second.handler.WebSocketHandler;

// handler를 이용하여 websocket을 활성화하기 위한 config
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocket // webcket 활성화
public class WebSocketConfig implements WebSocketConfigurer {

  private final WebSocketHandler webSocketHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    // websocket에 접속하기 위한 endpoint는 /ws/chat로 설정하고
    // 도메인이 다른 서버에서도 접속 가능하도록 "*" 를 설정에 추가
  }

}
