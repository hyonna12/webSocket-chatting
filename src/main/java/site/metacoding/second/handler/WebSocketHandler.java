package site.metacoding.second.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.second.dto.Message;
import site.metacoding.second.dto.MsgRoom;
import site.metacoding.second.service.MsgService;

// 한 서버에 여러 클라이언트가 접속할 수 있어서 
// 서버는 여러 클라이언트가 발송한 메시지를 받아서 처리해줄 handler 필요
@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

  private final MsgService msgService;
  private final ObjectMapper objectMapper;

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("payload : {}", payload);
    // client로부터 받은 메시지를 log로 출력

    Message msg = objectMapper.readValue(payload, Message.class);
    // 웹소켓 클라이언트로부터 메시지를 전달받아 Message 객체로 변환
    MsgRoom room = msgService.findById(msg.getRoomId());
    // 전달받은 Message 객체에 담긴 roomId로 발송 대상 채팅방 정보를 조회
    room.handleActions(session, msg, msgService);
    // 현재 채팅방에 입장해 있는 모든 클라이언트들(Set<WebSocketSession>)에게 타입에 맞는 메시지를 전송
  }
}
