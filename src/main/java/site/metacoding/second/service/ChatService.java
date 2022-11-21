package site.metacoding.second.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.second.dto.ChatRoom;

// 메시징 서비스 구현
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
  private final ObjectMapper objectMapper;
  private Map<String, ChatRoom> chatRooms;

  @PostConstruct
  private void init() {
    chatRooms = new LinkedHashMap<>();
    // 채팅방의 정보를 HashMap에 저장 - 추후 db로 옮김
  }

  // 채팅방 목록
  public List<ChatRoom> findAllRoom() {
    return new ArrayList<>(chatRooms.values());
  }

  // 채팅방 조회
  public ChatRoom findRoomById(String roomId) {
    return chatRooms.get(roomId);
    // 채팅방 map에 담긴 정보를 조회
  }

  // 채팅방 생성
  public ChatRoom createRoom(String name) {
    // 채팅방 객체를 생성하고
    String randomId = UUID.randomUUID().toString();
    ChatRoom chatRoom = ChatRoom.builder()
        .roomId(randomId)
        .name(name)
        .build();
    chatRooms.put(randomId, chatRoom);

    return chatRoom;
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