package site.metacoding.second.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import site.metacoding.second.domain.ChatMessage;

// 채팅방의 생성 및 조회는 rest api로 구현
// publisher 구현
// 채팅방은 클라이언트가 sub하고 있는 topic이며 해당방을 sub하는 모든 subscriber에게 채팅 보내줌
@RequiredArgsConstructor
@Controller
public class ChatController {

  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/chat/message")
  // websocket으로 들어오는 메시지 발행 처리
  // 클라이언트가 /pub/comm/message로 발행을 요청하면 msgController가 메시지 받아서 처리
  public void message(ChatMessage message) {
    if (ChatMessage.MessageType.ENTER.equals(message.getType()))
      message.setMessage(message.getSender() + "님이 입장하셨습니다.");
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}