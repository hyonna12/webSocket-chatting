package site.metacoding.second.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.second.domain.ChatRoom;
import site.metacoding.second.domain.ChatRoomRepository;

// 웹소켓 통신시 필요한 구현과 채팅화면 view 구성을 위한 구현
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

  private final ChatRoomRepository chatRoomRepository;

  // 채팅리스트 화면
  @GetMapping("/room")
  public String room(Model model) {
    return "/chat/room";
  }

  // 모든 채팅방 목록 반환
  @GetMapping("/rooms")
  @ResponseBody
  public List<ChatRoom> rooms() {
    return chatRoomRepository.findAllRoom();
  }

  // 채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestParam String name) {
    return chatRoomRepository.createChatRoom(name);
  }

  // 채팅방 입장 화면
  @GetMapping("/room/enter/{roomId}")
  public String roomEnter(Model model, @PathVariable String roomId) {
    model.addAttribute("roomId", roomId);
    return "/chat/roomdetail";
  }

  // 특정 채팅방 조회
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoom roomInfo(@PathVariable String roomId) {
    return chatRoomRepository.findRoomById(roomId);
  }

}
