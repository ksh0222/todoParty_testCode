package hello.todoparty.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동 생성
    private Long id;

    @Column(nullable = false) // @Column 쓰는 이유 속성 설정할려고 ex).길이, 중복 등등
    private String username;

    @Column(nullable = false)
    private String password;


    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
