package GDGoC.project.user_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private Post post;

  @ManyToOne
  private User author;

  private LocalDateTime modifyDate;

//  @ManyToMany
//  Set<User> likes;
}
