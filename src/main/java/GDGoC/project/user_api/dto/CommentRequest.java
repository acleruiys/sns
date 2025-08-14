package GDGoC.project.user_api.dto;

import jakarta.validation.constraints.NotEmpty;

public record CommentRequest(
        @NotEmpty(message = "내용은 필수항목입니다.")
        String content
) { }
