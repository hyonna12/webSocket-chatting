package site.metacoding.second.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

// 채팅방을 위한 dto
@Setter
@Getter
public class ChatRoom {
  // pub/sub 방식을 이용하면 구독자 관리가 되므로 웹소켓 세션관리가 필요없음
  // 발송 구현도 알아서 해결돼서 메시지 발송 구현도 없어도 됨

  private String roomId;
  private String name;

  public static ChatRoom create(String name) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.roomId = UUID.randomUUID().toString();
    chatRoom.name = name;
    return chatRoom;
  }

}
