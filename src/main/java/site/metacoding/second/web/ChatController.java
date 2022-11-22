package site.metacoding.second.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import site.metacoding.second.dto.ChatMessage;

// 채팅방의 생성 및 조회는 rest api로 구현
@Controller
@RequiredArgsConstructor
public class ChatController {

  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/chat/message")
  public void message(ChatMessage message) {
    if (ChatMessage.MessageType.ENTER.equals(message.getType()))
      message.setMessage(message.getSender() + "님이 입장하셨습니다.");
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }

}