package GDGoC.project.user_api.service;

import GDGoC.project.user_api.entity.Comment;
import GDGoC.project.user_api.entity.Post;
import GDGoC.project.user_api.entity.User;
import GDGoC.project.user_api.exception.DataNotFoundException;
import GDGoC.project.user_api.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
  private final CommentRepository commentRepository;

  public Comment createComment(Post post, String content, User author) {
    Comment c = new Comment();
    c.setPost(post);
    c.setContent(content);
    c.setAuthor(author);
    c.setCreateDate(LocalDateTime.now());
    return commentRepository.save(c);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Comment getComment(Integer id) {
    return commentRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("comment not found"));
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Comment> getComments(Integer postId) {
    return commentRepository.findByPostIdOrderByIdDesc(postId);
  }

  public void modifyComment(Comment comment, String content) {
    comment.setContent(content);
    comment.setModifyDate(LocalDateTime.now());
  }

  public void deleteComment(Comment comment) {
    commentRepository.delete(comment);
  }

//  public void addLike(Comment comment, User user) {
//    comment.getLikes().add(user);  // Set이라 중복 방지
//  }
//
//  public void removeLike(Comment comment, User user) {
//    comment.getLikes().remove(user);
//  }

  /* 작성자 확인 공통 로직 */
  public void assertAuthor(Comment comment, String userId) {
    if (!comment.getAuthor().getUsername().equals(userId)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    }
  }
}
