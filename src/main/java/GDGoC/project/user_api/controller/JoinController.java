package GDGoC.project.user_api.controller;

import GDGoC.project.user_api.dto.JoinDTO;
import GDGoC.project.user_api.service.JoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JoinController {
  private final JoinService joinService;

  public JoinController(JoinService joinService) {
    this.joinService = joinService;
  }

  @PostMapping("/join")
  public ResponseEntity<String> joinProcess(JoinDTO joinDTO) {
    joinService.joinProcess(joinDTO);
    return ResponseEntity.ok("회원가입 성공");
  }
}

