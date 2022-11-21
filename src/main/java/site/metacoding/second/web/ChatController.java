package site.metacoding.second.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.second.dto.ChatRoom;
import site.metacoding.second.service.ChatService;

// 채팅방의 생성 및 조회는 rest api로 구현
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

  private final ChatService chatService;

  @PostMapping
  public ChatRoom createRoom(@RequestParam String name) {
    return chatService.createRoom(name);
  }

  @GetMapping
  public List<ChatRoom> findAllRoom() {
    return chatService.findAllRoom();
  }
}