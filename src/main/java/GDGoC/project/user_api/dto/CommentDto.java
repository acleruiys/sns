package GDGoC.project.user_api.dto;

import GDGoC.project.user_api.entity.Comment;
import GDGoC.project.user_api.entity.User;

import java.time.LocalDateTime;

public record CommentDto(
        Integer id,
        String content,
        String authorId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        int likeCount,
        boolean likedByMe
) {
  public static CommentDto from(Comment comment, User me) {
    boolean liked = me != null && comment.getLikes().contains(me);
    return new CommentDto(
            comment.getId(),
            comment.getContent(),
            comment.getAuthor().getUsername(),
            comment.getCreateDate(),
            comment.getModifyDate(),
            comment.getLikes() == null ? 0 : comment.getLikes().size(),
            liked
    );
  }
}
