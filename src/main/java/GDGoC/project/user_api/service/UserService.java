package GDGoC.project.user_api.service;

import GDGoC.project.user_api.entity.User;
import GDGoC.project.user_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /* userId 로 조회 (로그인 세션용) */
  @Transactional(Transactional.TxType.SUPPORTS)
  public User getUser(String username) {
    System.out.println("userService.getUser");
    return userRepository.findByUsername(username);
  }

  /* id 로 조회 (공개 프로필용) */
  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<User> getById(Integer id) {
    return userRepository.findById(id);
  }
}
