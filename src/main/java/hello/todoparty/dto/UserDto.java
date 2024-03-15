package hello.todoparty.dto;

import hello.todoparty.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private String username;

    public UserDto(User user) {
        this.username = user.getUsername();
    }
}
