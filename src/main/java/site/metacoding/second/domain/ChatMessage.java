package site.metacoding.second.domain;

import lombok.Getter;
import lombok.Setter;

// 채팅 메시지를 주고 받기 위한 dto
@Setter
@Getter
public class ChatMessage {
  public enum MessageType {
    ENTER, TALK
  }

  private MessageType Type;
  private String roomId;
  private String sender;
  private String message;

}
