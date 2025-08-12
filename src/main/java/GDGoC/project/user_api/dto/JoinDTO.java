package GDGoC.project.user_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {
  private String username;
  private String password;
  private String name;
  private String phone;
}
