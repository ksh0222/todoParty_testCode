package hello.todoparty.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

// 수정 + 부가적인 부분 생각 필요
class UserTest {
    @Test
    @DisplayName("User 생성 테스트")
    void test1(){
        //given
        String username = "kang";
        String password = "123456789a";
        //when
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        //then
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);

    }

}

