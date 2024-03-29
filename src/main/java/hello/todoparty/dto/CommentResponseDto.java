package hello.todoparty.dto;

import hello.todoparty.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentResponseDto  extends CommonResponseDto {
    private Long id;
    private String text;
    private UserDto user;
    private LocalDateTime createDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserDto(comment.getUser());
        this.createDate = comment.getCreateDate();
    }

}
