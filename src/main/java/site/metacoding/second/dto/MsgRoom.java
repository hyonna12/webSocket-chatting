package site.metacoding.second.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.second.service.MsgService;

// 채팅방을 위한 dto
@Setter
@Getter
public class MsgRoom {
  private String roomId;
  private Set<WebSocketSession> sessions = new HashSet<>(); // 현재 방에 입장한 클라이언트의 session 정보

  @Builder
  public MsgRoom(String roomId) {
    this.roomId = roomId;
  }

  public void handleActions(WebSocketSession session, Message message, MsgService msgService) {
    if (message.getMessageType().equals(Message.MessageType.ENTER))
      sessions.add(session);
    // 채팅방 입장시 session정보 리스트에 클라이언트 session 추가
    message.setMessage(message.getSender() + "님이 입장했습니다.");

    sendMessage(message, msgService);

  }

  public <T> void sendMessage(T message, MsgService messageService) {
    sessions.parallelStream().forEach(session -> messageService.sendMessage(session, message));
    // 채팅방에 메시지 도착하면 채팅방의 모든 session에 메시지를 발송
  }
}
