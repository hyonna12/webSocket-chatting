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
public class MsgRoomRepository {

  private Map<String, MsgRoom> msgRoomMap;
  // 생성한 방을 map에 저장
  // jpa를 사용해서 db에 저장하도록 수정해야함!

  @PostConstruct
  private void init() {
    msgRoomMap = new LinkedHashMap<>();
  }

  public List<MsgRoom> findAllRoom() {
    List<MsgRoom> msgRooms = new ArrayList(msgRoomMap.values());
    Collections.reverse(msgRooms);
    return msgRooms;
  }

  public MsgRoom findBtRoomId(String roomId) {
    return msgRoomMap.get(roomId);
  }

  public MsgRoom createMsgRoom(String name) {
    MsgRoom room = MsgRoom.builder().roomId(name).build();
    msgRoomMap.put(room.getRoomId(), room);
    return room;
  }
}
