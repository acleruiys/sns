package GDGoC.project.user_api.repository;

import GDGoC.project.user_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
  Post findByContent(String content);
}