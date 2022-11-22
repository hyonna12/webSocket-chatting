package site.metacoding.second.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

// 채팅방을 위한 dto
@Setter
@Getter
public class ChatRoom {
  private String roomId;
  private String name;

  public static ChatRoom create(String name) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.roomId = UUID.randomUUID().toString();
    chatRoom.name = name;
    return chatRoom;
  }

}
