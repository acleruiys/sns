package GDGoC.project.user_api.dto;

import GDGoC.project.user_api.entity.Post;

import java.time.LocalDateTime;

public record PostDto(
        Integer id,
        String content,
        String authorId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
//        int commentCount,
//        int likeCount
) {
  public static PostDto from(Post post) {
    return new PostDto(
            post.getId(),
            post.getContent(),
            post.getAuthor().getUsername(),
            post.getCreateDate(),
            post.getModifyDate()
//            post.getCommentList() == null ? 0 : post.getCommentList().size(),
//            post.getLikes() == null ? 0 : post.getLikes().size()
    );
  }
}
