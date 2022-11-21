package site.metacoding.second.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.second.domain.MsgRoom;
import site.metacoding.second.service.MsgService;

// 채팅방의 생성 및 조회는 rest api로 구현
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class MsgController {

  private final MsgService msgService;

  @PostMapping
  public MsgRoom createRoom(@RequestParam String name) {
    return msgService.createRoom(name);
  }

  @GetMapping
  public List<MsgRoom> findAllRoom() {
    return msgService.findAllRoom();
  }
}