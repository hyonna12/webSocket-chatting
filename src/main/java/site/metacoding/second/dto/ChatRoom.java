package site.metacoding.second.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.second.service.ChatService;

// 채팅방을 위한 dto
@Setter
@Getter
public class ChatRoom {
  private String roomId;
  private String name;
  private Set<WebSocketSession> sessions = new HashSet<>(); // 현재 방에 입장한 클라이언트의 session 정보

  @Builder
  public ChatRoom(String roomId, String name) {
    this.roomId = roomId;
    this.name = name;
  }

  public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
    if (chatMessage.getMessageType().equals(ChatMessage.MessageType.ENTER))
      sessions.add(session);
    // 채팅방 입장시 session정보 리스트에 클라이언트 session 추가
    chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");

    sendMessage(chatMessage, chatService);

  }

  public <T> void sendMessage(T message, ChatService chatService) {
    sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    // 채팅방에 메시지 도착하면 채팅방의 모든 session에 메시지를 발송
  }
}
