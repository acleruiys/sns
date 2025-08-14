package GDGoC.project.user_api.repository;

import GDGoC.project.user_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByPostIdOrderByIdDesc(Integer postId);
}
