package site.metacoding.second.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.second.service.MsgService;

// 채팅방을 위한 dto
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MsgRoom {
  // pub/sub 방식을 이용하면 구독자 관리가 되므로 웹소켓 세션관리가 필요없음
  // 발송 구현도 알아서 해결돼서 메시지 발송 구현도 없어도 됨

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String roomId;

}
