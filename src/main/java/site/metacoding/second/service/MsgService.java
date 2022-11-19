package site.metacoding.second.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.second.dto.MsgRoom;

// 메시징 서비스 구현
@Slf4j
@RequiredArgsConstructor
@Service
public class MsgService {
  private final ObjectMapper objectMapper;
  private Map<String, MsgRoom> msgRooms;
  private MsgRoom msgRoom;
  // 서버에 생성된 모든 채핑방의 정보를 모아둠

  @PostConstruct
  private void init() {
    msgRooms = new LinkedHashMap<>();
    // 채팅방의 정보를 HashMap에 저장 - 추후 db로 옮김
  }

  // 채팅방 목록
  public List<MsgRoom> findAllRoom() {
    return new ArrayList<>(msgRooms.values());
  }

  // 채팅방 조회
  public MsgRoom findById(String roomId) {
    return msgRooms.get(roomId);
    // 채팅방 map에 담긴 정보를 조회
  }

  // 채팅방 생성
  public MsgRoom createRoom(String name) {
    String roomId = name;
    // 채팅방 객체를 생성하고
    msgRooms.put(roomId, msgRoom);
    return MsgRoom.builder().roomId(roomId).build();
    // 채팅방 map에 추가
  }

  // 메시지 발송
  public <T> void sendMessage(WebSocketSession session, T message) {
    try {
      session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
      // 지정한 웹소켓 세션에 메시지를 발송
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

}