package site.metacoding.second.web;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.second.domain.Message;
import site.metacoding.second.domain.MsgRoom;
import site.metacoding.second.service.MsgService;

// 채팅방의 생성 및 조회는 rest api로 구현
// publisher 구현
// 채팅방은 클라이언트가 sub하고 있는 topic이며 해당방을 sub하는 모든 subscriber에게 채팅 보내줌
@RequiredArgsConstructor
@RestController
public class MsgController {

  private final SimpMessageSendingOperations sendingOperations;

  @MessageMapping("/comm/message")
  public void message(Message message) {
    if (Message.MessageType.ENTER.equals(message.getMessageType())) {
      message.setMessage(message.getSender() + "이 입장했습니다.");
    }
    sendingOperations.convertAndSend("/sub/comm/room/" + message.getRoomId(), message);
  }
}