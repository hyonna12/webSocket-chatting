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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import site.metacoding.second.domain.ChatRoom;
import site.metacoding.second.service.MsgService;

// 웹소켓 통신시 필요한 구현과 채팅화면 view 구성을 위한 구현
@Controller
@RequiredArgsConstructor
@RequestMapping("/comm")
public class MsgRoomController {

  private final MsgService msgService;

  @GetMapping("/room")
  public String room(Model model) {
    return "/comm/room";
  }

  @GetMapping("/rooms")
  public List<ChatRoom> rooms() {
    return msgService.findAllRoom();
  }

  @ApiOperation(value = "방 입장", notes = "room Id를 통해서 방에 입장합니다.")
  @GetMapping("/room/enter/{roomId}")
  public String roomEnter(
      Model model,
      @ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {
    model.addAttribute("roomId", roomId);
    return "/comm/roonEnter";
  }

  @ApiOperation(value = "방 조회", notes = "room ID를 통해서 방을 조회합니다.")
  @GetMapping("/room/{roomId}")
  public ChatRoom roomInfo(
      @ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {
    return msgService.findById(roomId);
  }

  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestParam String name) {
    return msgService.createRoom(name);
  }

}
