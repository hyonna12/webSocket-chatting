package site.metacoding.second.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

// 채팅방을 생성하고 정보를 조회하는 repository
@Repository
public class ChatRoomRepository {

  private Map<String, ChatRoom> chatRoomMap;
  // 생성한 방을 map에 저장
  // jpa를 사용해서 db에 저장하도록 수정해야함!

  @PostConstruct
  private void init() {
    chatRoomMap = new LinkedHashMap<>();
  }

  public List<ChatRoom> findAllRoom() {
    // 채팅방 생성순서 최근 순으로 반환
    List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
    Collections.reverse(chatRooms);
    return chatRooms;
  }

  public ChatRoom findRoomById(String id) {
    return chatRoomMap.get(id);
  }

  public ChatRoom createChatRoom(String name) {
    ChatRoom chatRoom = ChatRoom.create(name);
    chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
    return chatRoom;
  }
}
