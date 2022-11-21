package site.metacoding.second.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// handler를 이용하여 websocket을 활성화하기 위한 config
@Configuration
@EnableWebSocketMessageBroker // stomp 사용하려고
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws-stmop") // stomp websocket의 연결 endpoint
        .setAllowedOriginPatterns("*")
        .withSockJS(); // 클라이언트와의 연결을 sockJs로
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/sub"); // 메시지를 구독하는 요청의 prefix
    registry.setApplicationDestinationPrefixes("/pub"); // 메시지를 발행하는 요청의 prefix
  }

}
