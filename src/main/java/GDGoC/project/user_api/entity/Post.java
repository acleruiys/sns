package GDGoC.project.user_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

//  /* mappedBy: 참조 엔티티의 속성명 */
//  @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//  private List<Comment> commentList;

  @ManyToOne
  private User author;

  private LocalDateTime modifyDate;

//  @ManyToMany
//  Set<User> likes;
}
