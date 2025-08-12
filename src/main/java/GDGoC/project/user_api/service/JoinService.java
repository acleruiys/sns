package GDGoC.project.user_api.service;

import GDGoC.project.user_api.dto.JoinDTO;
import GDGoC.project.user_api.entity.User;
import GDGoC.project.user_api.exceiption.UsernameAlreadyExistsException;
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

  public void joinProcess(JoinDTO joinDTO) {
    String username = joinDTO.getUsername();
    String password = joinDTO.getPassword();
    String name = joinDTO.getName();
    String phone = joinDTO.getPhone();

    // 사용자 존재 여부 확인 후 예외 처리
    checkIfUserExists(username);

    // 새로운 사용자 생성 및 저장
    User user = buildNewUser(username, password, name, phone);
    userRepository.save(user);
  }

  private void checkIfUserExists(String username) {
    if (userRepository.existsByUsername(username)) {
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
