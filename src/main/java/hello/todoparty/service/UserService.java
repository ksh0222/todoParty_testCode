package hello.todoparty.service;

import hello.todoparty.dto.UserRequestDto;
import hello.todoparty.entity.User;
import hello.todoparty.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder; //Security에서 사용되는 비밀번호 암호화를 위한 passwordEncoder 객체 선언
    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        if (userRepository.findByUsername(username).isPresent()) { // optional이여서 .isPresent() 함수 사용 가능
            throw new IllegalArgumentException("이미 존재하는 유저 입니다."); //예외
        }
        User user = new User(username, password);
        userRepository.save(user); //DB저장
    }

    public void login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
