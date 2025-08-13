package GDGoC.project.user_api.service;

import GDGoC.project.user_api.dto.JoinDto;
import GDGoC.project.user_api.entity.User;
import GDGoC.project.user_api.exception.UsernameAlreadyExistsException;
import GDGoC.project.user_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public JoinService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void joinProcess(JoinDto joinDto) {
    System.out.println("JoinService.joinProcess");
    String username = joinDto.username();
    String password = joinDto.password();
    String name = joinDto.name();
    String phone = joinDto.phone();

    System.out.println("checkIfUsernameExists");
    // 사용자 존재 여부 확인 후 예외 처리
    checkIfUserExists(username);

    // 새로운 사용자 생성 및 저장
    User user = buildNewUser(username, password, name, phone);
    userRepository.save(user);
  }

  private void checkIfUserExists(String username) {
    System.out.println("checkIfUsernameExists");
    if (userRepository.existsByUsername(username)) {
      System.out.println("username already exists");
      throw new UsernameAlreadyExistsException("Username already exists: " + username);
    }
  }

  private User buildNewUser(String username, String password, String name, String phone) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setName(name);
    user.setPhone(phone);
    user.setRole("ROLE_ADMIN");
    return user;
  }
}
